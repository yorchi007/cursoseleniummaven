package Utilitarios;


import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ExtentUtil {

    static ExtentReports extentReports=null;
    static Map<Long,ExtentTest> extentTestMap = new HashMap<Long, ExtentTest>();

    public static ExtentReports createReporter(String fileName){
        //HTML Reporter
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(fileName);
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        //extentHtmlReporter.config().setChartVisibilityOnOpen(true);
        //extentHtmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\configuracionReporte\\extent-config.xml"),false);
        //extentHtmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\recursos\\configuracionReporte\\extent-config.xml"),false);
        
        
        
        String css = "";
		try {
			css = UtilitariosIO.retornarString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    extentHtmlReporter.config().setCSS(css);
	    extentHtmlReporter.config().setEncoding("UTF-8");
	    extentHtmlReporter.config().setProtocol(Protocol.HTTPS);
	    extentHtmlReporter.config().setTimeStampFormat("MMM-dd-yyyy HH:mm:ss a");
	    
 
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);
        //System Info
        try {
            extentReports.setSystemInfo("HostName", InetAddress.getLocalHost().getHostName());
            extentReports.setSystemInfo("IP Address",InetAddress.getLocalHost().getHostAddress());
            extentReports.setSystemInfo("OS",System.getProperty("os.name"));
            extentReports.setSystemInfo("UserName",System.getProperty("user.name"));
            extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return extentReports;
    }

    public static ExtentReports getExtentReports(){
        return extentReports;
    }

    public static void saveReporter(){
        if(extentReports !=null){
            extentReports.flush();
        }
    }

    public static synchronized ExtentTest createTest(String testName){
        ExtentTest extentTest = extentReports.createTest(testName);
        extentTestMap.put(Thread.currentThread().getId(),extentTest);
        return extentTest;
    }

    public static synchronized ExtentTest createTest(String testName,String testDesc){
        ExtentTest extentTest = extentReports.createTest(testName,testDesc);
        extentTestMap.put(Thread.currentThread().getId(),extentTest);
        return extentTest;
    }

    public static synchronized ExtentTest fetchTest(){
        return extentTestMap.get(Thread.currentThread().getId());
    }


}