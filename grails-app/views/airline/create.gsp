

<%@ page import="com.tripplanner.domain.Airline" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'airline.label', default: 'Airline')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${airlineInstance}">
            <div class="errors">
                <g:renderErrors bean="${airlineInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="airline.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${airlineInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="iata"><g:message code="airline.iata.label" default="Iata" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'iata', 'errors')}">
                                    <g:textField name="iata" maxlength="3" value="${airlineInstance?.iata}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="frequentFlier"><g:message code="airline.frequentFlier.label" default="Frequent Flier" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'frequentFlier', 'errors')}">
                                    <g:textField name="frequentFlier" value="${airlineInstance?.frequentFlier}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
