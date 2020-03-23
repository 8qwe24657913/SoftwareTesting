package experiment;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@RunWith(Parameterized.class)
public class Experiment2 {
    private String username;
    private String password;
    private static WebDriver driver = null;
    private static final String driverPath = System.getProperty("user.dir") + "/res/geckodriver.exe";
    private static final String xlsxPath = System.getProperty("user.dir") + "/res/Selenium Lab2020.xlsx";
    private static final String firefoxBinaryPath = "D:/Program Files/Mozilla Firefox/firefox.exe";
    
    public Experiment2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.firefox.bin", firefoxBinaryPath);
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
    
    @Parameters
    public static Collection<Object[]> getData() throws IOException{
        try (Workbook workbook = new XSSFWorkbook(xlsxPath)) {
            List<Object[]> result = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowNum = sheet.getFirstRowNum(), rowEnd = sheet.getLastRowNum(); rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                String username = row.getCell(1).getStringCellValue();
                if (username.equals("")) break;
                String password = row.getCell(2).getStringCellValue();
                result.add(new Object[]{
                    username,
                    password,
                });
            }
            return result;
        }
    }
    
    
    @Test
    public void runTest() {
        driver.get("http://103.120.226.190/selenium-demo/git-repo");
        driver.findElement(By.name("user_number")).sendKeys(this.username);
        driver.findElement(By.name("password")).sendKeys(this.password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-2 > code:not(:empty)")));
        }
        assertEquals(driver.findElement(By.cssSelector(".mb-2:nth-child(6) > code:not(:empty)")).getText(), this.password);
    }

}
