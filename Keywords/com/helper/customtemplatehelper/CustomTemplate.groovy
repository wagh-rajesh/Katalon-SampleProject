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
import com.kms.katalon.core.testcase.Variable
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CustomTemplate {

	@Keyword
	public void selectBrand(String brandName) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/AllBrand'))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectBrand', [('Variable'): brandName]))
		WebUI.delay(GlobalVariable.timeoutFiveSec)
	}

	@Keyword
	public void scrollToTemplate(String templateName) {
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectTemplate', [('Variable'):templateName]), GlobalVariable.timeoutTwentySec)
		WebUI.scrollToElement(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectTemplate', [('Variable'):templateName]), GlobalVariable.timeoutTwentySec)
		GlobalVariable.timeoutTwoSec
	}

	@Keyword
	public void checkCustomTemplateHeaderLinkPresent() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/PO-Login/h2_Custom Template'), GlobalVariable.timeoutTwentySec)
		WebUI.verifyElementClickable(findTestObject('Object Repository/PO-Login/h2_Custom Template'))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
	}

	@Keyword
	public void selectTemplate(String templateName) {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectTemplate', [('Variable'):templateName]), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectTemplate', [('Variable'):templateName]))
		WebUI.waitForElementVisible(findTestObject('Common-OR/CustomTemplate/CriteriaSelections/TemplateName', [('Variable'):templateName]), GlobalVariable.timeoutTwentySec)
	}

	@Keyword
	public void clickViewPdf() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/AllPdfButtons/button_ViewPDF'), GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/AllPdfButtons/button_ViewPDF'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Common-OR/CustomTemplate/AllPdfButtons/button_orderPDF'), GlobalVariable.timeoutTwentySec)
		WebUI.delay(GlobalVariable.timeoutTenSec)
	}

	@Keyword
	public void verifyTemplatePreviewImg(String shortName) {
		boolean isPreviewImgPresent = WebUI.waitForElementPresent(findTestObject('Object Repository/Common-OR/CustomTemplate/TemplatePreviewImg', [('Variable'):shortName]), GlobalVariable.timeoutTwentySec)
		if(isPreviewImgPresent) {
			println("Preview Image with " + shortName + " is present")
		} else {
			KeywordUtil.markWarning("Warning :: Preview image not matching with Template ShortName")
		}
	}

	@Keyword
	public void verifyStepCompletion(String stepName) {
		boolean isStepCompleted = WebUI.waitForElementPresent(findTestObject('Object Repository/Common-OR/CustomTemplate/StepCompletion', [('Variable'):stepName]), GlobalVariable.timeoutTwentySec)
		if(isStepCompleted) {
			println("Step Completed for " + stepName)
		} else {
			KeywordUtil.markWarning("Warning :: Step completion mark is present")
		}
	}

	@Keyword
	public void verifyAvailableBrands(List brands) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutTwentySec)
		WebUI.click(findTestObject('Object Repository/Common-OR/CustomTemplate/AllBrand'))
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		for(String brandName in brands) {
			boolean isBrandPresent = WebUI.verifyElementPresent(findTestObject('Object Repository/Common-OR/CustomTemplate/SelectBrand', [('Variable'):brandName]), GlobalVariable.timeoutTenSec )
			if( isBrandPresent ){
				println( brandName + " is present in available list of brands.")
			} else {
				KeywordUtil.markWarning("Warning :: " + brandName + " is not present in Available brand list.")
			}
		}
	}
}
