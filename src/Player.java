/* MyFarm by Michael Gabriel A. Del Rosario [12137766] of CCPROG3 S13 */

/*
 This is the class for the Player, which should only have a single instance in the main method in
 class Main as there is only one user at a time. This class contains the Objectcoins for
 purchasing seeds and using tools (default 100), the bonus according to farmer type (default 0),
 the level (default 0), the experience accumulated (default 0), and the farmer type (default "Farmer").
 This class also contains the methods to update the player's level according to the experience accumulated,
 and the method to upgrade the player's farmer type according to the level, in which both shall be implemented
 by phase 2, as instructed.
*/
public class Player {
        public static int Objectcoins = 100, nBonusEarnings = 0, nDiscount = 0, 
        		          nWaterBonus = 0, nFertilizerBonus = 0;
        public static int nLevel = 0;
        public static double nExperience = 0;
        public static String sFarmerType = "Farmer";
        
        /*
         The updatePlayerLevel() method should update the player's level according to experience
         accumulated, said experience shall be checked in order to dictate whether a Player is
         going to level up or not.
        */
        public static void updatePlayerLevel() {
        	    if(nExperience >= (100 * (nLevel + 1))) { //Check if eligible to level up (Parentheses formatting important)
        	    		nLevel++; //Level up
	        	     	CoreLogic.mainView.reset(); //Update level in stats display
        	    }
        }
        
        /*
         The upgradePlayer() method should upgrade the player's farmer type according to the level
         and Objectcoins (if enough), conditions to check whether a Player is eligible for such
         is handled in the CoreLogic.
        */
        public static void upgradePlayer() {
        	    switch(sFarmerType) {
		        	    case "Farmer":
		        	    		sFarmerType = "Registered Farmer";
		        	    		nBonusEarnings = 1;
		        	    		nDiscount = 1;
		        	    		break;
		        	    case "Registered Farmer":
		        	    		sFarmerType = "Distinguished Farmer";
		        	    		nBonusEarnings = 2;
		        	    		nDiscount = 2;
		        	    		nWaterBonus = 1;
		        	    		break;
		        	    case "Distinguished Farmer":
		        	    		sFarmerType = "Legendary Farmer";
		        	    		nBonusEarnings = 4;
		        	    		nDiscount = 3;
		        	    		nWaterBonus = 2;
		        	    		nFertilizerBonus = 1;
		        	    		break;
        	    }
        }
}
