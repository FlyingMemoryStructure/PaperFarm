import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/*
 The CoreLogic class acts as the primary hub for model under the context of MVC architecture.
 This is where all classes interact with each other when needed, an example of this is the
 reduction of a Player's Objectcoins and experience gain after purchasing a seed. Additionally,
 this also contains the variables that dictate whether a game will continue to run or not where
 needed, and the checking if the game is over when a player runs out of living plants and Objectcoins
 to purchase more. It also contains the code for the bonus feature of changing perspectives, as well
 as the calls for refreshing the visuals of the program (view of MVC which is tied to the controller).
*/
public class CoreLogic{
		public static boolean bRunning = false;
		private static boolean bSwitchMode = false;
        private static int nTileX = 140, nTileY = 200;
		public static int nLevelRequirement = 5, nRegistrationFee = 200; //Fee starts at 200 and level starts at 5 for registered farmer
        public static int nDay = 1, time1 = 1, time2 = time1 * 2; //time2 formula
        public static boolean bFarmPerspective = true;
        public static String sMode = "Placeholder", sTemp = "Placeholder",
	                         sRecentAction = "Recent Action:", sDifficulty = "Easy"; 
        
		/*
         This is a placeholder seed acting as a sentinel of sorts
         for empty tiles (tiles with no seeds) upon initialization of which.
        */
        private static Seed seed = new Seed("Placeholder", "Placeholder", -888,
                    -888, -888, -888, -888, -888, -888, -888, -888);
        
        public static Tile tileTemp = new Tile(false, false, false, false, false, 
                                        false, seed, 0, 0);
		
		/* 
         Each tile will be treated as separate objects to handle
         the individual states of such.
        */
        public static TileLabel tilelabel[][] = new TileLabel[10][5];
        public static MainView mainView = new MainView();
		
		public static void Initialize() {
			    int i = 0, j = 0;
			    char RockLayout[][] = new char [10][5];
			    
			    /*
				 This is where a rock formation layout will be loaded from a txt file
				 according to a difficulty selected by a player.
				*/
			    try {
			    		File RockLayoutFile = new File("src/RockLayout/Easy.txt"); //Default is easy
			    		
			    		switch(sDifficulty) {
					    		case "Intermediate":
					    				RockLayoutFile = new File("src/RockLayout/Intermediate.txt");
					    				break;
					    		case "Hard":
						    			RockLayoutFile = new File("src/RockLayout/Hard.txt");
					    				break;
			    		}
			    		
				        FileReader fr = new FileReader(RockLayoutFile); 
				        BufferedReader br = new BufferedReader(fr);
				        int c = 0;             
				        try {
								while((c = br.read()) != -1) //Read char by char
								{
								        RockLayout[i][j] = (char) c; //Convert int to char
								
								      	i++; 
								    	if(i == 10) {
								    			i = 0;
								    			j++; //j will never exceed 5, thus no need for a reset of which
								    	}
								}
	
					        
								br.close();
						} catch (IOException e) {
								e.printStackTrace();
						}
			    } catch (FileNotFoundException e) {
				        System.out.println("Error in reading rock layout file");
				        e.printStackTrace();
			    }
			    
			    if(!bRunning) 
			    	    bRunning = true;
			    
		       /*
	            Initialization of the theoretical 10x5 plot of land
	            and the individual tiles.
	           */
	           for(j = 0; j < 5; j++) {
	                  for(i = 0; i < 10; i++) {
	                	      tilelabel[i][j] = new TileLabel(); //Initialize
	                	      tilelabel[i][j].tile = new Tile(false, false, false, false, false, 
	                                                          false, seed, 0, 0);
	                	      tilelabel[i][j].tile.x = nTileX + i * 55;
	                	      tilelabel[i][j].tile.nPrincipalY = nTileY;
	                	      tilelabel[i][j].tile.y = tilelabel[i][j].tile.nPrincipalY;
	                	      if(RockLayout[i][j] == 'O')
	                	    	  	tilelabel[i][j].tile.bRock = true;
	                	      /*
	                	       In Core(), we only need to check the existence of a rock when plowing since
	                	       the other actions will check if a tile is plowed or has a plant, which wouldn't
	                	       be true if it has never been plowed, thus no need to check for a rock everytime.
	                	      */
	                  }
	                  nTileY += 55;
	                  nTileX -= 10;
	           }
		}
		
		/*
	     The primary simulation's loop, consequential logic of objects' methods
	     with regards to other objects are implemented here.
	    */
	    public static void Core() {
	    	           int i = 0, j = 0, x = 10, y = 5;
	    	           
			           /*
			            Do respective action.
			           */
			           switch(sMode) {
			                   case "Plant":
			                	   	   bSwitchMode = true;
			                	       if(Player.Objectcoins >= 5) {
			                           		   for(j = 0; j < y; j++)
						                               for(i = 0; i < x; i++)					                    	    	   
			                	                               if(tilelabel[i][j].tile == tileTemp) {
							                   	                       if(!tilelabel[i][j].tile.bOccupied && tilelabel[i][j].tile.bPlowed) { //If tile is empty, plowed, and has no rock
							                   	                    	   	   boolean bSuccess = false;
							                   	                    	   
			                   	                                               switch(sTemp) {
			                   	                                                       case "Turnip": //If turnip was inputed
	                   	                                                                       seed = new Seed("Turnip", "Root Crop", 2, 2, 1, 1, 0, 
	                   	                                                                              ThreadLocalRandom.current().nextInt(1, 2 + 1), 
	                   	                                                                              5, 6, 5); //Initialize seed for the desired tile
			                   	                                                               
	                   	                                                                       bSuccess = true;
	                   	                                                                       
			                   	                                                               break;
				                   	                                                    case "Rose": //If rose was inputed
	                   	                                                                       seed = new Seed("Rose", "Flower", 1, 2, 1, 1, 0, 1, 
	                   	                                                                              5, 5, 2.5); //Initialize seed for the desired tile
			                   	                                                               
	                   	                                                                       bSuccess = true;
	                   	                                                                       
	                   	                                                                       break;
					                   	                                                case "Carrot": //If carrot was inputed
	                 	                                                                       seed = new Seed("Carrot", "Root Crop", 3, 2, 1, 1, 0, 
	                 	                                                                    		  ThreadLocalRandom.current().nextInt(1, 2 + 1), 
	                 	                                                                              10, 9, 7.5); //Initialize seed for the desired tile
			                   	                                                              
	                 	                                                                       bSuccess = true;
	                 	                                                                       
	                 	                                                                       break;
					                   	                                                case "Potato": //If potato was inputed
		               	                                                                        seed = new Seed("Potato", "Root Crop", 5, 4, 3, 2, 1, 
		               	                                                                    	       ThreadLocalRandom.current().nextInt(1, 10 + 1), 
		               	                                                                               20, 3, 12.5); //Initialize seed for the desired tile
				                   	                                                              
		               	                                                                        bSuccess = true;
		               	                                                                       
		               	                                                                        break;
					                   	                                                case "Tulip": //If tulip was inputed
		               	                                                                       seed = new Seed("Tulip", "Flower", 2, 3, 2, 1, 0, 1, 
		               	                                                                              10, 9, 5); //Initialize seed for the desired tile
			                   	                                                               
		               	                                                                       bSuccess = true;
		               	                                                                       
		               	                                                                       break;
					                   	                                                case "Sunflower": //If sunflower was inputed
					                   	                                                	   seed = new Seed("Sunflower", "Flower", 3, 3, 2, 2, 1, 1, 
	               	                                                                              20, 19, 7.5); //Initialize seed for the desired tile
		                   	                                                               
					                   	                                                	   bSuccess = true;
	               	                                                                       
					                   	                                                	   break;
					                   	                                                case "Mango": //If mango was inputed
					                   	                                                	   if(treeValid(tilelabel, i, j)) { //If not planted on corners
				               	                                                                       seed = new Seed("Mango", "Fruit tree", 10, 7, 7, 4, 4, 
				               	                                                                    	   	  ThreadLocalRandom.current().nextInt(5, 15 + 1), 
				               	                                                                              100, 8, 25); //Initialize seed for the desired tile
					                   	                                                              
				               	                                                                       bSuccess = true;
					                   	                                                	   }
					                   	                                                	   else
					                   	                                                		   	   sRecentAction = "Recent Action: Failed to plant (Next to corners/sides/obstacles)";
		               	                                                                       
		               	                                                                       break;
						                   	                                             case "Apple": //If apple was inputed
					                   	                                                	   if(treeValid(tilelabel, i, j)) { //If not planted on sides/corners or next to obstacles
				               	                                                                       seed = new Seed("Apple", "Fruit tree", 10, 7, 7, 5, 5, 
				               	                                                                    	   	  ThreadLocalRandom.current().nextInt(10, 15 + 1), 
				               	                                                                              200, 5, 25); //Initialize seed for the desired tile
					                   	                                                              
				               	                                                                       bSuccess = true;
					                   	                                                	   }
					                   	                                                	   else
					                   	                                                		   	   sRecentAction = "Recent Action: Failed to plant (Next to corners/sides/obstacles)";
		               	                                                                       
		               	                                                                       break;
			                   	                                                }
			                   	                                    
			                   	                                                if(bSuccess) {
					                   	                                                Player.Objectcoins -= (seed.nCost - Player.nDiscount); //Deduct Objectcoins from the player
					                   	                                               	sRecentAction = "Recent Action: Planted " +seed.sName;
			                   	                                                	    tilelabel[i][j].tile.plantTile(seed); //Place the desired seed onto the desired tile
			                   	                                                	    tilelabel[i][j].tile.bOccupied = true; //Update tile's occupancy (bOccupied)
			                   	                                                }
	                   	                                                	    j = y; //End loop
	                   	                                                	    i = x; //End loop
							                   	                       }
							                   	                       /*
							                   	                        If the tile coordinate input is occupied or not plowed (bOccupied = true or bPlowed = false)
							                   	                       */
							                   	                       else {
							                   	            	               System.out.println("This tile's not availabe...");
							                   	            	               sRecentAction = "Recent Action: Failed to plant (Invalid tile)";
								                   	            	           j = y; //End loop
	                   	                                                	   i = x; //End loop
							                   	                       }
							                   	                       
							                   	                       mainView.reset(); //Remove seed selection
							                   	               }
						                    	       }
							                   	       /*
							                   	        If the player does not have enough Objectcoins for the cheapest seed (currently 5 Objectcoins)
							                   	       */
							                   	       else {
							                   	        	   mainView.reset(); //Remove seed selection
							                   	    	       System.out.println("You're too poor to buy seeds...");
							                   	    	       sRecentAction = "Recent Action: Failed to plant (You're too poor)";
							                   	       }
							                           
							                           tileTemp = new Tile(false, false, false, false, false, 
				                                               			   false, seed, 0, 0);
							                           sTemp = "Placeholder"; //Deselect seed
							                           sMode = "Placeholder"; //Deselect action
			                           
			                           break;
			                   case "Wait":
			                   	       nDay++; //Advance to the next day.
			                   	       /*
			                   	        Update each tile's consequences after advancing to the next day.
			                   	       */
			                   	       for(i = 0; i < x; i++)
			       	                           for(j = 0; j < y; j++) {
			       	                        	       /*
			       	                        	        If a tile is occupied, advance its local nDayCount for its seed's progress.
			       	                        	       */
			       	                    	           if(tilelabel[i][j].tile.bOccupied) {
			       	                    	        	       tilelabel[i][j].tile.nDayCount++;
			       	                    	        	       /*
			       	                    	        	        If a tile is fully fertilized according to its needs.
			       	                    	        	       */
			       	                    	        	       if(tilelabel[i][j].tile.seed.nFertilizerNeeds == tilelabel[i][j].tile.nFertilizerCount)
			       	                    	        	    	       tilelabel[i][j].tile.bFertilized = true;
			       	                    	        	       /*We can't update this in a tileFertilizer method since this needs to always
			       	                    	        	         be updated as if we never call the said method and a plant has a fertilizer
			       	                    	        	         need of 0, then the boolean bFertilized will never be true and thus the tile
			       	                    	        	         won't be ready for harvest (bReady = true) as it relies on which.*/
			       	                    	           }
			       	                    	           /*
			       	                    	            If a tile's seed did not reach the required water and fertilizer needs, or
			       	                    	            if a tile's plant was not harvested on the day it should be harvested.
			       	                    	           */
			                   	                       if(((!tilelabel[i][j].tile.bWatered || !tilelabel[i][j].tile.bFertilized) 
			                   	                          && tilelabel[i][j].tile.nDayCount == tilelabel[i][j].tile.seed.nHarvestTime)
			                   	                          || (tilelabel[i][j].tile.nDayCount == tilelabel[i][j].tile.seed.nHarvestTime + 1)) {
			                   	                    	       tilelabel[i][j].tile.bWithered = true; //The plant shall wither
			                   	                    	       tilelabel[i][j].tile.bReady = false; //Disable harvest capability
			                   	    	               }
			                   	                       /*
			                   	                        If a tile reached the required water and fertilizer needs.
			                   	                       */
			                   	                       else if(tilelabel[i][j].tile.bWatered && tilelabel[i][j].tile.bFertilized 
			                   	                    		   && !tilelabel[i][j].tile.bWithered && tilelabel[i][j].tile.bFertilized 
			                   	                    		   && tilelabel[i][j].tile.nDayCount == tilelabel[i][j].tile.seed.nHarvestTime)
			                   	                    	       tilelabel[i][j].tile.bReady = true;
			       	                           }
			                   	       
			                   	       sRecentAction = "Recent Action: Waited";
			                   	       mainView.reset();
			                   	       sMode = "Placeholder";
			                   	       
			                    	   break;
			                   case "Plow":
			                	       bSwitchMode = true;
			                	       for(j = 0; j < y; j++)
						                       for(i = 0; i < x; i++)
			                	                       if(tilelabel[i][j].tile == tileTemp) {
				                                               if(!tilelabel[i][j].tile.bPlowed &&
						                   	                      !tilelabel[i][j].tile.bRock) {
						                       	                       Player.nExperience += 0.5;
						                       	                       System.out.println("0.5 Experience earned");
						                       	                       sRecentAction = "Recent Action: Plowed tile";
				                                               }
				                                               else if (tilelabel[i][j].tile.bRock) {
				                                            	   	   sRecentAction = "Recent Action: Failed to plow (Mine the rock first)";
				                                               }
				                                               else
				                                            	       sRecentAction = "Recent Action: Failed to plow tile (Already plowed)";
				                        	    
						                                       tilelabel[i][j].tile.plowTile(); //Plow the desired tile.
						                                       tileTemp = new Tile(false, false, false, false, false, 
				                                                                   false, seed, 0, 0);
						                                       
						                                       mainView.reset();
						                                       j = y; //End loop
				                       	                       i = x; //End loop
			                	                       }
			                	       
				                       break;
			                   case "Water":
			                	       bSwitchMode = true;
				                	   for(j = 0; j < y; j++)
					                           for(i = 0; i < x; i++)
		                	                           if(tilelabel[i][j].tile == tileTemp) {
							                           	       if(tilelabel[i][j].tile.bOccupied) {
							                           	    	       /*
							                           	    	        If the tile's seed is not fully watered (accounting for the bonus limit).
							                           	    	       */
							                           	    	       if(tilelabel[i][j].tile.nWaterCount != tilelabel[i][j].tile.seed.nWaterNeeds - 1 
							                           	    	          + tilelabel[i][j].tile.seed.nBonus1 + Player.nWaterBonus && !tilelabel[i][j].tile.bWithered) {
							                           	                       Player.nExperience += 0.5; //Add respective experience.
							                           	                       System.out.println("0.5 Experience earned"); //Display gained experience.
							                           	                       sRecentAction = "Recent Action: Watered " +tilelabel[i][j].tile.seed.sName;
							                           	    	       }
							                           	    	       else if(!tilelabel[i][j].tile.bWithered)
							                           	    	    	   	   sRecentAction = "Recent Action: Failed to water (Reached capacity)";
							                           	    	       else
							                           	    	    	       sRecentAction = "Recent Action: Failed to water (It's dead...)";
							                           	    	    
							                           	    	       tilelabel[i][j].tile.waterTile(); //Water the desired tile.
							                           	    	       
							                           	    	       i = x; //End loop
							                           	    	       j = y; //End loop
							                           	       }
							                           	       /*
							                           	        If the desired tile is not occupied.
							                           	       */
							                           	       else {
							                           	    	       System.out.println("Don't waste water...");
							                           	    	       sRecentAction = "Recent Action: Failed to water plant (Invalid tile)";
							                           	       }
							                           	       
							                           	       mainView.reset();
									                           tileTemp = new Tile(false, false, false, false, false, 
									                        		   			   false, seed, 0, 0);
		                	                           }
			                           
			                           break;
			                   case "Harvest":
			                	      bSwitchMode = true;
			                	      for(j = 0; j < y; j++)
			                                  for(i = 0; i < x; i++)
                	                                  if(tilelabel[i][j].tile == tileTemp) {
					                           	              if(tilelabel[i][j].tile.bOccupied) {
						                           	            	  /*
										                           	   If a tile's plant is ready for harvest.
										                           	  */
					                           	            	  	  if(tilelabel[i][j].tile.bReady) {
					                           	            	  		  
												                              /*Compute the profit with the formula: HarvestTotal + WaterBonus + FertilizerBonus, where
												                           	   HarvestTotal = ProductsProduced x (BaseSellingPricePerPiece + FarmerTypeEarningBonus),
												                           	   WaterBonus = HarvestTotal x 0.2 x (TimesCropWasWatered – 1),
												                           	   FertilizerBonus = HarvestTotal x 0.5 x TimesCropAddedFertilizer
												                           	  */
												                           	  int nProfit = (int) (tilelabel[i][j].tile.seed.nProduced * (tilelabel[i][j].tile.seed.nSellingPrice + (Player.nBonusEarnings * tilelabel[i][j].tile.seed.nProduced))
												                   	    		            + (tilelabel[i][j].tile.seed.nProduced * (tilelabel[i][j].tile.seed.nSellingPrice + (Player.nBonusEarnings * tilelabel[i][j].tile.seed.nProduced))) * 0.2 
												                   	    		            * (tilelabel[i][j].tile.nWaterCount - 1) + (tilelabel[i][j].tile.seed.nProduced 
												                   	    		            * (tilelabel[i][j].tile.seed.nSellingPrice + (Player.nBonusEarnings * tilelabel[i][j].tile.seed.nProduced))) * 0.5 + tilelabel[i][j].tile.nFertilizerCount);
										                           	    
										                           	          /*
										                           	    	   Determine tiles' plant/seed name to determine the experience gained from which.
										                           	    	  */
										                           	    	  switch (tilelabel[i][j].tile.seed.sName) {
										                           	    	          case "Turnip":
										                           	    	    	          Player.nExperience += 5; //Turnip, as instructed, awards 5 experience.
										                           	    	    	          System.out.println("5 Experience earned"); //Display experience gained.
										                           	    	    	          break;
										                           	    	  }
										                           	          Player.Objectcoins += nProfit; //Add computed profit to player's total Objectcoins.
										                           	          System.out.println("You earned: " +nProfit); //Display profit.
										                           	          System.out.println("Produce count: " +tilelabel[i][j].tile.seed.nProduced); //Display produce/harvest count.
										                           	          
										                           	          sRecentAction = "Recent Action: Harvested " +tilelabel[i][j].tile.seed.sName; //Place before harvest method to obtain plant name (before it resets)
										                           	          
										                           	          tilelabel[i][j].tile.harvestTile(); //Harvest the desired tile.
										                           	          
												                              i = x; //End loop
									                           	    	      j = y; //End loop
										                           	  }
					                           	            	  	  else if(tilelabel[i][j].tile.bWithered)
					                           	            	  		  	sRecentAction = "Recent Action: Failed to harvest (It's dead...)";
					                           	            	  	  else
					                           	            	  		  	sRecentAction = "Recent Action: Failed to harvest (Not ready)";
					                           	              }
					                           	              else
					                           	            	  	sRecentAction = "Recent Action: Failed to harvest (Invalid tile)";
					                           	              
					                           	              tileTemp = new Tile(false, false, false, false, false, 
					                           	            		  			  false, seed, 0, 0);
					                           	              mainView.reset();
                	                                  }
				                        
				                       break;
			                   case "Fertilize":
			                	       bSwitchMode = true;
			                	       if(Player.Objectcoins >= 10) {
					                           for(j = 0; j < y; j++)
							                           for(i = 0; i < x; i++)
				                	                           if(tilelabel[i][j].tile == tileTemp) {
											                           /*
											                            If a tile is occupied, advance its local nDayCount for its seed's progress.
											                    	   */
										                    	       if(tilelabel[i][j].tile.bOccupied) {
										                       	        	   /*
										                       	        	    If the desired tile has not reached the limit for fertilizer needs (accounting for the bonus limit),
										                       	        	    and if the desired tile has not withered yet.
										                       	        	   */
										                       	        	   if(tilelabel[i][j].tile.nFertilizerCount != tilelabel[i][j].tile.seed.nFertilizerNeeds 
										                               	    	  + tilelabel[i][j].tile.seed.nBonus2 + Player.nFertilizerBonus && !tilelabel[i][j].tile.bWithered &&
										                               	    	  !tilelabel[i][j].tile.bReady) {
										                               	                   Player.nExperience += 4; //Award the player with 4 experience, as instructed for the action.
										                               	                   Player.Objectcoins -= 10; //Expense for fertilizing as instructed
										                               	                   System.out.println("4 Experience earned"); //Display experience earned.
										                               	                   
										                               	                   sRecentAction = "Recent Action: Fertilized " +tilelabel[i][j].tile.seed.sName;
										                               	                   
										                               	                   tilelabel[i][j].tile.fertilizeTile(); //Fertilize the desired tile.
										                               	       }
										                       	        	   else if(tilelabel[i][j].tile.bReady)
										                       	        		   		   sRecentAction = "Recent Action: Failed to fertilize (Fully grown)";
										                       	        	   else if(!tilelabel[i][j].tile.bWithered)
										                       	        		   		   sRecentAction = "Recent Action: Failed to fertilize (Reached capacity)";
										                       	        	   else
										                       	        		   		   sRecentAction = "Recent Action: Failed to fertilize (It's dead...)";
								                       	       }
								                       	       /*
								                       	        If the desired tile is not occupied.
								                       	       */
								                       	       else {
								                       	        	   System.out.println("Don't waste fertilizer...");
								                       	        	   sRecentAction = "Recent Action: Failed to fertilize (Invalid tile)";
								                       	       }
								                    	       
								                    	       tileTemp = new Tile(false, false, false, false, false, 
						                                   			               false, seed, 0, 0);
								                    	       mainView.reset();
								                    	       i = x; //End loop
					                           	    	       j = y; //End loop
		                	                           }
			                	       }
			                	       else {
			                	    	       sRecentAction = "Recent Action: Failed to fertilize (You're too poor)";
			                	    	       mainView.reset();
			                	    	       sMode = "Placeholder";
			                	       }
			                           
				                       break;
			                   case "Upgrade":
			                	       bSwitchMode = true;
			                	   	   if(Player.nLevel >= nLevelRequirement && Player.nLevel > 0 && 
			                	   	      Player.Objectcoins >= nRegistrationFee && nLevelRequirement <= 15) { //15 is the maximum level requirement
			                	   		       Player.upgradePlayer();
			                	   		       Player.Objectcoins -= nRegistrationFee;
			                	   		       
			                	   		       nLevelRequirement += 5;
			                	   	           nRegistrationFee += 100;
			                	   	           
			                	   	           sRecentAction = "Upgraded farmer type";
			                	   	   }
			                	   	   else if(nLevelRequirement > 15)
			                	   		       sRecentAction = "Failed to upgrade farmer type (Reached highest type)";
			                	   	   else if(Player.nLevel < nLevelRequirement)
			                	   		       sRecentAction = "Failed to upgrade farmer type (Level up " +(nLevelRequirement - Player.nLevel) +" more times)";
			                	   	   else if(Player.Objectcoins < nRegistrationFee)
			                	   		       sRecentAction = "Failed to upgrade farmer type (You need " +(nRegistrationFee - Player.Objectcoins) +" more coins)";
			                	   	   
			                           mainView.reset();
			                           sMode = "Placeholder"; //Deselect Action
			                           
			                           break;
			                   case "Reset":
			                	   	   sMode = "Placeholder";
			                	   	   mainView.reset();
			                	   	   
			                	   	   sRecentAction = "Recent Action: Cancelled action";
			                	   	   
			                	   	   break;
			                   case "Shovel":
			                	       bSwitchMode = true;
			                	       if(Player.Objectcoins >= 7) {
						                       for(j = 0; j < y; j++)
							                       	   for(i = 0; i < x; i++)
				                	                           if(tilelabel[i][j].tile == tileTemp) {
				                	                        	   	   if((tilelabel[i][j].tile.bPlowed && tilelabel[i][j].tile.bOccupied && Player.Objectcoins >= 7) ||
				                	                        	   		   (!tilelabel[i][j].tile.bPlowed)) { //As per the specifications, there is nothing stated for using a shovel on a plowed tile, thus no effect
						                	                        	   	   Player.nExperience += 2;
						                	                        	   	   Player.Objectcoins -= 7;
				                	                        	   	   }
				                	                        	   	
					                                                   if(tilelabel[i][j].tile.bWithered)
							                       	                           sRecentAction = "Recent Action: Removed withered plant";
					                                                   else if(tilelabel[i][j].tile.bOccupied)
					                                                	   	   sRecentAction = "Recent Action: Removed plant";
					                                                   else if(tilelabel[i][j].tile.bPlowed)
				                                                	   	   	   sRecentAction = "Recent Action: Failed to shovel (cannot use on a plowed tile)";
					                                                   else
					                                            	           sRecentAction = "Recent Action: You wasted money";
					                        	    
							                                           tilelabel[i][j].tile.shovelTile(); //Plow the desired tile.
							                                           tileTemp = new Tile(false, false, false, false, false, 
					                                                                       false, seed, 0, 0);
							                                       
							                                           mainView.reset();
							                                           j = y; //End loop
					                       	                           i = x; //End loop
				                	                           }
			                	       }
			                	       else {
			                	    	       sRecentAction = "Recent Action: Failed to shovel (You're too poor)";
			                	    	       mainView.reset();
			                	    	       sMode = "Placeholder";
			                	       }
		                	       
				                	   break;
			                   case "Mine":
			                	       bSwitchMode = true;
			                	       if(Player.Objectcoins >= 50) {
					                	       for(j = 0; j < y; j++)
					                       	           for(i = 0; i < x; i++)
		                	                                   if(tilelabel[i][j].tile == tileTemp) {
		                	                                	   	   if(tilelabel[i][j].tile.bRock && Player.Objectcoins >= 50) {
		                	                                	   	           tilelabel[i][j].tile.tileMine();
		                	                                	   	           
		                	                                	   	           Player.nExperience += 15;
		                	                                	   	           Player.Objectcoins -= 50;
		                	                                	   	   
									                                           j = y; //End loop
							                       	                           i = x; //End loop
							                       	                           sRecentAction = "Recent Action: Mined rock";
		                	                                	   	   }
		                	                                	   	   else {
		                	                                	   	           sRecentAction = "Recent Action: Failed to mine (No rock here)";
		                	                                	   	           j = y; //End Loop
		                	                                	   	           i = x; //End Loop
		                	                                	   	   }
		                	                                	   	   
		                	                                	   	   mainView.reset();
		                	                                	   	   tileTemp = new Tile(false, false, false, false, false, 
		                	                                                			   false, seed, 0, 0);
		                	                                   }
			                	       }
			                	       else {
			                	    	   	   sRecentAction = "Recent Action: Cancelled mining (You're too poor)";
			                	    	   	   mainView.reset();
			                	    	   	   sMode = "Placeholder";
			                	       }
			                	       
			                	       break;
			            }
			           
			            Player.updatePlayerLevel();
			    }
			    
	    static boolean bReset = true; 
	    
	    /*
		 The run method primarily deals with the visuals of the game by calling methods
		 from MainView (view under MVC architecture context) which is tied to the Controller
		 (of MVC architecture context) in order to update the visuals accordingly to their
		 current status, per "tick" or loop in Main while the game is running. For instance,
		 this is where the calls to update a tile's visuals according to its status is placed,
		 whenever a tile gets plowed it will update the texture of said tile to that of a plowed one.
		*/
		public static void run() {
	    		int i = 0, j = 0;

	            if(!bFarmPerspective) {
	            	    sMode = "Placeholder";
	            	    time1 = 1;
	            	    time2 = time1 * 2;
	            	    int h = 30;
	            	    if(tilelabel[9][4].tile.y - h > tilelabel[0][0].tile.y) {
	            	    		bReset = true;
	            	    	
					            for(j = 4; j >= 0; j--) {
					            	    h = 30;
						                for(i = 9; i >= 0; i--) {
						                		tilelabel[i][j].tile.y += 4 - j; //j is IMPORTANT, it allows for the visual "squeeze" to happen
						                		h++;
						                	    
				                				if(tilelabel[i][j].nScale != 13 - tilelabel[i][j].h) {
				                						tilelabel[i][j].nScale++;
				                				}
						                	    //Toggle side view
						                }
					            }
	            	    }
	    		}
	            else {
	            		nTileX = 140;
	            		nTileY = 200;
        				if(tilelabel[0][0].tile.y != tilelabel[0][0].tile.nPrincipalY) { //This is a good enough indicator that it isn't in the standard farming dimensions
    							bReset = true;
    						
        						for(j = 0; j < 5; j++) {
        								for(i = 0; i < 10; i++) {
        										tilelabel[i][j].tile.y -= 4 - j; //j is IMPORTANT, it allows for the visual "unsqueeze" to happen
  	                	      
        										if(tilelabel[i][j].nScale != 5) 
        												tilelabel[i][j].nScale--;
        										
        										//Toggle top view
        								}
        						}
        				}
        				else {
	        					if(bSwitchMode) {
	        							if(sMode != "Plant")
	        									mainView.reset(); //Refresh render for select outline of new selected action
		    	    	    			bSwitchMode = false;
	        					}
        					
	        					time1 = 140;
	    						time2 = time1 * 2;
        						bReset = false;
        						mainView.renderActions();
        				}
	            }
	            
	            if(bReset)
	        			mainView.reset();
	        
	   	        /*
	   	         Display the individual tiles according to their status (plowed,
	   	         empty, occupied, ready to be harvested, withered)
	   	        */
	            for(j = 4; j >= 0; j--)
		                for(i = 9; i >= 0; i--)
		                		mainView.renderTile(tilelabel[i][j]);
	           
	            mainView.renderStats();
	            
	            mainView.mainFrame.repaint();
		}
		
		/*
		 The checkGameOver method is called per cycle of the primary loop in order to check whether
		 a Player still meets the conditions required for continuing the game or not. This primarily
		 revolves around whether a Player has enough money to buy seeds or the Player still has
		 living plants (even if the Player does not have enough money to buy more seeds) or not.
		*/
		public static void checkGameOver() {
				int i = 0, j = 0;
				boolean bAlive = true;
				
				for(j = 4; j >= 0; j--)
						for(i = 9; i >= 0; i--) {
								if((tilelabel[i][j].tile.bOccupied && !tilelabel[i][j].tile.bWithered) ||
								   Player.Objectcoins >= 5) { //5 is minimum to buy a plant (turnip or rose)
										bAlive = true;
										i = -1; //End loop
										j = -1; //End loop
								}
								else if(i == 0 && j == 0)
										bAlive = false; //No need to end loop, the loop has already ended by now
						}
				
				if(!bAlive)
						bRunning = false;
		}
		
		/*
		 the treeValid method is a utility method to check whether a tile is appropriate for
		 planting the tree according to the rules in the specifications, where there shall be no
		 obstacles (plants, rocks, withered plants) and dead-ends (edges of the plot) beside the
		 said tile.
		*/
		private static boolean treeValid(TileLabel[][] tilelabel, int i, int j) {
				if(i != 0 && j != 0 && i != 9 && j != 4 &&
     		    !tilelabel[i + 1][j + 1].tile.bOccupied && !tilelabel[i + 1][j + 1].tile.bRock &&
     		    !tilelabel[i + 1][j - 1].tile.bOccupied && !tilelabel[i + 1][j - 1].tile.bRock &&
     	  	    !tilelabel[i - 1][j + 1].tile.bOccupied && !tilelabel[i - 1][j + 1].tile.bRock &&
     		    !tilelabel[i - 1][j - 1].tile.bOccupied && !tilelabel[i - 1][j - 1].tile.bRock &&
     		    !tilelabel[i][j + 1].tile.bOccupied && !tilelabel[i][j + 1].tile.bRock &&
     	  	    !tilelabel[i + 1][j].tile.bOccupied && !tilelabel[i + 1][j].tile.bRock &&
     	        !tilelabel[i][j - 1].tile.bOccupied && !tilelabel[i][j - 1].tile.bRock &&
     	        !tilelabel[i - 1][j].tile.bOccupied && !tilelabel[i - 1][j].tile.bRock)
						return true; //Valid
				
				return false; //Invalid
		}
}
