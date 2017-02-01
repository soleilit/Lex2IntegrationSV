<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style="margin: 2.8em 0">
    <portlet:renderURL var="searchUrl"/>
    <button class="portlet-form-button" onclick="window.location='${searchUrl}&reset=1'">Ny sökning</button>
    <button class="portlet-form-button" onclick="history.go(-1)">Tillbaka</button>
</div>