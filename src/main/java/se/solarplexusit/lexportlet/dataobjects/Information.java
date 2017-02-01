package se.solarplexusit.lexportlet.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Information {
	Case caseItem;
	Document documentItem;
	Meeting meetingItem;
	Decision decisionItem;

	@XmlElement (name="case")
	public Case getCaseItem() {
		return caseItem;
	}

	public void setCaseItem(Case caseItem) {
		this.caseItem = caseItem;
	}

	@XmlElement (name="document")
	public Document getDocumentItem() {
		return documentItem;
	}

	public void setDocumentItem(Document documentItem) {
		this.documentItem = documentItem;
	}

	@XmlElement (name="meeting")
	public Meeting getMeetingItem() {
		return meetingItem;
	}

	public void setMeetingItem(Meeting meetingItem) {
		this.meetingItem = meetingItem;
	}

	@XmlElement (name="decision")
	public Decision getDecisionItem() {
		return decisionItem;
	}

	public void setDecisionItem(Decision decisionItem) {
		this.decisionItem = decisionItem;
	}
}
