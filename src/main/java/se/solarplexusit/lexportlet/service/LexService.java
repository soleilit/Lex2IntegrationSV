package se.solarplexusit.lexportlet.service;

import se.solarplexusit.lexportlet.dataobjects.*;

import java.util.List;

public interface LexService
{
	void setBaseUrl(String baseUrl);
	String getBaseUrl();
	
    List<SubjectArea> getSubjectAreas();

    SearchResult search(SearchCriteria criteria);

    SearchResult searchPlus(SearchCriteria criteria);

    Case getCase(String id);

    Document getDocument(String id);

    Document getDocumentFile(String id);

    Meeting getMeeting(String id);

    Decision getDecision(String id);
}
