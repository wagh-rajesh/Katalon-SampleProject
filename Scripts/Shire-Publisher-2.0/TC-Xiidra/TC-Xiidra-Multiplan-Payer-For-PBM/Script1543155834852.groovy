import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Driver as Driver
import java.sql.DriverManager as DriverManager
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import junit.framework.Assert as Assert

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'(templateName)

WebUI.delay(GlobalVariable.timeoutFiveSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'('states', 'Alaska', GlobalVariable.timeoutTenSec)

CustomKeywords.'com.helper.customtemplatehelper.CopaySelector.selectCopayOption'()

CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(payerName)

CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planNames, true)

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName, 
    payerName, planNames)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

WebUI.delay(3)

String downloadedFile = CustomKeywords.'com.helper.customtemplatehelper.FileHandler.isFileDownloaded'(templateName, locationName)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.verifyFileData'(downloadedFile)

WebUI.delay(3)

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyOrderPDFBtn'()

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

