package com.speakingclock.SpeakingClock.tests;


import com.speakingclock.SpeakingClock.model.SpeakingClock;
import com.speakingclock.SpeakingClock.service.MidDayOrNightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MidDayOrNightServiceTests {
	
	private MidDayOrNightService midDayOrNightService;
	
	@BeforeEach
	public void setUp() {
		midDayOrNightService= new MidDayOrNightService();
	}
	
	@Test
	public void testMidnightConversion() {
		SpeakingClock result = midDayOrNightService.convertTimeToWords("00:00");
		assertEquals("It's Midnight", result.getTimeInWords());
	}
	
	@Test
	public void testMiddayConversion() {
		SpeakingClock result = midDayOrNightService.convertTimeToWords("12:00");
		assertEquals("It's Midday", result.getTimeInWords());
	}
	
}
