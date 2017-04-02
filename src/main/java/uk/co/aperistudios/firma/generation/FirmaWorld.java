package uk.co.aperistudios.firma.generation;

import java.util.HashMap;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class FirmaWorld extends WorldType {
	HashMap<World, IChunkGenerator> chunkGens = new HashMap<World, IChunkGenerator>();
	IChunkGenerator chunkGen;
	public FirmaWorld() {
		super("firma");
	}

	@Override
	public boolean canBeCreated() {
		return true;
	}
	
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
    	return new FirmaChunkGen(world, true);
    }
    
    @Override
    public BiomeProvider getBiomeProvider(World world) {
    	return new FirmaBiomeProvider(world.getSeed(), this, world.getWorldInfo().getGeneratorOptions());
    }
    
    @Override
    public boolean showWorldInfoNotice() {
    	return true;
    }
    
    @Override
    public boolean isCustomizable() {
    	return true;
    }
    
    @Override
    public void onGUICreateWorldPress() {

    }
    

}