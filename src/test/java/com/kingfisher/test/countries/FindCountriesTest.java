package com.kingfisher.test.countries;


import com.beust.jcommander.internal.Maps;
import com.kingfisher.test.MyProjectTestCase;
import com.kingfisher.test.asserts.util.AssertJson;
import com.kingfisher.test.file.FileSearch;
import com.kingfisher.test.templates.CountriesTemplate;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

public class FindCountriesTest extends MyProjectTestCase {

    @Test
    public void checkTotalRecordsAndAlbaniaThird() {

        keywordManager.countries().getAllCountriesCodes();

        Response response = whatIsTheRestAssuredResponse();

        response
                .then().body("RestResponse.messages[0]", Matchers.equalTo("Total [249] records found."))
                .and().body("RestResponse.result[2].name", Matchers.equalTo("Albania"));

    }

    @Test
    public void checkAlbaniaIsPresent() {

        Map<String, String> givenValues = Maps.newHashMap("name", "Albania", "alpha2_code", "AL", "alpha3_code", "ALB");

        keywordManager.countries().getIsoCode("AL");

        Response response = whatIsTheRestAssuredResponse();

        String actualResponse = response.asString();
        String expectedResponse = FileSearch.findJsonTemplate(CountriesTemplate.GET_ISO_CODE_RESPONSE)
                .resolveParamsWithGivenValues(givenValues)
                .buildAsString();

        AssertJson.assertJsonEquals(actualResponse, expectedResponse);

    }

    @Test
    public void wrongJsonToShowDiffInReport() {

        Map<String, String> givenValues = Maps.newHashMap("name", "Alabania", "alpha2_code", "ALL", "alpha3_code", "ALB");

        keywordManager.countries().getIsoCode("AL");

        Response response = whatIsTheRestAssuredResponse();

        String actualResponse = response.asString();
        String expectedResponse = FileSearch.findJsonTemplate(CountriesTemplate.GET_ISO_CODE_RESPONSE)
                .resolveParamsWithGivenValues(givenValues)
                .buildAsString();

        AssertJson.assertJsonEquals(actualResponse, expectedResponse);

    }
}
