import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.scrollToTemplate'(templateName)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'(templateName)

WebUI.delay(GlobalVariable.timeoutFiveSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'(locationType, GlobalVariable.timeoutThirtySec)

CustomKeywords.'com.helper.customtemplatehelper.PlanOrPayerSelector.selectPlanOrPayer'(planOrPayer)

def planOrPayerNamesList = [:]

if (locationType == 'National') {
	WebUI.delay(GlobalVariable.timeoutThirtySec)
}

if (planOrPayer == 'shire_plan') {
	planOrPayerNamesList = CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planOrPayerName)
} else {
	planOrPayerNamesList = CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(planOrPayerName, hasPlanOrPayer, planOrPayer)
}

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName, planOrPayerNamesList)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

WebUI.delay(3)

String downloadedFile = CustomKeywords.'com.helper.customtemplatehelper.FileHandler.isFileDownloaded'(templateName, locationName)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.verifyFileData'(downloadedFile, planOrPayerNamesList, pdfDataColumns)

WebUI.delay(3)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.deleteDownloadedFile'(downloadedFile)

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

