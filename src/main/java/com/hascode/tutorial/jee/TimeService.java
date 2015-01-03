package com.hascode.tutorial.jee;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TimeService {
	@Inject
	private LocaleManager localeManager;

	public String getLocalizedTime(final Date date, final User user) {
		return String.format("hello %s: the date converted is %s", user.getName(), localeManager.getSpecialDateFormat().format(date));
	}
}
