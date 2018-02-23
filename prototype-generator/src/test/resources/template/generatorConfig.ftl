<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">  
<generatorConfiguration>  
    <context id="ko-artist" defaultModelType="flat" targetRuntime="org.ko.prototype.generator.mbg.plugin.GeneratorIntrospectedTableMyBatis3Impl">
    	<property name="autoDelimitKeywords" value="false"/>
    	<property name="beginningDelimiter" value="`"/>
    	<property name="endingDelimiter" value="`"/>
    	
    	<plugin type="org.mybatis.generator.plugins.ToStringPlugin"></plugin>
    	<plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin"></plugin>
    	<plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>
    	<plugin type="org.mybatis.generator.plugins.SerializablePlugin"></plugin>
    	
        <commentGenerator>  
            <property name="suppressDate" value="false"/>  
            <property name="suppressAllComments" value="false"/>  
        </commentGenerator> 
        
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://${ip}:${port?c}/${db}?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false" userId="${user}" password="${pwd}"/>
        
        <javaTypeResolver>  
            <property name="forceBigDecimals" value="false"/>  
        </javaTypeResolver>  
           
        <javaModelGenerator targetPackage="org.ko.prototype.data.bean.domain" targetProject="../${module}/src/main/java">
            <property name="enableSubPackages" value="true"/>  
            <property name="trimStrings" value="true"/>
            <property name="rootClass" value="org.ko.prototype.data.bean.domain.AbstractDomain"/>
        </javaModelGenerator>  
        
        <sqlMapGenerator targetPackage="mappers" targetProject="../${module}/src/main/resources">
            <property name="enableSubPackages" value="true"/>  
        </sqlMapGenerator>  
        
        <javaClientGenerator type="XMLMAPPER" targetPackage="org.ko.prototype.data.dao.mapper" targetProject="../${module}/src/main/java">
            <property name="enableSubPackages" value="true"/>  
        </javaClientGenerator>  

<#list tables as table>
        <table tableName="${table.name}" domainObjectName="${table.domainName}">
        	<property name="selectAllOrderByClause" value="id desc"/>
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
            <columnOverride column="class">
            	<property name="property" value="clazz"/>
            </columnOverride>
        </table>
</#list>
    </context>  
</generatorConfiguration>
