package se.solarplexusit.lexportlet.service.impl;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import se.solarplexusit.lexportlet.dataobjects.Case;
import se.solarplexusit.lexportlet.dataobjects.Decision;
import se.solarplexusit.lexportlet.dataobjects.Document;
import se.solarplexusit.lexportlet.dataobjects.Lex;
import se.solarplexusit.lexportlet.dataobjects.Meeting;
import se.solarplexusit.lexportlet.dataobjects.ResultItem;
import se.solarplexusit.lexportlet.dataobjects.SearchCriteria;
import se.solarplexusit.lexportlet.dataobjects.SearchResult;
import se.solarplexusit.lexportlet.dataobjects.SubjectArea;
import se.solarplexusit.lexportlet.dataobjects.Topic;
import se.solarplexusit.lexportlet.service.LexService;

public class TestLexService implements LexService
{
    private String baseUrl;
    private UnMarshallers unmarshallers;

    public TestLexService(String baseUrl, UnMarshallers unmarshallers)
    {
        this.baseUrl = baseUrl;
        this.unmarshallers = unmarshallers;
    }

    public List<SubjectArea> getSubjectAreas()
    {
        List<SubjectArea> subjectAreas = new ArrayList<SubjectArea>();

        subjectAreas.add(new SubjectArea("Barn och utbildning", "1"));
        subjectAreas.add(new SubjectArea("Bygga och bo", "2"));
        subjectAreas.add(new SubjectArea("Omsorg och stöd", "3"));
        subjectAreas.add(new SubjectArea("Räddningstjänst", "4"));
        subjectAreas.add(new SubjectArea("Trafik och infrastruktur", "5"));
        subjectAreas.add(new SubjectArea("Uppleva och göra", "6"));
        subjectAreas.add(new SubjectArea("Fastighetskontoret", "7"));

        return subjectAreas;
    }

    public SearchResult search(SearchCriteria criteria)
    {
        return searchPlus(criteria);
    }

    public SearchResult searchPlus(SearchCriteria criteria)
    {
        SearchResult result = new SearchResult();

        List<ResultItem> list = new ArrayList<ResultItem>();
        list.add(getDocumentTest("1"));
        list.add(getCaseTest("1"));
        result.setSearchResult(list);

        return result;
    }

    public Case getCase(String id)
    {
    	Case thisCase = null;
    	try {
	    	FileInputStream file = new FileInputStream("c:/temp/resources/case.xml");
	    	Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(new StreamSource(file));
	    	thisCase = lex.getInformation().getCaseItem();
	        return thisCase;
    	}
    	catch (Exception e){
    	}
    	return thisCase;
    }

    public Case getCaseTest(String id)
    {
    	
        Case testCase = new Case();

        testCase.setId(id);
        testCase.setCase_title("KS/2011:61");
        testCase.setDiarienummer("BN/2014:1");
        testCase.setType_name("Type name");
        testCase.setSubjectArea_name("Trafik och infrastruktur");
        testCase.setModifiedSign_date("Modified sign date");
        testCase.setDescription("Samråd för järnvägsplan sträckan Djurgårdsbron-Ropsten");
        testCase.setDate("Date");
        testCase.setCreatedSign_date("2011-03-15");
        testCase.setDiary_name("Kommunstyrelsen");
        testCase.setFile_format("pdf");
        testCase.setCaseItem(true);
        
        List<Document> documents = new ArrayList<Document>();
        documents.add(getDocument("1"));
        documents.add(getDocument("2"));
        documents.add(getDocument("3"));
        testCase.setDocuments(documents);

        List<Meeting> meetings = new ArrayList<Meeting>();
        meetings.add(getMeeting("3"));
        meetings.add(getMeeting("4"));
        meetings.add(getMeeting("5"));
        testCase.setMeetings(meetings);

        return testCase;
        
    }

    public Document getDocument(String id)
    {
    	Document thisDocument = null;
    	try {
	    	FileInputStream file = new FileInputStream("c:/temp/resources/document.xml");
	    	Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(new StreamSource(file));
	    	thisDocument = lex.getInformation().getDocumentItem();
	        return thisDocument;
    	}
    	catch (Exception e){
    	}
    	return thisDocument;
    }

    public Document getDocumentTest(String id)
    {
        Document document = new Document();

        document.setId(id);
        document.setCompanyname("company name");
        document.setCreatedSign_date("2011-05-03");
        document.setCustomSort("Custom sort");
        document.setDate("Date");
        document.setDescription("Inbjudan till s - Ropsten, Stockholm");
        document.setDiarienummer("Diarienummer");
        document.setDiary_name("Diary name");
        document.setDocumentItem(true);
        document.setFile_format("doc");
        document.setFile_size("100202");
        document.setFile_sizeType("");
        document.setType_name("type name");
        document.setData("test".getBytes());

        return document;
    }

    public Document getDocumentFile(String id)
    {
        return getDocument(id);
    }

    public Meeting getMeeting(String id)
    {
        Meeting meeting = new Meeting();

        meeting.setId(id);
        meeting.setCaseItem(true);
        meeting.setDate("2011-07-01");
        meeting.setDescription("Description");
        meeting.setDiarienummer("Diarienummer");
        meeting.setDiarium("Diarium");
        meeting.setDocumentItem(true);
        meeting.setFile_format("jpg");
        meeting.setMeetingItem(false);
        meeting.setModifiedSign_by("Modified sign by");
        meeting.setModifiedSign_date("Modified sign date");
        meeting.setPunktNr("1");
        meeting.setPunktRubrik("Punkt rubrik");
        meeting.setSecrecy(false);
        meeting.setTitle("Titel");
        meeting.setUnit("Unit name");

        List<Document> documents = new ArrayList<Document>();
        documents.add(getDocument("1"));
        documents.add(getDocument("2"));
        documents.add(getDocument("3"));
        meeting.setDocuments(documents);

        List<Decision> decisions = new ArrayList<Decision>();
        decisions.add(getDecision("1"));
        decisions.add(getDecision("2"));
        decisions.add(getDecision("3"));
        meeting.setDecisions(decisions);

        List<Topic> topics = new ArrayList<Topic>();
        topics.add(getTopic("1"));
        topics.add(getTopic("2"));
        topics.add(getTopic("3"));
        meeting.setTopics(topics);

        return meeting;
    }

    public Topic getTopic(String id)
    {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setTitle("Title");
        topic.setNumber("Number");
        topic.setSortOrder("Sort order");

        List<Document> documents = new ArrayList<Document>();
        documents.add(getDocument("1"));
        documents.add(getDocument("2"));
        documents.add(getDocument("3"));
        topic.setDocuments(documents);

        List<Decision> decisions = new ArrayList<Decision>();
        decisions.add(getDecision("1"));
        decisions.add(getDecision("2"));
        decisions.add(getDecision("3"));
        topic.setDecisions(decisions);

        return topic;
    }

    public Decision getDecision(String id)
    {
        Decision decision = new Decision();

        decision.setId(id);
        decision.setBeslutstext("Beslutstext");
        decision.setDate("2011-02-30");
        decision.setDescription("Description");
        decision.setPunktnr("1");
        decision.setPunktrubrik("Punkt rubrik");
        decision.setUnit("Unit");

        return decision;
    }

    private String getDateString(Date date)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

	@Override
	public void setBaseUrl(String baseUrl) {
		// TODO Auto-generated method stub
		this.baseUrl = baseUrl;
	}

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}
