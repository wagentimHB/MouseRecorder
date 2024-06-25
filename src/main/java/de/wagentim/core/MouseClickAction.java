package de.wagentim.core;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Logger;



public class MouseClickAction {
    
    private final IMouseActionListener mouseClickNotifier;
    private volatile boolean stop = false;
    private static Robot robot = null;

    public MouseClickAction(final IMouseActionListener mouseClickNotifier){
        this.mouseClickNotifier = mouseClickNotifier;
    }

    public void tracking(){


        try {
            robot = new Robot();
        } catch (Exception e) {
            return;
        }

    }

    public void stop(){
        this.stop = true;
    }
    
}
