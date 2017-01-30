package uk.co.aperistudios.firma.blocks.boring;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import uk.co.aperistudios.firma.blocks.GravityType;

public class BaseBlock extends Block {
	
	private GravityType gravityType;

	public BaseBlock(Material materialIn) {
		super(materialIn);
	}
	
	public GravityType getGravityType(){
		return gravityType;
	}
	
	public void setGravityType(GravityType gt){
		gravityType=gt;
	}

}
