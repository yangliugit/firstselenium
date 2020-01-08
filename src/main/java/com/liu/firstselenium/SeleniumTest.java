package com.liu.firstselenium;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {
	public static void main(String[] args) throws InterruptedException { //代码中存在线程方法，要捕捉InterruptedException
		WebDriver wb = null;
		//设置全局变量,当需要启动webdriver.chrome.driver即用指定目录的驱动
		System.setProperty("webdriver.chrome.driver", "F:\\eclipse-workspace\\firstselenium\\chromedriver.exe");
		try {
			wb = new ChromeDriver();
			String url = "http://www.baidu.com";
			wb.get(url);
			//驱动.manage().timeouts().implicitlyWait(arg0,arg1);其中args0为int类型的时间长度，arg1为TimeUnit。xx；其中xx为单位
			wb.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			wb.findElement(By.name("wd")).sendKeys("伊朗");
			wb.findElement(By.id("su")).click();
			Thread.sleep(1000);
			//获取当前浏览器信息,标题+当前地址
			System.out.println("webBrowser's title is: " + wb.getTitle());
			System.out.println("currentURL is : " + wb.getCurrentUrl());
//			执行js脚本
			String JSString = "alert('123')";
			((JavascriptExecutor) wb).executeScript(JSString);
			Thread.sleep(1000);
//			处理弹窗，确认弹窗
			Alert confirm = wb.switchTo().alert();
			confirm.getText();
			confirm.accept();
//			再次执行js脚本
			String nextJstring = "alert('456')";
			((JavascriptExecutor) wb).executeScript(nextJstring);
			Thread.sleep(1000);
//			处理弹窗，取消弹窗
			Alert disAlter = wb.switchTo().alert();
			disAlter.getText();
			disAlter.dismiss();
//			获取单个元素
			WebElement element = wb.findElement(By.name("wd"));
			System.out.println("text's id is: " + element.getAttribute("id"));
//			获取多个元素
			ArrayList<WebElement> eaList = (ArrayList<WebElement>) wb.findElements(By.className("result"));
			for(WebElement val:eaList) {
				System.out.println("id of result is: " + val.getAttribute("id"));
			}
//			定位层级元素			
			WebElement result1 = eaList.get(0);
			List<WebElement> eList = result1.findElements(By.tagName("div"));
			for(WebElement val: eList) {
				System.out.println("className of div is: " + val.getAttribute("class"));
			}
//			获取当前窗口
			String currentWindow = wb.getWindowHandle();
			System.out.println("current window is: " + currentWindow);
//			通过点击搜索热点来新开窗口
			wb.findElement(By.xpath("//*[@id=\"con-ar\"]/div[2]/div/div/table/tbody[1]/tr[1]/td[1]/span[1]/a")).click();
			Thread.sleep(1000);
//			获取所有窗口
			Set<String> windowSet = wb.getWindowHandles();
//			遍历所有窗口
			Iterator<String> iterator = windowSet.iterator();
			while(iterator.hasNext()) {
				if(currentWindow == iterator.next()) {
					continue;
				}
				wb.switchTo().window(iterator.next());
				System.out.println(wb.getTitle());
			}
			/* alert输入值
			 * Alert prompt = wb.switchTo().alert(); prompt.getText();
			 * prompt.sendKeys("youyouyou");
			 */
			
//			 处理下拉列表 
			/*Select select = new Select(wb.findElement(By.id("select")));
			select.selectByIndex(1); select.selectByValue("南京");
			select.selectByVisibleText("宿迁"); */
			//获取下拉框的全部选项 
			/*
			 * List<WebElement> selectList = select.getOptions(); for(WebElement w:
			 * selectList) { w.click(); }
			 */
			 
			 
//			处理cookies
//			添加一个cookie
//				Cookie cookie = new Cookie("COOKIE","NAME","D://COOKIES");
//				wb.manage().addCookie(cookie);
			//获取cookies
			Set<Cookie> set = wb.manage().getCookies(); //从驱动中获取cookies，返回的是set
			Iterator<Cookie> iterator1 = set.iterator();//迭代器输出set
			while(iterator1.hasNext()) {
				Cookie c = iterator1.next();
				c.getName();
				c.getValue();
				c.getPath();
			}
			wb.manage().deleteAllCookies();//cookie清理
//			wb.manage().deleteCookie(cookie);
			wb.manage().deleteCookieNamed("COOKIE");
			
//			等待加载完成页面
//			try {
				System.out.println("等待时间开始" + new Date());
//				wb.manage().timeouts().wait(1);//提示线程wait错误
				wb.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); //等待加载完成
				System.out.println("等待时间结束" + new Date());
//			} catch (InterruptedException e) {
				// TODO: handle exception
//				e.printStackTrace();
//			}
			//模拟鼠标和键盘操作
			Actions action = new Actions(wb);
//			action.click();//模拟鼠标点击
//			action.dragAndDrop(element, e);//模拟鼠标拖动
//			action.sendKeys(element, "12223").perform();//模拟键盘输入，此处perform理解为提交
//			action.click(element);//点击指定的元素
//			action.keyDown(Keys.ALT);//模拟键盘按下键	
//			添加键盘事件
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//wb.close();//关闭当前窗口，如果当前窗口是打开的最后一个窗口，则退出浏览器
			Thread.sleep(2000);
			wb.quit();//退出驱动，关闭所有窗口
			System.out.println("execute end、、、、、、、");
		}
	}
}