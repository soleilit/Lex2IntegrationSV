package se.solarplexusit.lexportlet.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.XmlMappingException;

import se.solarplexusit.lexportlet.dao.DaoException;
import se.solarplexusit.lexportlet.dao.SearchAdviceDao;
import se.solarplexusit.lexportlet.dataobjects.SearchAdvice;
import se.solarplexusit.lexportlet.dataobjects.Util.SearchAdviceWrapper;
import se.solarplexusit.lexportlet.service.impl.UnMarshallers;

public class FileSystemSearchAdviceDao implements SearchAdviceDao {
    private final File configFile;
    private UnMarshallers unmarshallers;
    private List<SearchAdvice> searchAdvice;
    private final Object lock = new Object();

    public FileSystemSearchAdviceDao(String configDir, UnMarshallers unmarshallers) {
        if (!configDir.endsWith(File.separator)) {
            configDir += File.separator;
        }
        configFile = new File(configDir + "search-advice.xml");
        if (fileExistsButIsNotWritable(configFile)) {
            throw new IllegalArgumentException("Config file " + configFile + " is not writable");
        } else if (fileExistsButIsNotWritable(configFile.getParentFile())) {
            throw new IllegalArgumentException("Config file directory " + configFile.getParent() + " is not writable");
        }
        this.unmarshallers = unmarshallers;
    }

    public List<SearchAdvice> findAll() {
        synchronized (lock) {
            if (searchAdvice == null) {
                try {
                    loadSearchAdvice();
                } catch (Exception e) {
                    throw new DaoException(e);
                }
            }
        }
        return searchAdvice;
    }

    public void saveAll(List<SearchAdvice> advice) {
    	/*
        synchronized (lock) {
            try {
                Marshaller.marshal(advice, new FileWriter(configFile));
                this.searchAdvice = advice;
            } catch (Exception e) {
                throw new DaoException(e);
            }
        }
        */
    	//TODO! Implement
    	return;
    }

    private boolean fileExistsButIsNotWritable(File file) {
        return file.exists() && !file.canWrite();
    }

    private void loadSearchAdvice() throws XmlMappingException, FileNotFoundException {
        synchronized (lock) {
            if (configFile.exists()) {
    	    	FileInputStream file = new FileInputStream(configFile);
    	    	SearchAdviceWrapper wrapper = (SearchAdviceWrapper) unmarshallers.getSearchAdviceUnMarshaller().unmarshal(new StreamSource(file));
    	    	searchAdvice = wrapper.getSearchAdviceList();
            } else {
                searchAdvice = createFallbackAdvice();
            }
        }
    }

    private List<SearchAdvice> createFallbackAdvice() {
        List<SearchAdvice> advice = new ArrayList<SearchAdvice>();
        advice.add(new SearchAdvice());
        advice.add(new SearchAdvice());
        advice.add(new SearchAdvice());
        advice.add(new SearchAdvice());
        return advice;
    }

}
