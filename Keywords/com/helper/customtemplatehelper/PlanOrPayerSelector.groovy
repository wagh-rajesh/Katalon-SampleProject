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

public class PlanOrPayerSelector {

	@Keyword
	public void selectPlanOrPayer(String planOrPayerName) {
		expandOrCollapsePlanPayerSelectionStep()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlanOrPayer/planOrPayerStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlanOrPayer/selectPlanOrPayer', [('Variable'): planOrPayerName]))
		WebUI.delay(GlobalVariable.timeoutTwentySec)
		expandOrCollapsePlanPayerSelectionStep()
	}

	public void expandOrCollapsePlanPayerSelectionStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlanOrPayer/planOrPayerStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlanOrPayer/planOrPayerStep'))
		WebUI.delay(1)
	}

	public boolean hasPlanOrPayerStep() {
		return true
	}

	public String selectPlanType(String planOrPayerType, String planOrPayer) {
		String testObjPath = 'Object Repository/Common-OR/CustomTemplate/StepSelections/'
		String planOrPayerName = 'Plan' // setting default value
		if(planOrPayer == 'shire_plan' || planOrPayer == 'plan') {
			testObjPath += 'ChoosePlans/selectPlanType'
			planOrPayerName = 'Plan'
		} else {
			testObjPath += 'ChoosePayers/selectPayerType'
			planOrPayerName = 'Payer'
		}
		WebUI.waitForElementClickable(findTestObject(testObjPath, [('Variable'): planOrPayerType]), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject(testObjPath, [('Variable'): planOrPayerType]))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		String webEle = 'select' + planOrPayerType + planOrPayerName
		return webEle;
	}
}
