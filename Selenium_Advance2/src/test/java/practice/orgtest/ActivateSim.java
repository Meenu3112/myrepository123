package practice.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class ActivateSim extends BaseClass{
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerimp.class)
	public void activateSim() {
		System.out.println("......Activate Sim.........");
		Assert.assertEquals("", "login");
	}

}
