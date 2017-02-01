package se.solarplexusit.lexportlet.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;

import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.lexportlet.service.LexServiceUrlInjector;

public class ShowMeetingController extends ResultItemDetailsController {
    private LexService lexService;

    public ShowMeetingController(LexService lexService) {
        this.lexService = lexService;
    }

    @Override
    public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
        // Ett hack för att hämta konfig från SV!
    	LexServiceUrlInjector.injectServiceUrl(request, lexService);

    	String meetingId = PortletRequestUtils.getStringParameter(request, "meetingId");
        ModelAndView mav = new ModelAndView("showMeeting");
        mav.addObject("meeting", lexService.getMeeting(meetingId));
        mav.addAllObjects(referenceData(request));
        return mav;
    }
    
    
}
