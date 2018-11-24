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

WebUI.callTestCase(findTestCase('Login-TC'), [('username') : 'testdrg2@shire.com', ('password') : 'hurlDEFT53'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Common-OR/CustomTemplate/AllBrand'))

WebUI.delay(GlobalVariable.timeoutTwoSec)

WebUI.click(findTestObject('Common-OR/CustomTemplate/SelectBrand'))

WebUI.delay(GlobalVariable.timeoutFiveSec)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/button_Vyvanse State Medicaid'))

WebUI.delay(6)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/div_2            Choose a Loca'))

WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/button_Please select'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/a_Arizona'))

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/div_3            Choose Plans'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('VyvanseStateMedicaid-OR/StateMedicaidBtn'), 0)

WebUI.delay(3)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/td_Arizona State Medicaid (AHC'))

WebUI.delay(3)

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/p_4            Choose Callout'))

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/input_Next_For the majority of'))

WebUI.click(findTestObject('Object Repository/VyvanseStateMedicaid-OR/input_For the majority of pati'))

WebUI.delay(5)

WebUI.verifyElementText(findTestObject('Common-OR/CustomTemplate/CriteriaSelections/TemplateName'), 'Vyvanse State Medicaid Single Plan Flashcard')

WebUI.callTestCase(findTestCase('Logout-TC'), [('username') : '', ('password') : ''], FailureHandling.STOP_ON_FAILURE)

