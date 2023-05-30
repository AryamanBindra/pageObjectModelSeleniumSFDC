package testdata;

import org.testng.annotations.DataProvider;

public class LoginData {
	
	@DataProvider(name = "LoginData")
	public Object[][] getData(){
		Object[][] data = {{"jul22.aryaman@ta.com", "Ary123man"}};
		return data;
	}

}
