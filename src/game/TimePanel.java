package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;


public class TimePanel extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;//We aren't serializing, but Eclipse thinks we might
	JLabel time;
    Timer t ;
    JButton start ;
    public TimePanel(){

        t= new Timer();

        time = new JLabel("Time goes here", JLabel.CENTER);
        start = new JButton ("Start");

        start.addActionListener(new TimeListener());
        add(time);
        add(start);

        java.util.Timer updateTimer= new java.util.Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
        	int s=0;
        	
        @Override
        public void run() {
            //update Panel text
        	s++;
        	int displays,m;
        	m=s/60;
        	displays=s%60;
        	update(displays,m);
        }
        }, 0, 1000);
    }    

    public void update(int s, int minute){
        String sec = Integer.toString(s);
        String min = Integer.toString(minute);
        if (s<=10){
            sec="0"+sec;
        }

        System.out.println(min+":"+sec);
        time.setText(min+":"+sec);
    }


class TimeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}
}