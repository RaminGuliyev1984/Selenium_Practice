package practice;

import Utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Test01 extends TestBase {


    @Test
    public void test01() {
        // 3. Amazon sayfasina gidelim. https://www.amazon.com/
        driver.get("https://www.amazon.com/");

        //4. Sayfa basligini(title) yazdirin
        // 5. Sayfa basliginin “Amazon” icerdigini test edin
        System.out.println(driver.getTitle());


        Assert.assertTrue(driver.getTitle().contains("Amazon"));

        // 6. Sayfa adresini(url) yazdirin
        System.out.println(driver.getCurrentUrl());

        // 7. Sayfa url’inin “amazon” icerdigini test edin.
        Assert.assertTrue(driver.getCurrentUrl().contains("amazon"));


        //  8. Sayfa handle degerini yazdirin
        System.out.println(driver.getWindowHandle());

        // 9. Sayfa HTML kodlarinda “alisveris” kelimesi gectigini test edin

        String actualElement = driver.getPageSource();
        String expectedElement = "alisveris";

        if (actualElement.contains(expectedElement)) {
            System.out.println("TEst PAssed");
        } else {
            System.out.println("Test FAILED");
        }
    }

    @Test
    public void test02() {

        //2- https://www.automationexercise.com/ adresine gidin
        driver.get("https://www.automationexercise.com/");
        //3- Sayfada 147 adet link bulundugunu test edin.

        List<WebElement> WebPages = driver.findElements(By.tagName("a"));
        int expectedLink = 147;
        int actualLink = WebPages.size();

        if (actualLink == 147) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        //4- Products linkine tiklayin
        driver.findElement(By.xpath("//*[@class='material-icons card_travel']")).click();

        //5- special offer yazisinin gorundugunu test edin
        WebElement element = driver.findElement(By.xpath("//*[@id=\"sale_image\"]"));

        if (element.isDisplayed()) {
            System.out.println("Special Offer yazi PASSED");
        } else {
            System.out.println("Special Offer yazi FAILED");
        }

        bekle(3000);

    }

    @Test
    public void test03() {

        //2.ChromeDriver kullanarak, facebook sayfasina gidin ve sayfa

        // basliginin (title) “facebook” oldugunu dogrulayin (verify), degilse dogru basligi yazdirin.
        driver.get("https://www.facebook.com");
        Assert.assertTrue(driver.getTitle().contains("Facebook"));
        System.out.println(driver.getTitle());

        //3.Sayfa URL’inin “facebook” kelimesi icerdigini dogrulayin, icermiyorsa “actual” URL’i yazdirin.
        Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
        System.out.println(driver.getCurrentUrl());
        // 4.https://www.walmart.com/ sayfasina gidin.
        driver.get("https://www.walmart.com/");
        //Sayfa basliginin “Walmart.com” icerdigini dogrulayin
        Assert.assertTrue(driver.getTitle().contains("Walmart.com"));

        //geri facebook donun ve sayfayi yenileyin
        driver.navigate().back();
        driver.navigate().refresh();
    }

    @Test
    public void test04() {
        // Amazan anasayfaya gidin
        driver.get("https://www.amazon.com");

        // Arama cubuguna Nutella yazdirip aratin
        WebElement aramaCubugu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaCubugu.sendKeys("Nutella" + Keys.ENTER);

        // Arama sonuclarinin Nutella icerdigini test edin
        WebElement aramaSonucu = driver.findElement(By.xpath("//*[@class='a-color-state a-text-bold']"));

        String expectedAramaSonucu = "Nutella";
        String actualAramaSonucu = aramaSonucu.getText();
        Assert.assertTrue(actualAramaSonucu.contains(expectedAramaSonucu));
        System.out.println("Test Passed");

        Assert.assertFalse(!actualAramaSonucu.contains(expectedAramaSonucu));
        System.out.println("Test Failed");
    }

    @Test
    public void test05() {
        //Amazon soyfasina gidelim. https://www.amazon.com/
        driver.get("https://www.amazon.com");
        //Sayfanin konumunu ve boyutlarini yazdirin
        System.out.println(driver.manage().window().getPosition());
        System.out.println(driver.manage().window().getSize());

        //Sayfayi simge durumuna getirin

        //simge durumunda 3 saniye bekleyip sayfayi maximize yapin
        bekle(3);
        driver.manage().window().maximize();
        //Sayfanin konumunu ve boyutlarini maximize durumunda yazdirin
        System.out.println(driver.manage().window().getPosition());
        System.out.println(driver.manage().window().getSize());

        //Sayfayi fullscreen yapin
        driver.manage().window().fullscreen();
        //Sayfanin konumunu ve boyutlarini fullscreen durumunda yazdirin
        System.out.println(driver.manage().window().getPosition());
        System.out.println(driver.manage().window().getSize());
        //Sayfanin konumunu ve boyutunu istediginiz sekilde ayarlayin
        Point point = new Point(300, 500);
        Dimension dimension = new Dimension(500, 650);
        //Sayfanin sizin istediginiz konum ve boyuta geldigini test edin
        Point expectedPoint = driver.manage().window().getPosition();
        Dimension expectedDimension = driver.manage().window().getSize();
        if (expectedDimension.equals(dimension) && expectedPoint.equals(point)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

    }

    @Test
    public void tes06() {

        //1.Youtube web sayfasına gidin ve sayfa başlığının “youtube” olup olmadığını
        //doğrulayın (verify), eğer değilse doğru başlığı(Actual Title) konsolda yazdirin.
        driver.get("https://www.youtube.com");
        String expectedTitle = "YouTube";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);
        System.out.println(actualTitle);
        //2.Sayfa URL'sinin “youtube” içerip içermediğini (contains) doğrulayın, içermiyorsa doğru URL'yi yazdırın.
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
        System.out.println(driver.getCurrentUrl());

        //3.Daha sonra Amazon sayfasina gidin https://www.amazon.com/
        driver.get("https://www.amazon.com/");

        //4.Youtube sayfasina geri donun
        driver.navigate().back();

        //5.Sayfayi yenileyin
        driver.navigate().refresh();

        //6.Amazon sayfasina donun
        driver.navigate().to("https://www.amazon.com/");
        bekle(5);

        //7.Sayfayi tamsayfa yapin
        driver.manage().window().fullscreen();

        //8.Ardından sayfa başlığının "Amazon" içerip içermediğini (contains) doğrulayın,
        Assert.assertTrue(driver.getTitle().contains("Amazon"));
        //9.Yoksa doğru başlığı(Actual Title) yazdırın.
        System.out.println(driver.getTitle());

        //10.Sayfa URL'sinin https://www.amazon.com/ olup olmadığını doğrulayın, degilse doğru URL'yi yazdırın
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.com/"));
        System.out.println(driver.getCurrentUrl());

    }

    @Test
    public void test07() {
        //https://www.automationexercise.com/ adresine gidin
        driver.get("https://www.automationexercise.com/");

        //Category bolumunde 3 element oldugunu test edin
        List<WebElement> categoryElements = driver.findElements(By.className("panel-heading"));
        int expectedElement = 3;
        int actualElement = categoryElements.size();
        if (expectedElement == actualElement) {
            System.out.println("Test Passed");
        } else {
            System.out.println("TEst Failed");
        }

        //Category isimlerini yazdirin
        for (WebElement name : categoryElements
        ) {
            System.out.println(name.getText());
        }
    }

    @Test
    public void test08() {
        //https://the-internet.herokuapp.com/add_remove_elements/ adresine gidin
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Add Element butonuna basin
        driver.findElement(By.xpath("//button[@onclick=\"addElement()\"]")).click();
        bekle(3);

        //Delete butonu’nun gorunur oldugunu test edin
        WebElement delete = driver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
        Assert.assertTrue(delete.isDisplayed());

        //“Add/Remove Elements” yazisinin gorunur oldugunu test edin
        WebElement AddRemove = driver.findElement(By.xpath("//*[@id=\"content\"]/h3"));
        Assert.assertTrue(AddRemove.isDisplayed());

        //Delete tusuna basin
        delete.click();
        bekle(5);
    }

    @Test
    public void tes09() {
        //1- https://www.amazon.com/ sayfasına gidin.
        driver.get("https://www.amazon.com/");

        //2- Arama kutusuna “city bike” yazip aratin
        WebElement aramaCubugu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaCubugu.sendKeys("city bike" + Keys.ENTER);

        //3- Görüntülenen sonuçların sayısını yazdırın
        String sonucSayi = driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1")).getText();
        String sonucSayiStr = sonucSayi.substring(0, 35);
        System.out.println(sonucSayiStr);

        //4- Listeden ilk urunun resmine tıklayın.
        driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[4]/div/div/div/div/div/div[1]")).click();
        bekle(3);
    }

    @Test
    public void test10() {
        //2- https://www.amazon.com/ adresine gidin
        driver.get("https://www.amazon.com/");

        // 3- Browseri tam sayfa yapin
        driver.manage().window().fullscreen();

        //  4- Sayfayi “refresh” yapin
        driver.navigate().refresh();

        //    5- Sayfa basliginin “Spend less” ifadesi icerdigini test edin
        Assert.assertTrue(driver.getTitle().contains("Spend less"));
        System.out.println(driver.getTitle());

        // 6- Gift Cards sekmesine basin
        driver.findElement(By.xpath("// *[@class=\"nav-a  \"][4]")).click();
        // 7- Birthday butonuna basin
        driver.findElement(By.xpath("//a[@aria-label=\"Birthday\"]")).click();

        //8-  Best Seller bolumunden ilk urunu tiklayin
        driver.findElement(By.id("acs-product-block-0")).click();

        //9-  Gift card details’den 25 $’i  secin
        WebElement element = driver.findElement(By.id("gc-mini-picker-amount-1"));
        bekle(3);
        //10-Urun ucretinin 25$ oldugunu test edin
        String expectedUcret = "$25";
        String actualUcret = element.getText();
        Assert.assertEquals(expectedUcret, actualUcret);
        bekle(3);
    }

    @Test
    public void test11() {

        //1.https://www.amazon.com/ sayfasina gidelim
        driver.get("https://www.amazon.com/");

        //2.arama kutusunu locate edelim
        WebElement element = driver.findElement(By.id("twotabsearchtextbox"));

        //3.“Samsung headphones” ile arama yapalim
        element.sendKeys("Samsung headphones" + Keys.ENTER);
        bekle(3);

        //4.Bulunan sonuc sayisini yazdiralim
        String sonucsayisi1 = driver.findElement(By.xpath("//h1[@class=\"a-size-base s-desktop-toolbar a-text-normal\"]")).getText();
        String sonucsayisiStr = sonucsayisi1.substring(0, 43);

        //5.Ilk urunu tiklayalim
        driver.findElement(By.xpath("//div[@class=\"a-section aok-relative s-image-fixed-height\"][1]")).click();
    }

    @Test
    public void test12() {
        //1. http://zero.webappsecurity.com sayfasina gidin
        driver.get("http://zero.webappsecurity.com");

        //2. Signin buttonuna tiklayin
        driver.findElement(By.xpath("//i[@class=\"icon-signin\"]")).click();

        //3. Login alanine  “username” yazdirin
        WebElement Login = driver.findElement(By.id("user_login"));
        Login.sendKeys("username");


        //4. Password alanina “password” yazdirin
        WebElement Password = driver.findElement(By.id("user_password"));
        Password.sendKeys("password");


        //5. Sign in buttonuna tiklayin
        driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();

        //6. Back tusu ile sayfaya donun
        driver.navigate().back();

        //7. Online Banking menusunden Pay Bills sayfasina gidin
        WebElement onlineBanking = driver.findElement(By.xpath("//*[@id=\"onlineBankingMenu\"]"));
        onlineBanking.click();
        WebElement PayBills = driver.findElement(By.id("pay_bills_link"));
        PayBills.click();

        //8. amount kismina yatirmak istediginiz herhangi bir miktari yazin
        WebElement amount = driver.findElement(By.id("sp_amount"));
        amount.sendKeys("12000");

        //9. tarih kismina “2020-09-10” yazdirin
        WebElement tarih = driver.findElement(By.xpath("//*[@id=\"sp_date\"]"));
        tarih.sendKeys("2020-09-10");

        //10. Pay buttonuna tiklayin
        driver.findElement(By.xpath("//*[@id=\"pay_saved_payees\"]")).click();

        //11. “The payment was successfully submitted.” mesajinin ciktigini test edin
        WebElement mesaj = driver.findElement(By.xpath("//*[@id=\"alert_content\"]/span"));
        String expectedMesaj = "The payment was successfully submitted.";
        String actualMesaj = mesaj.getText();
        Assert.assertTrue(expectedMesaj.contains(actualMesaj));

    }

    @Test
    public void test13() {
        //2. https://www.bestbuy.com/ Adresine gidin
        driver.get("https://www.bestbuy.com/");

        //3. farkli test method’lari olusturarak asagidaki testleri yapin


        //4.    ○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.bestbuy.com/"));

        //5.    ○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
        Assert.assertTrue(!driver.getTitle().contains("Rest"));

        //6.    ○ logoTest => BestBuy logosunun görüntülendigini test edin
        WebElement BestBuy = driver.findElement(By.xpath("//img[@class=\"logo\"][1]"));
        Assert.assertTrue(BestBuy.isDisplayed());

        //7.    ○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin
        WebElement Link = driver.findElement(By.xpath("//button[@lang=\"fr\"]"));
        Assert.assertTrue(Link.isDisplayed());
    }

    @Test
    public void test14() {
        // 2) https://www.youtube.com adresine gidin
        driver.get("https://www.youtube.com");
        // 3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin

        // ○titleTest =>Sayfa başlığının “YouTube”oldugunu test edin
        Assert.assertTrue(driver.getTitle().contains("YouTube"));

        //  ○imageTest =>YouTube resminin görüntülendiğini(isDisplayed()) test edin
        WebElement resim = driver.findElement(By.xpath("//*[@id=\"logo-icon\"][1]"));
        Assert.assertTrue(resim.isDisplayed());

        // ○Search Box 'in erisilebilir oldugunu test edin (isEnabled())
        WebElement SearchBox = driver.findElement(By.xpath("//div[@id=\"search-input\"]"));
        Assert.assertTrue(SearchBox.isEnabled());

        //  ○wrongTitleTest =>Sayfa basliginin “youtube”olmadigini dogrulayin
        Assert.assertTrue(!driver.getTitle().contains("youtube"));
        bekle(3);

    }

    @Test
    public void test15() {
        //    Verilen web sayfasına gidin.
        // a. https://the-internet.herokuapp.com/checkboxes
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // b. Checkbox1 ve checkbox2 elementlerini locate edin.
        WebElement Checkbox1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        WebElement Checkbox2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        // c. Checkbox1 seçili değilse onay kutusunu tıklayın
        if (!Checkbox1.isSelected()) {
            Checkbox1.click();
        }
        // d. Checkbox2 seçili değilse onay kutusunu tıklayın
        if (!Checkbox2.isSelected()) {
            Checkbox2.click();
        }
        // e. Checkbox1ve Checkbox2’nin seçili olduğunu test edin
        Assert.assertTrue(Checkbox1.isSelected());
        Assert.assertTrue(Checkbox2.isSelected());
        //Assert.assertTrue(Checkbox1.isSelected() && Checkbox2.isSelected());

    }

    @Test
    public void test16() {
        //2- https://www.google.com/ adresine gidin
        driver.get("https://www.google.com/");

        //3- cookies uyarisini kabul ederek kapatin
        //4- Sayfa basliginin “Google” ifadesi icerdigini test edin
        Assert.assertTrue(driver.getTitle().contains("Google"));

        //5- Arama cubuguna “Nutella” yazip aratin
        WebElement aramaCubugu = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        aramaCubugu.sendKeys("Nutella" + Keys.ENTER);


        //6- Bulunan sonuc sayisini yazdirin
        String sonuc = driver.findElement(By.id("result-stats")).getText();
        System.out.println(sonuc);
        bekle(3);

        //7- sonuc sayisinin 10 milyon’dan fazla oldugunu test edin
        String[] sonucSayi = sonuc.split(" ");
        String result = sonucSayi[1];
        result = result.replace(",", "");

        int resultSonucSayi = Integer.valueOf(result);
        System.out.println(resultSonucSayi);

        Assert.assertTrue(resultSonucSayi > 10000000);

    }

    @Test
    public void test17() {

        //“https://www.saucedemo.com” Adresine gidin
        driver.get("https://www.saucedemo.com");
        //Username kutusuna “standard_user” yazdirin
        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.sendKeys("standard_user" + Keys.ENTER);

        //Password kutusuna “secret_sauce” yazdirin
        WebElement passwordKutu = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordKutu.sendKeys("secret_sauce" + Keys.ENTER);

        //Login tusuna basin
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        //Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        WebElement urunuIsmi = driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']"));
        String productName = urunuIsmi.getText();

        //Add to Cart butonuna basin
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();

        //Alisveris sepetine tiklayin
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //Sectiginiz urunun basarili olarak sepete eklendigini control edin
        WebElement basariliUrun = driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']"));
        String actualUrun = basariliUrun.getText();
        if (actualUrun.equals(productName)) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("TEst Failed");
        }

    }

    @Test
    public void test18() {
        // Amazon anasayfaya gidin
        driver.get("https://www.amazon.com");

        // dropdown menuyu locate et
        WebElement aramaKutusu = driver.findElement(By.id("searchDropdownBox"));

        // Select class'indan bir obje olusturalim
        Select select = new Select(aramaKutusu);

        // arama kutusunun yanindaki dropdown menude 28 secenek oldugunu test edin
        int expectedSayi = 28;
        int actualSayi = select.getOptions().size();
        if (actualSayi == 28) {
            System.out.println(actualSayi);
        } else {
            System.out.println("Test Failed");
        }

        // dropdown menuden Baby kategorisini secin
        select.selectByVisibleText("Baby");
        bekle(3);

        // Arama kutusuna Nutella yazip aratin
        WebElement aramaKutusuYazdir = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        aramaKutusuYazdir.sendKeys("Nutella" + Keys.ENTER);

        // sectigimiz sonuc sayisini yazdirin
        String NutellaSayisi = driver.findElement(By.xpath("//h1[@class=\"a-size-base s-desktop-toolbar a-text-normal\"]")).getText();
        String opsiyonSecim = NutellaSayisi.substring(0, NutellaSayisi.indexOf(" "));
        int secdiyimOpsiyon = Integer.parseInt(opsiyonSecim);
        System.out.println(secdiyimOpsiyon);

        // bulunan sonuc sayisinin 10'dan fazla oldugunu test edin
        if (secdiyimOpsiyon > 10) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed1");
        }

        // sectigimiz opsiyon'un Baby oldugunu test edin
        aramaKutusu = driver.findElement(By.id("searchDropdownBox"));
        Select select1 = new Select(aramaKutusu);
        select1.getFirstSelectedOption().getText();
        String expected = "Baby";
        String actual = select1.getFirstSelectedOption().getText();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test19() {
        //https://the-internet.herokuapp.com/dropdown adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dropdown");

        //1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        WebElement DropDownElementi = driver.findElement(By.xpath("//*[@id=\"dropdown\"]"));
        Select select = new Select(DropDownElementi);
        select.selectByIndex(1);
        System.out.println("index ile secilen option1" + select.getFirstSelectedOption().getText());
        bekle(2);

        //2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
        select.selectByValue("2");
        System.out.println("value ile secilen option2" +select.getFirstSelectedOption().getText());
        bekle(2);

        //3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın


        //4.Tüm dropdown değerleri(value) yazdırın


        //5. Dropdown’un boyutunun 4 olduğunu test edin

    }
}





