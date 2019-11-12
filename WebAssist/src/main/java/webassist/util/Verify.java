package webassist.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Verify {
	
	private WebDriver wd;
	private Util util;
	
	By commentList = By.xpath("//virtual-scroller//div[contains(@class,'text')]");

    public Verify(WebDriver wd){
        this.wd = wd;
        util = new Util(wd);
    }
    
    /**
     * Wrong Input Validation
     */
    
    public boolean validateWrongID() {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		if(js.executeScript("return document.querySelectorAll('.cdk-live-announcer-element').length;").toString().equals("1")) {
			return true;
		} else {
			return false;
		}
	}
    
    /**
     * To verify input fields are Clear 
     * @param fieldsName
     * @return
     */
    
    public boolean verifyClearFields(String fieldsName) {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		String[] arrFields = fieldsName.split(",");
		String javascriptString = "";
		boolean flag = false;
		
		for(String st : arrFields) {
			javascriptString = "var a = document.querySelectorAll('mat-form-field input');" + 
					"var b;" +  
					"for(var i=0;i<a.length;i++){" + 
					"if(a[i].placeholder==='" + st + "'){" +  
					"b=a[i].value;" + 
					"break;" + 
					"}" +  
					"};"+
					"return b;";
			if(js.executeScript(javascriptString).toString().equals("")) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
    
    /**
     * To verify Comment is Posted
     * @param comment
     * @return
     */
    
    public boolean verifyComment(String comment) {
		boolean flag = false;
		List<WebElement> comments = wd.findElements(commentList);
		for(int i = 0; i< wd.findElements(commentList).size(); i++) {
			if(comments.get(i).getText().trim().equalsIgnoreCase(comment)&&
					util.isVisible("(//virtual-scroller//div[contains(@class,'text')])[" + (i+1) + "]", 10)) {
				flag=true;
				break;
			}
			else {
				flag=false;
			}
		}
		return flag;
	}
    
    /**
     * To verify Comment is Deleted
     * @param comment
     * @return
     */
    
    public boolean verifyDeletedComment(String comment) {
		boolean flag = false;
		List<WebElement> comments = wd.findElements(commentList);
		for(int i = 0; i< wd.findElements(commentList).size(); i++) {
			if(!(comments.get(i).getText().trim().equalsIgnoreCase(comment)&&
					util.isVisible("(//virtual-scroller//div[contains(@class,'text')])[" + (i+1) + "]", 10))) {
				flag=true;
				break;
			}
			else {
				flag=false;
			}
		}
		return flag;
	}
    
    /**
     * To verify the Output Field Values
     * @param ansSheetField
     * @param ansSheetFieldValue
     * @return
     */
    
    public boolean verifyASDetailsPageValue(String ansSheetField, String ansSheetFieldValue) {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		String javascriptString = "";
		boolean flag = false;
		javascriptString = "var a = document.querySelectorAll('mat-form-field input');" + 
				"var b;" +  
				"for(var i=0;i<a.length;i++){" + 
				"if(a[i].placeholder==='" + ansSheetField + "'){" +  
				"b=a[i].value;" + 
				"break;" + 
				"}" +  
				"};"+
				"return b;";
		if(js.executeScript(javascriptString).toString().equals(ansSheetFieldValue)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
    
    public boolean verifyREGDetailsPageValue(String regSheetField, String regSheetFieldValue) {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		String javascriptString = "";
		boolean flag = false;
		javascriptString = "var a = document.querySelectorAll('mat-form-field input');" + 
				"var b;" +  
				"for(var i=0;i<a.length;i++){" + 
				"if(a[i].placeholder==='" + regSheetField + "'){" +  
				"b=a[i].value;" + 
				"break;" + 
				"}" +  
				"};"+
				"return b;";
		if(js.executeScript(javascriptString).toString().equals(regSheetFieldValue)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
    
    
}
