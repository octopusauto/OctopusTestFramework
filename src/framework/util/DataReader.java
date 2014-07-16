package framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


/**
 * The datareader will read data.xml based on the Schema and objects generated
 * in TestSchemaGenerator project
 * 
 * data.xml will be generated in folder 'testdatagenerator'
 */
public class DataReader {

	public static void main(String[] args) {
		DataReader reader = new DataReader();
		generated.Data dataReadFromXML=reader.readConfig();
		List<generated.Test> testCaseList=dataReadFromXML.getTest();
		
		for (Iterator<generated.Test> iterator = testCaseList.iterator(); iterator.hasNext();) {
			generated.Test testCase = (generated.Test) iterator.next();
			System.out.println("Test Case details read from XML "+ testCase.getUsername()+testCase.getPassword());	
		}
	}
	
	
	/**
	 * @param testScenarioNumber
	 * @return
	 */
	public static List<generated.Test> readFromXML(String testScenarioNumber)
	{	
		DataReader reader = new DataReader();
		generated.Data dataReadFromXML=reader.readConfig();
		List<generated.Test> testCaseList=dataReadFromXML.getTest();
		List<generated.Test> testCaseWithSameScenarioNumber = new ArrayList<generated.Test>();
		
		for (Iterator<generated.Test> iterator = testCaseList.iterator(); iterator.hasNext();) {
			generated.Test testCase = (generated.Test) iterator.next();
	
			
			if(testCase.getName().equalsIgnoreCase(testScenarioNumber))
			{
				System.out.println("Add the scenario in the new list read from xml , scenarionumber is "+testScenarioNumber);

				System.out.println("test case data is "+testCase.getUsername()+" "+testCase.getPassword() );
				testCaseWithSameScenarioNumber.add(testCase);
			}
			else{
				continue;
			}
			
		}
	
		return testCaseWithSameScenarioNumber;
	}
	

	
	/**
	 * This method reads data.xml file from SchemaGenerator jar and generate it in the "src/testdatagenerator/' folder
	 * Later this data.xml extracted from jar will be read using the objects generated in the jar
	 */
	public void resdInputStreamConvertToFile()
	{
		InputStream inputStream = null;
		OutputStream outputStream = null;
	 
		try {
			// read this file into InputStream
		//	inputStream = new FileInputStream("/Users/mkyong/Downloads/holder.js");
			inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("data.xml");
			System.out.println("File input stream is");
	 
			// write the inputStream to a FileOutputStream
		//	outputStream =new FileOutputStream(new File("/Users/pushpinderrattan/Documents/workspaceKepler/SeleniumWebDriver/src/testdatagenerator/data.xml"));

			outputStream = new FileOutputStream(new File("src/testdatagenerator/data.xml"));
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	  }
	
	
	public generated.Data  readConfig()
	{
		generated.Data data = null;
		 try {
	 
			resdInputStreamConvertToFile();
			
			System.out.println("File is created from classloader");
			
			File file = new File("src/testdatagenerator/data.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(generated.Data.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			data = (generated.Data) jaxbUnmarshaller.unmarshal(file);
	 
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return data;
	}
	
	
}
