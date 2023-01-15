import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 The SeedClicked class is where the MouseListener of the seed selection is
 implemented, this deals with a different variable from GeneralClicked and thus
 it warrants a separate and different MouseListener.
*/
public class SeedClicked implements MouseListener {
		private SeedLabel seed;
		
		/*
		 This is where it acquires the seed selected by the Player.
		*/
		public SeedClicked(SeedLabel seed) {
				this.seed = seed;
		}
		
		/*
		 This is where it passes the seed selected by the Player
		 to the CoreLogic to proceed with the planting with the
		 said seed.
		*/
	    public void mouseClicked(MouseEvent e) {
	    		if(CoreLogic.sTemp == "Placeholder")
	    				CoreLogic.sTemp = this.seed.sName;
	    	    
	    	    CoreLogic.mainView.renderSeedSelected(); //Notify the Player what seed was selected
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
