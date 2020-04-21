<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${rootPackage}.repository.${entityName}Repository">

    <select id="queryList" resultType="${rootPackage}.dto.${entityName}DTO">
        SELECT
        <#list columns as column>
            ${addr}.${column.columnName}<#if column_has_next>,</#if>
        </#list>
        FROM ${name} ${addr}
    </select>

    <insert id="insertList">
        INSERT INTO ${name} ${addr}
          (
          <#list columns as column>
            ${addr}.${column.columnName}<#if column_has_next>,</#if>
          </#list>
          )
        <foreach collection="${entityName?uncap_first}s" item="${entityName?uncap_first}" open="VALUES (" separator="), (" close=")">
          <#list columns as column>
            #${r'{'}${entityName?uncap_first}.${column.propertyName}, jdbcType=${column.columnType ? upper_case}${r'}'}<#if column_has_next>,</#if>
          </#list>
        </foreach>
    </insert>
</mapper>