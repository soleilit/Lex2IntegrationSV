package se.solarplexusit.lexportlet.dao;

import se.solarplexusit.lexportlet.dataobjects.SearchAdvice;

import java.util.List;

public interface SearchAdviceDao {
    List<SearchAdvice> findAll();

    void saveAll(List<SearchAdvice> advice);
}
