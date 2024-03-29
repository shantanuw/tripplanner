
<%@ page import="com.tripplanner.domain.Airline" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'airline.label', default: 'Airline')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'airline.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'airline.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="iata" title="${message(code: 'airline.iata.label', default: 'Iata')}" />
                        
                            <g:sortableColumn property="frequentFlier" title="${message(code: 'airline.frequentFlier.label', default: 'Frequent Flier')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${airlineInstanceList}" status="i" var="airlineInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${airlineInstance.id}">${fieldValue(bean: airlineInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: airlineInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: airlineInstance, field: "iata")}</td>
                        
                            <td>${fieldValue(bean: airlineInstance, field: "frequentFlier")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${airlineInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
