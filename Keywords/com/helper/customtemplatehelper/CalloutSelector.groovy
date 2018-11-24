package com.helper.customtemplatehelper

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
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium

import internal.GlobalVariable

public class CalloutSelector {
	
	@Keyword
	public void selectCallouts(Integer noOfCalloutsToSelect=1) {
		expandOrCollapseCalloutSelectionStep()
		WebUI.check(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChooseCalloutOptions/selectCallouts', [('Variable'): 1]))
		WebUI.check(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChooseCalloutOptions/selectCallouts', [('Variable'): 2]))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		expandOrCollapseCalloutSelectionStep()
		WebUI.delay(GlobalVariable.timeoutTwoSec)
	}
	
	public void expandOrCollapseCalloutSelectionStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChooseCalloutOptions/calloutStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChooseCalloutOptions/calloutStep'))
		WebUI.delay(1)
	}
	
	public void countOfCheckBoxes() {
		
	}
}
