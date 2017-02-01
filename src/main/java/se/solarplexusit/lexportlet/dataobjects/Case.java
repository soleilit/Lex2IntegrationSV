package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import se.solarplexusit.lexportlet.dataobjects.Util.Date;
import se.solarplexusit.lexportlet.dataobjects.Util.Diary;
import se.solarplexusit.lexportlet.dataobjects.Util.Type;

@XmlRootElement
public class Case extends ResultItem {
    
    private String diarienummer;
    private String type_name;
    private String modifiedSign_date;
    private String diary_name;          //diarium
    private String subjectArea_name;    //ämnesområde
    private String case_title;          //diarienummer
    private List<Document> documents =  new ArrayList<Document>(); // datum, beskrivning, filtyp, filstorlek)
    private List<Meeting> meetings =  new ArrayList<Meeting>();
    private List<Decision> decisions =  new ArrayList<Decision>();
    private String createdSign_date;  

    Type type;
    Diary diary;
    Date createdSignDate;
    Date modifiedSignDate;
    Date finalizedSignDate;
    private List<SubjectArea> subjectAreas =  new ArrayList<SubjectArea>();
        
    public boolean isCaseItem() {
        return true;
    }

    public void setCaseItem(boolean caseItem) {
     super.caseItem = true;
    }

    public boolean isDocumentItem() {
        return false;
    }

    public void setDocumentItem(boolean documentItem) {
        super.documentItem = false;
    }

    public boolean isMeetingItem() {
        return false;
    }

    public void setMeetingItem(boolean meetingItem) {
        super.meetingItem = false;
    }

    public boolean isSecrecy() {
        return super.secrecy;
    }

    public void setSecrecy(boolean secrecy) {
        super.secrecy = true;
    }

    public String getDiarienummer() {
        return diarienummer;
    }

    public void setDiarienummer(String diarienummer) {
        this.diarienummer = diarienummer;
    }

    
    public String getType_name() {
    	if (type_name==null && type!=null)
    		return type.getName();
    	else 
    		return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getModifiedSign_date() {
        return modifiedSign_date;
    }

    public void setModifiedSign_date(String modifiedSign_date) {
        this.modifiedSign_date = modifiedSign_date;
    }

    public String getDiary_name() {
    	if (diary_name==null && diary!=null)
    		return diary.getName();
    	else 
    		return diary_name;
    }

    public void setDiary_name(String diary_name) {
        this.diary_name = diary_name;
    }

    public String getSubjectArea_name() {
    	if (subjectArea_name==null && subjectAreas.size()>0)
    		return subjectAreas.get(0).getName();
    	else
    		return subjectArea_name;
    }

    public void setSubjectArea_name(String subjectArea_name) {
        this.subjectArea_name = subjectArea_name;
    }

    @XmlAttribute (name="title")
    public String getCase_title() {
        return case_title;
    }

    public void setCase_title(String case_title) {
        this.case_title = case_title;
        // diarienummer s�tts fr�n case_title vid mappning fr�n search
        this.diarienummer = case_title;
    }

    @XmlElementWrapper (name="documents")
    @XmlElement (name="document", type=Document.class)
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @XmlElementWrapper (name="meetings")
    @XmlElement (name="meeting", type=Meeting.class)
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getCreatedSign_date() {
    	if (createdSign_date==null && createdSignDate!=null)
    		return createdSignDate.getDate();
    	else
    		return createdSign_date;
    }

    public void setCreatedSign_date(String createdSign_date) {
        this.createdSign_date = createdSign_date;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getName() {
        return "�rende";
    }

    public String getCustomSort() {
        if(diarienummer != null) {
            String year = diarienummer.substring(diarienummer.indexOf('/') + 1, diarienummer.lastIndexOf(':'));
            String index = diarienummer.substring(diarienummer.lastIndexOf(':') + 1);
            return "3 " + year.length() + year + index.length() + index + "z";
        }
        return "3z";
    }

    public String getFile_format() {
        return "aaa";
    }

    public void setFile_format(String file_format) {
        super.file_format = "aaa";
    }


	@XmlElement
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlElement
	public Diary getDiary() {
		return diary;
	}

	public void setDiary(Diary diary) {
		this.diary = diary;
	}

	@XmlElement (name="createdSign")
	public Date getCreatedSignDate() {
		return createdSignDate;
	}

	public void setCreatedSignDate(Date createdSignDate) {
		this.createdSignDate = createdSignDate;
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

	@XmlElement (name="finalizedSign")
	public Date getFinalizedSignDate() {
		return finalizedSignDate;
	}

	public void setFinalizedSignDate(Date finalizedSignDate) {
		this.finalizedSignDate = finalizedSignDate;
	}

    @XmlElementWrapper (name="decisions")
    @XmlElement (name="decision", type=Decision.class)
	public List<Decision> getDecisions() {
		return decisions;
	}

	public void setDecisions(List<Decision> decisions) {
		this.decisions = decisions;
	}

}
