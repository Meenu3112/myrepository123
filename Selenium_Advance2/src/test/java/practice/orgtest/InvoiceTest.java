package practice.orgtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
//@Listeners(com.comcast.crm.listenerutility.ListenerImp.class)
public class InvoiceTest extends BaseClass {

	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle,"login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
	}
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println("step1");
		System.out.println("Step-2");
	}
}
