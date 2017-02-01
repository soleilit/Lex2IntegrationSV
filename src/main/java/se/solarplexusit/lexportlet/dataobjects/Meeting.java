package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import se.solarplexusit.lexportlet.dataobjects.Util.Date;
import se.solarplexusit.lexportlet.dataobjects.Util.Type;

@XmlRootElement
public class Meeting extends ResultItem{

    private String title;
    private String unit;
    private String punktNr; //för sortering?
    private String punktRubrik; //för sortering?
    private String diarium; // endast i search
    private List<Topic> topics =  new ArrayList<Topic>();
    private String modifiedSign_date;
    private String modifiedSign_by;     
    private List<Document> documents =  new ArrayList<Document>();
    private List<Case> cases =  new ArrayList<Case>();
    private List<Decision> decisions =  new ArrayList<Decision>();
    
    Type type;
    Date createdSignDate;
    Date modifiedSignDate;
    // Endast i search, sätter diarium	
    private List<SubjectArea> subjectAreas =  new ArrayList<SubjectArea>();

    public boolean isCaseItem() {
        return false;
    }

    public void setCaseItem(boolean caseItem) {
     super.caseItem = false;
    }

    public boolean isDocumentItem() {
        return false;
    }

    public void setDocumentItem(boolean documentItem) {
        super.documentItem = false;
    }

    public boolean isMeetingItem() {
        return true;
    }

    public void setMeetingItem(boolean meetingItem) {
        super.meetingItem = true;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        // diarienummer sätts från title vid mappning från search
        this.diarienummer = title;
    }

    @XmlAttribute
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlElementWrapper (name="topics")
    @XmlElement (name="topic", type=Topic.class)
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getModifiedSign_date() {
    	if (modifiedSign_date==null && modifiedSignDate!=null)
    		return modifiedSignDate.getDate();
    	else
    		return modifiedSign_date;
    }

    public void setModifiedSign_date(String modifiedSign_date) {
        this.modifiedSign_date = modifiedSign_date;
    }

    public String getModifiedSign_by() {
    	if (modifiedSign_by==null && modifiedSignDate!=null)
    		return modifiedSignDate.getBy();
    	else
    		return modifiedSign_by;
    }

    public void setModifiedSign_by(String modifiedSign_by) {
        this.modifiedSign_by = modifiedSign_by;
    }

    public String getPunktNr() {
        return punktNr;
    }

    public void setPunktNr(String punktNr) {
        this.punktNr = punktNr;
    }

    public String getPunktRubrik() {
        return punktRubrik;
    }

    public void setPunktRubrik(String punktRubrik) {
        this.punktRubrik = punktRubrik;
    }


    public boolean isSecrecy() {
        return super.secrecy;
    }

    public void setSecrecy(boolean secrecy) {
        super.secrecy = true;
    }

    public String getDiarium() {
    	// diarium sätts från subjectarea name vid mappning från search
    	if (diarium==null && subjectAreas.size()>0)
    		return subjectAreas.get(0).getName();
    	else
    		return diarium;
    }

    public void setDiarium(String diarium) {
        this.diarium = diarium;
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

    public String getName() {
        return "Sammantr�de";
    }

    public String getCustomSort() {
        return "2 " + getDate() + "z";
    }

    public String getFile_format() {
        return "aaa";
    }

    public void setFile_format(String file_format) {
        super.file_format = "aaa";
    }

	@XmlElement (name="type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlElement (name="modifiedSign")
	public Date getModifiedSignDate() {
		return modifiedSignDate;
	}

	public void setModifiedSignDate(Date modifiedSignDate) {
		this.modifiedSignDate = modifiedSignDate;
	}

    @XmlElementWrapper (name="subjectAreas")
    @XmlElement (name="subjectArea", type=SubjectArea.class)
	public List<SubjectArea> getSubjectAreas() {
		return subjectAreas;
	}

	public void setSubjectAreas(List<SubjectArea> subjectAreas) {
		this.subjectAreas = subjectAreas;
	}

	@XmlElement (name="createdSign")
	public Date getCreatedSignDate() {
		return createdSignDate;
	}

	public void setCreatedSignDate(Date createdSignDate) {
		this.createdSignDate = createdSignDate;
	}
}

