package com.kingfisher.test.kdt;

import com.kingfisher.test.kdt.dictionary.CountriesKeywords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyProjectKeywordManager extends BaseKeywordManager<MyProjectKeywordManager> {

    private static Logger log = LoggerFactory.getLogger(MyProjectKeywordManager.class);
    private final CountriesKeywords countriesKeywords = new CountriesKeywords(this, log);

    public CountriesKeywords countries() {
        return countriesKeywords;
    }
}
