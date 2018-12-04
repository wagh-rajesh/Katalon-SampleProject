package com.helper.javascripthelper

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.sun.org.apache.xpath.internal.operations.String

import internal.GlobalVariable

public class JavascriptHelper {
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	JavascriptExecutor executor = ((JavascriptExecutor)driver)
	
	@Keyword
	public void executeScript(String script) {
		executor.executeScript(script)
	}
	
	@Keyword
	public void executeScript(String script, TestObject referenceObject) {
		WebElement element = WebUiCommonHelper.findWebElement(referenceObject, GlobalVariable.timeoutThirtySec)
		executor.executeScript(script, element)
	}
}
