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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain

import internal.GlobalVariable

public class PlansSelector {

	@Keyword
	public void selectPlan(String planName) {
		expandOrCollapsePlanSelectionStep()
		// Below explicit wait is needed as National type plans takes more time to load
		WebUI.delay(GlobalVariable.timeoutThirtySec)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/plansTableElement'), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/selectPlan', [('Variable'): planName]))
		WebUI.delay(GlobalVariable.timeoutTenSec)
		expandOrCollapsePlanSelectionStep()
	}

	@Keyword
	public List selectPlan(List planNames) {
		List<String> planNamesList = []
		expandOrCollapsePlanSelectionStep()
		for(String planTypeAndName in planNames) {
			def planTypeNameCombination = planTypeAndName.split('-')
			String planType = planTypeNameCombination[0].trim()
			String planName = planTypeNameCombination[1].trim()
			planNamesList.add(planName)
			String webEleName = selectPlanType(planType.capitalize())
			TestObject planTestOject = findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/' + webEleName , [('Variable'): planName])
			WebUI.waitForElementPresent(planTestOject, GlobalVariable.timeoutTwentySec)
			WebUI.click(planTestOject)
			WebUI.delay(GlobalVariable.timeoutFiveSec)
		}
		println("Generated payer name list ----------------> " + planNamesList)
		expandOrCollapsePlanSelectionStep()
		return planNamesList
	}

	public void expandOrCollapsePlanSelectionStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/planStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/planStep'))
		WebUI.delay(1)
	}

	public String selectPlanType(String planOrPayerType) {
		String testObjPath = 'Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePlans/selectPlanType'
		WebUI.waitForElementClickable(findTestObject(testObjPath, [('Variable'): planOrPayerType]), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject(testObjPath, [('Variable'): planOrPayerType]))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		String webEle = 'select' + planOrPayerType + 'Plan'
		return webEle;
	}
}
