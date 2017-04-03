package uk.co.aperistudios.firma.generation;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.types.OresEnum;

public class FirmaOreGen implements IWorldGenerator {
	public static int count = 0;
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		count = 0;
		for(OresEnum ore : OresEnum.values()){
			if(random.nextInt(100)< ore.getRarity()){
			//for(int i = 0; i < ore.getRarity(); i++){
				new FirmaMinable(ore, random.nextInt(ore.getRarity()/2)+ore.getRarity(), random.nextInt(5))
				.generate(world, random, chunkX, chunkZ);
			}
		}
		//System.out.println(count+" ores added to this chunk");
	}

}
