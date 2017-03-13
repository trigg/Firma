package uk.co.aperistudios.firma.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiKnappingButton extends GuiButton {
	boolean show = true;
	private GuiKnapping screen;
	private int locx, locy;

	public GuiKnappingButton(int buttonId, int x, int y, int widthIn, int heightIn, GuiKnapping screen, int locx, int locy) {
		super(buttonId, x, y, widthIn, heightIn, "");
		this.screen = screen;
		this.locx = locx;
		this.locy = locy;

	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		zLevel = 1;
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(screen.getTexture()));
		if (show) {
			Gui.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, 0, 0, 16, 16, 16, 16);
		}
	}

	public int getKnapX() {
		return locx;
	}

	public int getKnapY() {
		return locy;
	}

	public void set(boolean b) {
		show = b;
		
	}

	public boolean get() {
		return show;
	}

}
