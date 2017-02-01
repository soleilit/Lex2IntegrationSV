package se.solarplexusit.lexportlet.dataobjects;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Topic {
    private String id;
    private String number;
    private String title;
    private List<Document> documents = new ArrayList<Document>();
    private List<Case> cases = new ArrayList<Case>();
    private List<Decision> decisions = new ArrayList<Decision>();
    private String sortOrder;

    private String titleAttribute;

    @XmlAttribute
    public String getNumber() {
        return number;
    }

    public int getIntNumber() {
        if (StringUtils.isBlank(number)) return Integer.MIN_VALUE;
        if (number.startsWith("§")) {
            return Integer.parseInt(number.substring(1));
        } else {
            return Integer.parseInt(number);
        }
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlElement
    public String getTitle() {
    	if (title==null)
    		return titleAttribute;
    	else
    		return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @XmlElementWrapper (name="documents")
    @XmlElement (name="document", type=Document.class)
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @XmlElementWrapper (name="cases")
    @XmlElement (name="case", type=Case.class)
    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    @XmlElementWrapper (name="decisions")
    @XmlElement (name="decision", type=Decision.class)
    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }

    @XmlElement
    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @XmlAttribute (name="title")
	public String getTitleAttribute() {
		return titleAttribute;
	}

	public void setTitleAttribute(String titleAttribute) {
		this.titleAttribute = titleAttribute;
	}
}
