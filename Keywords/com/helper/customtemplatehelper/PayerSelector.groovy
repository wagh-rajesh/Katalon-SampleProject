package com.helper.customtemplatehelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List

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
import com.sun.org.apache.xpath.internal.operations.Bool

import internal.GlobalVariable

public class PayerSelector {

	@Keyword
	public void selectPayer(String payerName) {
		if (!checkIfCollapsedOrExpand()) {
			// the step is already collapsed. Need to expand accordian
			expandOrCollapsePayerSelectionStep()
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayer', [('Variable'): payerName]))
		WebUI.delay(GlobalVariable.timeoutTenSec)
		if (checkIfCollapsedOrExpand()) {
			// the step is already expanded. Need to collapse accordian
			expandOrCollapsePayerSelectionStep()
		}
	}

	@Keyword
	public void selectPayer(List payerNames) {
		Boolean isStepAlreadyExpanded = false
		isStepAlreadyExpanded = WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutTwentySec)
		if (isStepAlreadyExpanded) {
			expandOrCollapsePayerSelectionStep()
		}
		for(String payerName in payerNames) {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutTwentySec)
			WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayer', [('Variable'): payerName]))
			WebUI.delay(GlobalVariable.timeoutFiveSec)
		}
		isStepAlreadyExpanded = WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutTwentySec)
		if (isStepAlreadyExpanded) {
			expandOrCollapsePayerSelectionStep()
		}
	}

	public void expandOrCollapsePayerSelectionStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payerStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payerStep'))
		WebUI.delay(1)
	}

	private boolean checkIfCollapsedOrExpand() {
		TestObject payerStepObj = findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/choosePayerMsg')
		boolean isExpanded = WebUI.verifyElementPresent(payerStepObj, GlobalVariable.timeoutTwentySec)
		return isExpanded;
	}
}
