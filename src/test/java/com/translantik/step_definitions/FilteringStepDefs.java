package com.translantik.step_definitions;

import com.translantik.pages.DashboardPage;
import com.translantik.pages.FleetVehiclesPage;
import com.translantik.pages.LoginPage;
import com.translantik.utilities.BrowserUtils;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;

public class FilteringStepDefs {
    int expectedSize;
    int actualSize;
    List<WebElement> checkBoxes;
    @Given("the {string} user is logged in")
    public void the_user_is_in_the_login_page(String usertype) {
        if(usertype.equals("driver")){
            new LoginPage().login(ConfigurationReader.get("driver_username"),ConfigurationReader.get("driver_password"));
        }else if(usertype.equals("sales manager")){
            new LoginPage().login(ConfigurationReader.get("sales_manager_username"),ConfigurationReader.get("sales_manager_password"));
        }else if(usertype.equals("store manager")){
            new LoginPage().login(ConfigurationReader.get("store_manager_username"),ConfigurationReader.get("store_manager_password"));
        }else{
            System.out.println("invalid userID");
        }
    }

    @When("the user navigates to {string} {string} page")
    public void the_user_navigates_to_page(String tab, String module) {
    new DashboardPage().navigateToModule(tab,module);
    }
    @Then("Manage filters should be hidden")
    public void manage_filters_should_be_hidden() {
    new FleetVehiclesPage().waitUntilLoaderScreenDisappear();
    BrowserUtils.waitFor(5);
        Assert.assertFalse(new FleetVehiclesPage().manageFilters.isDisplayed());
    }

    @When("the user click the filter button")
    public void the_user_click_the_filter_button() {
        new FleetVehiclesPage().waitUntilLoaderScreenDisappear();
       // WebDriverWait wait= new WebDriverWait(Driver.get(),5);
       // wait.until(ExpectedConditions.elementToBeClickable(new FleetVehiclesPage().filterButton));
      //  BrowserUtils.hover(new FleetVehiclesPage().filterButton);
        BrowserUtils.waitFor(3);
       // BrowserUtils.waitForClickablility(new FleetVehiclesPage().filterButton,20);
     new FleetVehiclesPage().filterButton.click();
     // BrowserUtils.clickWithJS(new FleetVehiclesPage().filterButton);
    BrowserUtils.waitFor(5);
    }

    @Then("Manage filter options should be displayed after clicking")
    public void manage_filter_options_should_be_displayed_after_clicking() {

        System.out.println(new FleetVehiclesPage().manageFilters.getText());
       Assert.assertTrue(new FleetVehiclesPage().manageFilters.isDisplayed());

    }
    @When("the user click on manage filter button")
    public void the_user_click_on_manage_filter_button() {
    BrowserUtils.waitFor(2);
    new FleetVehiclesPage().manageFilters.click();

    }

    @Then("user should apply filtering for each of the data columns on the page")
    public void user_should_apply_filtering_for_each_of_the_data_columns_on_the_page() {



        }
    @When("select all boxes")
    public void select_all_boxes() {


        BrowserUtils.waitFor(3);
       checkBoxes = new FleetVehiclesPage().manageFilterCheckBoxes;
         expectedSize = checkBoxes.size();
//        for (WebElement checkBox : checkBoxes) {
//            checkBox.click();
//        }
        for (int i = 0; i <checkBoxes.size() ; i++) {
            checkBoxes.get(i).click();
            new FleetVehiclesPage().waitUntilLoaderScreenDisappear();
        }

    }

    @When("the user click on reset button")
    public void the_user_click_on_reset_button() {
        new FleetVehiclesPage().resetButton.click();
    }

    @Then("User can  remove all filterings by using the reset button")
    public void user_can_remove_all_filterings_by_using_the_reset_button() {
        for (WebElement checkBox : checkBoxes) {
            Assert.assertFalse(checkBox.isSelected());
        }
    }
    @Then("User can apply filtering for each of the {string}  on the page")
    public void user_can_apply_filtering_for_each_of_the_on_the_page(String dataColumns) {
        if (dataColumns.equals("Driver")){
            new FleetVehiclesPage().methodFilter(dataColumns);

            BrowserUtils.waitFor(2);
            String expectedDriverName =new FleetVehiclesPage().driverColumnName.getText();
            new FleetVehiclesPage().containsSearchBox.sendKeys(expectedDriverName+ Keys.ENTER);

            System.out.println("expectedDriverName = " + expectedDriverName);
            String actualDriverName = new FleetVehiclesPage().getActualCellName(dataColumns).getText();
            System.out.println("actualDriverName = " + actualDriverName);
            Assert.assertTrue(expectedDriverName.contains(actualDriverName));
        }else if(dataColumns.equals("Tags")){
            new FleetVehiclesPage().methodFilter(dataColumns);
            BrowserUtils.waitFor(2);
            String expectedTagName =new FleetVehiclesPage().tagName.getText();
            new FleetVehiclesPage().isAnyOfSearchBox.sendKeys(expectedTagName+ Keys.ENTER);

            System.out.println("expectedTagName = " + expectedTagName);
            String actualTagName = new FleetVehiclesPage().getActualCellName(dataColumns).getText();
            System.out.println("actualTagName = " + actualTagName);
            Assert.assertTrue(expectedTagName.contains(actualTagName));
        }


    }

    @Then("all check boxes are selected")
    public void all_check_boxes_are_selected() {
        List<WebElement> filteredItems = new FleetVehiclesPage().filteredItems;
        actualSize = filteredItems.size();
        Assert.assertEquals(expectedSize,actualSize);

    }
    @When("the user click on multiple options")
    public void the_user_click_on_multiple_options() {

        List<WebElement> manageFilterSearchBox = new FleetVehiclesPage().manageFilterCheckBoxes;
        for (int i = 0; i < 2; i++) {
            manageFilterSearchBox.get(i).click();
            new DashboardPage().waitUntilLoaderScreenDisappear();
        }
    }
    @When("the user sends values for the Licence Plate and Tags")
    public void the_user_sends_values_for_the_Licence_Plate_and_Tags() {

        new FleetVehiclesPage().filteredItems.get(0).click();
        new FleetVehiclesPage().containsSearchBox.sendKeys("55"+Keys.ENTER);

        new DashboardPage().waitUntilLoaderScreenDisappear();

        new FleetVehiclesPage().filteredItems.get(1).click();
        new FleetVehiclesPage().isAnyOfSearchBox.sendKeys("Purchased"+Keys.ENTER);

    }
    @Then("the application should be able to support multiple filtering of columns")
    public void the_application_should_be_able_to_support_multiple_filtering_of_columns() {

        String text = new FleetVehiclesPage().firstRowAfterFiltering.getText();

        System.out.println("text = " + text);

        Assert.assertTrue(text.contains("55") && text.contains("Purchased"));
    }



}




