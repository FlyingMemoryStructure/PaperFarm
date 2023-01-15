import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 The PlantClicked class is a special GeneralClicked variant specifically for the "plant" action,
 which involves the extra condition and calls of having enough Objectcoins for the cheapest plant
 and rendering of the seed selection. This also temporarily pauses the game's primary model or logic
 in order to avoid any conflicts such as being stuck with planting and the seed selection display
 while changing perspectives, where it is impossible to do farming actions in a side view perspective.
*/
public class PlantClicked extends GeneralClicked implements MouseListener {
    //We may opt to extend GeneralClicked as the method to pass the sMode is the same, extra processes will be added in the mouseClicked
	String sMode = "Placeholder";
	
	/*
	 Acquire the selected action of the Player, which is to plant.
	*/
    public PlantClicked(String sMode) {
			super(sMode);
	}

    /*
	 If a player has enough Objectcoins for the cheapest plant, render the seed selection upon
	 clicking the plant action button.
	*/
	public void mouseClicked(MouseEvent e) {
    	    //Select action
    	    if(CoreLogic.sMode != "Plant") {
    	    		if(Player.Objectcoins >= 5) {
    	    			    CoreLogic.sRecentAction = "Recent Action: Showing affordable seeds...";
    	    			    CoreLogic.mainView.reset();
		                    CoreLogic.mainView.renderSeedSelection();
		                    Main.bLogicPause = true;
    	    		}
    	    		
                    passMode("Plant");
    	    }
    	    //To cancel planting, select another action
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
