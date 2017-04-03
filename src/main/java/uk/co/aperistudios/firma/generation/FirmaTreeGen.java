package uk.co.aperistudios.firma.generation;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;
import uk.co.aperistudios.firma.types.OresEnum;

public class FirmaTreeGen implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		byte b = world.getChunkFromChunkCoords(chunkX, chunkZ).getBiomeArray()[0];
		FirmaTree[] tl = FirmaBiome.getTreeForBiome(b, chunkZ*16);
		if(tl.length==0){ return; }
		FirmaTree ft = tl[random.nextInt(tl.length)];
		FirmaBiome bi = (FirmaBiome) Biome.getBiome(b);
		for(int i = 0; i < bi.treeCount; i++){
			ft.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}

}
