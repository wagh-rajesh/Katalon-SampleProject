import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

CustomKeywords.'com.helper.login.LoginHelper.loginToApplication'()

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.scrollToTemplate'(templateName)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.selectTemplate'(templateName)

WebUI.delay(GlobalVariable.timeoutFiveSec)

CustomKeywords.'com.helper.customtemplatehelper.LocationSelector.selectLocation'(locationType, locationName, GlobalVariable.timeoutTenSec)

CustomKeywords.'com.helper.customtemplatehelper.PlanOrPayerSelector.selectPlanOrPayer'(planOrPayer)

if (planOrPayerNames instanceof String ) {
	planOrPayerNames = CustomKeywords.'com.helper.customtemplatehelper.DataFormatter.formatData'(planOrPayerNames)
}

List<String> planOrPayerNamesList = []

if (planOrPayer == 'shire_plan') {
	planOrPayerNamesList = CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planOrPayerNames)
} else {
	hasPlanOrPayer = CustomKeywords.'com.helper.customtemplatehelper.PlanOrPayerSelector.hasPlanOrPayerStep'()
	planOrPayerNamesList = CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(planOrPayerNames, hasPlanOrPayer, planOrPayer)
}

CustomKeywords.'com.helper.customtemplatehelper.CriteriaVerifier.verifyCriteria'(templateName, locationName, sellSheetName, planOrPayerNamesList)

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

WebUI.delay(3)

String downloadedFile = CustomKeywords.'com.helper.customtemplatehelper.FileHandler.isFileDownloaded'(templateName, locationName)

CustomKeywords.'com.helper.customtemplatehelper.FileHandler.verifyFileData'(downloadedFile, planOrPayerNamesList)

WebUI.delay(3)

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

