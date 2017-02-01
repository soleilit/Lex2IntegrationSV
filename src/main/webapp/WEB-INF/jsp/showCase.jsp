<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el" %>

<script src="/lex2search/script/lex.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">


<h1 class="portlet-section-header">Söktjänst - Träfflista - Detaljerad information om ärende</h1>
<jsp:include page="include/search-criteria.jsp"/>
<jsp:include page="include/search-and-back-buttons.jsp"/>

<h2 class="portlet-section-subheader">Information om ärende</h2>

<div class="portlet-font" style="padding: 1em 1em 2.6em 1em; margin-bottom:3em;">
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Diarium</div>
        <div style="float: left; padding: 0 2px">${theCase.diary_name}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Ämnesområde</div>
        <div style="float: left; padding: 0 2px">${theCase.subjectArea_name}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Diarienummer</div>
        <div style="float: left; padding: 0 2px">${theCase.case_title}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Ärendemening</div>
        <div style="float: left; padding: 0 2px; width:75%">${theCase.description}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Skapat datum</div>
        <div style="float: left; padding: 0 2px" class="lexNoWrap">${theCase.createdSign_date}</div>
    </div>
    <div style="clear:both; float:none; padding: 0.2em 0">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Sekretessgrund</div>
        <div style="float: left; padding: 0 2px">Ingen</div>
    </div>
</div>

<c:if test="${!empty theCase.documents}">
    <div id="lexRelatedDocsContainer">
        <h2 class="portlet-section-subheader">Relaterade dokument</h2>
        <display:table name="theCase.documents" class="lex-result-table" id="showCaseDocument" cellspacing="1"
                       cellpadding="3" defaultsort="1" style="width: 100%; margin-bottom:3em">
            <display:column title="Datum" sortable="true" property="createdSign_date" style="width: 8em;"
                            class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font lexNoWrap" />

            <display:column title="Beskrivning" sortProperty="diarienummer" sortable="true"
                            class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">
                ${showCaseDocument.description}
                <c:if test="${!showCaseDocument.secrecy}">
                    <br />
                    <c:if test="${showCaseDocument.fileAvailable}">
                        Filtyp: ${showCaseDocument.file_format}
                        <br />
                        Filstorlek:
                        <c:choose>
                        <c:when test="${showCaseDocument.file_size > 100000}">
                            <fmt:formatNumber value="${showCaseDocument.file_size*0.000001}" maxFractionDigits="1"/> MB
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${showCaseDocument.file_size*0.001}" maxFractionDigits="1"/> KB
                        </c:otherwise>
                        </c:choose>
                        <br />
                    </c:if>
                    <portlet:renderURL var="documentDetailsUrl">
                        <portlet:param name="action" value="showDocument"/>
                        <portlet:param name="documentId" value="${showCaseDocument.id}"/>
                    </portlet:renderURL>
                    <div>
                        <a href="${documentDetailsUrl}" title="Läs mer om dokument ${showCaseDocument.description}">Läs mer</a>
                    </div>
                </c:if>
            </display:column>

            <display:column title="Fulltext" style="portlet-section-body text-align: center; width: 18px;" sortable="true" sortProperty="file_format">
                <c:if test="${showCaseDocument.fileAvailable}">
                    <c:choose>
                        <c:when test="${!showCaseDocument.secrecy}">
                            <c:url var="binaryUrl" value="/binary/${showCaseDocument.fileName}">
                                <c:param name="id" value="${showCaseDocument.id}"/>
                            </c:url>
                            <a href="${binaryUrl}" title="Läs dokument" target="_blank">
                                <img src="/lex2search/images/icon_${fn:toLowerCase(showCaseDocument.file_format)}.png" border="0"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <img src="/lex2search/images/icon_${fn:toLowerCase(showCaseDocument.file_format)}_unavailable.png" border="0"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </display:column>
        </display:table>
    </div>

    <script type="text/javascript">
    /* <![CDATA[ */
    lexTableTitleGeneratorSetTitle('lexRelatedDocsContainer');
    /* ]]> */
    </script>
</c:if>

<c:if test="${!empty theCase.meetings}">
    <div id="lexRelatedMeetingsContainer">
        <h2 class="portlet-section-subheader">Relaterade sammanträden</h2>
        <display:table name="theCase.meetings" class="lex-result-table" id="showCaseMeeting" cellspacing="1" cellpadding="3" style="width: 100%" defaultsort="2">
            <display:column title="Enhet" sortable="true" sortProperty="unit"
                            class="${showCaseMeeting.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">
                ${showCaseMeeting.unit}
                <c:if test="${!showCaseMeeting.secrecy}">
                    <portlet:renderURL var="meetingDetailsUrl">
                        <portlet:param name="action" value="showMeeting"/>
                        <portlet:param name="meetingId" value="${showCaseMeeting.id}"/> 
                    </portlet:renderURL>
                    <div>
                        <a href="${meetingDetailsUrl}" title="Läs mer om sammanträde ${showCaseMeeting.unit} ${showCaseMeeting.date}">Läs mer</a>
                    </div>
                </c:if>
            </display:column>

            <display:column title="Sammanträdesdatum" sortable="true" property="date"
                            class="${showCaseMeeting.secrecy ? 'lexSecrecyItem ' : ''}portlet-font lexNoWrap" />

            <display:column title="Punktnr" sortable="true" property="punktNr"
                            class="${showCaseMeeting.secrecy ? 'lexSecrecyItem ' : ''}portlet-font" />

            <display:column title="Punktrubrik" sortable="true" property="punktRubrik"
                            class="${showCaseMeeting.secrecy ? 'lexSecrecyItem ' : ''}portlet-font" />
        </display:table>
    </div>

    <script type="text/javascript">
    /* <![CDATA[ */
    lexTableTitleGeneratorSetTitle('lexRelatedMeetingsContainer');
    /* ]]> */
    </script>
</c:if>
