package com.speakingclock.SpeakingClock.controller;

import com.speakingclock.SpeakingClock.exception.TimeConversionException;
import com.speakingclock.SpeakingClock.model.SpeakingClock;
import com.speakingclock.SpeakingClock.service.MidDayOrNightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/speakingClock/dayType")
public class MidDayOrNightController {

	@Autowired
	private MidDayOrNightService midDayOrNightService;

	@GetMapping("/{time}")
	public ResponseEntity convertTimeToWords(@PathVariable String time) {
		try {
			SpeakingClock speakingClock = midDayOrNightService.convertTimeToWords(time);
			return ResponseEntity.ok(speakingClock);
		} catch (TimeConversionException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception has occurred");
		}
	}
}
