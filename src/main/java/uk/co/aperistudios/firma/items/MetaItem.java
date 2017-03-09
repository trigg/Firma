package uk.co.aperistudios.firma.items;

import java.util.Arrays;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import uk.co.aperistudios.firma.FirmaMod;

public abstract class MetaItem extends Item {

	private String name;
	private List<String> subs;
	
	public MetaItem(String name){
		this.name=name;
        this.setRegistryName(name);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(name);
        FirmaMod.allItems.add(this);
        this.setCreativeTab(FirmaMod.itemTab);		
	}
	
	public void setSubs(List<String> list){
		this.subs = list;
	}
	
	public void setSubs(String[] list){
		setSubs(Arrays.asList(list));
	}

    public int getMetadata(int damage)
    {
        return damage;
    }
    
    public int getSubCount(){
    	return subs.size();
    }
    
    public String getSubName(int i){
    	return subs.get(i);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return  FirmaMod.MODID+":"+name+ "." + subs.get(stack.getItemDamage());
    }
    
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	for(int c = 0; c < subs.size(); c++){
    		ItemStack is = new ItemStack(this,1,c);
    		subItems.add(is);
    	}
    }
}
