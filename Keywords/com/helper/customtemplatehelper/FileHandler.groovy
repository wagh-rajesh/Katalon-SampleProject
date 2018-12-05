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
	public boolean verifyFileData(String pdfFilePath, List planNames) {
		String modifiedInputFilePath = 'file:' + pdfFilePath.replaceAll('/', '///')
		JsonElement jsonfileElement = new JsonPrimitive(modifiedInputFilePath)
		URL TestURL = new URL(jsonfileElement.getAsString())
		BufferedInputStream bis = new BufferedInputStream(TestURL.openStream());
		PDDocument doc = PDDocument.load(bis);
		String pdfText = new PDFTextStripper().getText(doc);
		doc.close();
		bis.close();
		println("\n################## Downloaded PDF contents ##################\n")
		println(pdfText)
		for( String planName in planNames) {
			if(pdfText.contains(planName)) {
				println(planName + " -> Present in PDF data")
			} else {
				KeywordUtil.markFailedAndStop("[Error] : '" + planName + "' Not present in PDF.")
			}
		}
		println("\n################## End of PDF Contents ######################\n")
	}
}
