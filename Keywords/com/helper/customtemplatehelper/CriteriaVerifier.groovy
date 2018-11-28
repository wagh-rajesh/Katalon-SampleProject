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

import internal.GlobalVariable

public class CriteriaVerifier {


	@Keyword
	public void verifyCriteria(String templateName, String locationName, String sellSheetName, List selectedPlansNames) {
		verifyTemplateName(templateName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifyLocation(locationName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifySellSheetName(sellSheetName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifyPlans(selectedPlansNames)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifyViewPDFBtn()
		WebUI.delay(GlobalVariable.timeoutOneSec)
	}

	@Keyword
	public void verifyCriteria(String templateName, String locationName, String sellSheetName, List selectedPayerNames, List selectedPlansNames) {
		verifyCriteria(templateName, locationName, sellSheetName, selectedPlansNames)
		verifyPayers(selectedPayerNames)
		WebUI.delay(GlobalVariable.timeoutOneSec)
	}

	public void verifyTemplateName(String templateName) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/TemplateName'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementText(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/TemplateName'), templateName, FailureHandling.STOP_ON_FAILURE)
	}

	public void verifyLocation(String locationName) {
		WebUI.verifyElementText(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/Location'), locationName, FailureHandling.STOP_ON_FAILURE)
	}

	public void verifyPlans(List plansList) {
		for(String planName in plansList) {
			WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/PlansSelections/AllPlans', [('Variable'): planName]))
		}
	}

	/*
	 * objectName : CommercialPlans, MedicaidPlans, MedicarePlans
	 *
	 */

	@Keyword
	public void verifyPlansBasedOnType(String objectName, List planNames) {
		for(String planName in planNames) {
			WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/PlansSelections/' + objectName, [('Variable'): planName]))
		}
	}

	public void verifyPayers(List payerNames) {
		for(String payerName in payerNames) {
			WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/PayerSelections/AllPayers', [('Variable'): payerName]))
		}
	}

	/*
	 * objectName : CommercialPayers, MedicaidPayers, MedicarePayers
	 *
	 */

	@Keyword
	public void verifyPayersBasedOnType(String objectName, List payerNames) {
		for(String payerName in payerNames) {
			WebUI.verifyElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/PayerSelections/' + objectName, [('Variable'): payerName]))
		}
	}

	public void verifySellSheetName(String sellSheetName) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/SellSheetName'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/SellSheetName'), 'value', sellSheetName, 0)
	}

	public void verifyViewPDFBtn() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/ViewPDFBtn'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementClickable(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/ViewPDFBtn'))
	}

	@Keyword
	public void verifyOrderPDFBtn() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/OrderPDFBtn'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementClickable(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/OrderPDFBtn'))
	}
}
