package se.solarplexusit.lexportlet.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.transform.Source;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.transform.StringSource;

import se.solarplexusit.lexportlet.dataobjects.Case;
import se.solarplexusit.lexportlet.dataobjects.Decision;
import se.solarplexusit.lexportlet.dataobjects.Document;
import se.solarplexusit.lexportlet.dataobjects.Lex;
import se.solarplexusit.lexportlet.dataobjects.Meeting;
import se.solarplexusit.lexportlet.dataobjects.SearchCriteria;
import se.solarplexusit.lexportlet.dataobjects.SearchResult;
import se.solarplexusit.lexportlet.dataobjects.SubjectArea;
import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.lexportlet.service.LexServiceException;
import se.solarplexusit.lexportlet.wsdl.GetCase;
import se.solarplexusit.lexportlet.wsdl.GetCaseResponse;
import se.solarplexusit.lexportlet.wsdl.GetDecision;
import se.solarplexusit.lexportlet.wsdl.GetDecisionResponse;
import se.solarplexusit.lexportlet.wsdl.GetDocument;
import se.solarplexusit.lexportlet.wsdl.GetDocumentResponse;
import se.solarplexusit.lexportlet.wsdl.GetMeeting;
import se.solarplexusit.lexportlet.wsdl.GetMeetingResponse;
import se.solarplexusit.lexportlet.wsdl.GetSubjectAreas;
import se.solarplexusit.lexportlet.wsdl.GetSubjectAreasResponse;
import se.solarplexusit.lexportlet.wsdl.ObjectFactory;
import se.solarplexusit.lexportlet.wsdl.Search;
import se.solarplexusit.lexportlet.wsdl.SearchPlus;
import se.solarplexusit.lexportlet.wsdl.SearchPlusResponse;
import se.solarplexusit.lexportlet.wsdl.SearchResponse;

/**
 * 
 */
public class GetLexService extends WebServiceGatewaySupport implements LexService
{
    private static final String SOAP_ACTION = "http://tempuri.org/IBsPublish/";
    private static final String GET_SUBJECT_AREAS = "GetSubjectAreas";
    private static final String SEARCH = "Search";
    private static final String SEARCH_PLUS = "SearchPlus";
    private static final String GET_CASE = "GetCase";
    private static final String GET_DOCUMENT = "GetDocument";
//    private static final String GET_DOCUMENT_XML = "GetDocumentXML";
    private static final String GET_MEETING = "GetMeeting";
    private static final String GET_DECISION = "GetDecision";
    private static final String FETCH_AMOUNT_MINIS = "minis";
    private static final String FETCH_AMOUNT_COMPLETE = "Complete";
//    private static final String DOCUMENT_FETCH_AMOUNT_COMPLETE_MINI = "Complete:mini";
    private static final String DOCUMENT_FETCH_AMOUNT_COMPLETE_FILE = "Complete:file";
    private UnMarshallers unmarshallers;
    private CacheManager cacheManager;
    

    public GetLexService(UnMarshallers unmarshallers)
    {
    	try {
	        this.unmarshallers = unmarshallers;
			// this.setDefaultUri(baseUrl);
			this.setMarshaller(unmarshallers.getWsdlMarshaller());
			this.setUnmarshaller(unmarshallers.getWsdlUnMarshaller());
			SaajSoapMessageFactory factory = new SaajSoapMessageFactory(MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL));
			this.setMessageFactory(factory);
    	}
    	catch (Exception e) {
			throw new RuntimeException(e);
    	}

        cacheManager = CacheManager.create();
        Cache subjectAreasMemCache = new Cache("subjectAreas", 1000, false, false, 3600, 0); // Max 100 objects. Live for 1 minute
        cacheManager.addCache(subjectAreasMemCache);	
    }
    
	public List<SubjectArea> getSubjectAreas() {
		
		List<SubjectArea> subjectAreas = new Vector<SubjectArea>();
		try {
            Cache cache = cacheManager.getCache("subjectAreas");
            Element fromCache = cache.get("key");
            if (fromCache != null)
            {
                return (List<SubjectArea>) fromCache.getObjectValue();
            }
            else
            {
					GetSubjectAreas request = new GetSubjectAreas();
					
					GetSubjectAreasResponse response = (GetSubjectAreasResponse)
							getWebServiceTemplate().marshalSendAndReceive(
							request,
							new SoapActionCallback(SOAP_ACTION+GET_SUBJECT_AREAS));
					
			        Source source = new StringSource(response.getGetSubjectAreasResult().getValue());
			
			        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
		
			        subjectAreas = (ArrayList<SubjectArea>) lex.getConfig().getSubjectAreas();
			        cache.put(new Element("key", subjectAreas));
			        return subjectAreas;
				
            }
		}
		catch (Exception e) {
			// logger.warn(e);
		}
		return subjectAreas;
	}
	
    public SearchResult search(SearchCriteria criteria)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
        	ObjectFactory factory = new ObjectFactory();        	
        	Search request = factory.createSearch();
        	
        	request.setInfoTypes(factory.createSearchInfoTypes(criteria.getInfoTypesToString()));
        	request.setSubjects(factory.createSearchSubjects(criteria.getSubjectareasToString()));
        	request.setFromDate(factory.createSearchFromDate(dateFormat.format(criteria.getFromDate())));
        	request.setToDate(factory.createSearchToDate(dateFormat.format(criteria.getToDate())));
        	request.setSearchText(factory.createSearchSearchText(criteria.getSearchText()));
        	request.setFetchAmount(factory.createSearchFetchAmount(FETCH_AMOUNT_MINIS));
        	
			SearchResponse response = (SearchResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+SEARCH));
			
	        Source source = new StringSource(response.getSearchResult().getValue());
        	
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
            SearchResult searchResult = new SearchResult();
            searchResult.setSearchResult(lex.getSearchResult());
	        
            return searchResult;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    /**
     * Används ej
     */
    public SearchResult searchPlus(SearchCriteria criteria)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        	ObjectFactory factory = new ObjectFactory();        	
        	SearchPlus request = factory.createSearchPlus();
        	
        	request.setInfoTypes(factory.createSearchPlusInfoTypes(criteria.getInfoTypesToString()));        	
        	request.setFromDate(factory.createSearchPlusFromDate(dateFormat.format(criteria.getFromDate())));
        	request.setToDate(factory.createSearchPlusToDate(dateFormat.format(criteria.getToDate())));
        	request.setSearchText(factory.createSearchPlusSearchText(criteria.getSearchText()));
        	request.setFetchAmount(factory.createSearchPlusFetchAmount(FETCH_AMOUNT_MINIS));
        	request.setDocType(factory.createSearchPlusDocType("")); //Medborgarförslag
        	request.setInfoTypeType(factory.createSearchPlusInfoTypeType(""));
        	
			SearchPlusResponse response = (SearchPlusResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+SEARCH_PLUS));
			
	        Source source = new StringSource(response.getSearchPlusResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
	        
            SearchResult searchResult = new SearchResult();
            searchResult.setSearchResult(lex.getSearchResult());
	        
            return searchResult;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    public Case getCase(String id)
    {
        try
        {
        	ObjectFactory factory = new ObjectFactory();        	
        	GetCase request = factory.createGetCase();
        	
        	request.setId(factory.createGetCaseId(id));
        	request.setFetchAmount(factory.createGetCaseFetchAmount(FETCH_AMOUNT_COMPLETE));
        	
			GetCaseResponse response = (GetCaseResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+GET_CASE));
			
	        Source source = new StringSource(response.getGetCaseResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
	        
	        // Hämta error om det finns
	        if (lex.getErrorItem() != null)
	        	throw new Exception(lex.getErrorItem().getMessage());
	        
	        Case caseItem = lex.getInformation().getCaseItem();
            return caseItem;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    public Document getDocument(String id)
    {
        try
        {
        	ObjectFactory factory = new ObjectFactory();        	
        	GetDocument request = factory.createGetDocument();
        	
        	request.setId(factory.createGetDocumentId(id));
        	request.setFetchAmount(factory.createGetDocumentFetchAmount(FETCH_AMOUNT_COMPLETE));
        	
			GetDocumentResponse response = (GetDocumentResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+GET_DOCUMENT));
			
	        Source source = new StringSource(response.getGetDocumentResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
	        
	        // Hämta error om det finns
	        if (lex.getErrorItem() != null)
	        	throw new Exception(lex.getErrorItem().getMessage());
	        
	        Document document = lex.getInformation().getDocumentItem();
            return document;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    public Document getDocumentFile(String id)
    {
        try
        {
        	ObjectFactory factory = new ObjectFactory();        	
        	GetDocument request = factory.createGetDocument();
        	
        	request.setId(factory.createGetDocumentId(id));
        	request.setFetchAmount(factory.createGetDocumentFetchAmount(DOCUMENT_FETCH_AMOUNT_COMPLETE_FILE));
        	
			GetDocumentResponse response = (GetDocumentResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+GET_DOCUMENT));
			
	        Source source = new StringSource(response.getGetDocumentResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);

	        // Hämta error om det finns
	        if (lex.getErrorItem() != null)
	        	throw new Exception(lex.getErrorItem().getMessage());
	        
	        Document document = lex.getInformation().getDocumentItem();
	        return document;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    public Meeting getMeeting(String id)
    {
        try
        {
        	ObjectFactory factory = new ObjectFactory();        	
        	GetMeeting request = factory.createGetMeeting();
        	
        	request.setId(factory.createGetMeetingId(id));
        	request.setFetchAmount(factory.createGetMeetingFetchAmount(FETCH_AMOUNT_COMPLETE));
        	
			GetMeetingResponse response = (GetMeetingResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+GET_MEETING));
			
	        Source source = new StringSource(response.getGetMeetingResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
	        
	        // Hämta error om det finns
	        if (lex.getErrorItem() != null)
	        	throw new Exception(lex.getErrorItem().getMessage());
	        
            Meeting meeting = lex.getInformation().getMeetingItem();
	        return meeting;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

    public Decision getDecision(String id)
    {
        try
        {
        	ObjectFactory factory = new ObjectFactory();        	
        	GetDecision request = factory.createGetDecision();
        	
        	request.setId(factory.createGetDecisionId(id));
        	request.setFetchAmount(factory.createGetDecisionFetchAmount(FETCH_AMOUNT_COMPLETE));
        	
			GetDecisionResponse response = (GetDecisionResponse)
					getWebServiceTemplate().marshalSendAndReceive(
					request,
					new SoapActionCallback(SOAP_ACTION+GET_DECISION));
			
	        Source source = new StringSource(response.getGetDecisionResult().getValue());
	        Lex lex = (Lex) unmarshallers.getLexUnMarshaller().unmarshal(source);
	        
	        // Hämta error om det finns
	        if (lex.getErrorItem() != null)
	        	throw new Exception(lex.getErrorItem().getMessage());
	        
	        Decision decision = lex.getInformation().getDecisionItem();
            return decision;
        }
        catch (Exception e)
        {
            throw new LexServiceException(e);
        }
    }

	@Override
	public void setBaseUrl(String baseUrl) {
		setDefaultUri(baseUrl);
	}

	@Override
	public String getBaseUrl() {
		return getDefaultUri();
	}
}
