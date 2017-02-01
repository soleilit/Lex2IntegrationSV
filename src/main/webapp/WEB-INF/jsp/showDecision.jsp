<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<script src="/lex2search/script/lex.js" type="text/javascript"></script>
<h1 class="portlet-section-header">Söktjänst - Träfflista - Detaljerad information om beslut</h1>
<jsp:include page="include/search-criteria.jsp" />
<jsp:include page="include/search-and-back-buttons.jsp"/>

<h2 class="portlet-section-subheader">Information om beslutet</h2>
<div class="portlet-font" style="padding:1em 1em 2.6em 1em; margin-bottom:2.6em;">
    <div style="clear:both; float:none">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Enhet</div>
        <div style="float: left; padding: 0 2px">${decision.unit}</div>
    </div>
    <div style="clear:both; float:none">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Sammanträdesdatum</div>
        <div style="float: left; padding: 0 2px" class="lexNoWrap">${decision.date}</div>
    </div>
    <div style="clear:both; float:none">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Punktnr</div>
        <div style="float: left; padding: 0 2px">${decision.punktnr}</div>
    </div>
    <div style="clear:both; float:none">
        <div style="float: left; width: 13em; padding: 0 2px; font-weight: bold">Punktrubrik</div>
        <div style="float: left; padding: 0 2px; width: 75%">${decision.punktrubrik}</div>
    </div>
    <div style="clear:both; float:none">
        <div style="padding: 0 2px; font-weight: bold">Beslutstext</div>
        <div style="padding: 0.5em 2px; width:100%">
            ${decision.beslutstext}
        </div>
    </div>
</div>
