<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script src="/lex2search/script/lex.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/lex2search/styles/color-select.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportlet.css">
<link rel="stylesheet" type="text/css" href="/lex2search/styles/lexportletedit.css">


<h1 class="portlet-section-header">Söktjänst - Träfflista</h1>

<jsp:include page="include/search-criteria.jsp"/>

<div style="margin: 2.8em 0">
    <portlet:renderURL var="searchUrl"/>
    <button class="portlet-form-button" onclick="window.location='${searchUrl}&amp;reset=1'">Ny sökning</button>
</div>

<div style="margin-bottom: 0.8em">
    ${fn:length(result.searchResult)} sökträffar hittades i LEX med de angivna sökkriterierna
</div>
<div id="lexSearchResultContainer">
    <display:table name="result.searchResult" class="lex-result-table" id="row" cellspacing="1" cellpadding="3" defaultsort="2" defaultorder="descending">
        
        <display:column title="Typ"  sortProperty="name" sortable="true" class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">

            <c:choose>
                <c:when test="${row.caseItem}">
                    Ärende
                </c:when>
                <c:when test="${row.documentItem}">
                    Dokument
                </c:when>
                <c:otherwise>
                    Sammanträde och beslut
                </c:otherwise>
            </c:choose>

        </display:column>
        
        <display:column title="Beskrivning" sortProperty="customSort" sortable="true" class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">

            <c:choose>
                <c:when test="${row.caseItem}">
                    Diarienummmer: ${row.diarienummer}
                    <br />
                    Ärendemening: ${row.description}
                    <c:if test="${!row.secrecy}">
                        <portlet:renderURL var="caseDetailsUrl">
                            <portlet:param name="action" value="showCase"/>
                            <portlet:param name="caseId" value="${row.id}"/>
                        </portlet:renderURL>
                        <div style="margin-top:0.6em">
                            <a href="${caseDetailsUrl}" title="Läs mer om ärende ${row.diarienummer}">Läs mer</a>
                        </div>
                    </c:if>
                </c:when>
                <c:when test="${row.documentItem}">
                    <c:choose>
                        <c:when test="${row.owners[0].type eq 'Case'}">
                            Diarienummmer: ${row.owners[0].name}
                        </c:when>
                        <c:when test="${row.owners[0].type eq 'Meeting'}">
                            Sammanträde: ${row.owners[0].name}
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                    <br />
                    Dokumentbeskrivning: ${row.description}
                    <br />
                    Filtyp: ${row.file_format}
                    <br />
                    Filstorlek:
                    <c:choose>
                        <c:when test="${row.file_size > 100000}">
                            <fmt:formatNumber value="${row.file_size*0.000001}" maxFractionDigits="1"/> MB
                        </c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${row.file_size*0.001}" maxFractionDigits="1"/> KB
                        </c:otherwise>
                    </c:choose>
                    <br />
                    <c:if test="${!row.secrecy}">
                        <portlet:renderURL var="documentDetailsUrl">
                            <portlet:param name="action" value="showDocument"/>
                            <portlet:param name="documentId" value="${row.id}" />
                        </portlet:renderURL>
                        <div style="margin-top: 0.6em">
                            <a href="${documentDetailsUrl}" title="Läs mer om dokument ${row.description}">Läs mer</a>
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    Enhet: ${row.unit}
                    <br />
                    <c:if test="${!row.secrecy}">
                        <portlet:renderURL var="meetingDetailsUrl">
                            <portlet:param name="action" value="showMeeting"/>
                            <portlet:param name="meetingId" value="${row.id}"/>
                        </portlet:renderURL>
                        <div style="margin-top: 0.6em">
                            <a href="${meetingDetailsUrl}" title="Läs mer om sammanträde ${row.unit} ${row.date}">Läs mer</a>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>

        </display:column>
        
        <display:column title="Datum" sortable="true" class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">

            <c:choose>
                <c:when test="${row.caseItem}">
                    Skapatdatum: <span class="lexNoWrap">${row.createdSign_date}</span>
                </c:when>
                <c:when test="${row.documentItem}">
                    Inkom/upprättat datum: <span class="lexNoWrap">${row.date}</span>
                </c:when>
                <c:otherwise>
                    Sammanträdesdatum: <span class="lexNoWrap">${row.date}</span>
                </c:otherwise>
            </c:choose>

        </display:column>
        
        <display:column title="Ämnesområde / Diarium" sortable="true" class="${row.secrecy ? 'lexSecrecyItem ' : ''}portlet-font">

            <c:choose>
                <c:when test="${row.caseItem}">
                    ${row.subjectArea_name}
                </c:when>
                <c:when test="${row.documentItem}">
                    ${row.diary_name}
                </c:when>
                <c:otherwise>
                    ${row.diarium}
                </c:otherwise>
            </c:choose>

        </display:column>
        
        <display:column title="Fulltext" sortProperty="file_format" style="text-align: center;" sortable="true" >

            <c:if test="${row.documentItem && row.fileAvailable}">
                <c:choose>
                    <c:when test="${!row.secrecy}">
                        <c:url var="binaryUrl" value="/binary/${row.fileName}">
                            <c:param name="id" value="${row.id}" />
                        </c:url>
                        <a href="${binaryUrl}" title="Läs dokument" target="_blank">
                            <img src="/lex2search/images/icon_${fn:toLowerCase(row.file_format)}.png" border="0"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <img src="/lex2search/images/icon_${fn:toLowerCase(row.file_format)}_unavailable.png" border="0"/>
                    </c:otherwise>
                </c:choose>
            </c:if>

        </display:column>
        
    </display:table>
</div>

<script type="text/javascript">
    /* <![CDATA[ */
    lexTableTitleGeneratorSetTitle('lexSearchResultContainer');
    /* ]]> */
</script>