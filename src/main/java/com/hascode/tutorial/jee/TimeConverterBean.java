package com.hascode.tutorial.jee;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TimeConverterBean {
	private String userName;
	private Date userDate;
	private String dateConverted;

	@Inject
	private TimeService timeService;

	public void doConvert() {
		dateConverted = timeService.getLocalizedTime(userDate, new User(userName));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getDateConverted() {
		return dateConverted;
	}

	public void setDateConverted(final String dateConverted) {
		this.dateConverted = dateConverted;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(final Date userDate) {
		this.userDate = userDate;
	}
}
