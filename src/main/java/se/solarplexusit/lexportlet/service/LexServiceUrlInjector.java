package se.solarplexusit.lexportlet.service;

import javax.portlet.PortletRequest;

import senselogic.sitevision.api.Utils;
import senselogic.sitevision.api.context.PortletContextUtil;
import senselogic.sitevision.api.property.PropertyUtil;

public class LexServiceUrlInjector {

	public static void injectServiceUrl(PortletRequest request, LexService lexService) {
    	Object object = request.getAttribute("sitevision.utils");
    	if (object instanceof Utils) {
    		Utils utils = (Utils)object;
    		PortletContextUtil portletContextUtil = utils.getPortletContextUtil();
        	PropertyUtil propertyUtil = utils.getPropertyUtil();
        	String baseUrl = propertyUtil.getString(portletContextUtil.getCurrentPage(), "lex_service_url");
            if (baseUrl == null) {
            	throw new IllegalStateException("Metadata 'lex_service_url' saknas");
            }
            lexService.setBaseUrl(baseUrl);
    	}
	}
	
}
