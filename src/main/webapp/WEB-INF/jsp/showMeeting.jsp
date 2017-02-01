<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<script src="/lex2search/script/lex.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">


<h1 class="portlet-section-header">Söktjänst - Träfflista - Detaljerad information om sammanträde</h1>
<jsp:include page="include/search-criteria.jsp"/>
<jsp:include page="include/search-and-back-buttons.jsp"/>

<h2 class="portlet-section-subheader">Information om sammanträde ${meeting.title}</h2>
<div class="portlet-font" style="padding:1em 1em 2.6em 1em; margin-bottom:2.6em;">
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Enhet</div>
        <div style="float: left; padding: 0 2px">${meeting.unit}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Sammanträdesdatum</div>
        <div style="float: left; padding: 0 2px" class="lexNoWrap">${meeting.date}</div>
    </div>
    <c:forEach items="${meeting.documents}" var="document">
        <c:if test="${document.fileAvailable}">
            <div style="clear:both; float:none; padding: 0.2em 0">
                <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">${document.type_name}</div>
                <div style="float: left; padding: 0 2px">
                    <c:url var="binaryUrl" value="/binary/${document.fileName}">
                        <c:param name="id" value="${document.id}" />
                    </c:url>
                    <a href="${binaryUrl}" title="Läs dokument" target="_blank">
                        <img src="/lex2search/images/icon_${fn:toLowerCase(document.file_format)}.png" border="0"/>
                    </a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>


<c:if test="${!empty meeting.topics}">
    <h2 class="portlet-section-subheader">Relaterade sammanträdespunkter, beslut, ärenden och dokument</h2>
    <div id="lexMeetingTopicsContainer">
        <display:table name="meeting.topics" class="lex-result-table" id="showMeetingTopic" cellspacing="1"
                       cellpadding="3" defaultsort="1" style="width: 100%; margin-bottom:1.6em">

            <display:column title="Punktnr" sortable="true" sortProperty="sortOrder" style="width: 6.5em;" class="portlet-section-body">
                ${showMeetingTopic.number}
            </display:column>

            <display:column title="Punktrubrik" sortable="true" property="title" class="portlet-section-body"/>

            <display:column title="Beslut" sortable="true" class="portlet-section-body">
                <c:forEach var="decision" items="${showMeetingTopic.decisions}">
                    <portlet:renderURL var="decisionDetailsUrl">
                        <portlet:param name="action" value="showDecision"/>
                        <portlet:param name="decisionId" value="${decision.id}"/>
                    </portlet:renderURL>
                    <div style="height: 22px">
                        <a href="${decisionDetailsUrl}" title="Visa beslut på ${showMeetingTopic.number}">Beslut</a>
                    </div>
                </c:forEach>
            </display:column>

            <display:column title="Ärende" sortable="true" class="portlet-section-body">
                <c:forEach var="theCase" items="${showMeetingTopic.cases}">
                    <portlet:renderURL var="caseDetailsUrl">
                        <portlet:param name="action" value="showCase"/>
                        <portlet:param name="caseId" value="${theCase.id}"/>
                    </portlet:renderURL>
                    <div style="height: 22px">
                        <a href="${caseDetailsUrl}" title="Läs mer om ärend ${theCase.case_title}">${theCase.case_title}</a>
                    </div>
                </c:forEach>
            </display:column>

            <display:column title="Dokument" sortable="true" class="portlet-section-body">
                <c:forEach var="document" items="${showMeetingTopic.documents}">
                    <portlet:renderURL var="documentDetailsUrl">
                        <portlet:param name="action" value="showDocument"/>
                        <portlet:param name="documentId" value="${document.id}"/>
                    </portlet:renderURL>
                    <div style="height: 22px">
                        <a href="${documentDetailsUrl}" title="Läs mer om dokument ${document.type_name}">${document.type_name}</a>
                    </div>

                </c:forEach>
            </display:column>

            <display:column title="Fulltext" sortable="true" class="portlet-section-body">
                <c:forEach var="document" items="${showMeetingTopic.documents}">
                    <div style="height: 22px; text-align:center">
                    <c:choose>
                        <c:when test="${document.fileAvailable}">
                            <c:choose>
                                <c:when test="${!document.secrecy}">
                                    <c:url var="binaryUrl" value="/binary/${document.fileName}">
                                        <c:param name="id" value="${document.id}" />
                                    </c:url>
                                    <a href="${binaryUrl}" title="Läs dokument" target="_blank">
                                        <img src="/lex2search/images/icon_${fn:toLowerCase(document.file_format)}.png" border="0"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <img src="/lex2search/images/icon_${fn:toLowerCase(document.file_format)}_unavailable.png" border="0"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            &nbsp;
                        </c:otherwise>
                    </c:choose>
                    </div>
                </c:forEach>
            </display:column>
        </display:table>
    </div>
    <script type="text/javascript">
    /* <![CDATA[ */
    lexTableTitleGeneratorSetTitle('lexMeetingTopicsContainer');
    /* ]]> */
    </script>
</c:if>

