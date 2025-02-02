package com.Lpass.Scripts;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Lpaas.Elements.Add_Brand_Element;

import com.Lpaas.basesetup.BaseSetup;

import Excels.Excels;
import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import utils.CommonUtils;

public class Brand extends CommonUtils {
	public Add_Brand_Element elements;
	public Sheet s;
	public Sheet s1;
	public Select Select;

	//Before Executing this script Change Required data in Excle file. 
	@BeforeClass
	public void setUp() throws Exception, InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//openbrowser(browserType);
		ProgramLogin();
		CommonUtils.ModuleName = "Brand";
		CommonUtils.Reports("Brand.html", "Brand");
		s = CommonUtils.readExcelSheet(Excels.filepath2 + "LoginCredentials.xls", 0);
		s1 = CommonUtils.readExcelSheet(Excels.filepath2 + "Brand.xls", 0);
		elements = PageFactory.initElements(driver, Add_Brand_Element.class);

	}
	@Test(priority = 1)
	public void CreationofAddstore() throws InterruptedException, IOException {
		for (int i = 1; i < s1.getRows(); i++) {
			driver.navigate().to(CommonUtils.getExcelData(0, i, s1));
			Thread.sleep(1000);
			elements.Publish.click();
			elements.BrandName.sendKeys(CommonUtils.getExcelData(1, i, s1));
			Thread.sleep(1000);
			elements.BrandCode.sendKeys(CommonUtils.getExcelData(2, i, s1));
			Thread.sleep(1000);
			elements.BrandURL.sendKeys(CommonUtils.getExcelData(2, i, s1));
			Thread.sleep(1000);
			elements.SaveButton.click();
			Thread.sleep(1000);
			if(elements.success_msg.isDisplayed()) {
				CommonUtils.captureScreenshot_pass(driver, "saveButton", "popup displayed sucessfully");
			}
			else {
				CommonUtils.captureScreenshot_fail(driver, "saveButton", "popup not displayed");
			}

		}
	}
	@AfterClass(alwaysRun = true)
    public void quitbrowser() {
        if(driver != null)
            driver.quit();
	}
}