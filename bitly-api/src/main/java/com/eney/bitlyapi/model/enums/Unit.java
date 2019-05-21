package com.eney.bitlyapi.model.enums;

import com.eney.bitlyapi.service.DateUtils;

/**
 * Created by YoungMan on 2019-05-21.
 */

public enum Unit {

	hour {
		public String convertLongToDateStr(long date) {
			return DateUtils.convertToHour(date);
		}
	},
	day {
		public String convertLongToDateStr(long date) {
			return DateUtils.convertToDay(date);
		}
	},
	week {
		public String convertLongToDateStr(long date) {
			return DateUtils.convertToWeek(date);
		}
	},
	month {
		public String convertLongToDateStr(long date) {
			return DateUtils.convertToMonth(date);
		}
	};

	public abstract String convertLongToDateStr(long date);

}
