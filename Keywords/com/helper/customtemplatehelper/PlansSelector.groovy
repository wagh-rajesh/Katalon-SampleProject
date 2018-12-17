package com.helper.customtemplatehelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain

import internal.GlobalVariable

public class PlansSelector {

	public static PLAN_ROW_DATA =  [:] // Sample Data : ['plan-type': null, 'lives': null, 'tier-name': null]
	public static plansTableColumns = []
	def plansRowData = [:]
	WebDriver driver = DriverFactory.getWebDriver()
	WebElement Table = driver.findElement(By.xpath("//table/tbody"))

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
		fetchColumnHeader()
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
			fetchRowData(planName)
		}
		println("Selected Row Data from PlansSelector ----------------> " + plansRowData)
		expandOrCollapsePlanSelectionStep()
//		return planNamesList
		return PLAN_ROW_DATA
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

	public void fetchRowData(String planName) {
		// Based on plan names as key, separate hash with all row data in key-value format will be stored in Hash
		List<WebElement> tableRows = Table.findElements(By.xpath("tr[@data-name='" + planName + "']"))
		List<WebElement> selectedRow = tableRows.get(0).findElements(By.tagName('td'))
		for (int j = 0; j < selectedRow.size(); j++) {
			for (def columnName in plansTableColumns) {
				PLAN_ROW_DATA[columnName] = selectedRow.get(plansTableColumns.indexOf(columnName)).getText();
			}
		}
		println("PLAN_ROW_DATA for + " + planName + " : -> " + PLAN_ROW_DATA)
		plansRowData[planName] = PLAN_ROW_DATA
	}

	public void fetchColumnHeader() {
		List<WebElement> headerRow = Table.findElements(By.xpath("//tr//th[@class='sorting']"))
		for (int j = 0; j < headerRow.size(); j++) {
			def columnHeader = headerRow.get(j).getText().trim()
			def tempkey = columnHeader.toLowerCase().split(' ').join('-')

			def drugList = [
				'xiidra',
				'vyvanse',
				'mydayis'
			]

			if (drugList.contains(tempkey)) {
				tempkey = 'status'
			}

			if (columnHeader.size() > 0) {
				plansTableColumns.add(tempkey)
				PLAN_ROW_DATA[tempkey] = null
			}
		}
	}
}
