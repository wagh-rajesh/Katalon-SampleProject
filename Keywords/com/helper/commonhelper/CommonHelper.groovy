package com.helper.commonhelper

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

public class CommonHelper {
	
	public TestObject selectPayerType(String objectRepoPath, String payerType) {
//		TestObject testObj = findTestObject(objectRepoPath + '/' +  )
		WebUI.waitForElementClickable(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayerType', [('Variable'): payerType]), GlobalVariable.timeoutThirtySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/selectPayerType', [('Variable'): payerType]))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		String webEle = ''
		switch(payerType) {
			case 'Commercial':
				webEle = 'selectCommericalPayer'
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
		
		TestObject testObj = findTestObject('Object Repository/Common-OR/CustomTemplate/StepSelections/ChoosePayers/' + webEle , [('Variable'): payerName])
		return testObj
	}
}
