package com.speakingclock.SpeakingClock.service;

import com.speakingclock.SpeakingClock.exception.TimeConversionException;
import com.speakingclock.SpeakingClock.model.SpeakingClock;
import org.springframework.stereotype.Service;

@Service
public class MidDayOrNightService {

	public SpeakingClock convertTimeToWords(String time) {

		try {
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			String timeInWords = "It's ";

			if (hours < 0 || hours >= 24) {
				throw new TimeConversionException(
						"Hour in time " + time + " is invalid. Cannot exceed 24 or cannot be negative");
			}

			if (minutes < 0 || minutes >= 60) {
				throw new TimeConversionException(
						"Minutes in time " + time + " is invalid. Cannot exceed 60 or cannot be negative");
			}

			if (hours == 0) {
				timeInWords += "Midnight";
			} else if (hours == 12) {
				timeInWords += "Midday";
			} else {
				timeInWords += "Neither Midday nor Midnight";
			}

			SpeakingClock speakingClock = new SpeakingClock();
			speakingClock.setTime(time);
			speakingClock.setTimeInWords(timeInWords);

			return speakingClock;

		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			throw new TimeConversionException("Invalid time format : " + time);
		}
	}
}
