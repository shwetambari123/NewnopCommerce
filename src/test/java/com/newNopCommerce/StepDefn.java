package com.newNopCommerce;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yogesh on 15-09-2016.
 */


public class StepDefn {
    WebDriver driver;

    public String productDetails;


    public void selectByVisibleText(By element, String text) {
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }


    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Yogesh/IdeaProjects/NewnopCommerce/src/main/browser/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Given("^customer is on home page$")
    public void onHomePage() {
        String title = driver.getTitle();
        String pageTitle = "nopCommerce demo store";
        Assert.assertTrue(title.contains(pageTitle));

    }

    public void selectText(By element, String text) {
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void doRegister(DataTable details) {
        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.id("gender-female")).click();
        //driver.findElement()
        List<List<String>> customerDetails = details.raw();
        driver.findElement(By.cssSelector("#FirstName")).sendKeys(customerDetails.get(1).get(0));
        driver.findElement(By.cssSelector("#LastName")).sendKeys(customerDetails.get(1).get(1));
        selectText(By.name("DateOfBirthDay"), customerDetails.get(1).get(2));
        Select select1 = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select1.selectByVisibleText(customerDetails.get(1).get(3));
        Select select2 = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select2.selectByVisibleText(customerDetails.get(1).get(4));
        Random random = new Random();
        driver.findElement(By.name("Company")).sendKeys(customerDetails.get(1).get(6));
        driver.findElement(By.name("Email")).sendKeys(random.nextInt() + customerDetails.get(1).get(5));
        driver.findElement(By.name("Password")).sendKeys(customerDetails.get(1).get(7));
        driver.findElement(By.name("ConfirmPassword")).sendKeys(customerDetails.get(1).get(8));
        driver.findElement(By.name("register-button")).click();
    }

    public void loginUnSuccessfull() {
        String welcome = "Your registration completed";
        String message = driver.findElement(By.tagName("body")).getText();
        Assert.assertFalse(message.contains(welcome));
    }

    @When("^customer enter valid register details$")
    public void customerValidRegisterDetails(DataTable detail) {

        doRegister(detail);
    }

    @Then("^user should login successfull$")
    public void user_should_login_successfull() {
        String welcome = "Your registration completed";
        String message = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(message.contains(welcome));
    }


    @When("^customer select fourth product  in list$")
    public void selectFourthProduct() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.linkText("Computers"))).perform();
        WebElement suboption = driver.findElement(By.linkText("Notebooks"));
        suboption.click();
        List<WebElement> notebook = driver.findElements(By.className("item-box"));
        productDetails = notebook.get(4).getText();
        notebook.get(4).click();
        driver.findElement(By.cssSelector("#add-to-cart-button-9")).click();
        // driver.findElement(By.className("button-1 add-to-cart-button")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".cart-label")).click();
        // System.out.println(productTitile);

    }

    @Then("^customer see same product details in shopping cart$")
    public void productDetailShoppingCart() {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String shoppingDetail = driver.findElement(By.cssSelector(".product-name")).getText();
        String shoppingPrice = driver.findElement(By.cssSelector(".product-unit-price")).getText();
        Assert.assertTrue(productDetails.contains(shoppingDetail));
        Assert.assertTrue(productDetails.contains(shoppingPrice));
    }

    //blank field registration///
    @When("^customer enter blank spaces in manadatory fields$")
    public void doBlankfieldsRegidter(DataTable blankdetail) throws Throwable {
        try {
            doRegister(blankdetail);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Then("^user should not login successfully$")
    public void CustomerloginUnSuccessfull() {
        loginUnSuccessfull();
    }

    //invalid data registration////
    @When("^customer enter invalid data in manadatory fields$")
    public void invalidDataregister(DataTable invalidData) {

        try {
            doRegister(invalidData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginUnSuccessfull();
    }

    ////notebook menuselection///
    @Then("^he select product with \"([^\"]*)\" from menu$")
    public void selectProductFromMenu(String Notebooks) {

        Actions actions = new Actions(driver);
        WebElement mainmenu = driver.findElement(By.linkText("Computers"));
        actions.moveToElement(mainmenu).perform();
        driver.findElement(By.linkText("Notebooks")).click();

    }


    @When("^user select the second result it sjould be added$")
    public void selectSecondResult() {

        List<WebElement> box = driver.findElements(By.className("product-item"));
        int pom = box.size();
        WebElement seccondelem = box.get(1);
        WebElement second_result = seccondelem.findElement(By.className("buttons"));
        second_result.click();
        WebElement addcart = second_result.findElement(By.tagName("input"));
        addcart.click();
        Assert.assertTrue(driver.findElement(By.className("cart-qty")).getText().contains("1"));
        //System.out.print("Wait");

        driver.findElement(By.className("cart-qty")).clear();
    }


    @When("^user select the fifth product it should  be added$")
    public void selectFifthNotebookShouldBeAdded() {
        try {
            List<WebElement> notebook = driver.findElements(By.className("item-box"));
            WebElement fifthitem = notebook.get(4);
            productDetails = notebook.get(4).getText();
            notebook.get(4).click();
            driver.findElement(By.cssSelector("#add-to-cart-button-9")).click();
            Thread.sleep(25000);

            driver.findElement(By.cssSelector(".cart-label")).click();
        } catch (Exception e) {
            System.out.println("\n selection of product fail");
        }

    }

    @Then("^selected product is same as in  shopping cart$")
    public void selectedProductSameShoppingCart() {
        try {
            String cartproduccttitle = driver.findElement(By.cssSelector(".product-name")).getText();
            String cartproductprice = driver.findElement(By.cssSelector(".product-unit-price")).getText();
            Assert.assertTrue(productDetails.contains(cartproduccttitle));
            Assert.assertTrue(productDetails.contains(cartproductprice));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n failed assertion");
        }

    }

    @When("^user enter checkout details as$")
    public void user_enter_checkout_details_as(DataTable checkoutdetail) {

       try {
           driver.findElement(By.cssSelector("#termsofservice")).click();
           List<List<String>> checkoutdetails = checkoutdetail.raw();
           //driver.findElement(By.cssSelector("#termsofservice")).click();
           driver.findElement(By.cssSelector("#checkout")).click();
           selectByVisibleText(By.cssSelector("#BillingNewAddress_CountryId"), "United Kingdom");//Select country = new Select(driver.findElement(By.cssSelector("#CountryId")));country.selectByVisibleText("United Kingdom");
           driver.findElement(By.cssSelector("#BillingNewAddress_City")).sendKeys(checkoutdetails.get(1).get(0));
           driver.findElement(By.cssSelector("#BillingNewAddress_Address1")).sendKeys(checkoutdetails.get(1).get(1));
           driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys(checkoutdetails.get(1).get(2));//sendKeys("IG3 9TS");
           driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys(checkoutdetails.get(1).get(3));
           driver.findElement(By.cssSelector(".button-1.new-address-next-step-button")).click();
           // driver.findElement(By.cssSelector("#shippingoption_1")).click();//next day air delivary
           driver.findElement(By.id("shippingoption_1")).click();
           driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/input")).click();
           //driver.findElement(By.cssSelector(".button-1.shipping-method-next-step-button")).click();
           driver.findElement(By.id("paymentmethod_1")).click();//choose credit card method
           //       driver.findElement(By.id("paymentmethod_1")).click();
           driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
           // driver.findElement(By.xpath(".//*[@id='payment-method-buttons-container']/input")).click();// driver.findElement(By.className("button-1 payment-method-next-step-button")).click();
           driver.findElement(By.cssSelector("input.button-1.payment-method-next-step-button")).click();
       }catch (Exception e)
       {
           e.printStackTrace();
           System.out.println("checkout not proceed");
       }
    }
    @Then("^customer get error message enter required field$")
    public void enterRequiredfield()  {
       System.out.println("Enter required fields");
    }


    @Then("^he proceed payment information$")
    public void gotoPaymentInformation(DataTable paymentData) {

       try {
           List<List<String>> paymentDetails = paymentData.raw();
           driver.findElement(By.id("CardholderName")).sendKeys(paymentDetails.get(1).get(0));
           driver.findElement(By.id("CardNumber")).sendKeys(paymentDetails.get(1).get(1));
           String month = paymentDetails.get(1).get(2);
           selectByVisibleText(By.cssSelector("#ExpireMonth"), month);
           String year = paymentDetails.get(1).get(3);
           selectByVisibleText(By.cssSelector("#ExpireYear"), year);
           driver.findElement(By.cssSelector("#CardCode")).sendKeys(paymentDetails.get(1).get(4));
           driver.findElement(By.cssSelector(".button-1 payment-info-next-step-button")).click();

       }catch (Exception e)
       {
           e.printStackTrace();
           System.out.println("paymnent not proceed");
       }
    }

    @Then("^customer get error message of invalid card number$")
    public void messageInvalidCardNumber()  {

        System.out.println("Invalid card number");
    }


}
