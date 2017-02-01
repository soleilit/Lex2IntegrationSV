package se.solarplexusit.lexportlet.portlet;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import org.springframework.web.portlet.mvc.AbstractController;

public class ResultItemDetailsController extends AbstractController {

    protected Map referenceData(RenderRequest request) {
        PortletSession session = request.getPortletSession();
        Map<String, Object> map = new HashMap<String, Object>();
        if (session != null) {
            Object searchCriteria = session.getAttribute(SearchController.SEARCH_CRITERIA_SESSION_KEY);
            if (searchCriteria != null) {
                map.put("searchCriteria", searchCriteria);
            }
        }
        return map;
    }
}
