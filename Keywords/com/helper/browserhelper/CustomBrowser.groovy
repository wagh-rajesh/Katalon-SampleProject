package com.helper.browserhelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.sun.media.sound.SoftReverb.Delay

import internal.GlobalVariable

public class CustomBrowser {
	
	@Keyword
	public void openBrowser(String applicationUrl) {
		WebUI.openBrowser(applicationUrl)
		WebUI.waitForPageLoad(GlobalVariable.timeoutTwentySec)
		WebUI.maximizeWindow()
	}
	
	@Keyword
	public void openBrowser() {
		openBrowser(GlobalVariable.applicationUrl)
	}
	
	@Keyword
	public void closeBrowser() {
		WebUI.switchToDefaultContent()
		WebUI.switchToWindowIndex(0)
		WebUI.closeBrowser()
	}
	
	@Keyword
	public void refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebUI.refresh()
		WebUI.waitForPageLoad(GlobalVariable.timeoutTwentySec)
		KeywordUtil.markPassed("Refresh successfully")
	}
	
}

