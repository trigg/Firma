package uk.co.aperistudios.firma.renderer;

import java.util.List;
import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.FastTESR;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;

public class ShitOnFloorRenderer extends FastTESR<SoFTileEntity>{

	@Override
	public void renderTileEntityFast(SoFTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, VertexBuffer vertexBuffer) {
		ItemStack item = te.getItem();
        if(item!=null && !item.isEmpty())
        {
            World world = te.getWorld();

            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(item, world, null);
            List<BakedQuad> quads;

            // Blocks with facing
            for(EnumFacing facing : EnumFacing.values())
            {
                quads = model.getQuads(null, facing, 0L);
                for(BakedQuad quad : quads)
                {
                	
                	int[] quadinfo = quad.getVertexData();
                    vertexBuffer.addVertexData(quadinfo);
                    vertexBuffer.putPosition(x, y, z);
                }
            }

            quads = model.getQuads(null, null, 0L);

            // inventory item without facing
            for(BakedQuad quad : quads)
            {
            	
            	int[] quadinfo = quad.getVertexData().clone();
            	// Make a copy of quad info and swap y/z positions to lay the item down
            	for(int i = 0; i < 4; i++){
            		int v = quadinfo[i*7+1];
            		quadinfo[i*7+1] = quadinfo[i*7+2];
            		quadinfo[i*7+2] = v;
            	}
                vertexBuffer.addVertexData(quadinfo);
                vertexBuffer.putPosition(x, y-0.45f, z);
            }
        }
	}

}
