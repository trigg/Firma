package uk.co.aperistudios.firma.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import uk.co.aperistudios.firma.FirmaMod;

public class Rock extends Item {

    public Rock() {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName("rock");
        this.setCreativeTab(FirmaMod.itemTab);
    }
    
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }
}
