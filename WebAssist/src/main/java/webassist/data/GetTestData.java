package webassist.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class GetTestData {

	public static String getOutputTestData(String path,String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(System.getProperty("user.dir") + "/" + path);
			//Load q-i properties file
			prop.load(input);
			//get the property value and return it to caller
			return prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@DataProvider(name = "ASSearchOutput") 
	public static Object[][] outputFieldValues() {

		String path="testdata/ASSearch/ASSearchTest.properties";

		return new Object[][] { { GetTestData.getOutputTestData(path,"uinField"), 
			GetTestData.getOutputTestData(path,"uin") },
			{ GetTestData.getOutputTestData(path,"pasField"), 
			GetTestData.getOutputTestData(path,"pas") },
			{ GetTestData.getOutputTestData(path,"testCenterField"), 
			GetTestData.getOutputTestData(path,"testCenterCode") },
			{ GetTestData.getOutputTestData(path,"adminField"), 
			GetTestData.getOutputTestData(path,"admin") },
			{ GetTestData.getOutputTestData(path,"regField"), 
			GetTestData.getOutputTestData(path,"reg") },
			{ GetTestData.getOutputTestData(path,"lastNameField"), 
			GetTestData.getOutputTestData(path,"lastName") },
			{ GetTestData.getOutputTestData(path,"firstNameField"), 
			GetTestData.getOutputTestData(path,"firstName") },
			{ GetTestData.getOutputTestData(path,"dobField"), 
			GetTestData.getOutputTestData(path,"dob") }
		};

	}
	
	@DataProvider(name = "ASSearchInputUIN") 
	public static Object[][] inputFieldValues() {

		String path="testdata/ASSearch/ASSearchTest.properties";

		return new Object[][] { { GetTestData.getOutputTestData(path,"uinSearchType"), 
			GetTestData.getOutputTestData(path,"uin") }
		};

	}

}
