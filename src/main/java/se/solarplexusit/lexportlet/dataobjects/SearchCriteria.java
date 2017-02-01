package se.solarplexusit.lexportlet.dataobjects;

import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.util.Map;
import java.io.Serializable;

public class SearchCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
//    private List<InfoType> infoTypes = Collections.emptyList();
//    private List<SubjectArea> subjectAreas = Collections.emptyList();
    private Date fromDate;
    private Date toDate;
    private String searchText;

    private List<String> infoTypes = Collections.emptyList();
    private List<String> subjectAreas = Collections.emptyList();
    private String infoTypesText;
    private String subjectAreasText;

    public SearchCriteria() {
    }
    
/*
    public List<InfoType> getInfoTypes() {
        return infoTypes;
    }

    public void setInfoTypes(List<InfoType> infoTypes) {
        if (infoTypes == null) {
            throw new NullPointerException("InfoTypes can not be null");
        }
        this.infoTypes = infoTypes;
    }

    public List<SubjectArea> getSubjectAreas() {
        return subjectAreas;
    }

    public void setSubjectAreas(List<SubjectArea> subjectAreas) {
        if (subjectAreas == null) {
            throw new NullPointerException("SubjectAreas can not be null");
        }
        this.subjectAreas = subjectAreas;
    }
*/
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

	public List<String> getInfoTypes() {
		return infoTypes;
	}

	public String getInfoTypesToString() {
		String text = "";
		for (String infoType : infoTypes) {
				if (!text.equals("")) 
					text += ",";
				text += infoType; 
		}
		return text;
	}

	public void setInfoTypes(List<String> infoTypes) {
		this.infoTypes = infoTypes;
	}
	public List<String> getSubjectAreas() {
		return subjectAreas;
	}
	public String getSubjectareasToString() {
		String text = "";
		for (String sujectArea : subjectAreas) {
				if (!text.equals("")) 
					text += ",";
				text += sujectArea; 
		}
		return text;
	}	
	public void setSubjectAreas(List<String> subjectAreas) {
		if (subjectAreas!=null)
			this.subjectAreas = subjectAreas;
		else
			this.subjectAreas = Collections.emptyList();
	}

	public String getInfoTypesText() {
		return infoTypesText;
	}

	public void setInfoTypesText(Map<String, String> infoTypesMap) {
		infoTypesText = "";
		for (String infoType : infoTypes) {
			if (!infoTypesText.equals("")) 
				infoTypesText += ", "; 
			infoTypesText += (String) infoTypesMap.get(infoType);
		}
	}

	public String getSubjectAreasText() {
		return subjectAreasText;
	}

	public void setSubjectAreasText(Map<String, String> subjectAreasMap) {
		subjectAreasText = "";
		if (!subjectAreas.isEmpty()) {
			for (String subjectArea : subjectAreas) {
				if (!subjectAreasText.equals("")) 
					subjectAreasText += ", "; 
				subjectAreasText += (String) subjectAreasMap.get(subjectArea);
			}
		}
	}
}
