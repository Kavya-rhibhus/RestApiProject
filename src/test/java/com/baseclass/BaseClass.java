package com.baseclass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
	
	
	public  String formattedTime;
	public   String formattedDate; 
	
	public   String   ReportName;
	public ExtentReports extent;
	public ExtentTest test;
    String  Reportpath="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com";
   
    public String  SpryntzReportPath = Reportpath+"\\SpryntzApiTesting\\Spryntz-Api-Report\\";
   
    public String  RedevRaffoluxReportPath = "C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\RedevApiTesting\\Redev-Raffolux-Api-Report";
   
    public String  UpSchoolReportPath = Reportpath+"UpSchoolApiTesting\\UpSchool-Api-Report\\";
    
    public String  FactoReportPath=Reportpath+"\\FactoApiTesting\\Facto-Api-Report";
    
    public String  SpaceBandReoprt  ="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\SpaceBand\\Space-bands-Report\\"; 
   
    public String  AbodooReportPath ="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\AbodooApi\\Testing\\Abodoo-testResult";
                       
    public String  BeamReportPath ="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\BeamEducationTesting\\copy\\Beam-Api-Report\\";
    public String ChariotReportPath ="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\ChariotApiTesting\\Chariot-Report\\";
    
    public String MWCReportPath ="C:\\Users\\HP\\eclipse\\newapitestingautomation\\src\\test\\java\\com\\MonacoTesting\\MWC-Api-Report\\";
    
}