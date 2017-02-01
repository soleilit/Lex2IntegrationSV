package se.solarplexusit.support.spring;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.FaultMessageResolver;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapFaultMessageResolver;

import java.io.IOException;

public class LoggingSoapFaultMessageResolver implements FaultMessageResolver {
    private FaultMessageResolver soapFaultMessageResolver;

    public LoggingSoapFaultMessageResolver() {
        soapFaultMessageResolver = new SoapFaultMessageResolver();
    }

    public void resolveFault(WebServiceMessage webServiceMessage) throws IOException {
        try {
            soapFaultMessageResolver.resolveFault(webServiceMessage);
        } catch (SoapFaultClientException e) {
            //log.error("SoapFaultClientException: Code=" + e.getFaultCode().getLocalPart() + ".  Reason=" + e.getFaultStringOrReason());
            throw (e);
        }
    }
}
