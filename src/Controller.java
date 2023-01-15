import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 The Controller class handles all the textures respective to the current state of an object,
 for instance, it will update the texture of a tile depending on its current status:
 utilize a plant texture if occupied by a plant (with a respective plant's texture),
 utilize a withered texture if withered, utilize a harvest texture once ready for such
 (with a respective plant's harvest texture), utilize an unplowed texture if unplowed,
 utilize a rock texture if it has a rock, etc. It will also handle the buttons' textures.
*/
public class Controller {
		/*
		 The handleTileTexture method handles the texture of each tile accordingly to its
		 state, for instance, if an unplowed tile becomes plowed, this method will handle
		 its texture to be that of a plowed tile. If an image specified is not found, it
		 will provide a text for the JLabel subclass TileLabel.
		*/
		public static void handleTileTexture(TileLabel tilelabel) {
				try {
		        	    String sPath = "Placeholder";
		        	    if(tilelabel.tile.bRock) { //If the tile has a rock
		        	    		sPath = "src/Images/Rock.png"; //Use rock texture
		        	    		tilelabel.h = 6; //Custom scaling
		        	    }
		        	    else if(!tilelabel.tile.bPlowed) { //If the tile is not plowed
		        	    		sPath = "src/Images/Unplowed.png"; //Use unplowed texture
		        	    		tilelabel.h = 4; //Custom scaling
		        	    }
		        	    else if (!tilelabel.tile.bOccupied) { //If the tile is plowed but not occupied
		        	    		sPath = "src/Images/Plowed.png"; //Use plowed texture
		        	    		tilelabel.h = 4; //Custom scaling
		        	    }
		        	    else if (!tilelabel.tile.bReady && !tilelabel.tile.bWithered) { //If the tile is occupied but not withered or ready for harvest
		        	    		tilelabel.h = 5; //Custom scaling
		        	    		
		        	    		switch(tilelabel.tile.seed.sName) { //Render plant texture according to what plant resides on a tile
		        	    				case "Turnip":
		        	    						sPath = "src/Images/TurnipSeedling.png";
		        	    						break;
		        	    				case "Rose":
		        	    						sPath = "src/Images/RoseSeedling.png";
		        	    						break;
		        	    				case "Carrot":
		        	    						sPath = "src/Images/CarrotSeedling.png";
		        	    						break;
		        	    				case "Potato":
		        	    						sPath = "src/Images/PotatoSeedling.png";
		        	    						break;
		        	    				case "Tulip":
		        	    						sPath = "src/Images/TulipSeedling.png";
		        	    						break;
		        	    				case "Sunflower":
		        	    						sPath = "src/Images/SunflowerSeedling.png";
		        	    						break;
		        	    				case "Mango":
		        	    						sPath = "src/Images/MangoSeedling.png";
		        	    						break;
		        	    				case "Apple":
		        	    						sPath = "src/Images/AppleSeedling.png";
		        	    						break;
		        	    		}
		        	    }
		        	    else if(tilelabel.tile.bReady) { //If a tile has a plant that is ready for harvest
			        	    	tilelabel.h = 6; //Custom scaling
		        	    		
		        	    		switch(tilelabel.tile.seed.sName) { //Render plant (ready) texture according to what plant resides on a tile
		        	    				case "Turnip":
		        	    						sPath = "src/Images/TurnipHarvest.png";
		        	    						break;
		        	    				case "Rose":
		        	    						sPath = "src/Images/RoseHarvest.png";
		        	    						break;
		        	    				case "Carrot":
		        	    						sPath = "src/Images/CarrotHarvest.png";
		        	    						break;
		        	    				case "Potato":
		        	    						sPath = "src/Images/PotatoHarvest.png";
		        	    						break;
		        	    				case "Tulip":
		        	    						sPath = "src/Images/TulipHarvest.png";
		        	    						break;
		        	    				case "Sunflower":
		        	    						sPath = "src/Images/SunflowerHarvest.png";
		        	    						break;
		        	    				case "Mango":
		        	    						sPath = "src/Images/MangoHarvest.png";
		        	    						break;
		        	    				case "Apple":
		        	    						sPath = "src/Images/AppleHarvest.png";
		        	    						break;
		        	    		}
		        	    }
		        	    else if(tilelabel.tile.bWithered) { //If a tile has a withered plant
			        	    	tilelabel.h = 6; //Custom scaling
		        	    		
		        	    		sPath = "src/Images/Withered.png"; //Use withered texture

		        	    }
		        	    
		                BufferedImage img = ImageIO.read(new File(sPath));
		                
		                /*
		                 This is where different scaling is handled according to what the object is
		                 (For example: a rock texture is taller than an unplowed tile texture)
		                */
		                ImageIcon icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, tilelabel.h * tilelabel.nScale + 13, Image.SCALE_DEFAULT));
		                if(tilelabel.tile.seed.sType == "Fruit tree" && tilelabel.tile.bReady) //If the tile has a tree ready for harvest
		                	    icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, tilelabel.h * tilelabel.nScale + 18, Image.SCALE_DEFAULT));
		                else if(!tilelabel.tile.bPlowed && !tilelabel.tile.bRock)
		                		icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, tilelabel.h * tilelabel.nScale, Image.SCALE_DEFAULT));
		                
		                tilelabel.setIcon(icon);
		                
		        } catch (IOException e) { //Handle exception by utilizing text instead for the JLabels
		        	    if(!tilelabel.tile.bPlowed)
		        	    		tilelabel.setText("Unplowed");
		        	    else if (!tilelabel.tile.bOccupied)
		        	    		tilelabel.setText("Plowed");
		        	    else if (!tilelabel.tile.bReady && !tilelabel.tile.bWithered)
		        	    		tilelabel.setText("|" +tilelabel.tile.seed.sName.charAt(0) +"|");
		        	    else if(tilelabel.tile.bReady)
		        	    		tilelabel.setText("Harvest");
		        	    else if(tilelabel.tile.bWithered)
		        	    		tilelabel.setText("Withered");
		        	    else if(tilelabel.tile.bRock)
	        	    		tilelabel.setText("Rock");
		        }
		}
		
		/*
		 The handleText method handles the JLabels that utilize a text instead of an image,
		 an example of this is for the yes/no button for upgrading the farmer type. It sets
		 the content of the text, format, size, and font.
		*/
		public static void handleText(JLabel Label, String sName) {
				Label.setFont(new Font("Verdana", Font.PLAIN, 13));
				Label.setText("MISSING"); //Default
			
				switch(sName) {
						case "Stats":
								Label.setFont(new Font("Verdana", Font.PLAIN, 18));
								Label.setText("Experience: " +Player.nExperience +" | Coins: " +Player.Objectcoins 
										      +" | Day: " +CoreLogic.nDay +" | Level: " +Player.nLevel);
								break;
						case "RecentAction":
								Label.setFont(new Font("Verdana", Font.PLAIN, 18));
								Label.setForeground(Color.WHITE);
								Label.setText(CoreLogic.sRecentAction);
								break;
						case "SeedSelected":
								Label.setFont(new Font("Verdana", Font.PLAIN, 18));
								Label.setText("Seed Selected: " +CoreLogic.sTemp +" (Pick a tile)...");
						        break;
						case "Perspective":
								Label.setFont(new Font("Verdana", Font.PLAIN, 14));
								Label.setText("Press E to change modes (Farming Mode : Side View)");
								break;
						case "FarmerType":
								Label.setFont(new Font("Verdana", Font.PLAIN, 17));
								Label.setForeground(Color.WHITE);
								Label.setText("Farmer Type: " +Player.sFarmerType);
								break;
						case "UpgradeConfirmation":
								Label.setFont(new Font("Verdana", Font.PLAIN, 14));
								Label.setText("Are you sure? [Level Requirement: " +CoreLogic.nLevelRequirement
										      +" | Fee: " +CoreLogic.nRegistrationFee +"]");
								break;
						case "Yes":
								Label.setFont(new Font("Verdana", Font.PLAIN, 13));
								Label.setText("Yes");
								break;
						case "No":
								Label.setFont(new Font("Verdana", Font.PLAIN, 13));
								Label.setText("No");
								break;
						case "Easy":
								Label.setFont(new Font("Verdana", Font.PLAIN, 25));
								Label.setText("Easy");
								break;
						case "Intermediate":
								Label.setFont(new Font("Verdana", Font.PLAIN, 25));
								Label.setText("Intermediate");
								break;
						case "Hard":
								Label.setFont(new Font("Verdana", Font.PLAIN, 25));
								Label.setText("Hard");
								break;
				}
		}
		
		/*
		 The handleTexture method handles the textures of all JLabels that require images
		 except for those of the tiles (the tiles have a special method to handle its
		 textures according to its states). This handles the buttons to be more precise,
		 if an image specified is not found, it will provide a JLabel with a text instead.
		*/
		public static void handleTexture(JLabel Label, String sName) {
				try {
		        		BufferedImage img = ImageIO.read(new File("src/Images/Missing.png"));
		        		ImageIcon icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)); //Default
		        		
		        		switch(sName) { //Handle texture according to the object involved
				        		case "Selected":
					        			img = ImageIO.read(new File("src/Images/Selected.png"));
					        			icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(72, 72, Image.SCALE_DEFAULT));
										break;
								case "Plow":
										img = ImageIO.read(new File("src/Images/Plow.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
										break;
								case "Plant":
										img = ImageIO.read(new File("src/Images/Plant.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
										break;
								case "Water":
										img = ImageIO.read(new File("src/Images/Water.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(72, 72, Image.SCALE_DEFAULT));
										break;
								case "Fertilize":
										img = ImageIO.read(new File("src/Images/Fertilize.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
										break;
								case "Wait":
										img = ImageIO.read(new File("src/Images/Wait.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
										break;
								case "Harvest":
										img = ImageIO.read(new File("src/Images/Harvest.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
										break;
								case "Upgrade":
										img = ImageIO.read(new File("src/Images/Upgrade.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
										break;
								case "Shovel":
										img = ImageIO.read(new File("src/Images/Shovel.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(72, 72, Image.SCALE_DEFAULT));
										break;
								case "Mine":
										img = ImageIO.read(new File("src/Images/Mine.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
										break;
								case "Difficulty":
										img = ImageIO.read(new File("src/Images/Difficulty.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(250, 60, Image.SCALE_DEFAULT));
										break;
								case "GameOver":
										img = ImageIO.read(new File("src/Images/GameOver.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT));
										break;
								case "TurnipSeed":
										img = ImageIO.read(new File("src/Images/TurnipSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "RoseSeed":
										img = ImageIO.read(new File("src/Images/RoseSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "CarrotSeed":
										img = ImageIO.read(new File("src/Images/CarrotSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "PotatoSeed":
										img = ImageIO.read(new File("src/Images/PotatoSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "TulipSeed":
										img = ImageIO.read(new File("src/Images/TulipSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "SunflowerSeed":
										img = ImageIO.read(new File("src/Images/SunflowerSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "MangoSeed":
										img = ImageIO.read(new File("src/Images/MangoSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
								case "AppleSeed":
										img = ImageIO.read(new File("src/Images/AppleSeed.png"));
										icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
										break;
						}
		        		
		        		Label.setIcon(icon);
				} catch (IOException e) {
						switch(sName) {
								case "Plow":
										Label.setText("Plow");
										break;
								case "Water":
										Label.setText("Water");
										break;
								case "Fertilize":
										Label.setText("Fertilize");
										break;
								case "Wait":
										Label.setText("Wait");
										break;
								case "Harvest":
										Label.setText("Harvest");
										break;
								case "Upgrade":
										Label.setText("Upgrade");
										break;
								case "Shovel":
										Label.setText("Shovel");
										break;
								case "Mine":
										Label.setText("Mine");
										break;
								case "Difficulty":
										Label.setText("Difficulty");
										break;
								case "GameOver":
										Label.setText("GameOver");
										break;
								case "TurnipSeed":
										Label.setText("TurnipSeed");
										break;
								case "RoseSeed":
										Label.setText("RoseSeed");
										break;
						}
				}
		}
}
