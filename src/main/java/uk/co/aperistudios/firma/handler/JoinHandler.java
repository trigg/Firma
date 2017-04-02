package uk.co.aperistudios.firma.handler;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import uk.co.aperistudios.firma.CommonProxy;
import uk.co.aperistudios.firma.SpawnTeleport;

public class JoinHandler {

	@SubscribeEvent
	public void teleport(PlayerChangedDimensionEvent event) {
		if (CommonProxy.d == 0) {
			return;
		}
		if (event.player.world.isRemote) {
			return;
		}
		if (event.toDim == 0) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void move(TickEvent.PlayerTickEvent event) {
		

		if (event.player.world.isRemote) {
			//System.out.println("CLIENT "+event.player.world.getBiome(event.player.getPosition()).getBiomeName());

			return;
		}
		//System.out.println(event.player.world.getBiome(event.player.getPosition()).getBiomeName());
		if (CommonProxy.d == 0) {
			return;
		}
		World w = event.player.world;
		if (w != null && w.getWorldType() == WorldType.DEFAULT) {
			new SpawnTeleport(DimensionManager.getWorld(CommonProxy.d)).teleport(event.player, DimensionManager.getWorld(CommonProxy.d));
		}
	}
}
