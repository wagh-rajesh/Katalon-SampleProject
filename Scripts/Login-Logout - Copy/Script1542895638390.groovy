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

WebUI.openBrowser('')

WebUI.navigateToUrl('shire-publisher.devweb01.fingertipformulary.com')

//WebUI.setText(findTestObject('Object Repository/PO-Login/input_The username password or'), 'testdrg2@shire.com')
WebUI.setText(findTestObject('Object Repository/PO-Login/input_The username password or'), 'testdrg2@shire.com')

//WebUI.setEncryptedText(findTestObject('Object Repository/PO-Login/input_Please enter your work e'), 'IcG9dyLVxtQ3e4OpB5V01Q==')
WebUI.setEncryptedText(findTestObject('Object Repository/PO-Login/input_Please enter your work e'), 'IcG9dyLVxtQ3e4OpB5V01Q==')

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/PO-Login/input_Forgot your password_sub'))

WebUI.delay(20)

WebUI.click(findTestObject('Object Repository/PO-Login/a_Shire FF Admin'))

WebUI.click(findTestObject('Object Repository/PO-Login/a_Logout'))

WebUI.click(findTestObject('Object Repository/PO-Login/a_hide this'))

WebUI.closeBrowser()

