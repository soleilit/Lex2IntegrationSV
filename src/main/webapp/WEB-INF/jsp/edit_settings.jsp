<%--
  Author: Jon Wikman, Objectware AB
  Date: 2008-apr-11
  Time: 10:13:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<script src="/lex2search/script/lex-edit.js" type="text/javascript"></script>
<script src="/lex2search/script/color-select.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">


<script type="text/javascript">
setTimeout('colorSelectInit()', 200);

function colorSelectInit() {
    cs0 = new color_select('cs0', document.getElementById('color_select_field').value);
    cs0.attach_to_element(document.getElementById('color_select_btn'));
}

function cs0_change_update(newColor) {
    document.getElementById('color_select_field').value = newColor;
}
</script>
<h1 id="lex-edit-header">Utseende</h1>
<hr />
<portlet:renderURL var="editSearchAdviceUrl">
    <portlet:param name="action" value="editSearchAdvice"/>
</portlet:renderURL>
<ul id="lex-edit-menu">
    <li><a href="${editSearchAdviceUrl}">Söktips</a></li>
    <li><a href="#" class="lex-edit-menu-selected">Utseende</a></li>
</ul>
<div>
    <portlet:actionURL var="actionURL"/>
    <form:form action="${actionURL}&action=editSettings" commandName="settings">
        <div>
            <form:label path="color" cssClass="lex-search-form-label">
                Färg:
            </form:label>
            <form:input path="color" id="color_select_field"/>
            <input type="submit" value="Välj färg" id="color_select_btn" onclick="cs0.toggle_color_select(); return false;" />
        </div>
        <div class="buttonContainer">
            <input type="submit" value="Spara" />
        </div>
    </form:form>
</div>