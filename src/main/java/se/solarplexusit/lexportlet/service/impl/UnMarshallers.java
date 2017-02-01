package se.solarplexusit.lexportlet.service.impl;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class UnMarshallers {
    private Jaxb2Marshaller lexUnMarshaller;
    private Jaxb2Marshaller searchAdviceUnMarshaller;
    private Jaxb2Marshaller wsdlMarshaller;
    private Jaxb2Marshaller wsdlUnMarshaller;

    public Jaxb2Marshaller getLexUnMarshaller() {
		return lexUnMarshaller;
	}

	public void setLexUnMarshaller(Jaxb2Marshaller lexUnMarshaller) {
		this.lexUnMarshaller = lexUnMarshaller;
	}

	public Jaxb2Marshaller getSearchAdviceUnMarshaller() {
		return searchAdviceUnMarshaller;
	}

	public void setSearchAdviceUnMarshaller(Jaxb2Marshaller searchAdviceUnMarshaller) {
		this.searchAdviceUnMarshaller = searchAdviceUnMarshaller;
	}

	public Jaxb2Marshaller getWsdlMarshaller() {
		return wsdlMarshaller;
	}

	public void setWsdlMarshaller(Jaxb2Marshaller wsdlMarshaller) {
		this.wsdlMarshaller = wsdlMarshaller;
	}

	public Jaxb2Marshaller getWsdlUnMarshaller() {
		return wsdlUnMarshaller;
	}

	public void setWsdlUnMarshaller(Jaxb2Marshaller wsdlUnMarshaller) {
		this.wsdlUnMarshaller = wsdlUnMarshaller;
	}
}
