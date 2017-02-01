package se.solarplexusit.lexportlet.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="search-advice", namespace="LeXml.xsd")
public class SearchAdvice {
    private String header = "";
    private String text = "";

    @XmlElement (namespace="LeXml.xsd")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @XmlElement (namespace="LeXml.xsd")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getTextWithHtmlBreaks()
    {
        return (this.text != null ? text.replaceAll("\r\n", "<br />") : "");
    }
}
