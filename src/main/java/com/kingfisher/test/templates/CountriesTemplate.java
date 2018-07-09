package com.kingfisher.test.templates;

import com.kingfisher.test.file.templates.JsonTemplate;

public enum CountriesTemplate implements JsonTemplate {

    GET_ISO_CODE_RESPONSE("json.templates/get-iso-code-response.json");

    private final String path;

    CountriesTemplate(String path) {
        this.path = path;
    }

    @Override
    public String getTemplatePath() {
        return path;
    }


}
