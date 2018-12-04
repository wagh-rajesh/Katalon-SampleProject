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

if (planOrPayer == 'shire_plan') {
	CustomKeywords.'com.helper.customtemplatehelper.PlansSelector.selectPlan'(planOrPayerNames)
} else {
	hasPlanOrPayer = CustomKeywords.'com.helper.customtemplatehelper.PlanOrPayerSelector.hasPlanOrPayerStep'()
	CustomKeywords.'com.helper.customtemplatehelper.PayerSelector.selectPayer'(planOrPayerNames, hasPlanOrPayer)
}

CustomKeywords.'com.helper.customtemplatehelper.CustomTemplate.clickViewPdf'()

CustomKeywords.'com.helper.login.LoginHelper.logoutApplication'()

CustomKeywords.'com.helper.browserhelper.CustomBrowser.closeBrowser'()

