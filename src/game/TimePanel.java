/**
 * Adopted from code provided at http://stackoverflow.com/questions/18926839/timer-stopwatch-gui/18926890#18926890
 */
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
    Timer t;
    int playerTurn;
    //JButton start ;
    public TimePanel(GUI gui, Game game, int startTime, JLabel timerLabel){

        t= new Timer();
        playerTurn =game.getPlayerTurn();
//        time = new JLabel("Time goes here", JLabel.CENTER);
//        time.setForeground(Color.BLACK);
//        time.setVisible(true);
        //start = new JButton ("Start");

        //start.addActionListener(new TimeListener());
//        add(time);
        //add(start);
        
        time = timerLabel;

        java.util.Timer updateTimer= new java.util.Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
        	int s=startTime;
        	
        @Override
        public void run() {
        	if(game.getWinner()!=0){
        		updateTimer.cancel();
        		return;
        	}
            //update Panel text
        	if(playerTurn==game.getPlayerTurn()){
        		s--;
        	}
        	else{
        		s=startTime;
        		playerTurn=game.getPlayerTurn();
        	}
        	if(s==0){
        		int winner=1;
        		if(game.getPlayerTurn()==1)
        			winner=2;
        		game.setWinner(winner);
        		gui.renderBoard();//to show winner pane
        		}
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
        if (s<10){
            sec="0"+sec;
        }

//        System.out.println(min+":"+sec);
        time.setText("<html> <b>"+min+":"+sec+"</b> </html>");
    }


class TimeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}
}