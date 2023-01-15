import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 The MainView class acts as the view under the MVC architecture context of the program.
 This is where texture handling methods of the controller is called to load textures
 accordingly to the states of different objects of the program. This is also where all the
 visual components of the game are initialized and organized.
*/
public class MainView {
        public JFrame mainFrame  = new JFrame("Placeholder");  
        public JPanel panel = new JPanel();
        
        /*
		 This initializes the main JFrame of the game, which includes the insertion of the
		 main JPanel to the said JFrame.
		*/
        public MainView() {
    	        this.mainFrame = new JFrame("Paper Farm");
    	        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        this.mainFrame.setSize(800, 800);
    	        
    	        this.panel.setBackground(null); //Default background color
    	        this.panel.setLayout(null);
    	        this.mainFrame.add(panel);
    	        
    	        this.mainFrame.setResizable(false);
    	        this.mainFrame.setVisible(true);
    	        this.mainFrame.addKeyListener(new Perspective());
        }

        /*
		 The renderTile method initializes the visuals of each tile and calls the
		 Controller method handleTileTexture in order to update the tile according
		 to its state.
		*/
        public void renderTile(TileLabel tilelabel) {
        	    TileClicked tileclicked = new TileClicked(tilelabel);
                
        	    Controller.handleTileTexture(tilelabel);
        	    
        	    if(!tilelabel.tile.bPlowed)
        	    		tilelabel.setBounds(tilelabel.tile.x, tilelabel.tile.y, 50, 50); //Do NOT change the last 2 parameters, it is the hitbox for clicking
        	    else
        	    		tilelabel.setBounds(tilelabel.tile.x, tilelabel.tile.y - 3, 50, 50); //Do NOT change the last 2 parameters, it is the hitbox for clicking
                tilelabel.addMouseListener(tileclicked);
                this.panel.add(tilelabel);
        }
        
        /*
		 The renderActions method initializes the visuals of the buttons for actions,
		 the textures of which are handled by the Controller method handleTexture.
		*/
        public void renderActions() {
        		//Plow button
            	JLabel PlowLabel = new JLabel();
	        	
            	Controller.handleTexture(PlowLabel, "Plow");
	        	
	        	PlowLabel.setBounds(50, 650, 70, 50);
        		PlowLabel.addMouseListener(new GeneralClicked("Plow"));  
        		this.panel.add(PlowLabel);
        		
        		//Plant button
            	JLabel PlantLabel = new JLabel();
	        	
            	Controller.handleTexture(PlantLabel, "Plant");
	        	
	        	PlantLabel.setBounds(143, 650, 70, 50);
	        	PlantLabel.addMouseListener(new PlantClicked("Plant"));  
        		this.panel.add(PlantLabel);
        		
        		//Water button
            	JLabel WaterLabel = new JLabel();
	        	
            	Controller.handleTexture(WaterLabel, "Water");
	        	
            	WaterLabel.setBounds(226, 650, 70, 50);
            	WaterLabel.addMouseListener(new GeneralClicked("Water"));  
        		this.panel.add(WaterLabel);
        		
        		//Fertilize button
            	JLabel FertilizeLabel = new JLabel();
	        	
            	Controller.handleTexture(FertilizeLabel, "Fertilize");
	        	
            	FertilizeLabel.setBounds(320, 650, 70, 50);
            	FertilizeLabel.addMouseListener(new GeneralClicked("Fertilize"));  
        		this.panel.add(FertilizeLabel);
        		
        		//Wait button
            	JLabel WaitLabel = new JLabel();
	        	
            	Controller.handleTexture(WaitLabel, "Wait");
	        	
            	WaitLabel.setBounds(410, 650, 70, 50);
            	WaitLabel.addMouseListener(new GeneralClicked("Wait"));  
        		this.panel.add(WaitLabel);
        		
        		//Harvest button
            	JLabel HarvestLabel = new JLabel();
	        	
            	Controller.handleTexture(HarvestLabel, "Harvest");
	        	
            	HarvestLabel.setBounds(490, 650, 70, 50);
            	HarvestLabel.addMouseListener(new GeneralClicked("Harvest"));  
        		this.panel.add(HarvestLabel);
        		
        		//Upgrade button
            	JLabel UpgradeLabel = new JLabel();
	        	
            	Controller.handleTexture(UpgradeLabel, "Upgrade");
	        	
            	UpgradeLabel.setBounds(4, 40, 70, 70);
            	UpgradeLabel.addMouseListener(new GeneralClicked("UpgradeAsk"));  
        		this.panel.add(UpgradeLabel);
        		
        		//Shovel button
            	JLabel ShovelLabel = new JLabel();
	        	
            	Controller.handleTexture(ShovelLabel, "Shovel");
	        	
            	ShovelLabel.setBounds(580, 650, 70, 50);
            	ShovelLabel.addMouseListener(new GeneralClicked("Shovel"));  
        		this.panel.add(ShovelLabel);
        		
        		//Mine button
            	JLabel MineLabel = new JLabel();
	        	
            	Controller.handleTexture(MineLabel, "Mine");
	        	
            	MineLabel.setBounds(670, 650, 70, 50);
            	MineLabel.addMouseListener(new GeneralClicked("Mine"));  
        		this.panel.add(MineLabel);
        		
        		//Selected outline
        		JLabel SelectedLabel = new JLabel();
        		
        		Controller.handleTexture(SelectedLabel, "Selected");
	        	
        		switch(CoreLogic.sMode) {
		        		case "Plow":
			    	        	SelectedLabel.setBounds(52, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Plant":
			    	        	SelectedLabel.setBounds(132, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Water":
			    	        	SelectedLabel.setBounds(226, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Fertilize":
			    	        	SelectedLabel.setBounds(315, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Harvest":
			    	        	SelectedLabel.setBounds(492, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Shovel":
			    	        	SelectedLabel.setBounds(580, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
		        		case "Mine":
			    	        	SelectedLabel.setBounds(665, 640, 70, 70);
			    	        	this.panel.add(SelectedLabel);
		        				break;
        		}
        }
        
        /*
		 The renderStats method initializes the display of the current stats of
		 the Player which includes the Objectcoins, experience, level, day, and
		 a note for the bonus feature of changing perspectives. These texts are handled
		 by the Controller method handleText which dictates the contents of the text, font,
		 and its size.
		*/
        public void renderStats() {
        		//Stats display
        		JLabel PlayerStatsLabel = new JLabel();
        		
        		Controller.handleText(PlayerStatsLabel, "Stats");
        		
        		if(Player.nLevel < 10)
        				PlayerStatsLabel.setBounds(150, 450, 500, 100);
        		else
        				PlayerStatsLabel.setBounds(130, 450, 510, 100);
        		
        		this.panel.add(PlayerStatsLabel);
        		
        		//Perspective note
        	    JLabel PerspectiveLabel = new JLabel();
        	
	        	Controller.handleText(PerspectiveLabel, "Perspective");
	        	
	        	PerspectiveLabel.setBounds(185, 430, 500, 100);
            	this.panel.add(PerspectiveLabel);
            	
            	//Most-recent action display
            	JLabel RecentActionLabel = new JLabel();
            	
            	Controller.handleText(RecentActionLabel, "RecentAction");
        		
            	RecentActionLabel.setBounds(15, 693, 600, 100);
        		this.panel.add(RecentActionLabel);
        		
        		JPanel RecentActionBar = new JPanel();
	    		
        		RecentActionBar.setBackground(Color.GRAY);
        		RecentActionBar.setBounds(0, 728, 800, 35);
	    		this.panel.add(RecentActionBar);
        		
        		//Farmer Type display
        		JLabel FarmerTypeLabel = new JLabel();
        		
        		Controller.handleText(FarmerTypeLabel, "FarmerType");
        		
        		FarmerTypeLabel.setBounds(12, -33, 500, 100);
        		this.panel.add(FarmerTypeLabel);
        		
        		JPanel FarmerTypeBar = new JPanel();
	    		
        		FarmerTypeBar.setBackground(Color.GRAY);
        		FarmerTypeBar.setBounds(0, 0, 800, 35);
	    		this.panel.add(FarmerTypeBar);
        }
        
        /*
		 The reset method is a utility method used in the replacement of previous visuals
		 of each object of the program, to be updated with new visuals according to its
		 current state.
		*/
        public void reset() {
                panel.removeAll();
        }

        /*
		 The renderSeedSelection method initializes the seed selection visual when a
		 Player's current action is to plant, if the player has enough Objectcoins for
		 the cheapest plan which is 5 Objectcoins. The textures of the seed selection
		 are handled by the Controller method handleTexture.
		*/
		public void renderSeedSelection() {
	        	if(Player.Objectcoins >= 5) { //If a Player has enough Objectcoins for the cheapest plant/s
	        			//Turnip button
	        		    SeedLabel TurnipSeedLabel = new SeedLabel();
	        		
	        			Controller.handleTexture(TurnipSeedLabel, "TurnipSeed");
	        		
			        	TurnipSeedLabel.sName = "Turnip";
			        	TurnipSeedLabel.setBounds(95, 590, 70, 50);
			        	TurnipSeedLabel.addMouseListener(new SeedClicked(TurnipSeedLabel));  
			    		this.panel.add(TurnipSeedLabel);
			    		
			    		SeedLabel RoseSeedLabel = new SeedLabel();
		        		
	        			Controller.handleTexture(RoseSeedLabel, "RoseSeed");
	        		
	        			RoseSeedLabel.sName = "Rose";
	        			RoseSeedLabel.setBounds(185, 590, 70, 50);
	        			RoseSeedLabel.addMouseListener(new SeedClicked(RoseSeedLabel));  
			    		this.panel.add(RoseSeedLabel);
			    		
			    		if(Player.Objectcoins >= 10) { //If a Player has enough Objectcoins for the second cheapest plant/s
				    			SeedLabel CarrotSeedLabel = new SeedLabel();
				        		
			        			Controller.handleTexture(CarrotSeedLabel, "CarrotSeed");
			        		
			        			CarrotSeedLabel.sName = "Carrot";
			        			CarrotSeedLabel.setBounds(275, 590, 70, 50);
			        			CarrotSeedLabel.addMouseListener(new SeedClicked(CarrotSeedLabel));  
					    		this.panel.add(CarrotSeedLabel);
					    		
					    		SeedLabel TulipSeedLabel = new SeedLabel();
				        		
			        			Controller.handleTexture(TulipSeedLabel, "TulipSeed");
			        		
			        			TulipSeedLabel.sName = "Tulip";
			        			TulipSeedLabel.setBounds(365, 590, 70, 50);
			        			TulipSeedLabel.addMouseListener(new SeedClicked(TulipSeedLabel));  
					    		this.panel.add(TulipSeedLabel);
					    		
					    		if(Player.Objectcoins >= 20) { //If a Player has enough Objectcoins for the third cheapest plant/s
						    			SeedLabel PotatoSeedLabel = new SeedLabel();
						        		
					        			Controller.handleTexture(PotatoSeedLabel, "PotatoSeed");
					        		
					        			PotatoSeedLabel.sName = "Potato";
					        			PotatoSeedLabel.setBounds(455, 590, 70, 50);
					        			PotatoSeedLabel.addMouseListener(new SeedClicked(PotatoSeedLabel));  
							    		this.panel.add(PotatoSeedLabel);
							    		
							    		SeedLabel SunflowerSeedLabel = new SeedLabel();
						        		
					        			Controller.handleTexture(SunflowerSeedLabel, "SunflowerSeed");
					        		
					        			SunflowerSeedLabel.sName = "Sunflower";
					        			SunflowerSeedLabel.setBounds(545, 590, 70, 50);
					        			SunflowerSeedLabel.addMouseListener(new SeedClicked(SunflowerSeedLabel));  
							    		this.panel.add(SunflowerSeedLabel);
							    		
							    				if(Player.Objectcoins >= 100) { // If a Player has enough Objectcoins for the fourth cheapest plant
								    					SeedLabel MangoSeedLabel = new SeedLabel();
										        		
									        			Controller.handleTexture(MangoSeedLabel, "MangoSeed");
									        		
									        			MangoSeedLabel.sName = "Mango";
									        			MangoSeedLabel.setBounds(635, 590, 70, 50);
									        			MangoSeedLabel.addMouseListener(new SeedClicked(MangoSeedLabel));  
											    		this.panel.add(MangoSeedLabel);
											    		
											    		if(Player.Objectcoins >= 200) { //If a Player has enough Objectcoins for the most expensive plant
												    			SeedLabel AppleSeedLabel = new SeedLabel();
												        		
											        			Controller.handleTexture(AppleSeedLabel, "AppleSeed");
											        		
											        			AppleSeedLabel.sName = "Apple";
											        			AppleSeedLabel.setBounds(365, 520, 70, 50);
											        			AppleSeedLabel.addMouseListener(new SeedClicked(AppleSeedLabel));  
													    		this.panel.add(AppleSeedLabel);
											    		}
							    				}
					    		}
			    		}
	        	}
		}
		
		/*
		 The renderSeedSelected method displays the selected seed of the Player to notify 
		 the Player what is going to be planted when clicking a valid tile.
		*/
		public void renderSeedSelected() {
				//Display seed selected for planting
	    		JLabel SeedSelectedLabel = new JLabel();
	    		
	    		Controller.handleText(SeedSelectedLabel, "SeedSelected");
	    		
	    		SeedSelectedLabel.setBounds(15, 500, 500, 100);
	    		this.panel.add(SeedSelectedLabel);
		}
		
		/*
		 The renderUpgradeConfirmation method displays a confirmation whether a Player
		 would like to proceed to the upgrade of farmer type which displays the required
		 Objectcoins and experience to do such. This also displays a yes and no button for
		 the player to respond accordingly.
		*/
		public void renderUpgradeConfirmation() {
				JLabel UpgradeConfirmationLabel = new JLabel();
				
				Controller.handleText(UpgradeConfirmationLabel, "UpgradeConfirmation");
	    		
				UpgradeConfirmationLabel.setBounds(75, 10, 500, 100);
	    		this.panel.add(UpgradeConfirmationLabel);
	    		
	    		//Yes button
            	JLabel YesLabel = new JLabel();
	        	
            	Controller.handleText(YesLabel, "Yes");
	        	
            	YesLabel.setBounds(207, 52, 70, 50);
            	YesLabel.setForeground(Color.WHITE);
        		this.panel.add(YesLabel);
        		
        		JPanel YesBox = new JPanel();
	    		
	    		YesBox.setBounds(200, 70, 40, 17);
	    		YesBox.setBackground(Color.DARK_GRAY);
	    		YesBox.addMouseListener(new GeneralClicked("Upgrade"));  
	    		this.panel.add(YesBox);
	    		
	    		//No button
            	JLabel NoLabel = new JLabel();
	        	
            	Controller.handleText(NoLabel, "No");
	        	
            	NoLabel.setBounds(260, 52, 70, 50);
            	NoLabel.setForeground(Color.WHITE);
        		this.panel.add(NoLabel);
        		
        		JPanel NoBox = new JPanel();
	    		
        		NoBox.setBounds(250, 70, 40, 17);
        		NoBox.setBackground(Color.DARK_GRAY);
        		NoBox.addMouseListener(new GeneralClicked("Reset"));  
	    		this.panel.add(NoBox);
		}
		
		/*
		 The renderDifficulty method displays the difficulty buttons which is done so
		 at the start of the game, which dictates the rock formation and amount of the game.
		*/
		public void renderDifficulty() {
			    //Difficulty label
				JLabel DifficultyLabel = new JLabel();
	        	
	        	Controller.handleTexture(DifficultyLabel, "Difficulty");
	        	
	        	DifficultyLabel.setBounds(260, 140, 350, 107);
	    		this.panel.add(DifficultyLabel);
			
	    		//Easy button
	        	JLabel EasyLabel = new JLabel();
	        	
	        	Controller.handleText(EasyLabel, "Easy");
	        	
	        	EasyLabel.setBounds(355, 203, 350, 107);
	        	EasyLabel.setForeground(Color.WHITE);
	    		this.panel.add(EasyLabel);
	    		
	    		JPanel EasyBox = new JPanel();
	    		
	    		EasyBox.setBounds(293, 240, 180, 37);
	    		EasyBox.setBackground(Color.DARK_GRAY);
	    		EasyBox.addMouseListener(new DifficultyClicked("Easy"));  
	    		this.panel.add(EasyBox);
	    		
	    		//Intermediate button
	        	JLabel IntermediateLabel = new JLabel();
	        	
	        	Controller.handleText(IntermediateLabel, "Intermediate");
	        	
	        	IntermediateLabel.setBounds(302, 253, 350, 107);
	        	IntermediateLabel.setForeground(Color.WHITE);
	    		this.panel.add(IntermediateLabel);
	    		
	    		JPanel IntermediateBox = new JPanel();
	    		
	    		IntermediateBox.setBounds(293, 290, 180, 37);
	    		IntermediateBox.setBackground(Color.DARK_GRAY);
	    		IntermediateBox.addMouseListener(new DifficultyClicked("Intermediate"));  
	    		this.panel.add(IntermediateBox);
	    		
	    		//Hard button
	        	JLabel HardLabel = new JLabel();
	        	
	        	Controller.handleText(HardLabel, "Hard");
	        	
	        	HardLabel.setBounds(354, 303, 350, 107);
	        	HardLabel.setForeground(Color.WHITE);
	    		this.panel.add(HardLabel);
	    		
	    		JPanel HardBox = new JPanel();
	    		
	    		HardBox.setBounds(293, 340, 180, 37);
	    		HardBox.setBackground(Color.DARK_GRAY);
	    		HardBox.addMouseListener(new DifficultyClicked("Hard"));  
	    		this.panel.add(HardBox);
		}
		
		/*
		 The renderGameOver method simply displays a GameOver notification for the Player
		 when the Player runs out of Objectcoins to buy seeds while having no living plants.
		*/
		public void renderGameOver() {
				JLabel GameOverLabel = new JLabel();
	        	
	        	Controller.handleTexture(GameOverLabel, "GameOver");
	        	
	        	GameOverLabel.setBounds(257, 303, 350, 107);
	    		this.panel.add(GameOverLabel);
		}
}