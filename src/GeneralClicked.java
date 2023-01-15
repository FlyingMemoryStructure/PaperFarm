import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 The GeneralClicked class is where the MouseListener of most buttons are
 implemented, mostly the action buttons where the class passes the desired
 action selected by a Player to the primarily logic or model of the program.
*/
public class GeneralClicked implements MouseListener {
		String sMode = "Placeholder";
		
		/*
		 This acquires the action selected or clicked by a Player
		*/
		public GeneralClicked(String sMode) {
				this.sMode = sMode;
		}
		
		/*
		 This passes the action selected or clicked by a Player to CoreLogic or the model
		*/
		protected void passMode(String sMode){
				CoreLogic.sMode = sMode;
		}
		
		/*
		 This calls the passMode method based on some conditions for some actions (for example:
		 if the Player has enough Objectcoins to mine) to pass the selected action of the Player
		 to the CoreLogic or model.
		*/
	    public void mouseClicked(MouseEvent e) {
	    	    if(sMode == "UpgradeAsk")
	    	    		CoreLogic.mainView.renderUpgradeConfirmation();
	    	    
	    	    //Select action
	    	    if(CoreLogic.sMode != this.sMode && CoreLogic.sMode != "Plant") { //Prevent switching to another action while logic loop is paused
	                    if(!(this.sMode == "Mine" && Player.Objectcoins < 50)) //Just to avoid having to reset everytime an attempt to mine was made while poor
	                    		CoreLogic.sMode = this.sMode;
	                    else
	                    		CoreLogic.sRecentAction = "Recent Action: Failed to mine (You're too poor)";
	    	    }
	    	    //Cancel action
	    	    else if(CoreLogic.sMode != "Plant" && CoreLogic.sMode != "Wait") //Prevent switching to another action while logic loop is paused, and avoid canceling wait when double clicking too fast
	    	    	    CoreLogic.sMode = "Reset";
	    }
	
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

}
