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

public class LocationSelector {
	
	/*
	 * locationType can be following
	 *  1.	states
	 *  2.	metro_stat_areas, etc
	 */
	
	@Keyword
	public void selectLocation(String locationType, String stateName, Integer waitTimeout) {
		expandLocationStep()
		selectLocationType(locationType)
		selectState(stateName, waitTimeout)
	}
	
	@Keyword
	public void selectLocation(String locationType, String stateName, String msaName, Integer waitTimeout) {
		selectLocation(locationType, stateName, GlobalVariable.timeoutTwoSec)
		selectMsa(msaName)
		WebUI.delay(1)
	}
	
	public void expandLocationStep() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/locationStep'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/locationStep'))
		WebUI.delay(1)
	}
	
	public void selectLocationType(String locationType) {
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/selectLocationDropdown'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/selectStateOrMsa', [('Variable'): locationType]))
		WebUI.delay(1)
	}
	
	public void selectState(String locationName, Integer waitTime=1) {
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/stateDropdown'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/selectState', [('Variable'): locationName]))
		WebUI.delay(waitTime)
	}
	
	public void selectMsa(String msaName) {
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/msaDropDown'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/Location/selectMsa', [('Variable'): msaName]))
		WebUI.delay(GlobalVariable.timeoutTwentySec)
	}
	
	
}
