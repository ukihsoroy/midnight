package org.ko.prototype.generator.mbg.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

	private static final Logger log = LoggerFactory.getLogger(GeneratorIntrospectedTableMyBatis3Impl.class);
	
	/**
	 * 覆盖已有xml，而不是合并
	 */
	@Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answers = new ArrayList<GeneratedXmlFile>();

        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(),
                context.getSqlMapGeneratorConfiguration().getTargetProject(),
                false, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answers.add(gxf);
            }
        }

        return answers;
    }
}
