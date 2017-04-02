package uk.co.aperistudios.firma.generation;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import uk.co.aperistudios.firma.CommonProxy;
import uk.co.aperistudios.firma.Util;

public class FirmaWorldProvider extends WorldProvider {
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public DimensionType getDimensionType() {
		return CommonProxy.firmaDimension;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public int getDimension() {
		return CommonProxy.d;
	}
	
	@Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        BlockPos blockpos = new BlockPos(x, 0, z);
        Block a = this.world.getGroundAboveSeaLevel(blockpos).getBlock();
        
        return this.world.getBiome(blockpos).ignorePlayerSpawnSuitability() ? true :Util.isSoil(a) || Util.isGravel(a) || Util.isSand(a);
    }
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		return 2;
	}
	
	@Override
	public Biome getBiomeForCoords(BlockPos pos)
    {
		return getBiomeProvider().getBiome(pos, FirmaBiome.OCEAN);
    }
	
	@Override
	public ICapabilityProvider initCapabilities() {
		return super.initCapabilities();
	}
	
	@Override
    public boolean canDropChunk(int x, int z)
    {
        return !this.world.isSpawnChunk(x, z) || !this.world.provider.getDimensionType().shouldLoadSpawn();
    }
	
	@Override
    protected void init()
    {
        this.hasSkyLight = true;
        this.biomeProvider = world.getWorldInfo().getTerrainType().getBiomeProvider(world);
        world.getWorldInfo().getGeneratorOptions();
    }
	
	@Override
	public long getSeed() {
		return world.getWorldInfo().getSeed();
	}
}
