package de.wagentim;

import java.awt.Dimension;

import de.wagentim.core.MouseManager;
import de.wagentim.helper.ScreenHelper;

public class Starter {
	

	private final ScreenHelper screenHelper;
	private final MouseManager mouseManager;
	private final long TIME_SEC = 1000*60*60*8;
	private long curr = 0;
	private int counter = 0;
	
	public Starter() {
		screenHelper = ScreenHelper.getInstance();
		mouseManager = new MouseManager();
	}
	
	public void run() {
		Dimension size = screenHelper.getMonitorSize();
		System.out.println("Width: " + size.getWidth() + " : " + "Height: " + size.getHeight());
		System.out.println("Starting Recording Mouse Position...");
//		mouseManager.trackingMouseMovement();
		curr = System.currentTimeMillis();
		while (!shouldFinish())
		{
			System.out.println("Times: " + counter);
			mouseManager.replayMouseMovement();
			try {
				counter++;
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean shouldFinish() {
		return (System.currentTimeMillis() - curr) > TIME_SEC;
	}
	
	public static void main(String[] args) {
		Starter starter = new Starter();
		starter.run();
	}
}
