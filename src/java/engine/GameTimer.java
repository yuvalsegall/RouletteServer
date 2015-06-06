/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yuvalsegall
 */
public class GameTimer{
    
    private Timer timer;
    private long startTime;
    
    public void startTimer(TimerTask task, long interval){
        timer = new Timer();
        startTime = Calendar.getInstance().getTimeInMillis();
        timer.schedule(task, interval);
    }
    
    public void stopTimer(){
        timer.cancel();
    }
    
    public int getTimeOutCount(){
        return (int)(TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTimeInMillis() - startTime));
    }
}
