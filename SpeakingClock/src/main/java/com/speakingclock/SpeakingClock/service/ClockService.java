package com.speakingclock.SpeakingClock.service;

import com.speakingclock.SpeakingClock.exception.TimeConversionException;
import com.speakingclock.SpeakingClock.model.SpeakingClock;
import org.springframework.stereotype.Service;


@Service
public class ClockService {

	private final String[] numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

	private final String[] teens = { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
			"seventeen", "eighteen", "nineteen" };

	private final String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety" };

	public SpeakingClock convertTimeToWords(String time) {
		try {
			String[] parts = time.split(":");
			if (parts.length != 2) {
				throw new TimeConversionException("Invalid time format: " + time);
			}

			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);

			if (hours < 0 || hours >= 24) {
				throw new TimeConversionException("Hour in time is invalid. Cannot exceed 24 or cannot be negative");
			}
			if (minutes < 0 || minutes >= 60) {
				throw new TimeConversionException("Minutes in time is invalid. Cannot exceed 60 or cannot be negative");
			}

			String timeInWords = "It's ";

			timeInWords += convertHoursToWords(hours);
			if (minutes > 0) {
				timeInWords += " " + convertMinutesToWords(minutes);
			}

			SpeakingClock speakingClock = new SpeakingClock();
			speakingClock.setTime(time);
			speakingClock.setTimeInWords(timeInWords);

			return speakingClock;
		} catch (NumberFormatException ex) {
			throw new TimeConversionException("Invalid time format: " + time);
		}
	}

	private String convertHoursToWords(int hours) {
		if (hours < 10) {
			return numbers[hours];
		} else if (hours < 20) {
			return teens[hours - 10];
		} else {
			//System.out.println(tens[hours / 10] + (hours % 10 == 0 ? "" : " " + numbers[hours % 10]));
			return tens[hours / 10] + (hours % 10 == 0 ? "" : " " + numbers[hours % 10]);
		}
	}

	private String convertMinutesToWords(int minutes) {
		if (minutes < 10) {
			return numbers[minutes];
		} else if (minutes < 20) {
			return teens[minutes - 10];
		} else {
			return tens[minutes / 10] + (minutes % 10 == 0 ? "" : " " + numbers[minutes % 10]);
		}
	}
}
