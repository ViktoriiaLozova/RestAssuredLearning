package com.kingfisher.test;

import com.kingfisher.test.logger.ToLoggerPrintStream;
import com.kingfisher.test.properties.PropertiesController;
import com.kingfisher.test.restassured.filters.KfAllureRestAssured;
import com.kingfisher.test.restassured.filters.KfRequestLoggingFilter;
import com.kingfisher.test.restassured.filters.KfResponseLoggingFilter;
import com.kingfisher.test.testng.BaseTestCase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import com.kingfisher.test.kdt.MyProjectKeywordManager;
import org.testng.annotations.BeforeSuite;

import static com.kingfisher.test.json.util.JsonUtil.getJackson;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public abstract class MyProjectTestCase extends BaseTestCase<MyProjectKeywordManager> {

    protected MyProjectTestCase() {
        super(new MyProjectKeywordManager());
    }

    @BeforeSuite
    public void before() {
        ToLoggerPrintStream loggerPrintStream = new ToLoggerPrintStream(log);
        RestAssuredConfig config = RestAssured.config()
                .encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false).defaultContentCharset("UTF-8"))
                .logConfig(new LogConfig(loggerPrintStream.getPrintStream(), true))
                .objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory((aClass, s) -> getJackson()))
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(PropertiesController.getProperty("sample.api.base.url"))
                .setBasePath(PropertiesController.getProperty("sample.api.base.path"))
                .setConfig(config)
                .setContentType(ContentType.ANY)
                .setAccept(ContentType.ANY)
                .addFilter(new KfRequestLoggingFilter(LogDetail.ALL))
                .addFilter(new KfResponseLoggingFilter(LogDetail.ALL))
                .addFilter(new KfAllureRestAssured())
                .build();
    }

}
