package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import se.solarplexusit.lexportlet.dataobjects.Util.Config;

@XmlRootElement
public class Lex {
	private Information information;
	private Config config;
    private List<ResultItem> searchResult =  new ArrayList<ResultItem>();
	LexError errorItem;

	@XmlElement
	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	@XmlElement
	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

    @XmlElementWrapper (name="infoSet")
    @XmlElements({
        @XmlElement(name="meeting", type=Meeting.class),
        @XmlElement(name="document", type=Document.class),
        @XmlElement(name="case", type=Case.class)
    })
	public List<ResultItem> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<ResultItem> searchResult) {
		this.searchResult = searchResult;
	}


	@XmlElement (name="error")
	public LexError getErrorItem() {
		return errorItem;
	}

	public void setErrorItem(LexError errorItem) {
		this.errorItem = errorItem;
	}
}
