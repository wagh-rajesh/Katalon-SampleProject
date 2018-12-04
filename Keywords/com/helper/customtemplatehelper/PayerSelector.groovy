package com.helper.customtemplatehelper

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class PayerSelector {

	@Keyword
	public void selectPayer(String payerName) {
		if (!checkIfCollapsedOrExpand()) {
			// the step is already collapsed. Need to expand accordian
			expandOrCollapsePayerSelectionStep()
		}
		expandOrCollapsePayerSelectionStep()
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayer', [('Variable'): payerName]))
		WebUI.delay(GlobalVariable.timeoutTenSec)
		expandOrCollapsePayerSelectionStep()
		if (checkIfCollapsedOrExpand()) {
			// the step is already expanded. Need to collapse accordian
			expandOrCollapsePayerSelectionStep()
		}
	}

	@Keyword
	public void selectPayer(List payerNames, boolean hasPlanOrPayerStep) {
		if(!hasPlanOrPayerStep) {
			expandOrCollapsePayerSelectionStep()
		}
		
		for(String payerName in payerNames) {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutTwentySec)
			WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayer', [('Variable'): payerName]))
			WebUI.delay(GlobalVariable.timeoutFiveSec)
		}
		expandOrCollapsePayerSelectionStep()
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
