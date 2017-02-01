<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<h1 class="portlet-section-header">Söktjänst - Träfflista - Detaljerad information om dokument</h1>
<jsp:include page="include/search-criteria.jsp"/>
<jsp:include page="include/search-and-back-buttons.jsp"/>
<h2 class="portlet-section-subheader">Information om dokumentet</h2>

<div class="portlet-font" style="padding:1em 1em 2.6em 1em; margin-bottom:1.6em;">
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Diarium</div>
        <div style="float: left; padding: 0 2px">${document.diary_name}</div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Tillhör</div>
        <div style="float: left; padding: 0 2px; width: 75%">
            <c:forEach items="${document.owners}" var="owner" varStatus="status">
                <c:if test="${status.index != 0}">
                    <br />
                </c:if>
                <c:choose>
                    <c:when test="${owner.type eq 'Case'}">
                        <c:out value="Ärende: "/>
                    </c:when>
                    <c:when test="${owner.type eq 'Meeting'}">
                        <c:out value="Sammanträde: "/>
                    </c:when>
                    <c:when test="${owner.type eq 'MeetingTopic'}">
                        <c:out value="Beslut: "/>
                    </c:when>
                </c:choose>
                <c:out value="${owner.name}" />
            </c:forEach>
        </div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Dokumentbeskrivning:</div>
        <div style="float: left; padding: 0 2px; width: 60%">${document.description}</div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Dokumenttyp:</div>
        <div style="float: left; padding: 0 2px; width: 75%">
        <div style="float: left; padding: 0 2px; width: 75%">${document.type_name}</div>         
        </div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Filtyp:</div>
        <div style="float: left; padding: 0 2px">${document.file_format}</div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Filstorlek:</div>
        <div style="float: left; padding: 0 2px">

		<c:choose>
			<c:when test="${document.file_size > 100000}">
			<fmt:formatNumber value="${document.file_size*0.000001}" maxFractionDigits="1"/> MB
			</c:when>
			<c:otherwise>
			<fmt:formatNumber value="${document.file_size*0.001}" maxFractionDigits="1"/> KB
			</c:otherwise>
		</c:choose> 

        </div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Inkommet/upprättat datum:</div>
        <div style="float: left; padding: 0 2px" class="lexNoWrap">${document.createdSign_date}</div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Sekretessgrund:</div>
        <div style="float: left; padding: 0 2px">Ingen</div>
    </div>

    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Avs/mottagare:</div>
        <div style="float: left; padding: 0 2px"><c:if test="${document.companyname == null}">Ingen</c:if></div>
    </div>

    <c:if test="${document.fileAvailable}">
        <div style="clear:both; float:none">
            <div style="float: left; width: 13em; padding: 0 2px;">&nbsp;</div>
            <div style="float: left; padding: 0 2px">
                <c:url var="binaryUrl" value="/binary/${document.fileName}">
                    <c:param name="id" value="${document.id}"/>
                </c:url>
                Fulltext
                <a href="${binaryUrl}" title="Läs dokument" target="_blank">
                    <img src="/lex2search/images/icon_${fn:toLowerCase(document.file_format)}.png" border="0"/>
                </a>
            </div>
        </div>
    </c:if>
</div>