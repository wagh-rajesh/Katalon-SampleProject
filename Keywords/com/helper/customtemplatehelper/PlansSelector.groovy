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

import internal.GlobalVariable

public class PlansSelector {

	@Keyword
	public void selectPlan(String planName) {
		expandOrCollapsePlanSelectionStep()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/plansTableElement'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/selectPlan', [('Variable'): planName]))
		WebUI.delay(GlobalVariable.timeoutTenSec)
		expandOrCollapsePlanSelectionStep()
	}


	public void expandOrCollapsePlanSelectionStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/planStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/planStep'))
		WebUI.delay(1)
	}
}
