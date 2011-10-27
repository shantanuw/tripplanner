
<%@ page import="com.tripplanner.domain.Trip" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'trip.label', default: 'Trip')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'trip.id.label', default: 'Id')}" />
                        
                            <th><g:message code="trip.airline.label" default="Airline" /></th>
                        
                            <g:sortableColumn property="name" title="${message(code: 'trip.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="city" title="${message(code: 'trip.city.label', default: 'City')}" />
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'trip.startDate.label', default: 'Start Date')}" />
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'trip.endDate.label', default: 'End Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tripInstanceList}" status="i" var="tripInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tripInstance.id}">${fieldValue(bean: tripInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: tripInstance, field: "airline")}</td>
                        
                            <td>${fieldValue(bean: tripInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: tripInstance, field: "city")}</td>
                        
                            <td><g:formatDate date="${tripInstance.startDate}" /></td>
                        
                            <td><g:formatDate date="${tripInstance.endDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
            </div>
        </div>
    </body>
</html>