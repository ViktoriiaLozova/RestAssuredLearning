package com.kingfisher.test.homework;

import com.kingfisher.test.MyProjectTestCase;
import com.kingfisher.test.asserts.matchers.ResponseMatchers;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeVerification extends MyProjectTestCase{

    @Test
    public void statusCodeVerification() {
        keywordManager
                .countries().getIsoCode("AL");
        Response response = whatIsTheRestAssuredResponse();
        Assert.assertTrue(isStatusCodeCorrect(response));

        keywordManager
                .expectStatusCode(200, () -> keywordManager
                        .countries().getIsoCode("BB"));

        keywordManager
                .expectStatusCode(404, () -> keywordManager
                        .countries().getIncorrectIsoCode("BB"));
    }

    @Test
    public void filterCountries() {
        keywordManager
                .countries().getAllCountriesCodes();
        Response allCountriesResponse = whatIsTheRestAssuredResponse();
        allCountriesResponse.then().body("RestResponse.result.findAll {it.alpha3_code == 'UKR'}", Matchers.hasSize(1));
    }

    private boolean isStatusCodeCorrect(Response response) {
        return response.statusCode() == 200;
    }


}
