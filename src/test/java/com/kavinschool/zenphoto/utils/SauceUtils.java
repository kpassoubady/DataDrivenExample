/*
 *
 *  * Author: Kangeyan Passoubady
 *  * (c) Kavin School
 *
 */
package com.kavinschool.zenphoto.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Sauce utils.
 */
public class SauceUtils {
    private static final String SAUCE_PLATFORM_API = "https://saucelabs.com/rest/v1/info/platforms/all";
    private static JSONArray platforms;

    /**
     * Instantiates a new Sauce labs utils.
     */
    public SauceUtils() {
    }

    /**
     * Update test.
     *
     * @param sessionId the session id
     * @param result    the result
     */
    public static void updateTest(String sessionId, ITestResult result) {
        String testName = StringUtils.simplifyMethodClassName(result.getTestClass().getName(), result.getName());
        String buildName = null;
        if(System.getenv("BUILD_NUMBER") != null) {
            buildName = System.getenv("JOB_NAME") + " #" + System.getenv("BUILD_NUMBER");
        }
        updateTest(sessionId, testName, result.isSuccess(), null, buildName);
    }

    /**
     * Update test.
     *
     * @param sessionId   the session id
     * @param testName    the test name
     * @param passed      the passed
     * @param buildNumber the build number
     */
    public static void updateTest(String sessionId, String testName, boolean passed, String buildNumber) {
        updateTest(sessionId, testName, passed, null, buildNumber);
    }

    /**
     * Update test.
     *
     * @param sessionId   the session id
     * @param testName    the test name
     * @param passFail    the pass fail
     * @param customData  the custom data
     * @param buildNumber the build number
     */
    public static void updateTest(String sessionId, String testName, boolean passFail, String customData, String buildNumber) {
        String username = System.getenv("SAUCE_USERNAME");
        String password = System.getenv("SAUCE_ACCESS_KEY");

        JSONObject updates = new JSONObject();
        updates.put("passed", passFail);
        updates.put("name", testName);

        if (buildNumber != null) {
            updates.put("build", buildNumber);
        }

        if (customData != null) {
            updates.put("custom-data", customData);
        }

        String sauceApiUrl = "https://saucelabs.com/rest/v1/" + username + "/jobs/" + sessionId;
        Unirest.setHttpClient(UniRestUtils.getHttpClient());

        HttpResponse<String> request;
        try {
            request = Unirest.put(sauceApiUrl).basicAuth(username, password).header("accept", "application/json")
                    .header("Content-Type", "application/json").body(updates.toString()).asString();
            System.out.println("Update Saucelabs Test Result: ");
            System.out.println(request.getBody());
        } catch (UnirestException e) {
            System.out.println("Failed to update Saucelabs" + sessionId);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get sauce labs plat forms json array.
     *
     * @return the json array
     */
    public static JSONArray getSauceLabsPlatForms(){
        if(platforms == null) {
            platforms = UniRestUtils.getResponse(SAUCE_PLATFORM_API).getBody().getArray();
        }
        return platforms;
    }

    /**
     * Get latest browser platform version string.
     *
     * @param browserName    the browser name
     * @param browserVersion the browser version
     * @return the string
     */
    public static String getLatestBrowserPlatformVersion(String browserName, String browserVersion){
        String browserApiName = browserName;
        switch(browserName.toLowerCase()){
            case "gc":
            case "google chrome":
                browserApiName = "chrome";
                break;
            case "ff":
                browserApiName = "firefox";
                break;
            case "edge":
                browserApiName = "microsoftedge";
                break;
            case "safari":
                browserApiName = "safari";
                break;
        }
        ArrayList<String> platformVersions = new ArrayList<String>();
        getSauceLabsPlatForms();
        for (int i = 0; i < platforms.length(); i++){
            if (browserApiName.equals(platforms.getJSONObject(i).getString("api_name").toLowerCase())
                    && browserVersion.equals(platforms.getJSONObject(i).getString("short_version"))
            ){
                platformVersions.add(platforms.getJSONObject(i).getString("os"));
            }
        }
        Collections.sort(platformVersions);
        if(platformVersions.size()==0){
            return "";
        }
        return platformVersions.get(platformVersions.size()-1).toString();
    }

    /**
     * Get latest mobile platform version string.
     *
     * @param deviceName the device name
     * @return the string
     */
    public static String getLatestMobilePlatformVersion(String deviceName){
        ArrayList<Double> platformVersions = new ArrayList<Double>();
        getSauceLabsPlatForms();
        for (int i = 0; i < platforms.length(); i++){
            if (deviceName.equals(platforms.getJSONObject(i).getString("long_name"))){
                String version = platforms.getJSONObject(i).getString("short_version");
                if(StringUtils.isNumeric(version)){
                    platformVersions.add(Double.parseDouble(version));
                }
            }
        }
        Collections.sort(platformVersions);
        if(platformVersions.size()==0){
            return "";
        }
        return platformVersions.get(platformVersions.size()-1).toString();
    }

    /**
     * Annotate.
     *
     * @param driver the driver
     * @param text   the text
     */
    public static void annotate(WebDriver driver, String text) {
        ((JavascriptExecutor) driver).executeScript("sauce:context=" + text);
    }

    /**
     * Update test status.
     *
     * @param driver the driver
     * @param result the result
     */
    public static void updateTestStatus(WebDriver driver, ITestResult  result) {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]){
        System.out.println(getLatestMobilePlatformVersion("Samsung Galaxy S9 HD GoogleAPI Emulator"));
//        System.out.println(getLatestMobilePlatformVersion("iPhone 6s Simulator"));
//        System.out.println(getLatestBrowserPlatformVersion("safari", "11"));
//        System.out.println(getLatestBrowserPlatformVersion("safari", "9"));
        System.out.println(getLatestBrowserPlatformVersion("chrome", "70"));
    }

}
