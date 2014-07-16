package framework.util;

import java.util.Iterator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	public class DataProviderTest {

		
	/**
	 * This method will read "test3" data set test case from data.xml from TestSchema.jar
	 * 
	 * @return
	 */
	@DataProvider(name = "Authentication")
	  public static Object[][] credentials() {
		  
		  List<generated.Test> testcaseList=DataReader.readFromXML("test3");
		  
	        return new Object[][] { { testcaseList }};
	  }
	  
	  @Test(dataProvider = "Authentication")
	  public void testDataprovider(List<Object> testCase)
	  {
		  for (Iterator<Object> iterator = testCase.iterator(); iterator.hasNext();) {
		     generated.Test test = (generated.Test) iterator.next();
			System.out.println("test case is "+test.getUsername()+"  "+test.getPassword()+" firstname if any"+test.getEmpfirsname()+" lastname if any"+test.getEmplastname()+"Spouse name , if any"+test.getEmpSpouseName());
		}
		  
	  }
	  
	}

