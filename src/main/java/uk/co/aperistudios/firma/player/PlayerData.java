package uk.co.aperistudios.firma.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
	private static HashMap<UUID,PlayerData> playerDatas = new HashMap<UUID,PlayerData>();
	/**
	 * Get transient non-save information about players
	 * @return
	 */
	public static PlayerData getPlayerData(UUID userId){
		PlayerData pd = null;
		if(playerDatas.containsKey(userId)){
			pd=playerDatas.get(userId);
		}
		if(pd==null){
			pd = new PlayerData();
			playerDatas.put(userId, pd);
		}
		return pd;
	}
	
	
	public boolean knapCraft[] = new boolean[25];
	
	
	public PlayerData(){
	}
	
	public void resetKnapCraft(){
		knapCraft = new boolean[25];
		for(int i = 0; i<25;i++){
			knapCraft[i]=true;
		}
	}

	public void setKnapCraft(int x, int y, boolean b) {
		knapCraft[x+(y*5)] = b;
	}

}
