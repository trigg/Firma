package uk.co.aperistudios.firma.crafting;

import java.util.ArrayList;
import uk.co.aperistudios.firma.CraftingManager;

public class RecipeShape {
	public int width;
	public int height;
	public ArrayList<Boolean> map= new ArrayList<Boolean>();

	public static RecipeShape makeRecipeShape(String... objects) {
		RecipeShape r = new RecipeShape();
		r.width = -1;
		r.height = 0;
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof String) {
				String line = ((String) objects[i]);
				int strlen = line.length();
				if (r.width == -1) {
					r.width = strlen;
				}
				assert r.width == strlen;
				for (int c = 0; c < line.length(); c++) {
					r.map.add(!line.substring(c, c + 1).equals(" "));
				}
				r.height++;
			}
		}
		return r;
	}
	
	public boolean getMapAt(int x, int y){
		return map.get(y*width+x);
	}

	public boolean matches(boolean isInverse, boolean[] b) {
		for (int startx = 0; startx <= (CraftingManager.knapWidth - width); startx++) {
			for (int starty = 0; starty <= (CraftingManager.knapHeight - height); starty++) {
				if(matchesAtOffset(isInverse, b, startx, starty)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean matchesAtOffset(boolean isInverse, boolean[] b, int startx, int starty){
		// First, we need all spots outside this cross section to be removed (or filled in for clay which is inverse)
		for(int x = 0; x<CraftingManager.knapWidth; x++){
			for(int y = 0 ; y<CraftingManager.knapHeight; y++){
				if(x>=startx && x<=startx+width && y>=starty && y<= starty+height){
					continue; // Inside cross section
				}
				boolean bit = b[(y)*CraftingManager.knapWidth +(x)];
				if(isInverse && bit==false){
					return false;
				}
				if(!isInverse && bit==true){
					return false;
				}
			}
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				boolean bit = b[(starty + y) * CraftingManager.knapWidth + (startx + x)];
				if (isInverse) {
					if (bit == getMapAt(x, y)) {
						return false;
					}
				} else {
					if (bit != getMapAt(x, y)) {
						return false;
					}
				}
			}
		}
		return true;

	}

}
