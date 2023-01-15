/* MyFarm by Michael Gabriel A. Del Rosario [12137766] of CCPROG3 S13 */

/*
 This is the class for the seed/plant situated to planted tiles. This class should
 contain the data/variables/information for each individual seed initialized
 according to the provided table of the variations of which, this includes the
 seed name, seed type, harvest time (day), water needs' bonus limit, water needs,
 fertilizer needs' bonus limit, fertilizer needs, produce count (on harvest),
 cost for planting, profit after selling (harvesting), and experience yield. 
*/
public class Seed {
	    public String sName = "Placeholder"; 
	    public String sType = "Placeholder";
        public int nHarvestTime = 0, nBonus1 = 0, nWaterNeeds = 0,
                   nBonus2 = 0, nFertilizerNeeds = 0, nProduced = 0,
                   nCost = 0, nSellingPrice = 0;
        public double nExperience = 0;
        
        /*
         Constructor for a seed, the parameter input in initializing such in the
         main method in the Main class shall be based on the table provided for the
         variation of seeds.
        */
        public Seed(String sName, String sType, int nHarvestTime,
        	        int nBonus1, int nWaterNeeds, int nBonus2,
        	        int nFertilizerNeeds, int nProduced, int nCost,
        	        int nSellingPrice, double nExperience) {
                this.sName = sName;
                this.sType = sType;
                this.nHarvestTime = nHarvestTime;
                this.nBonus1 = nBonus1;
                this.nWaterNeeds = nWaterNeeds;
        	    this.nBonus2 = nBonus2;
        	    this.nFertilizerNeeds = nFertilizerNeeds;
        	    this.nProduced = nProduced;
        	    this.nCost = nCost;
        	    this.nSellingPrice = nSellingPrice;
        	    this.nExperience = nExperience;
        }
}
