<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="portlet-section-text" style="margin: 0.8em 0;">
    Du har sökt på följande:
    <br />
    <c:if test="${searchCriteria != null}">
    	<fmt:message key="search.label.infoTypes"/>: ${searchCriteria.infoTypesText} <br>
    	<fmt:message key="search.label.subjectArea"/>: ${searchCriteria.subjectAreasText} <br>
        <fmt:message key="search.label.date"/>: <fmt:formatDate value="${searchCriteria.fromDate}" pattern="yyyy-MM-dd"/>
        -&gt;
        <fmt:formatDate value="${searchCriteria.toDate}" pattern="yyyy-MM-dd"/><br>
        <fmt:message key="search.label.freeText"/>: ${searchCriteria.searchText}
    </c:if>
</div>