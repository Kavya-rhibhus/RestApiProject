package com.baseclass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
	
	
	public  String formattedTime;
	public   String formattedDate;
	
	public   String   ReportName;
	public ExtentReports extent;
	public ExtentTest test;
	 String  Reportpath="C:\\Users\\desktop\\eclipse\\RestApi_Automation_All_Projects\\ApiAutomation\\src\\test\\java\\com\\";
	
    public String  SpryntzReportPath = Reportpath+"SpryntzApiTesting\\Spryntz-Api-Report\\";
    public String  RedevRaffoluxReportPath = Reportpath+"RedevApiTesting\\Redev-Raffolux-Api-Report\\";
    public String  UpSchoolReportPath = Reportpath+"UpSchoolApiTesting\\UpSchool-Api-Report\\";
    public String  FactoReportPath=Reportpath+"FactoApiTesting\\Facto-Api-Report";
     
     
     
     
     
     
     
}  
