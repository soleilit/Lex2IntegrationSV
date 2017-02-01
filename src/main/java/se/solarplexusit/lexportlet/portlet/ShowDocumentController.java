package se.solarplexusit.lexportlet.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.PortletRequestUtils;

import se.solarplexusit.lexportlet.service.LexService;
import se.solarplexusit.lexportlet.service.LexServiceUrlInjector;


public class ShowDocumentController extends ResultItemDetailsController {
    private LexService lexService;

    public ShowDocumentController(LexService lexService) {
        this.lexService = lexService;
    }

    @Override
    public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
        // Ett hack för att få konfig från SV!
    	LexServiceUrlInjector.injectServiceUrl(request, lexService);

    	String documentId = PortletRequestUtils.getStringParameter(request, "documentId");
        ModelAndView mav = new ModelAndView("showDocument");
        mav.addObject("document", lexService.getDocument(documentId));
        mav.addAllObjects(referenceData(request));
        return mav;
    }
        
}
