package se.solarplexusit.lexportlet.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement
public class SearchResult {
    private List<ResultItem> searchResult =  new ArrayList<ResultItem>();

    @XmlAnyElement (lax=true)
    public List<ResultItem> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<ResultItem> searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
}
