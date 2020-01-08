package com.liu.firstselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class OpenBaidu {
	public static void main(String[] args) {
		WebDriver cdriver = null;
		/*实现分布式指定执行自动化测试环境
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		//设置使用的浏览器名称
		caps.setBrowserName("chrome");
		caps.setPlatform(Platform.WINDOWS);
		*/
		//设置系统runtime全局变量
		System.setProperty("webdriver.chrome.driver","F:\\eclipse-workspace\\firstselenium\\chromedriver.exe");
		try {
			cdriver = new ChromeDriver();
			String baidu_url = "http://www.baidu.com";
			cdriver.get(baidu_url);
			cdriver.manage().window().maximize();
			cdriver.findElement(By.name("wd")).sendKeys("南邮李俊");
			Thread.sleep(3000);
			WebElement btn = cdriver.findElement(By.id("su"));
			btn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			cdriver.close();
		}
		
	}
}
