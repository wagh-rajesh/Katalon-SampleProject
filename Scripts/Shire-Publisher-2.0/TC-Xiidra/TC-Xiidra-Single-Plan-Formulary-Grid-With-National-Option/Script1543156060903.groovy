import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.scrollToTemplate'(templateName)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'(templateName)

WebUI.delay(GlobalVariable.timeoutFiveSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'(locationType, GlobalVariable.timeoutThirtySec)

CustomKeywords.'com.helper.customtemplatehelper.PlanOrPayerSelector.selectPlanOrPayer'(planOrPayer)

if (planOrPayer == 'shire_plan') {
	CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planOrPayerName)
} else {
	CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(planOrPayerName)
}

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName, planOrPayerName)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

WebUI.delay(3)

String downloadedFile = CustomKeywords.'com.helper.customtemplatehelper.FileHandler.isFileDownloaded'(templateName, locationName)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.verifyFileData'(downloadedFile)

WebUI.delay(3)

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

