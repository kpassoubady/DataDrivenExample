package com.kavinschool.zenphoto.pages;

import com.kavinschool.zenphoto.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdminDashBoardPage {

    WebDriver driver;

    @FindBy(how = How.ID, using="links")
    @CacheLookup
    private WebElement headerMsg;

    @FindBy(how = How.LINK_TEXT, using="Users")
    @CacheLookup
    private WebElement usersTab;

    @FindBy(how = How.LINK_TEXT, using="Tags")
    @CacheLookup
    private WebElement tagsTab;

    @FindBy(how = How.XPATH, using="//table[@id='user-0']/tbody/tr/td")
    @CacheLookup
    private WebElement usersInfoLink;

    @FindBy(how = How.ID, using="checkAllAuto")
    @CacheLookup
    private WebElement deleteAllCheckBox;

    @FindBy(how = How.ID, using="delete_tags")
    @CacheLookup
    private WebElement deleteCheckedTagsButton;

    @FindBy(how = How.ID, using="save_tags")
    //@CacheLookup
    private WebElement addTagsButton;

    @FindBy(how = How.LINK_TEXT, using="Log Out")
    @CacheLookup
    private WebElement logout;

    public AdminDashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderMsg() {
        return headerMsg.getText();
    }

    public AdminDashBoardPage clickUsersTab() {
        usersTab.click();
        return this;
    }

    public AdminDashBoardPage clickTagsTab() {
        tagsTab.click();
        return this;
    }

    public String getUsersInfoLinkText() {
        return usersInfoLink.getText();
    }

    public GalleryPage clickLogoutLinkReturnGalleryPage() {
        logout.click();
        return PageFactory.initElements(driver, GalleryPage.class);
    }

    public AdminDashBoardPage clickDeleteAllCheckBox() {
        deleteAllCheckBox.click();
        return this;
    }

    public AdminDashBoardPage clickDeleteCheckedTagsButton() {
        deleteCheckedTagsButton.click();
        return this;
    }

    public AdminDashBoardPage clickAddTagsButton() {
        addTagsButton.click();
        return this;
    }


    public AdminDashBoardPage typeNewTags(int tagNum, String tag) {
        driver.findElement(By.id("new_tag_" + tagNum)).sendKeys(tag);
        return this;
    }

    public AdminDashBoardPage fillTagsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.TAGS_FILE))) {

            Integer tagNum = 0; // Holds 0 to 39 allowed input tags counter
            String strLine = br.readLine(); // Skips the header row
            // Read from the file until end
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                typeNewTags(tagNum,strLine);

                tagNum++;
                if (tagNum > 39) {
                    clickAddTagsButton();
                    Thread.sleep(2000);
                    tagNum = 0; // Reset the tag once it reaches 40
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return this;
    }

}
