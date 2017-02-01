package se.solarplexusit.lexportlet.validation;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;


public class LexValidationEventHandler implements ValidationEventHandler {

	@Override
	public boolean handleEvent(ValidationEvent event) {
		StringBuffer strBuff = new StringBuffer();
		strBuff.
			append("EVENT").
			append("\nSEVERITY:  " + event.getSeverity()).
			append("\nMESSAGE:  " + event.getMessage()).
			append("\nLINKED EXCEPTION:  " + event.getLinkedException()).
			append("\nLOCATOR").
			append("\n    LINE NUMBER:  " + event.getLocator().getLineNumber()).
			append("\n    COLUMN NUMBER:  " + event.getLocator().getColumnNumber()).
			append("\n    OFFSET:  " + event.getLocator().getOffset()).
			append("\n    OBJECT:  " + event.getLocator().getObject()).
			append("\n    NODE:  " + event.getLocator().getNode()).
			append("\n    URL:  " + event.getLocator().getURL());
		return true;
		}
}
