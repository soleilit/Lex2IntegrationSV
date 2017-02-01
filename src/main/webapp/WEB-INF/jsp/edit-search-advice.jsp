<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<script src="/lex2search/script/lex-edit.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">


<portlet:renderURL var="editSettingsUrl">
    <portlet:param name="action" value="editSettings"/>
</portlet:renderURL>
<ul id="lex-edit-menu">
    <li><a href="#" class="lex-edit-menu-selected">Söktips</a></li>
    <li><a href="${editSettingsUrl}">Utseende</a></li>
</ul>
<portlet:actionURL var="actionUrl"/>
<div class="lex-edit-container">
<form:form action="${actionUrl}" commandName="adviceList">
    <div class="buttonContainer">
        <input type="submit" name="addAdvice" value="Lägg till söktips"/>
        <input type="submit" name="removeSelected" value="Ta bort markerade"/>
        <input type="submit" value="Spara"/>
    </div>
    <div class="adviceListContainer">
        <c:forEach items="${adviceList.items}" varStatus="vs">
            <fieldset class="lex-edit-fieldset">
                <legend class="lex-search-tip-header">Söktips ${vs.count}</legend>
                    <div class="lex-row">
                        <input type="checkbox" name="remove${vs.index}" id="lex-edit-checkbox${vs.index}"/> <label class="lex-search-checkbox-label" for="lex-edit-checkbox${vs.index}">Markera för borttagning</label>
                    </div>
                    <div class="lex-row">
                        <form:label path="items[${vs.index}].header" cssClass="lex-search-form-label" for="lex-edit-header${vs.index}">Rubrik:</form:label>
                        <form:input path="items[${vs.index}].header" id="lex-edit-header${vs.index}"/>
                    </div>
                    <div class="lex-row">
                        <form:label path="items[${vs.index}].text" cssClass="lex-search-form-label" for="lex-edit-text${vs.index}">Text:</form:label>
                        <form:textarea path="items[${vs.index}].text" id="lex-edit-text${vs.index}"/>
                    </div>
            </fieldset>
        </c:forEach>
    </div>
    <div class="buttonContainer">
        <input type="submit" name="addAdvice" value="Lägg till söktips"/>
        <input type="submit" name="deleteSelected" value="Ta bort markerade"/>
        <input type="submit" value="Spara"/>
    </div>
</form:form>
</div>