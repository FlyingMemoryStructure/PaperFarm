import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 The Perspective class listens whether 'E' or 'e' is pressed on the keyboard.
 This will toggle the two different perspectives of the game which are side view
 and top view (farm view/perspective).
*/
public class Perspective implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e)
		{
	
		}
		
		@Override
		public void keyPressed(KeyEvent e)
		{
				if (e.getKeyCode() == 'E')
						if(!CoreLogic.bFarmPerspective)
								CoreLogic.bFarmPerspective = true;
						else
								CoreLogic.bFarmPerspective = false;
		}
		@Override
		public void keyReleased(KeyEvent e)
		{
			
		}

}