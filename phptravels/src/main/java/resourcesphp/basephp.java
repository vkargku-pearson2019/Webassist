package resourcesphp;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class basephp {
		
		private final String CHROME_PATH = "./driver/chromedriver.exe";
		public static WebDriver wd;
		
		/**
		 * Launch Browser
		 * @param browserType
		 * @return
		 */
		public WebDriver launchBrowser(String browserType) {
			if(browserType.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", CHROME_PATH);
				wd = new ChromeDriver();
			}
			wd.manage().window().maximize();
			return wd;
		}
		

	}


