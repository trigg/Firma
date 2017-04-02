package uk.co.aperistudios.firma;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class SpawnTeleport extends Teleporter {
    private WorldServer worldserver;
 
    public SpawnTeleport(WorldServer worldserver) {
        super(worldserver);
       
        // Setup Variables
        this.worldserver = worldserver;
       
    }
 
    public void teleport(Entity entity, World world) {
        EntityPlayerMP playerMP = (EntityPlayerMP) entity;
        double dx = 0;
        double dy = 0;
        double dz = 0;
        
        WorldInfo wi = worldserver.getWorldInfo();
 
        if (wi != null) {
       
            dx = wi.getSpawnX();
            dy = wi.getSpawnY();
            dz = wi.getSpawnZ();
           
        }
 
        // check for zeros
        if (dx == 0 && dy == 0 && dz == 0) {
       
            // Set height to something big
            dy = 250;
 
            // Drop down until find solid
            while (world.getBlockState(new BlockPos((int) dx, (int) dy - 1, (int) dz)).equals(Blocks.AIR.getDefaultState()) && dy > 0) {
           
                dy--;
               
            }
 
            // Last check if dy == 0
            if (dy == 0) {
           
                dy = 128;
               
            }
           
        }
 
        // Offset locations for accuracy
        dx = dx + 0.5d;
        dy = dy + 1.0d;
        dz = dz + 0.5d;
        entity.setPosition(dx, dy, dz);
       
        // Freeze motion
        entity.motionX = entity.motionY = entity.motionZ = 0.0D;
        playerMP.setPosition(dx, dy, dz);
 
        if (playerMP.world.provider.getDimension() != world.provider.getDimension()) {
            playerMP.getServer().getPlayerList().transferPlayerToDimension(playerMP, world.provider.getDimension(), this);
        }
 
        entity.setPosition(dx, dy, dz); 
   
    }
 
    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
    	return false;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
    	return;
    }
    
    @Override
    public void removeStalePortalLocations(long par1)
    {
    }
}
