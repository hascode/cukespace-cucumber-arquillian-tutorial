package com.hascode.tutorial.jee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.Stateless;

@Stateless
public class LocaleManager {
	public DateFormat getSpecialDateFormat() {
		return new SimpleDateFormat("EEE, MMM d, yyyy");
	}
}
