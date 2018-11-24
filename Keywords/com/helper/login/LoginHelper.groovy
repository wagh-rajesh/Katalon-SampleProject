package com.helper.login
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class LoginHelper {
	
	@Keyword
	public void loginToApplication(String applicationUrl, String Username, String Password) {
		WebUI.openBrowser(applicationUrl)
		WebUI.waitForPageLoad(GlobalVariable.timeoutTwentySec)
		WebUI.maximizeWindow()
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/PO-Login/input_The username password or'), GlobalVariable.timeoutTwentySec)
		
		WebUI.sendKeys(findTestObject('Object Repository/PO-Login/input_The username password or'), Username)
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		WebUI.sendKeys(findTestObject('Object Repository/PO-Login/input_Please enter your work e'), Password)
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		
		WebUI.click(findTestObject('Object Repository/PO-Login/input_Forgot your password_sub'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/PO-Login/h2_Custom Template'), GlobalVariable.timeoutTwentySec)
	}
	
	@Keyword
	public void loginToApplication() {
		loginToApplication(GlobalVariable.applicationUrl, GlobalVariable.Username, GlobalVariable.Password)
	}
	
	@Keyword
	public void logoutApplication() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/PO-Login/a_Shire FF Admin'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/PO-Login/a_Shire FF Admin'))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		WebUI.click(findTestObject('Object Repository/PO-Login/a_Logout'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/PO-Login/input_The username password or'), GlobalVariable.timeoutTwentySec)
	}
}