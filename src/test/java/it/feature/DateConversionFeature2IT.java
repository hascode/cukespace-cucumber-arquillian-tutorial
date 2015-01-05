package it.feature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import com.thoughtworks.selenium.DefaultSelenium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/it/feature/date_conversion.feature" })
public class DateConversionFeature2IT {
	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "sample.war").addPackages(true, "com.hascode").addAsWebResource(new File("src/main/webapp", "conversion.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
	}

	@Drone
	DefaultSelenium browser;

	@ArquillianResource
	URL deploymentURL;

	String userName;

	@Given("^a user named '(.+)'$")
	public void create_user_with_name(final String name) throws Throwable {
		userName = name;
	}

	@When("^this user enters the date '(.+)' into the time conversion service$")
	public void this_user_enters_the_date_into_the_time_conversion_service(final String dateString) throws Throwable {
		browser.open(deploymentURL + "conversion.xhtml");
		browser.type("id=timeConversion:userDate", dateString);
		browser.type("id=timeConversion:userName", userName);
		browser.click("id=timeConversion:doConvert");
		browser.waitForPageToLoad("20000");
	}

	@Then("^the service returns a conversion hint with the message '(.*)'$")
	public void the_service_returns_a_converted_date(final String dateConverted) throws Throwable {
		assertThat(browser.getValue("timeConversion:dateConverted"), equalTo(dateConverted));
	}
}
