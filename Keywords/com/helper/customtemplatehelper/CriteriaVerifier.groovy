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
	public void verifyCriteria(String templateName, String locationName, String sellSheetName) {
		verifyTemplateName(templateName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifyLocation(locationName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
		verifySellSheetName(sellSheetName)
		WebUI.delay(GlobalVariable.timeoutOneSec)
	}
	
	public void verifyTemplateName(String templateName) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/TemplateName'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementText(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/TemplateName'), templateName, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("------------------> Template name is verified on criteria section")
	}

	public void verifyLocation(String locationName) {
		WebUI.verifyElementText(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/Location'), locationName, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("------------------> Location name is verified on criteria section")
	}

	public void verifyPayers() {
	}

	public void verifyPlans() {
	}

	public void verifyCommercialPlans() {
	}

	public void verifyMedicaidPlans() {
	}

	public void verifyMedicarePlans() {
	}

	public void verifySellSheetName(String sellSheetName) {
		WebUI.verifyElementText(findTestObject('Object Repository/Common-OR/CustomTemplate/CriteriaSelections/SellSheetName'), sellSheetName, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("------------------> Sellsheet name is verified on criteria section")
	}

}
