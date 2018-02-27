package em.mod.init;

import static em.mod.util.handlers.RenderHandler.registerItemRender;

import em.mod.Mod;
import em.mod.util.handlers.Access;
import em.mod.util.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class Registry {

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		if (Access.isDevEnvironment())
			Mod.logger().info(ItemInit.getItems());
		event.getRegistry().registerAll(ItemInit.getItems());
		event.getRegistry().registerAll(BlockInit.getItems());
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			RenderHandler.createStateMappers();
		}
		event.getRegistry().registerAll(BlockInit.getBlocks());
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		RenderHandler.registerItemMetaRenders();
		for (Item item : ItemInit.getItems()) {
			if (item != null && !item.getHasSubtypes()) {
				registerItemRender(item);
			}
		}

		RenderHandler.registerBlockMetaRenders();
		for (ItemBlock item : BlockInit.getItems()) {
			if (item != null && !item.getHasSubtypes()) {
				registerItemRender(item);
			}
		}

		// Template
		// ModTools.TOOLS.forEach(ModelHandler::registerModel);
	}
}
