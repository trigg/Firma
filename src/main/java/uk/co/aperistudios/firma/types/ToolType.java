package uk.co.aperistudios.firma.types;

import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.crafting.RecipeShape;

public enum ToolType {
	Axe("axe"),Chisel("chisel"),Hammer("hammer"),Hoe("hoe"),Javelin("javelin"),Knife("knife"),Mace("mace"),Pick("pick"),ProPick("propick"),Saw("saw"),Scythe("scythe"),Shovel("shovel"),Sword("sword")
	;
	
	private String name;

	ToolType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public RecipeShape getShape() {
		if(this == Axe){
			return CraftingManager.axeShape;
		}else if(this == Chisel){
			return CraftingManager.chiselShape;
		}else if(this == Hammer){
			return CraftingManager.hammerShape;
		}else if(this == Hoe){
			return CraftingManager.hoeShape1;
		}else if(this == Javelin){
			return CraftingManager.javelinShape;
		}else if(this == Knife){
			return CraftingManager.knifeShape1;
		}else if(this == Mace){
			return CraftingManager.maceShape;
		}else if(this == Pick){
			return CraftingManager.pickaxeShape;
		}else if(this == ProPick){
			return CraftingManager.propickaxeShape;
		}else if(this == Saw){
			return CraftingManager.sawShape;
		}else if(this == Scythe){
			return CraftingManager.scytheShape;
		}else if(this == Shovel){
			return CraftingManager.swordShape;
		}else if(this == Sword){
			return CraftingManager.swordShape;
		}
		return null;
	}
}
