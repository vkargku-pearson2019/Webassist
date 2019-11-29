package resourcesphp;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.Properties;

	public class Getenvironmentphp {

		/**
		 * Read environment information
		 * @param key
		 * @return
		 */
		public static String getAppEnvProperty(String key) {
			Properties prop = new Properties();
			InputStream input = null;

			try {
				input = new FileInputStream(System.getProperty("user.dir") + "/Environmentfiles/environmentphp.properties");
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


