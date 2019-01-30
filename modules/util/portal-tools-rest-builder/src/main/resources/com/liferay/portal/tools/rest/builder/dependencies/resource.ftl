package ${configYAML.apiPackagePath}.resource;

<#compress>
	<#list openAPIYAML.components.schemas?keys as schemaName>
		import ${configYAML.apiPackagePath}.dto.${schemaName};
		import ${configYAML.apiPackagePath}.dto.${schemaName}Collection;
	</#list>
</#compress>

import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.vulcan.context.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o${configYAML.application.baseURI}/${openAPIYAML.info.version}
 *
 * @author ${configYAML.author}
 * @generated
 */
@Generated("")
@Path("/${openAPIYAML.info.version}")
public interface ${resourceName}Resource {

	<#list openAPIYAML.pathItems?keys as path>
		<#assign pathItem = openAPIYAML.pathItems[path] />

		<#if pathItem.delete??>
			<#assign
				annotationHTTPMethod = "@DELETE"

				operation = pathItem.delete
				operationName = "delete"
			/>
		<#elseif pathItem.get??>
			<#assign
				annotationHTTPMethod = "@GET"

				operation = pathItem.get
				operationName = "get"
			/>
		<#elseif pathItem.head??>
			<#assign
				annotationHTTPMethod = "@HEAD"

				operation = pathItem.head
				operationName = "head"
			/>
		<#elseif pathItem.options??>
			<#assign
				annotationHTTPMethod = "@OPTIONS"

				operation = pathItem.options
				operationName = "options"
			/>
		<#elseif pathItem.post??>
			<#assign
				annotationHTTPMethod = "@POST"

				operation = pathItem.post
				operationName = "post"
			/>
		<#elseif pathItem.put??>
			<#assign
				annotationHTTPMethod = "@PUT"

				operation = pathItem.put
				operationName = "put"
			/>
		</#if>

		<#assign
			annotationConsumes = ""
			annotationConsumesValue = ""
		/>

		<#if operation.requestBody?? && operation.requestBody.content??>
			<#assign annotationConsumesValue>
				<#compress>
					<#list operation.requestBody.content?keys?sort as mediaType>
						"${mediaType}",
					</#list>
				</#compress>
			</#assign>
		</#if>

		<#if annotationConsumesValue?has_content>
			<#assign annotationConsumes = "@Consumes({${annotationConsumesValue[0..(annotationConsumesValue?length - 2)]}})" />
		</#if>

		<#assign
			annotationProduces = ""
			annotationProducesValue = ""
		/>

		<#if operation.responses??>
			<#assign annotationProducesValue>
				<#compress>
					<#list operation.responses?values as response>
						<#if response.content??>
							<#list response.content?keys?sort as mediaType>
								"${mediaType}",
							</#list>
						</#if>
					</#list>
				</#compress>
			</#assign>
		</#if>

		<#if annotationProducesValue?has_content>
			<#assign annotationProduces = "@Produces({${annotationProducesValue[0..(annotationProducesValue?length - 2)]}})" />
		</#if>

		<#assign annotationRequiresScope = "" />

		<#if pathItem.get??>
			<#assign annotationRequiresScope = "@RequiresScope(\"${configYAML.application.name}.read\")" />
		<#else>
			<#assign annotationRequiresScope = "@RequiresScope(\"${configYAML.application.name}.write\")" />
		</#if>

		<#assign methodParameters = "" />

		<#if operation.parameters??>
			<#assign methodParameters>
				<@compress single_line=true>
					<#list operation.parameters as parameter>
						@${parameter.in?cap_first}Param("${parameter.name}")

						${parameter.schema.type?cap_first}

						<#assign parameterName = "" />

						<#list parameter.name?split("[^A-Za-z0-9]", "r") as s>
							<#if s?has_content>
								<#if parameterName?has_content>
									<#assign parameterName = "${parameterName}${s?cap_first}" />
								<#else>
									<#assign parameterName = "${s}" />
								</#if>
							</#if>
						</#list>

						${parameterName},
					</#list>
				</@compress>
			</#assign>

			<#assign methodParameters = "${methodParameters[0..(methodParameters?length - 2)]}" />
		</#if>

		<#assign
			methodReturnType = "Response"
			methodReturnValue = "Response"
		/>

		<#if operation.responses??>
			<#list operation.responses?values as response>
				<#if response.content??>
					<#list response.content?values as content>
						<#if content.schema??>
							<#assign schema = content.schema />

							<#if schema.type??>
								<#if stringUtil.equals(schema.type, "array")>
									<#assign reference = "${schema.items.reference}" />

									<#if reference?contains("/schemas/")>
										<#assign schemaName = "${reference[(reference?last_index_of('/') + 1)..(reference?length - 1)]}" />

										<#assign
											methodReturnType = "${schemaName}Collection"
											methodReturnValue = "${schemaName}Collection<${schemaName}>"
										/>
									</#if>
								</#if>
							</#if>

							<#if schema.reference??>
								<#assign reference = "${schema.reference}" />

								<#if reference?contains("/schemas/")>
									<#assign schemaName = "${reference[(reference?last_index_of('/') + 1)..(reference?length - 1)]}" />

									<#assign
										methodReturnType = "${schemaName}"
										methodReturnValue = "${schemaName}"
									/>
								</#if>
							</#if>
						</#if>
					</#list>
				</#if>
			</#list>
		</#if>

		<#assign name>
			<@compress single_line=true>
				${operationName?lower_case}

				<#list path?replace("\\{.*?\\}", "", "rs")?split("[^A-Za-z0-9]", "r") as s>
					<#if s?has_content>
						${s?cap_first}
					</#if>
				</#list>
			</@compress>
		</#assign>

		<#assign name = "${name?replace(' ', '')}" />

		<#assign template>
			@Path("${path}")
			${annotationConsumes}
			${annotationHTTPMethod}
			${annotationProduces}
			${annotationRequiresScope}
			public ${methodReturnValue} ${name}(${methodParameters}) throws Exception;
		</#assign>

		<#list template?split("\n") as line>
			<#if line?trim?has_content>
${line?replace("^\t\t", "", "r")}
			</#if>
		</#list>
	</#list>

}