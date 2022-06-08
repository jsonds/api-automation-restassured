package com.automation.tests;

import framework.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.http.HttpStatus.SC_OK;

public class CategoryTest extends TestBase {

    /**
     * API Test to verify,
     * Name = "Carbon credits"
     * CanRelist = true
     * The Promotions element with Name = "Gallery" has a Description that contains the text "Good position in category"
     */
    @Test(description = "Test case #1: Verify get request category body")
    public void validateAcceptanceCriteria() {

        String path = "Categories/6327/Details.json";
        String expectedName = "Carbon credits";
        String expectedDescriptionOfGallery = "Good position in category";
        int expectedSizeOfDescriptionGallery = 1;

        Response response = sendApiRequest().param("catalogue", "false").get(path);
        response.prettyPeek();
        softAssert.assertEquals(response.getStatusCode(), SC_OK, "Status code is invalid");
        softAssert.assertEquals(response.jsonPath().getString("Name"), expectedName, "Name did not match " + expectedName);
        softAssert.assertTrue(response.jsonPath().getBoolean("CanRelist"), "CanRelist value did not match");

        // Get all promotion details to a list
        List<Map<String, String>> values = response.jsonPath().getList("Promotions");
        // Check if there is one item with Name = Gallery and Description = Good position in category
        int actualListSize = values.stream().filter(t -> t.get("Name").equals("Gallery") && t.get("Description").equals(expectedDescriptionOfGallery)).collect(Collectors.toList()).size();
        softAssert.assertEquals(actualListSize, expectedSizeOfDescriptionGallery);
        softAssert.assertAll();


    }
}
