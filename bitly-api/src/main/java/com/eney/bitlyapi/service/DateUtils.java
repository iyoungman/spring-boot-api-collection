package com.eney.bitlyapi.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YoungMan on 2019-05-21.
 */

public class DateUtils {

	public static String convertToHour(long time) {
		Date date = new Date(time * 1000L);
		Format format = new SimpleDateFormat("yyyy-MM-dd'T'HH");
		return format.format(date);
	}

	public static String convertToDay(long time) {
		Date date = new Date(time * 1000L);
		Format format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String convertToWeek(long time) {
		Date date = new Date(time * 1000L);
		Format format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String convertToMonth(long time) {
		Date date = new Date(time * 1000L);
		Format format = new SimpleDateFormat("yyyy-MM");
		return format.format(date);
	}
}
