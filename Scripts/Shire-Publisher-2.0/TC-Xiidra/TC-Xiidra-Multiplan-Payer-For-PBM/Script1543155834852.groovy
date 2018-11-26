import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.scrollToTemplate'('Xiidra Multiplan Payer for PBM')

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'('Xiidra Multiplan Payer for PBM')

WebUI.delay(GlobalVariable.timeoutFiveSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'('states', 'Alaska', GlobalVariable.timeoutTenSec)

CustomKeywords.'com.helper.customtemplatehelper.CopaySelector.selectCopayOption'()

CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'('Premera Blue Cross')

CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'('Premera Blue Cross 3-Tier')

CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'('Premera Blue Cross 4-Tier')

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

