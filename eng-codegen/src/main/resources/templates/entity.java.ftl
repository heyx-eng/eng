package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if table.convert>
@TableName("${table.name}")
</#if>
public class ${entity} extends ${superEntityClass}<Long> {

    private static final long serialVersionUID = 1L;

<#list table.fields as field>
	<#if field.keyFlag>
		<#assign keyPropertyName=field.propertyName>
	</#if>
	<#if field.comment?? && field.comment!="">
    /**
     * ${field.comment}
     */
	</#if>
	<#if field.keyFlag>
		<#if field.keyIdentityFlag>
	@TableId(value="${field.name}", type= IdType.AUTO)
		<#elseif field.convert>
	@TableId("${field.name}")
		</#if>
	<#elseif field.convert>
	@TableField("${field.name}")
	</#if>
	private ${field.propertyType} ${field.propertyName};
</#list>

<#list table.fields as field>
	<#if field.propertyType == "Boolean">
		<#assign getprefix="is">
	<#else>
		<#assign getprefix="get">
	</#if>
	public ${field.propertyType} ${getprefix}${field.capitalName}() {
		return ${field.propertyName};
	}

	<#if entityBuilderModel>
	public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
	<#else>
	public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
	</#if>
		this.${field.propertyName} = ${field.propertyName};
		<#if entityBuilderModel>
		return this;
		</#if>
	}
</#list>

	<#if entityColumnConstant>
	<#list table.fields as field>
	public static final String ${field.name.toUpperCase()} = "${field.name}";
	</#list>

	</#if>
	<#if activeRecord>
	@Override
	protected Serializable pkVal() {
		<#if keyPropertyName>
		return this.${keyPropertyName};
		<#else>
		return this.id;
		</#if>
	}

	</#if>
}
