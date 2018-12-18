package com.helper.customtemplatehelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.pdfbox.pdfparser.PDFParser
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class FileHandler {

	public static String actualFileName

	@Keyword
	public String isFileDownloaded(String templateName, String locationName) {
		String fileName = getDownloadedFileName(templateName, locationName)
		String downloadPath = getDownloadDirPath()
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		String lastAttempt = '';
		if (dirContents.length > 0) {
			for (int i = 0; i < dirContents.length; i++) {
				if (dirContents[i].getName().contains(fileName)) {
					actualFileName = dirContents[i].toString()
					KeywordUtil.markPassed(actualFileName + ' exist in ' + downloadPath + ' and was deleted')
					return actualFileName
				}
			}
		}
	}

	public String getDownloadDirPath() {
		String home = System.getProperty('user.home')
		String userDownloadsPath = new File(home + '/Downloads/')
		return userDownloadsPath
	}

	public String getDownloadedFileName(String templateName, String locationName){
		String fileName = ''
		TimeZone.setDefault(TimeZone.getTimeZone('EST'))
		String date = new Date().format( 'MMddyy' ).toString()
		def splittedTemplateName = templateName.toLowerCase().split(' ')
		def tempName = splittedTemplateName.join('-')
		fileName = tempName + '_' + locationName + '_' + date
		return fileName
	}

	@Keyword
	public boolean verifyFileData(String pdfFilePath) {
		String modifiedInputFilePath = 'file:' + pdfFilePath.replaceAll('/', '///')
		JsonElement jsonfileElement = new JsonPrimitive(modifiedInputFilePath)
		URL TestURL = new URL(jsonfileElement.getAsString())
		BufferedInputStream bis = new BufferedInputStream(TestURL.openStream());
		PDDocument doc = PDDocument.load(bis);
		String pdfText = new PDFTextStripper().getText(doc);
		doc.close();
		bis.close();
		println("################## Downloaded PDF contents ##################\n")
		println(pdfText)
		println("################## End of PDF Contents ######################\n")
	}

	@Keyword
	public boolean verifyFileData(String pdfFilePath, def plansRowData, List pdfColumnData) {
		String modifiedInputFilePath = 'file:' + pdfFilePath.replaceAll('/', '///')
		println("Downloaded File Path ------------------> " + modifiedInputFilePath)
		WebUI.delay(GlobalVariable.timeoutFiveSec)
		JsonElement jsonfileElement = new JsonPrimitive(modifiedInputFilePath)
		URL TestURL = new URL(jsonfileElement.getAsString())
		BufferedInputStream bis = new BufferedInputStream(TestURL.openStream());
		PDDocument doc = PDDocument.load(bis);
		String pdfText = new PDFTextStripper().getText(doc);
		doc.close();
		bis.close();
		println("\n################## Downloaded PDF contents ##################\n")
		println(pdfText)
		println("\n################## End of PDF Contents ######################\n")
		// Pass all PDF column data to verify here
		println("\n\n\nData to be verified from UI based on rows selected : -----------------> \n" + plansRowData + "\n\n\n")
		plansRowData.each { planName, planData ->
			if(pdfText.contains(planName)) {
				println(planName + " -----------------> Present in PDF data")
				for (String pdfCol in pdfColumnData) {
					println("Checking '" + pdfCol + "' present in PDF Data")
					if (planData != null || planData.size() > 0) {
						def valueToVerfiy = planData.find{ it.key == pdfCol }?.value
						pdfText.contains(valueToVerfiy.trim())
						println(valueToVerfiy + " --------------> Present in PDF data")
					} else {
						KeywordUtil.markWarning("[Error] : '" + valueToVerfiy + "' Not present in PDF.")
					}
				}
			} else {
				KeywordUtil.markFailedAndStop("[Error] : '" + planName + "' Not present in PDF.")
			}
		}
		println("\n\n\n################## End of verifications contents ######################\n\n\n")
	}
	
	@Keyword
	public boolean deleteDownloadedFile(String pdfFileName) {
		println("\n\n################## Deleting File ##################\n\n")
		println("File Name : ----------> " + pdfFileName)
		def downloadedFile = new File(pdfFileName)
		downloadedFile.delete();
		WebUI.delay(GlobalVariable.timeoutTwoSec)
		println("\n\n################## End - Delete File ##################\n\n")
	}
}
