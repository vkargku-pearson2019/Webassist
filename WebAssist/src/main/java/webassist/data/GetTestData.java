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

}
