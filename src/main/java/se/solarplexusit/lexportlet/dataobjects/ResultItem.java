package se.solarplexusit.lexportlet.dataobjects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement
public abstract class ResultItem {
    boolean caseItem;
    boolean documentItem;
    boolean meetingItem;
    private String id;
    protected String description;
    private String date;
    boolean secrecy = false;
    protected String diarienummer;
    protected String file_format;

    public abstract String getCustomSort();

    public abstract String getName();

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public abstract boolean isCaseItem();

    public abstract void setCaseItem(boolean caseItem);

    public abstract boolean isDocumentItem();

    public abstract void setDocumentItem(boolean documentItem);

    public abstract boolean isMeetingItem();

    public abstract void setMeetingItem(boolean meetingItem);

    public abstract boolean isSecrecy();

    public abstract void setSecrecy(boolean secrecy);

    public String getDiarienummer() {
        return diarienummer;
    }

    public void setDiarienummer(String diarienummer) {
        this.diarienummer = diarienummer;
    }

}
