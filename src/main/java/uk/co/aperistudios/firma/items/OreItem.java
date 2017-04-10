package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.gui.GuiHandler;
import uk.co.aperistudios.firma.gui.GuiKnapping;
import uk.co.aperistudios.firma.player.PlayerData;
import uk.co.aperistudios.firma.types.OresEnum;

public class OreItem extends MetaItem {

	public OreItem(String name) {
		super(name);
		ArrayList<String> subs = new ArrayList<String>();
		for(OresEnum ore : OresEnum.values()){
			for(String s : new String[] {"poor","small","med","rich"}){
				subs.add(ore.getName()+s);
			}	
		}
		setSubs(subs);
	}
}
