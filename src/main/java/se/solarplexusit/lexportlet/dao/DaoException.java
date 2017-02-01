package se.solarplexusit.lexportlet.dao;

public class DaoException extends RuntimeException {
    public DaoException(Exception e) {
        super(e);
    }
}
