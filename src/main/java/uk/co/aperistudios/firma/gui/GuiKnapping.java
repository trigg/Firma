package uk.co.aperistudios.firma.gui;

import java.io.IOException;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.packet.KnapToServer;
import uk.co.aperistudios.firma.player.PlayerData;

public class GuiKnapping extends FirmaGuiContainer{
	public GuiKnapping(EntityPlayer player,int x,int y,int z) {
		super(null, 176, 103);
		inventorySlots=
		new ContainerSpecialCrafting(player.inventory, null, player.world, x, y, z);
		tex = new ResourceLocation(FirmaMod.MODID+":gui/guiknapping.png");
		// TODO Auto-generated constructor stub
	}

	//public static PlayerData staticPlayerData;
	public static CraftMat staticMaterial;
	public static String staticMaterialRock;

	//public GuiKnapping() {
	//	
	//}
	
	@Override
	public void initGui(){
		super.initGui();
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 5; x++)
			{
				buttonList.add(new GuiKnappingButton(x + (y * 5), guiLeft + (x * 16) + 10, guiTop + (y * 16) + 12, 16, 16, this, x,y));
			}
		}
	}
	
	/*@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	    zLevel=-1;
	    int mx = width/2;
	    int my = height/2;
	    this.mc.getTextureManager().bindTexture(new ResourceLocation("firma:textures/gui/guiknapping.png"));
	    this.drawTexturedModalRect(0, 0, 0, 0, 256, 256);
	}*/
	
	 @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof GuiKnappingButton){
			GuiKnappingButton gkb = (GuiKnappingButton) button;
			int x = gkb.getKnapX();
			int y = gkb.getKnapY();
			gkb.setRemoved();
			//staticPlayerData.setKnapCraft(x,y,false);
			KnapToServer nts = new KnapToServer(x,y,false);
			FirmaMod.dispatcher.sendToServer(nts);
		}
		
	}

	public String getTexture() {
		if(staticMaterial == CraftMat.ANVIL){
			
		}else if(staticMaterial == CraftMat.CLAY){
			
		}else if(staticMaterial == CraftMat.STONE){
			if(staticMaterialRock != null){
				return "firma:textures/blocks/rock/"+staticMaterialRock+".png";
			}
		}else if(staticMaterial == CraftMat.LEATHER){
			
		}
		return "firma:textures/blocks/rock/andesite.png";
	}


	public void resetButtons() {
		for(int i = 0; i  < 25; i++){
			((GuiKnappingButton)buttonList.get(i)).show=true;
		}
		
	}

	@Override
	public void drawForeground(int let, int top) {		
	}

}
