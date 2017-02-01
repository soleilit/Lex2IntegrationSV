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

import se.solarplexusit.lexportlet.dataobjects.Util.Type;

@XmlRootElement
public class Decision {
    private String id;
    private String unit;              //Enhet
    private String date;              //Sammanträdesdatum
    private String punktnr;
    private String punktrubrik;
    private String beslutstext;       //Beslutstext
    private String description;
    
    private Owners owners; 
    Type type;
    
    @XmlType
    private static class Owners {
        private List<Topic> topics =  new ArrayList<Topic>();
        private Owner owner;
        
        @XmlElementWrapper (name="topics")
        @XmlElement (name="topic", type=Topic.class)
    	public List<Topic> getTopics() {
    		return topics;
    	}

    	public void setTopics(List<Topic> topics) {
    		this.topics = topics;
    	}
    	
        @XmlElement
		public Owner getOwner() {
			return owner;
		}

		public void setOwner(Owner owner) {
			this.owner = owner;
		}

    }

    
    @XmlAttribute
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlAttribute
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPunktnr() {
    	if (punktnr==null && owners.getTopics().size()>0)
    		return owners.getTopics().get(0).getNumber();
    	else
    		return punktnr;
    }

    public void setPunktnr(String punktnr) {
        this.punktnr = punktnr;
    }

    public String getPunktrubrik() {
    	if (punktnr==null && owners.getTopics().size()>0)
    		return owners.getTopics().get(0).getTitle();
    	else
    		return punktrubrik;
    }

    public void setPunktrubrik(String punktrubrik) {
        this.punktrubrik = punktrubrik;
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

    @XmlElement (name="decisionText")
    public String getBeslutstext() {
        return beslutstext;
    }

    public void setBeslutstext(String beslutstext) {
        this.beslutstext = beslutstext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
	public Owners getOwners() {
		return owners;
	}

	public void setOwners(Owners owners) {
		this.owners = owners;
	}

	@XmlElement
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
