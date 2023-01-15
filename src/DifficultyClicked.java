import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 The DifficultyClicked class primarily handles the difficulty
 delegation according to what difficulty a Player clicked at the
 start of the game. This dictates the rock layout and amount.
*/
public class DifficultyClicked implements MouseListener {
		String sDifficulty = "Placeholder";
		
		/*
		 This allows the acquisition of the difficulty clicked by the player.
		*/
		public DifficultyClicked(String sDifficulty) {
				this.sDifficulty = sDifficulty;
		}
		
		/*
		 This passes the acquired difficulty selected by the player to the
		 backbone of the game which handles the rock formations.
		*/
		private void passDifficulty(String sDifficulty){
				CoreLogic.sDifficulty = sDifficulty;
		}
		
		/*
		 This calls the passDifficulty method upon clicking of a difficulty button
		 to dictate the game's difficulty and rock formation. This also initializes the
		 game's preliminary variables to start running the primary loop.
		*/
	    public void mouseClicked(MouseEvent e) {
	    	    passDifficulty(this.sDifficulty);
	    	    CoreLogic.Initialize();
	    	    CoreLogic.mainView.reset();
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
