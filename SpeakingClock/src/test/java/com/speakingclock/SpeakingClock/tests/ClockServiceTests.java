package com.speakingclock.SpeakingClock.tests;

import com.speakingclock.SpeakingClock.exception.TimeConversionException;
import com.speakingclock.SpeakingClock.model.SpeakingClock;
import com.speakingclock.SpeakingClock.service.ClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClockServiceTests {

    private ClockService clockService;

    @BeforeEach
    public void setUp() {
        clockService = new ClockService();
    }

    @Test
    public void testValidTimeConversion() {
        SpeakingClock result = clockService.convertTimeToWords("10:26");
        assertEquals("It's ten twenty six", result.getTimeInWords());
    }

    @Test
    public void testInvalidTimeFormat() {
        TimeConversionException exception = assertThrows(TimeConversionException.class, () -> {
            clockService.convertTimeToWords("12;48");
        });
        assertEquals("Invalid time format: 12;48", exception.getMessage());
    }

    @Test
    public void testNegativeHours() {
        TimeConversionException exception = assertThrows(TimeConversionException.class, () -> {
            clockService.convertTimeToWords("-2:30");
        });
        assertEquals("Hour in time is invalid. Cannot exceed 24 or cannot be negative", exception.getMessage());
    }

    @Test
    public void testInvalidMinutes() {
        TimeConversionException exception = assertThrows(TimeConversionException.class, () -> {
            clockService.convertTimeToWords("09:-35");
        });
        assertEquals("Minutes in time is invalid. Cannot exceed 60 or cannot be negative", exception.getMessage());
    }

    @Test
    public void testValidMinimumTime() {
        SpeakingClock result = clockService.convertTimeToWords("00:00");
        assertEquals("It's zero", result.getTimeInWords());
    }

    @Test
    public void testValidMaximumTime() {
        SpeakingClock result = clockService.convertTimeToWords("23:59");
        assertEquals("It's twenty three fifty nine", result.getTimeInWords());
    }

    @Test
    public void testInvalidHourAbove24() {
        TimeConversionException exception = assertThrows(TimeConversionException.class, () -> {
            clockService.convertTimeToWords("25:30");
        });
        assertEquals("Hour in time is invalid. Cannot exceed 24 or cannot be negative", exception.getMessage());
    }

    @Test
    public void testInvalidMinutesAbove60() {
        TimeConversionException exception = assertThrows(TimeConversionException.class, () -> {
            clockService.convertTimeToWords("12:61");
        });
        assertEquals("Minutes in time is invalid. Cannot exceed 60 or cannot be negative", exception.getMessage());
    }
}
