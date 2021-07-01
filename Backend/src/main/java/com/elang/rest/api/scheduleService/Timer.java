package com.elang.rest.api.scheduleService;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Timer {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void cekMenangLelang() {
		
		final Runnable beeper = new Runnable() {
			public void run() { System.out.println("beep"); }
		};
		
		scheduler.scheduleAtFixedRate(beeper, 3, 3, SECONDS);
	}
}