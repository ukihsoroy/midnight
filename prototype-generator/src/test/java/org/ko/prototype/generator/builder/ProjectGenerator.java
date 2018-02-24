package org.ko.prototype.generator.builder;

import static java.lang.System.out;

import java.io.File;
import java.io.FileFilter;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ProjectGenerator {

	private static final String TARGET = "d:\\{0}-server";
	
	@Test public void build() throws Exception {
		// 修改PNAME的项目名称
		String appName = System.getProperty("PNAME", "live");
		out.println("generating app " + appName);
		
		File dir = new File(this.getClass().getClassLoader().getResource(".").toString());
		File source = dir.getParentFile().getParentFile().getParentFile().getParentFile();
		int index = source.getPath().indexOf("\\");
		
		File target = new File(MessageFormat.format(TARGET, new Object[]{appName}));
		FileUtils.deleteDirectory(target);
		
		// copy dir
		out.println(">>>>>>>>>>>>>>>> copying directory");
		FileUtils.copyDirectory(
				new File(source.getPath().substring(index + 1))
				, target
				, pathname -> {
                    String name = pathname.getName();
                    return !name.equalsIgnoreCase("out") && !name.startsWith(".");
                }
		);
		
		// change dir name
		out.println(">>>>>>>>>>>>>>>> change directory names");
		Collection<File> dirs = FileUtils.listFilesAndDirs(
				target
//				, TrueFileFilter.INSTANCE
				, new IOFileFilter(){
					@Override
					public boolean accept(File file) {
//						return FilenameUtils.getBaseName(file.getName()).toLowerCase().contains("prototype");
						return file.getName().toLowerCase().contains("prototype");
//						return true;
					}

					@Override
					public boolean accept(File dir, String name) {
						return true;
//						return FilenameUtils.getBaseName(dir.getName()).toLowerCase().contains("prototype");
					}
			
				}
				, TrueFileFilter.INSTANCE
		);
		
		Set<File> sets = new TreeSet<File>(new Comparator<File>(){
			@Override
			public int compare(File name1, File name2) {
				int value = name2.getPath().length() - name1.getPath().length();
				if(value == 0){
					value = name1.getPath().compareTo(name2.getPath());
				}
				return value;
			}
		});
		
		sets.addAll(dirs);
//		sets.stream().forEach(e -> System.out.println(e));
		
		for(File file : sets){
			if(file.getName().toLowerCase().contains("prototype")){
				out.println(file.getAbsolutePath());
				if(file.isFile()){
					String origName = file.getName();
					String newName = StringUtils.replace(origName, "Prototype", StringUtils.capitalize(appName));
					file.renameTo(new File(FilenameUtils.getFullPath(file.getAbsolutePath()) + newName));
				}else{
					if(FilenameUtils.getBaseName(file.getName()).contains("prototype")){
						String origName = file.getName();
						String newName = StringUtils.replace(origName, "prototype", appName);
						file.renameTo(new File(FilenameUtils.getFullPath(file.getAbsolutePath()) + newName));
					}
				}
			}
		}
		
		// change file contents
		out.println(">>>>>>>>>>>>>>>> change file contents");
		Collection<File> files = FileUtils.listFiles(
				target
				, TrueFileFilter.INSTANCE
				, TrueFileFilter.INSTANCE
		);
//		files.stream().forEach(e -> System.out.println(e));
		for(File file : files){
			if(file.isFile() && StringUtils.isNotBlank(FilenameUtils.getExtension(file.getName()))){
				out.println(file.getAbsolutePath());
				String content = FileUtils.readFileToString(file, "utf8");
				content = StringUtils.replace(content, "prototype", appName);
				content = StringUtils.replace(content, "Prototype", StringUtils.capitalize(appName));
				FileUtils.write(file, content, "utf8");
			}
		}
		
		out.println(appName + "创建成功！ ");
		out.println("已生成到" + target);
	}
	
}
