import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.Timer;

/* MyFarm by Michael Gabriel A. Del Rosario [12137766] of CCPROG3 S13 */

/*
 This is the Main class containing the CoreLogic, which contains
 all the interconnected functionalities of all classes/objects, in
 which the consequences of executing a class' method to another class
 will be implemented (Example: A valid tile with a seed was watered
 through the waterTile() method of Tile class, class Player should
 earn some experience as consequent of which as was instructed).
*/
public class Main {
		public static boolean bLogicPause = false;
		
	    public static void main(String args[]) {
	    	    /*
	    	     We have to do a sentinel load first in order to avoid the program loading renderDifficulty() too fast
	    	     that by the time it adds the JLabels to the panel, the textures weren't loaded in yet.
	    	    */
	    		Controller.handleTexture(new JLabel(), "Load");
	    	
	    		while(!CoreLogic.bRunning)
                		CoreLogic.mainView.renderDifficulty();
                
	    		CoreLogic.mainView.reset();
	    		//playAudio();
	    		
                while(CoreLogic.bRunning) {
                		long Start = System.nanoTime();
                	
                		if(!bLogicPause)
                				CoreLogic.Core();
        	            
                		//Utilize Swing's timer for the rendering of visuals at a certain rate
        	            ActionListener Render = new ActionListener() {
        	    				public void actionPerformed(ActionEvent e) {
        	    						CoreLogic.run();
        	    				}
        	    		};

        	    		/*
        	    		 This process for computation of the rate in which the while loop loops or
        	    		 the game refreshes in terms of data and rendering to be precise, is formulated
        	    		 to revolve around the system's time in order to adjust accordingly to the system's
        	    		 speed in a way, 16666666 is computed from the division of 1000000000 by 60 which is
        	    		 the desired framerate of 60 frames per second, the divisor of the resultant was adjusted according
        	    		 to how my machine ran on different values for which.
        	    		*/
    	    	        long Duration = System.nanoTime() - Start;

    	    	        if(Duration >= 1000000000)
    	    	        		Duration = 0;
			    	    
    	    			Timer timer = new Timer((int) ((Duration + 16666666) / 10000000) * CoreLogic.time1, Render);
    	    			timer.start();
    	    			
			    	    try {
			    	    	    Thread.sleep(((Duration + 16666666) / 10000000) * CoreLogic.time2);
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
    	    	        timer.stop();
    	    	        
    	    	        /*
    	    	         Note: Dynamic variables time1 and time2 are needed in order to
    	    	         avoid rendering some components too quickly, which will result
    	    	         to a visual flicker of which that phases in and out whenever
    	    	         updated, this is done so by having different refresh rates for
    	    	         farming (top view) mode and side-view mode.
    	    	        */
    	    	        
    	    	        CoreLogic.checkGameOver();
                }
                
                CoreLogic.mainView.reset();
                CoreLogic.mainView.renderGameOver();
                CoreLogic.mainView.mainFrame.repaint();
	    }
	    
	    /*
		 The playAudio method initializes and plays the background music of the game.
		*/
	    public static void playAudio() {
		    	try {
		    			Clip clip = AudioSystem.getClip();
			            AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(new File("src/Audio/Music.wav").getAbsoluteFile());
			            
			            clip = AudioSystem.getClip();
			            clip.open(audioInputStream);
			            clip.loop(Clip.LOOP_CONTINUOUSLY);
			            clip.start();
		        } catch (Exception e) {
		        		e.printStackTrace();
		        }
	    }
}
