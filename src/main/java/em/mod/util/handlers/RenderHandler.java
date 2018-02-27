package em.mod.util.handlers;

import em.mod.util.IMetaRenderHandler;
import em.mod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {

	public static void registerItemMetaRenders() {
		for (Item item : Item.REGISTRY) {
			if (item != null && item instanceof IMetaRenderHandler) {
				IMetaRenderHandler handler = (IMetaRenderHandler) item;
				if (!item.getHasSubtypes())
					throw new IllegalArgumentException(String.format("Item %s attempted to register as a meta item but does not have subtypes!", item.getRegistryName()));
				for (int j = 0; j < handler.getItemCount(); j++) {
					if (handler.getName(j) != null) {
						registerItemRender(item, j, handler.getName(j));
					} else {
						continue;
					}
				}
			}
		}
	}

	public static void registerBlockMetaRenders() {
		for (Block block : Block.REGISTRY) {
			if (block != null && block instanceof IMetaRenderHandler) {
				IMetaRenderHandler handler = (IMetaRenderHandler) block;
				if (!Item.getItemFromBlock(block).getHasSubtypes())
					throw new IllegalArgumentException(String.format("Block %s attempted to register as a meta item but does not have subtypes!", block.getRegistryName()));
				for (int j = 0; j < handler.getItemCount(); j++) {
					if (handler.getName(j) != null) {
						registerBlockRender(block, j, handler.getName(j));
					} else {
						continue;
					}
				}
			}
		}
	}

	public static void pre(FMLPreInitializationEvent event) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			registerTileEntityRenders();
			registerEntityRenderers();
		}
	}

	private static void registerTileEntityRenders() {
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStarcraftSkull.class, new RendererStarcraftSkull());
	}

	private static void registerEntityRenderers() {
		// RenderingRegistry.registerEntityRenderingHandler(EntityZealot.class, getRenderFactory(new RenderZealot(Minecraft.getMinecraft().getRenderManager(), new ModelZealot(), 0.4f)));
	}

	@SideOnly(Side.CLIENT)
	public static void createStateMappers() {
		// ModelLoader.setCustomStateMapper(STARCRAFT_SKULL, getNormalVariant(STARCRAFT_SKULL));
	}

	public static void registerItemRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerItemRender(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
	}

	public static void registerBlockRender(Block block) {
		registerItemRender(Item.getItemFromBlock(block));
	}

	public static void registerBlockRender(Block block, int meta, String fileName) {
		registerItemRender(Item.getItemFromBlock(block), meta, fileName);
	}

	public static void registerFluid(final Block block) {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(block), new ItemMeshDefinition() {

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation(block.getRegistryName(), "fluid");
			}
		});

		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(block.getRegistryName(), "fluid");
			}
		});
	}

	public static void registerLayered(final Block block) {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(block), new ItemMeshDefinition() {

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation(block.getRegistryName(), "normal");
			}
		});
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				char num = state.toString().charAt(state.toString().length() - 2);
				return new ModelResourceLocation(block.getRegistryName(), "layers=" + num);
			}
		});
	}

	public static StateMapperBase getNormalVariant(Block block) {
		return new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(block.getRegistryName(), "normal");
			}
		};
	}

	public static IRenderFactory getRenderFactory(Render renderer) {
		return new IRenderFactory() {
			@Override
			public Render createRenderFor(RenderManager manager) {
				return renderer;
			}
		};
	}
}