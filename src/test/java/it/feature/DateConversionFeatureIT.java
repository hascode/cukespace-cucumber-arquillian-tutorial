package it.feature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import com.hascode.tutorial.jee.LocaleManager;
import com.hascode.tutorial.jee.TimeService;
import com.hascode.tutorial.jee.User;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/it/feature/date_conversion.feature" })
public class DateConversionFeatureIT {
	@Deployment
	public static JavaArchive createArchiveAndDeploy() {
		return ShrinkWrap.create(JavaArchive.class).addClasses(LocaleManager.class, TimeService.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	TimeService timeService;

	User user;

	Date rawDate;

	@Given("^a user named '(.+)'$")
	public void create_user_with_name(final String name) throws Throwable {
		user = new User(name);
	}

	@When("^this user enters the date '(.+)' into the time conversion service$")
	public void this_user_enters_the_date_into_the_time_conversion_service(@Format("yyyy-MM-dd HH:mm:ss") final Date date) throws Throwable {
		rawDate = date;
	}

	@Then("^the service returns a conversion hint with the message '(.*)'$")
	public void the_service_returns_a_converted_date(final String dateConverted) throws Throwable {
		assertThat(timeService.getLocalizedTime(rawDate, user), equalTo(dateConverted));
	}

}
