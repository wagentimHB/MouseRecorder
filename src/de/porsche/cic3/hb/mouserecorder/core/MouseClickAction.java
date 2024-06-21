package de.porsche.cic3.hb.mouserecorder.core;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Logger;

import com.github.kwhat.jnativehook.GlobalScreen;


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

        Thread t = new Thread(new Runnable(){

            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            
            @Override
            public void run() {
                while(!stop){

                   
                }
            }

        });

        t.start();
    }

    public void stop(){
        this.stop = true;
    }
    
}
