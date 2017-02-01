package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import se.solarplexusit.lexportlet.dataobjects.Util.Date;
import se.solarplexusit.lexportlet.dataobjects.Util.Diary;
import se.solarplexusit.lexportlet.dataobjects.Util.File;
import se.solarplexusit.lexportlet.dataobjects.Util.Type;

@XmlRootElement
public class Document extends ResultItem {
    private String diary_name;         //diarium
    private List<Owner> owners;
    private String file_size;          //filStorlek
    private String file_sizeType;
    private String createdSign_date;
    private byte[] data;
    private String type_name;
    private String companyname;
    private String customSort;
    
    Type type;
    Diary diary;
    Date createdSignDate;
    Date finalizedSignDate;
    Date modifiedSignDate;
    File file;
    private List<SubjectArea> subjectAreas =  new ArrayList<SubjectArea>();
    
    public boolean isFileAvailable() {
        return getFile_format() != null;
    }

    public boolean isCaseItem() {
        return false;
    }

    public void setCaseItem(boolean caseItem) {
        super.caseItem = false;
    }

    public boolean isDocumentItem() {
        return true;
    }

    public void setDocumentItem(boolean documentItem) {
        super.documentItem = true;
    }

    public boolean isMeetingItem() {
        return false;
    }

    public void setMeetingItem(boolean meetingItem) {
        super.meetingItem = false;
    }


    public String getFile_format() {
    	if (file_format==null && file!=null)
    		return file.getFormat();
    	else
    		return file_format;
    }

    public void setFile_format(String file_format) {
        this.file_format = file_format;
    }

    public String getFile_size() {
    	if (file_size==null && file!=null)
    		return file.getSize();
    	else
    		return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getFile_sizeType() {
    	if (file_sizeType==null && file!=null)
    		return file.getSizeType();
    	else
    		return file_sizeType;
    }

    public void setFile_sizeType(String file_sizeType) {
        this.file_sizeType = file_sizeType;
    }

    public String getDiary_name() {
    	if (diary_name==null && diary!=null)
    		return diary.getName();
    	// diary_name sätts från subject area vid mappning från search
    	else if (subjectAreas.size()>0)
    		return subjectAreas.get(0).getName();
    	else
    		return diary_name;
    }

    public void setDiary_name(String diary_name) {
        this.diary_name = diary_name;
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

    public boolean isSecrecy() {
        return super.secrecy;
    }

    public void setSecrecy(boolean secrecy) {
        super.secrecy = true;
    }

    public byte[] getData() {
    	if (data==null && file!=null)
    		return file.getData();
    	else
    		return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        String fileName = "Unknown";
        if (StringUtils.isNotBlank(description)) {
            if (description.length() > 15) {
                fileName = description.substring(0, 15);
            } else {
                fileName = description;
            }
        }
        if (StringUtils.isNotBlank(getFile_format()) && !fileName.endsWith(getFile_format())) {
            fileName += "." + getFile_format();
        }

        return fileName;

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

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @XmlElementWrapper (name="owners")
    @XmlElement (name="owner", type=Owner.class)
    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
        // diarienummer s�tts fr�n owners name vid mappning fr�n search
        if (owners.size()>0)
        	this.diarienummer = owners.get(0).getName();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getName() {
        return "Dokument";
    }

    public String getCustomSort() {
        return customSort;
    }

    public void setCustomSort(String customSort) {
        this.customSort = customSort;
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

    
	@XmlElement
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@XmlElement (name="finalizedSign")
	public Date getFinalizedSignDate() {
		return finalizedSignDate;
	}

	public void setFinalizedSignDate(Date finalizedSignDate) {
		this.finalizedSignDate = finalizedSignDate;
		// date s�tts fr�n finalizedSignDate vid mappning fr�n search
		if (this.getDate()==null)
			this.setDate(finalizedSignDate.getDate()); 
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
	
}
