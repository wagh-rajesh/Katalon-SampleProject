package com.helper.customtemplatehelper

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
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
	public List selectPayer(List payerNames, boolean hasPlanOrPayerStep) {
		List<String> payerNamesList = []
		if(!hasPlanOrPayerStep) {
			expandOrCollapsePayerSelectionStep()
		}
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/payersTableElement'), GlobalVariable.timeoutTwentySec)
		for(String payerTypeName in payerNames) {
			def payerTypeNameCombination = payerTypeName.split('-')
			String payerType = payerTypeNameCombination[0].trim()
			String payerName = payerTypeNameCombination[1].trim()
			payerNamesList.add(payerName)
			String webEleName =  selectPayerType(payerType.capitalize())
			TestObject payerTestOject = findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/' + webEleName , [('Variable'): payerName])
			WebUI.waitForElementPresent(payerTestOject, GlobalVariable.timeoutTwentySec)
			WebUI.click(payerTestOject)
			WebUI.delay(GlobalVariable.timeoutFiveSec)
		}
		println("Generated payer name list ----------------> " + payerNamesList)
		expandOrCollapsePayerSelectionStep()
		return payerNamesList
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

	public String selectPayerType(String payerType) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayerType', [('Variable'): payerType]), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayerType', [('Variable'): payerType]))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		String webEle = ''
		switch(payerType) {
			case 'Commercial':
				webEle = 'selectCommercialPayer'
				break;
			case 'Medicare':
				webEle = 'selectMedicarePayer'
				break;
			case 'Medicaid':
				webEle = 'selectMedicaidPayer'
				break;
			case 'State Medicaid':
				webEle = 'selectStateMedicaidPayer'
				break;
			case 'Managed Medicaid':
				webEle = 'selectManagedMedicaidPayer'
				break;
			default:
				KeywordUtil.markFailedAndStop("[Custom Keyword Error] : Could not identify the segment for plan selection.")
				break;
		}
		return webEle;
	}
}
