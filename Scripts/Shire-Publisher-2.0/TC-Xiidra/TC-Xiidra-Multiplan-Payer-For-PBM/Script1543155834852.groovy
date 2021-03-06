import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'(templateName)

WebUI.delay(GlobalVariable.timeoutTenSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'(locationType, locationName, GlobalVariable.timeoutTenSec)

CustomKeywords.'com.helper.customtemplatehelper.CopaySelector.selectCopayOption'()

if (payerName instanceof String ) {
	payerName = CustomKeywords.'com.helper.customtemplatehelper.DataFormatter.formatData'(payerName)
}

CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(payerName, false, 'payer')

if (planNames instanceof String ) {
	planNames = CustomKeywords.'com.helper.customtemplatehelper.DataFormatter.formatData'(planNames)
}

def planOrPayerNamesList = [:]
planOrPayerNamesList = CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planNames)

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName, 
    planOrPayerNamesList)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

WebUI.delay(3)

String downloadedFile = CustomKeywords.'com.helper.customtemplatehelper.FileHandler.isFileDownloaded'(templateName, locationName)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.verifyFileData'(downloadedFile, planOrPayerNamesList, pdfDataColumns)

WebUI.delay(3)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.deleteDownloadedFile'(downloadedFile)

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyOrderPDFBtn'()

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

