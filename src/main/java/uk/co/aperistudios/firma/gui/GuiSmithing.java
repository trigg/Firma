package uk.co.aperistudios.firma.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.container.AnvilContainer;

public class GuiSmithing extends FirmaGuiContainer{

	private AnvilTileEntity te;

	public GuiSmithing(EntityPlayer player, World world, AnvilTileEntity ate) {
		super(new AnvilContainer(player.inventory, world,ate),100,100); //TODO Fix width/height to texture
		tex = new ResourceLocation(FirmaMod.MODID + ":textures/gui/guismithing.png");
		this.te = ate;
	}

	@Override
	public void drawForeground(int let, int top) {
		
	}

}
