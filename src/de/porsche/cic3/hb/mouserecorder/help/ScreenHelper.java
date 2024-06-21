package de.porsche.cic3.hb.mouserecorder.help;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public final class ScreenHelper {

	private final static ScreenHelper screenHelper = new ScreenHelper();
	
	private ScreenHelper() {}
	
	public final static ScreenHelper getInstance() {
		return screenHelper;
	}
	
	/**
	 * By each call the current screen size will be calculated
	 * @return {@link Point}
	 */
	public final Dimension getMonitorSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
}
