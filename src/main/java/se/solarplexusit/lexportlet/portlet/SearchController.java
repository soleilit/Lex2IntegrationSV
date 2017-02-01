package se.solarplexusit.lexportlet.portlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.mvc.SimpleFormController;

import se.solarplexusit.lexportlet.dao.SearchAdviceDao;
import se.solarplexusit.lexportlet.dataobjects.Case;
import se.solarplexusit.lexportlet.dataobjects.Document;
import se.solarplexusit.lexportlet.dataobjects.InfoType;
import se.solarplexusit.lexportlet.dataobjects.Meeting;
import se.solarplexusit.lexportlet.dataobjects.Owner;
import se.solarplexusit.lexportlet.dataobjects.ResultItem;
import se.solarplexusit.lexportlet.dataobjects.SearchCriteria;
import se.solarplexusit.lexportlet.dataobjects.SearchResult;
import se.solarplexusit.lexportlet.dataobjects.SubjectArea;
import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.lexportlet.service.LexServiceUrlInjector;
import se.solarplexusit.lexportlet.validation.SearchCriteriaValidator;

public class SearchController extends SimpleFormController implements MessageSourceAware
{
    private LexService lexService;
    private SearchAdviceDao searchAdviceDao;
    public static final String SEARCH_CRITERIA_SESSION_KEY = "searchCriteria";
    private MessageSource messageSource;
    private Map<String, String> infoTypes = new HashMap<String, String>();
    private Map<String, String> subjectAreas = new TreeMap();

    public SearchController(LexService lexService, SearchAdviceDao searchAdviceDao, MessageSource messageSource)
    {
        this.lexService = lexService;
        this.searchAdviceDao = searchAdviceDao;
        this.messageSource = messageSource;
        // Initialiserar infotypes
        infoTypes.put(InfoType.DOCUMENT.toString(), messageSource.getMessage("infoTypes.document", null, new Locale("sv")));
        infoTypes.put(InfoType.CASE.toString(), messageSource.getMessage("infoTypes.case", null, new Locale("sv")));
        infoTypes.put(InfoType.MEETING.toString(), messageSource.getMessage("infoTypes.meeting", null, new Locale("sv")));
        // Initialiserar subject areas
        // initAreas();
        
        this.setFormView("search");
        this.setSuccessView("search-result");

        this.setCommandClass(SearchCriteria.class);
        this.setCommandName("criteria");
        this.setValidator(new SearchCriteriaValidator());
        this.setBindingErrorProcessor(new DefaultBindingErrorProcessor()
        {
            public void processMissingFieldError(String field, BindingResult bindingResult)
            {
                if (field.equals("fromDate"))
                {
                    bindingResult.reject("field.fromDate.required");
                }
                else if (field.equals("toDate"))
                {
                    bindingResult.reject("field.toDate.required");
                }
            }

            public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult)
            {
                String field = ex.getPropertyChangeEvent().getPropertyName();
                if (field.equals("fromDate"))
                {
                    bindingResult.reject("field.fromDate.required");
                }
                else if (field.equals("toDate"))
                {
                    bindingResult.reject("field.toDate.required");
                }
            }
        });
    }

    private void initAreas() {
    	List<SubjectArea> subjectAreasTemp = new ArrayList<SubjectArea>();
        subjectAreasTemp = lexService.getSubjectAreas();
        subjectAreas = new TreeMap();
        for (SubjectArea subjectArea : subjectAreasTemp) {
			subjectAreas.put(subjectArea.getId(), subjectArea.getName());
		}
    }
    
 // Ett hack för att hämta konfig från SV!
    @Override
    public ModelAndView handleRenderRequest(RenderRequest request,
            RenderResponse response) throws Exception {
    	LexServiceUrlInjector.injectServiceUrl(request, lexService);
   		initAreas();
    	return super.handleRenderRequest(request, response);
    }
    
    @Override
    protected void initBinder(PortletRequest portletRequest, PortletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Override
    protected Object formBackingObject(PortletRequest portletRequest) throws Exception
    {
        if (portletRequest.getPortletSession(true).getAttribute(SEARCH_CRITERIA_SESSION_KEY) != null
                && portletRequest.getParameter("reset") == null)
        {
            return portletRequest.getPortletSession(true).getAttribute(SEARCH_CRITERIA_SESSION_KEY);
        }

        SearchCriteria criteria = new SearchCriteria();
        criteria.setFromDate(DateUtils.addDays(new Date(), -30));
        criteria.setToDate(new Date());

        return criteria;
    }

    @Override
    protected Map referenceData(PortletRequest portletRequest) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("searchAdvices", searchAdviceDao.findAll());
        
        map.put("infoTypes", infoTypes);
        
        map.put("subjectAreas", subjectAreas);
        
        return map;
    }

    @Override
    protected ModelAndView onSubmitRender(Object o) throws Exception
    {
        SearchCriteria criteria = (SearchCriteria) o;
        criteria.setInfoTypesText(infoTypes);
        criteria.setSubjectAreasText(subjectAreas);
        
        SearchResult result = lexService.search(criteria);

        List<ResultItem> newResultItems = new ArrayList<ResultItem>();
        for (ResultItem item : result.getSearchResult())
        {
            if (item.isDocumentItem() && ((Document) item).getOwners() != null)
            {
                boolean documentAdded = false;
                for (Owner owner : ((Document) item).getOwners())
                {
                    for (ResultItem item2 : result.getSearchResult())
                    {
                        if (owner.getType().equalsIgnoreCase("Case") && item2.isCaseItem()
                                && ((Case) item2).getDiarienummer().equals(owner.getName()))
                        {
                            if (item.getDate() == null)
                            {
                                ((Document) item).setCustomSort(item2.getCustomSort().substring(
                                        0, item2.getCustomSort().length() - 1) + " ");
                            }
                            else
                            {
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                long t = df.parse(item.getDate()).getTime();
                                ((Document) item).setCustomSort(
                                        item2.getCustomSort().substring(0, item2.getCustomSort().length() - 1)
                                        + " " + item.getDate());
                            }
                            newResultItems.add(item);
                            documentAdded = true;
                            break;
                        }
                        else if (owner.getType().equalsIgnoreCase("Meeting") && item2.isMeetingItem()
                                && ((Meeting) item2).getDiarienummer().equals(
                                owner.getName()))
                        {
                            if (item.getDate() == null)
                            {
                                ((Document) item).setCustomSort(item2.getCustomSort().substring(
                                        0, item2.getCustomSort().length() - 1) + " ");
                            }
                            else
                            {
                                ((Document) item).setCustomSort(
                                        item2.getCustomSort().substring(0, item2.getCustomSort().length() - 1)
                                        + " " + item.getDate());
                            }
                            newResultItems.add(item);
                            documentAdded = true;
                            break;
                        }
                    }
                }

                if (!documentAdded)
                {
                    ((Document) item).setCustomSort("1 " + (item.getDate() != null ? item.getDate() : "") + " ");
                    newResultItems.add(item);
                }
            }
            else
            {
                newResultItems.add(item);
            }
        }

        result.setSearchResult(newResultItems);
        
        ModelAndView mav = new ModelAndView(getSuccessView());
        mav.addObject(SEARCH_CRITERIA_SESSION_KEY, criteria);
        mav.addObject("result", result);
        return mav;
    }

    @Override
    protected ModelAndView onSubmitRender(RenderRequest renderRequest, RenderResponse renderResponse, Object o,
            BindException e) throws Exception
    {
        ModelAndView mav = super.onSubmitRender(renderRequest, renderResponse, o, e);
        PortletSession session = renderRequest.getPortletSession(true);
        session.setAttribute(SEARCH_CRITERIA_SESSION_KEY, o);
        return mav;
    }

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
