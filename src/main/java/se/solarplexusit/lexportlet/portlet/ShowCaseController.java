package se.solarplexusit.lexportlet.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;

import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.lexportlet.service.LexServiceUrlInjector;

public class ShowCaseController extends ResultItemDetailsController {
    private LexService lexService;

    public ShowCaseController(LexService lexService) {
        this.lexService = lexService;
    }

    @Override
    public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
    	// Ett hack för att få konfig från SV!
    	LexServiceUrlInjector.injectServiceUrl(request, lexService);

        String caseId = PortletRequestUtils.getStringParameter(request, "caseId");
        ModelAndView mav = new ModelAndView("showCase");
        mav.addObject("theCase", lexService.getCase(caseId));
        mav.addAllObjects(referenceData(request));
        return mav;
    }
    
    
}
