package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class Util {

    @XmlType
    public static class Type {
    	private String id;
    	private String name;
    	@XmlAttribute
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;			
		}
		@XmlAttribute
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;			
		}
    }

    @XmlType
    public static class Diary {
    	private String id;
    	private String name;
    	@XmlAttribute
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;			
		}
		@XmlAttribute
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;			
		}
    }

    @XmlType
    public static class Date {
    	private String date;
    	private String by;
    	@XmlAttribute
    	public String getDate() {
    		return date;
    	}
    	public void setDate(String date) {
    		this.date = date;
    	}
    	@XmlAttribute
		public String getBy() {
			return by;
		}
		public void setBy(String by) {
			this.by = by;
		}

    }
    
    @XmlType
    public static class File {
    	private String format;
    	private String size;
    	private String sizeType;
    	private byte[] data;
    	
		@XmlAttribute
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
		@XmlAttribute
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		@XmlAttribute
		public String getSizeType() {
			return sizeType;
		}
		public void setSizeType(String sizeType) {
			this.sizeType = sizeType;
		}
		@XmlElement
		public byte[] getData() {
			return data;
		}
		public void setData(byte[] data) {
			this.data = data;
		}
	    @Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	    }

    }
    
    @XmlType
    public static class Owners {
    	private Owner owner;

		public Owner getOwner() {
			return owner;
		}

		public void setOwner(Owner owner) {
			this.owner = owner;
		}
    }
    
    @XmlType
    public static class Config {
        private List<SubjectArea> subjectAreas =  new ArrayList<SubjectArea>();

        @XmlElementWrapper (name="subjectAreas")
        @XmlElement (name="subjectArea", type=SubjectArea.class)
		public List<SubjectArea> getSubjectAreas() {
			return subjectAreas;
		}

		public void setSubjectAreas(List<SubjectArea> subjectAreas) {
			this.subjectAreas = subjectAreas;
		}
    }
    
    @XmlRootElement (name="array-list", namespace="LeXml.xsd")
    public static class SearchAdviceWrapper {
        private List<SearchAdvice> searchAdviceList =  new ArrayList<SearchAdvice>();

        @XmlElement (name="search-advice", type=SearchAdvice.class, namespace="LeXml.xsd")
		public List<SearchAdvice> getSearchAdviceList() {
			return searchAdviceList;
		}

		public void setSearchAdviceList(List<SearchAdvice> searchAdviceList) {
			this.searchAdviceList = searchAdviceList;
		}
    }
    
}
