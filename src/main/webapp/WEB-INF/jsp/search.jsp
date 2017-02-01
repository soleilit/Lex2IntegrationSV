<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<script src="/lex2search/script/lex.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">

<portlet:actionURL var="actionUrl" />
<form:form commandName="criteria" action="${actionUrl}">
<!--      <table summary="Struktur">-->
    <table>
        <caption></caption>
        <tr>
            <th id="lexSearchSearchForm"></th>
            <th id="lexSearchSearchTip"></th>
        </tr>
        <tr>
            <td class="lexSearchLeft portlet-font" style="padding-right: 5%; width: 20em; vertical-align: top;">

                <%-- Info types --%>
                <div style="clear:both; float:none; width: 100%">
                    <div style="float: left; width: 100%;">
                        <strong><fmt:message key="search.header.infoTypes"/></strong>
                    </div>
					                    
                    <form:checkboxes path="infoTypes" items="${infoTypes}" title="${search.label.infoTypes}" cssClass="portlet-form-input-field" element="div"/>
                    
                    <p class="portlet-section-text">                    
                    <fmt:message key="search.label.infoTypesInstruction"/>
                    </p>
                </div>
                
                <%-- Subject areas --%>
                <div style="clear:both; float:none; padding-top: 1.5em; width: 100%; border-color: black" >
                    <form:label path="subjectAreas" cssClass="portlet-section-text" cssStyle="display: block">
                        <strong><fmt:message key="search.header.criteria"/>:</strong>
                    </form:label>
                    <div style="float: left; width: 100%;">
                        <fmt:message key="search.label.subjectArea"/>
                    </div>
                    <form:select path="subjectAreas" items="${subjectAreas}" title="${search.label.subjectArea}" size="3" cssClass="portlet-form-input-field" >
	                     <form:options cssClass="portlet-form-input-field" />
                     </form:select>
                     <br>
                    <fmt:message key="search.label.subjectAreaInstruction"/>
                    <br><br>
                </div>

                <div style="clear:both; float:none;">
                    <div style="float: left; width: 100%;">
                        <fmt:message key="search.label.date"/>
                    </div>
                    <br />
                    
                    <%-- fromDate --%>
                    <div style="float: left; width: 47%;">
                        <fmt:message key="search.label.fromDate" var="search.label.fromDate"/>
                        <form:label path="fromDate" cssClass="portlet-form-field-label" cssStyle="display: block">
                            <fmt:message key="search.label.fromDate"/>:
                        </form:label>
                        <form:input path="fromDate" title="${search.label.fromDate}" cssClass="portlet-form-input-field" cssStyle="width: 100%" />
                        <div><fmt:message key="search.label.dateFormat"/></div>
                    </div>

                    <%-- toDate --%>
                    <div style="float: left; width: 47%; padding-left: 1em">
                        <form:label path="toDate" cssClass="portlet-form-field-label" cssStyle="display: block">
                            <fmt:message key="search.label.toDate"/>:
                        </form:label>
                        <fmt:message key="search.label.toDate" var="search.label.toDate"/>
                        <form:input path="toDate" title="${search.label.toDate}"  cssClass="portlet-form-input-field" cssStyle="width: 100%" />
                        <div><fmt:message key="search.label.dateFormat"/></div>
                    </div>
                </div>

                <%-- Free text --%>
                <div style="clear:both; float:none; padding-top: 1.5em; width: 100%">
                    <form:label path="searchText" cssClass="portlet-form-field-label" cssStyle="display: block">
                        <fmt:message key="search.label.freeText"/>:
                    </form:label>
                    <fmt:message key="search.label.freeText" var="search.label.freeText"/>
                    <form:input path="searchText" title="${search.label.freeText}"  cssClass="portlet-form-input-field" cssStyle="width:100%"/>
                </div>

                <div style="clear:both; float:none; padding-top: 1.5em; width: 100%">
                    <form:errors path="*" cssClass="portlet-msg-error" cssStyle="padding: 0.2em 0"/>
                </div>
                <%-- ================================================== --%>
                
                <%-- ================================================== --%>
                <%-- Search button --%>
                <div style="width: 100%; text-align: right">
                    <input type="reset" value="Rensa" class="portlet-form-button" onclick="clearForm(document.getElementById('criteria'));return false" onkeypress="" />
                    <fmt:message key="search.button.search" var="searchButtonSearch"/>
                    <input type="submit" title="${search.button.search}" value="${searchButtonSearch}"
                           class="portlet-form-button" />
                    <%-- ================================================== --%>
                </div>
            </td>
            <td class="lexSearchRight portlet-font" style="vertical-align: top; vertical-align: baseline">
                
                <c:forEach var="searchAdvice" items="${searchAdvices}">
                    <div class="portlet-section-text lexSearchAdvice">
                        <strong>${searchAdvice.header}</strong>
                        <br>
                        ${searchAdvice.textWithHtmlBreaks}
                        <br>
                        <br>
                    </div>
                </c:forEach>
                <%-- ================================================== --%>

            </td>
        </tr>
    </table>
</form:form>