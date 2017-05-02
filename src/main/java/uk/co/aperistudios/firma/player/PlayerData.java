package uk.co.aperistudios.firma.player;

import java.util.HashMap;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.crafting.CraftMat;

public class PlayerData {
	private static HashMap<UUID, PlayerData> playerDatas = new HashMap<UUID, PlayerData>();

	/**
	 * Get transient non-save information about players
	 * 
	 * @return
	 */
	public static PlayerData getPlayerData(UUID userId) {
		PlayerData pd = null;
		if (playerDatas.containsKey(userId)) {
			pd = playerDatas.get(userId);
		}
		if (pd == null) {
			pd = new PlayerData();
			playerDatas.put(userId, pd);
		}
		return pd;
	}

	private boolean knapCraft[] = new boolean[25];
	private CraftMat knapMaterial = null;
	private ItemStack is = null;
	private World world;
	private BlockPos pos;

	public PlayerData() {
	}

	public void resetKnapCraft() {
		knapCraft = new boolean[25];
		for (int i = 0; i < 25; i++) {
			knapCraft[i] = true;
		}
		knapMaterial = null;
		is = null;
	}

	public void setKnapCraft(int x, int y, boolean b) {
		knapCraft[x + (y * 5)] = b;
	}

	public CraftMat getCraftingMaterial() {
		return knapMaterial;
	}

	public void setCraftingMaterial(CraftMat cm) {
		this.knapMaterial = cm;
	}

	public boolean[] getKnapLayout() {
		return knapCraft;
	}

	public static PlayerData getPlayerClient() {
		return getPlayerData(Minecraft.getMinecraft().player.getUniqueID());
	}

	public ItemStack getItemStack() {
		return is;
	}

	public void setItemStack(ItemStack is) {
		this.is = is;
	}

	public void setTileEntity(World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
	}

	public TileEntity getTileEntity() {
		return world.getTileEntity(pos);
	}

	public World getWorld() {
		return world;
	}
	
	public BlockPos getPosition(){
		return pos;
	}
}
