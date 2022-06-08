package framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import util.LoggerUtil;
import util.PropertyReader;

import static framework.Constants.COLON_DOUBLE_SLASH;
import static framework.Constants.SINGLE_SLASH;
import static io.restassured.RestAssured.given;

public class TestBase {

    public static RequestSpecification requestSpecification = null;
    public SoftAssert softAssert;

    @BeforeClass
    public void initialiseAssertions() {
        softAssert = new SoftAssert();
        LoggerUtil.log("Before class method successfully completed", LogLevels.INFO);
    }

    @AfterTest
    public void assertTest() {
        softAssert.assertAll();
        LoggerUtil.log("After class method successfully completed", LogLevels.INFO);
    }

    /**
     * This method provides the capability create the RequestSpecification which is used to invoke an API.
     * API protocol, HOST, Version are parameterized and is configured via a property file.
     * @return RequestSpecBuilder object
     */
    @BeforeClass
    public static RequestSpecification sendApiRequest() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(PropertyReader.getProperty("API_PROTOCOL") + COLON_DOUBLE_SLASH + PropertyReader.getProperty("API_HOST"))
                .setBasePath(SINGLE_SLASH + PropertyReader.getProperty("API_VERSION") + SINGLE_SLASH).addHeader("Accept", "application/json")
                .setRelaxedHTTPSValidation()
                .build();
        LoggerUtil.log("RequestSpecBuilder successfully created", LogLevels.INFO);
        return given().spec(requestSpecification).when().log().all();
    }

}