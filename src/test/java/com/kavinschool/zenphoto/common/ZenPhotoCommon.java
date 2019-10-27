/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.common;

import com.kavinschool.zenphoto.pages.AdminDashBoardPage;
import com.kavinschool.zenphoto.pages.GalleryPage;
import com.kavinschool.zenphoto.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * The Class ZenPhotoCommon.
 */
public class ZenPhotoCommon {

    WebDriver driver;
    LoginPage loginPage;
    AdminDashBoardPage dashBoardPage;

    public ZenPhotoCommon(WebDriver driver) {
        this.driver = driver;
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, AdminDashBoardPage.class);
    }


    public void doZenPhotoUserTest(String baseUrl, String userId, String password)
            throws InterruptedException {

        String pageUrl = "/zenphoto/zp-core/admin.php";
        driver.get(baseUrl + pageUrl);

        dashBoardPage = doZenPhotoLogin(userId, password);

        Thread.sleep(3000);
        assertTrue(dashBoardPage.getHeaderMsg()
                .startsWith("Logged in as " + userId), "Header message does not contain the user " + userId);
        dashBoardPage.clickUsersTab();
        Thread.sleep(3000);
        assertEquals(userId.trim(), dashBoardPage.getUsersInfoLinkText().trim());

        doZenPhotoLogout();
        Thread.sleep(3000);
    }

    public AdminDashBoardPage doZenPhotoLogin(String userId, String password) {
        System.out.println("userId:" + userId);
        System.out.println("password:" + password);

        return loginPage.login(userId, password);
    }

    public GalleryPage doZenPhotoLogout() throws InterruptedException {
        return dashBoardPage.clickLogoutLinkReturnGalleryPage();
    }

    /**
     * Do tags creation.
     *
     * @param userId   the user id
     * @param password the password
     * @throws InterruptedException the interrupted exception
     */
    public void doTagsCreation(String baseUrl, String userId, String password) throws InterruptedException {

        System.out.println("userId:" + userId);
        System.out.println("password:" + password);

        String pageUrl = "/zenphoto/zp-core/admin.php";
        driver.get(baseUrl + pageUrl);

        AdminDashBoardPage dashboardPage = doZenPhotoLogin(userId, password);
        dashboardPage.clickTagsTab().clickDeleteAllCheckBox().clickDeleteCheckedTagsButton();

        Thread.sleep(2000); // pause

        // Here BufferedReader is added for fast reading.
        // Another way to use try which automatically closes the resource
        dashboardPage.fillTagsFromFile();

        // The last few left outs are added
        dashboardPage.clickAddTagsButton();

        doZenPhotoLogout();
    }
}
