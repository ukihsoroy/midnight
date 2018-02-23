package org.ko.prototype.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ko.prototype.generator.bean.SelectOption;
import org.ko.prototype.generator.bean.TableMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.ko.prototype.generator.bean.DBConfig;
import org.ko.prototype.generator.bean.QueryElement;

import freemarker.template.Template;

public abstract class AbstractRepositoryBuilder extends AbstractBuilder {

	private static final Logger log = LoggerFactory.getLogger(AbstractRepositoryBuilder.class);
	
	private static final String REPOSITORY_TEMPLATE_NAME = "Repository.ftl";
	private static final String MAPPER_TEMPLATE_NAME = "RepositoryMapper.ftl";
	private static final String GRID_TEMPLATE_NAME = "Grid.ftl";
	private static final String MODEL_TEMPLATE_NAME = "Model.ftl";
	private static final String SERVICE_TEMPLATE_NAME = "Service.ftl";
	private static final String EXCEL_SERVICE_TEMPLATE_NAME = "ExcelService.ftl";
	private static final String CONTROLLER_TEMPLATE_NAME = "Controller.ftl";
	private static final String HTML_TEMPLATE_NAME = "Page.ftl";
	private static final String JS_TEMPLATE_NAME = "PageJS.ftl";
	private static final String CSS_TEMPLATE_NAME = "PageCSS.ftl";
	
	private static final String EXIT_MSG = "代码生成已终止";
	
	private boolean passAll = false;
	
	@Autowired private freemarker.template.Configuration freeMarkerConfiguration;
	
	protected DBConfig config;
	protected String moduleName;
	
	protected boolean withUI = false;
	
	@Override
	protected DBConfig getDBConfig() {
		return config;
	}

	protected abstract String getModuleName();
	protected abstract String getAdminRoot();
	
	protected void generateStubs(String...tableNames) throws Exception {
		if(ArrayUtils.isEmpty(tableNames)){
			log.info("no table name was specified");
			return;
		}
		
		String dir = new File(this.getClass().getClassLoader().getResource(".").toURI()).getAbsolutePath();
		
		int index = dir.indexOf("target");
		String moduleRoot = new File(dir.substring(0, index)).getParent().toString();
		moduleRoot += "\\" + moduleName;
		
		if(StringUtils.isNotBlank(getJavaFileOutputFolder())){
			moduleRoot = getJavaFileOutputFolder();
		}
		
		if(!new File(moduleRoot).isDirectory()){
			System.out.println(moduleRoot + " doens't exist");
		}
		
		String componentName = StringUtils.split(moduleName, "-")[1];
		String javaDir = moduleRoot + "\\src\\main\\java\\org\\ko\\prototype\\" + componentName + "\\dao\\repository\\";
		String mapperDir = moduleRoot + "\\src\\main\\resources\\mappers\\";
		
		File repoDir = new File(javaDir);
		if(!repoDir.exists()){
			repoDir.mkdirs();
		}
		
		File mDir = new File(mapperDir);
		if(!mDir.exists()){
			mDir.mkdirs();
		}
		
		for(String table : tableNames){
			List<TableMetaData> meta = super.getTableMetaData(table);
			long textColumnCount = meta.stream().filter(m -> m.getDataType().toLowerCase().endsWith("text")).count();
			
			String textColumnName = null;
			Optional<TableMetaData> textOptional = meta.stream().filter(m -> m.getDataType().toLowerCase().endsWith("text")).findFirst();
			if(textOptional.isPresent()){
				textColumnName = textOptional.get().getColumnName();
			}
			
			String domainName = buildDomainName(table);
			
			Map<String, Object> model = new HashMap<>();
			model.put("appName", APP_NAME);
			model.put("Table", domainName);
			model.put("t", table);
			model.put("textColumnCount", textColumnCount);
			model.put("componentName", componentName);
			model.put("textColumnName", textColumnName);
			
			String abbr = getAbbr(table);
			model.put("abbr", abbr);
			model.put("meta", meta);
			model.put("now", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
			
			String deleteStatus = "1";
			Optional<TableMetaData> optional = meta.stream().filter(e -> e.getColumnName().equals("delete_status")).findFirst();
			if(optional.isPresent()){
				deleteStatus = "delete_status";
			}
			model.put("deleteStatus", deleteStatus);
			
			optional = meta.stream().filter(e -> e.isPrimaryKey()).findFirst();
			if(optional.isPresent()){
				model.put("pk", optional.get().getFieldName());
			}
			
			String adminRoot = getAdminRoot();
			if(StringUtils.isNotBlank(adminRoot) && withUI){
				model.put("admin", componentName);
				
				// excel title
				List<String> titles = new ArrayList<>();
				for(TableMetaData m : meta){
					if(m.isPrimaryKey() || m.getColumnName().equalsIgnoreCase("delete_status") || m.getColumnName().equalsIgnoreCase("tx_code")){
						continue;
					}
					String comment = StringUtils.trimToEmpty(m.getComment());
					String title = comment.split("\\s+")[0];
					titles.add(StringUtils.trimToEmpty(title).replaceAll(":", ""));
				}
				model.put("titles", titles);
				
				String serviceDir = moduleRoot + "\\src\\main\\java\\org\\ko\\prototype\\" + componentName + "\\service\\";
				File svrDir = new File(serviceDir);
				if(!svrDir.exists()){
					svrDir.mkdirs();
				}
				
				String excelServiceDir = moduleRoot + "\\src\\main\\java\\org\\ko\\prototype\\" + componentName + "\\service\\file\\";
				File exsvrDir = new File(excelServiceDir);
				if(!exsvrDir.exists()){
					exsvrDir.mkdirs();
				}
				
				String controllerDir = moduleRoot + "\\src\\main\\java\\org\\ko\\prototype\\" + componentName + "\\controller\\";
				File ctlDir = new File(controllerDir);
				if(!ctlDir.exists()){
					ctlDir.mkdirs();
				}
				
				// generate service java file
				String serviceFileName = serviceDir + domainName + "Service.java";
				if(!hint(serviceFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				Template template = freeMarkerConfiguration.getTemplate(SERVICE_TEMPLATE_NAME);
				Writer out = new OutputStreamWriter(new FileOutputStream(new File(serviceFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", serviceFileName);
				
				// generate excel service java file
				String excelServiceFileName = excelServiceDir + domainName + "ExcelService.java";
				if(!hint(excelServiceFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				template = freeMarkerConfiguration.getTemplate(EXCEL_SERVICE_TEMPLATE_NAME);
				out = new OutputStreamWriter(new FileOutputStream(new File(excelServiceFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", serviceFileName);

				// generate controller java file
				String controllerFileName = controllerDir + domainName + "Controller.java";
				if(!hint(controllerFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				template = freeMarkerConfiguration.getTemplate(CONTROLLER_TEMPLATE_NAME);
				out = new OutputStreamWriter(new FileOutputStream(new File(controllerFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", controllerFileName);
				
				// generate html file
				QueryElement textElement = null;
				QueryElement dateElement = null;
				QueryElement selectElement = null;
				final List<String> ignoredFields = Arrays.asList(new String[]{"id", "delete_status", "tx_code"});
				for(TableMetaData m : meta){
					if(ignoredFields.contains(m.getColumnName().toLowerCase())){
						continue;
					}
					
					String text = m.getComment().split("\\s+")[0].replaceAll(":", "");
					
					if(textElement == null && m.getDataType().equalsIgnoreCase("varchar")){
						textElement = new QueryElement();
						textElement.setText(text);
						textElement.setElementType("text");
						textElement.setColumnName(m.getColumnName());
						textElement.setLength(m.getLength());
						model.put("textElement", textElement);
					}
					
					if(dateElement == null && (m.getDataType().toLowerCase().contains("date") || m.getDataType().toLowerCase().contains("time"))){
						dateElement = new QueryElement();
						dateElement.setText(text);
						dateElement.setElementType("date");
						dateElement.setColumnName(m.getColumnName());
						dateElement.setLength(m.getLength());
						model.put("dateElement", dateElement);
					}
					
					if(selectElement == null && m.getComment().contains("#") && !m.getColumnName().equalsIgnoreCase("delete_status")){
						selectElement = new QueryElement();
						selectElement.setText(text);
						selectElement.setElementType("select");
						selectElement.setColumnName(m.getColumnName());
						selectElement.setLength(m.getLength());
						
						String[] parts = m.getComment().split("\\s+");
						List<SelectOption> options = new ArrayList<>();
						for(int i = 1; i < parts.length; i++){
							String[] arr = StringUtils.split(parts[i], "-");
							SelectOption option = new SelectOption();
							option.setValue(arr[0]);
							if(arr.length > 1){
								option.setText(arr[1].split("#")[0]);
								options.add(option);
							}
						}
						
						selectElement.setOptions(options);
						model.put("selectElement", selectElement);
					}
				}
				
				File htmlDir = new File(adminRoot);
				if(!htmlDir.exists()){
					htmlDir.mkdirs();
				}
				
				String htmlFileName = htmlDir + "\\" + StringUtils.uncapitalize(domainName) + ".html";
				if(!hint(htmlFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				template = freeMarkerConfiguration.getTemplate(HTML_TEMPLATE_NAME);
				out = new OutputStreamWriter(new FileOutputStream(new File(htmlFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", htmlFileName);
				
				// generate js file
				File jsDir = new File(adminRoot + "\\js\\page");
				if(!jsDir.exists()){
					jsDir.mkdirs();
				}
				
				String jsFileName = jsDir + "\\" + StringUtils.uncapitalize(domainName) + ".js";
				if(!hint(jsFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				template = freeMarkerConfiguration.getTemplate(JS_TEMPLATE_NAME);
				out = new OutputStreamWriter(new FileOutputStream(new File(jsFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", jsFileName);
				
				// generate css file
				File cssDir = new File(adminRoot + "\\css\\page");
				if(!cssDir.exists()){
					cssDir.mkdirs();
				}
				
				String cssFileName = cssDir + "\\" + StringUtils.uncapitalize(domainName) + ".css";
				if(!hint(cssFileName)){
					System.out.println(EXIT_MSG);
					return;
				}
				template = freeMarkerConfiguration.getTemplate(CSS_TEMPLATE_NAME);
				out = new OutputStreamWriter(new FileOutputStream(new File(cssFileName)), "UTF-8");
				template.process(model, out);
				out.close();
				log.info("generated {}", cssFileName);
			}
			
			String domainDir = moduleRoot + "\\src\\main\\java\\org\\ko\\prototype\\" + componentName + "\\bean\\domain\\";
			File dDir = new File(domainDir);
			if(!dDir.exists()){
				dDir.mkdirs();
			}

			Template template;
			Writer out;
			
			// generate grid java file
			String gridFileName = domainDir + domainName + "Grid.java";
			if(!hint(gridFileName)){
				System.out.println(EXIT_MSG);
				return;
			}
			template = freeMarkerConfiguration.getTemplate(GRID_TEMPLATE_NAME);
			out = new OutputStreamWriter(new FileOutputStream(new File(gridFileName)), "UTF-8");
			template.process(model, out);
			out.close();
			log.info("generated {}", gridFileName);
			
			// generate model java file
			String modelFileName = domainDir + domainName + "Model.java";
			if(!hint(modelFileName)){
				System.out.println(EXIT_MSG);
				return;
			}
			template = freeMarkerConfiguration.getTemplate(MODEL_TEMPLATE_NAME);
			out = new OutputStreamWriter(new FileOutputStream(new File(modelFileName)), "UTF-8");
			template.process(model, out);
			out.close();
			log.info("generated {}", modelFileName);
			
			// generate java file
			String javaFileName = javaDir + domainName + "Repository.java";
			if(!hint(javaFileName)){
				System.out.println(EXIT_MSG);
				return;
			}
			template = freeMarkerConfiguration.getTemplate(REPOSITORY_TEMPLATE_NAME);
			out = new OutputStreamWriter(new FileOutputStream(new File(javaFileName)), "UTF-8");
			template.process(model, out);
			out.close();
			log.info("generated {}", javaFileName);
			
			// generate xml file 
			String xmlFileName = mapperDir + domainName + "RepositoryMapper.xml";
			if(!hint(xmlFileName)){
				System.out.println(EXIT_MSG);
				return;
			}
			template = freeMarkerConfiguration.getTemplate(MAPPER_TEMPLATE_NAME);
			out = new OutputStreamWriter(new FileOutputStream(new File(xmlFileName)), "UTF-8");
			template.process(model, out);
			out.close();
			log.info("generated {}", xmlFileName);
		}
	}
	
	private String getAbbr(String table){
		String abbr = "";
		
		String[] elements = table.split("_");
		if(elements.length > 1){
			for(int i = 1; i < elements.length; i++){
				abbr += elements[i].substring(0, 1);
			}
		}else{
			abbr = elements[0];
		}
		
		return abbr;
	}
	
	private boolean hint(String path){
		boolean pass = false;
		
		if(passAll){
			return passAll;
		}
		
		File file = new File(path);
		if(file.exists()){
			Scanner scanner = new Scanner(System.in);
			do{
				System.out.println(path + "已存在");
				System.out.print("继续生成有可能会覆盖已有改动，是否要替换(yes/no/all):");
				String input = StringUtils.trimToEmpty(scanner.nextLine());
				if("yes".equalsIgnoreCase(input) || "y".equalsIgnoreCase(input)){
					pass = true;
					break;
				}else if("no".equalsIgnoreCase(input) || "n".equalsIgnoreCase(input)){
					pass = false;
					break;
				}else if("all".equalsIgnoreCase(input) || "a".equalsIgnoreCase(input)){
					passAll = true;
					pass = true;
					break;
				}else{
					System.out.println("无效的输入");
				}
			}while(true);
		}else{
			pass = true;
		}
		
		return pass;
	}
}
