package de.wagentim.core;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.wagentim.helper.FileHelper;


public final class MouseManager {
	
	private final static int TRACKING_DELAY = 10;
	private final static int TRACKING_PERIOD = 100;
	private final static int RECORD_SEC = 5000;
	private int limitation = RECORD_SEC / TRACKING_PERIOD;
	private static volatile int counter = 0;
	private final FileHelper fileHelper;
	private final static String FILE = "\\MouseRecorder\\position.txt";
	private final static String CLICK = "click";
	
	public MouseManager() {
		fileHelper = FileHelper.getInstance();
		counter = 0;
	}
	
	public void trackingMouseMovement() {
		Timer recordTimer = new Timer();
		recordTimer.scheduleAtFixedRate(new RecordMouse(recordTimer, FILE), TRACKING_DELAY, TRACKING_PERIOD);
	}
	
	public void replayMouseMovement() {
		
		List<String> lines = fileHelper.readMouseMovement(FILE); // read contents line by line to the List container
		limitation = lines.size();
		String[] contents = (String[]) lines.toArray(new String[limitation]);	// convert the list to the array //TODO: remove this part
		Timer replayTimer = new Timer();
		try {
			Robot robot = new Robot();
			replayTimer.scheduleAtFixedRate(new ReplayMouseMovement(contents, robot, replayTimer), TRACKING_DELAY, TRACKING_PERIOD);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void click(int x, int y) throws AWTException{
		Robot bot = new Robot();
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	
	private class ReplayMouseMovement extends TimerTask {
		
		private final String[]  movements;
		private final Robot robot;
		private final Timer replayTimer;
		
		public ReplayMouseMovement(final String[] movements, final Robot robot, Timer replayTimer) {
			this.movements = movements;
			this.robot = robot;
			this.replayTimer = replayTimer;
		}

		@Override
		public void run() {
			
			if(counter == limitation) {
				this.cancel();
				counter = 0;
				replayTimer.cancel();
				return;
			}
			
			String content = movements[counter];
			int index = content.indexOf(':');
			int x = Integer.parseInt(content.substring(0, index));
			int y = Integer.parseInt(content.substring(index + 1, content.length()));
//			System.out.println("x: " + x + " -> " + "y: " + y);
			robot.mouseMove(x, y);
			counter++;
		}
		
	}
	
	private class RecordMouse extends TimerTask{

		private final Timer recordTimer;
		private final String file;
		private final StringBuilder sb;
		
		public RecordMouse(Timer recordTimer, String file) {
			this.recordTimer = recordTimer;
			this.file = file;
			sb = new StringBuilder();
		}

		@Override
		public void run() {
			PointerInfo pointInfo = MouseInfo.getPointerInfo();
			int x = (int) pointInfo.getLocation().getX();
			int y = (int) pointInfo.getLocation().getY();
//			System.out.println("X: " + x + " : Y: " + y);
			sb.append(x + ":" + y + "\n");
			int buttonNummer = MouseInfo.getNumberOfButtons();
			System.out.println("Button Number: " + buttonNummer);
			if(counter == limitation) {
				this.cancel();
				counter = 0;
				recordTimer.cancel();
				fileHelper.writeToFile(sb.toString(), file);
			}
			
			counter++;
		}

	}
}
