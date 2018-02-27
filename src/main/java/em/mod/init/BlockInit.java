package em.mod.init;

import java.util.ArrayList;
import java.util.List;

import em.mod.Mod;
import em.mod.util.handlers.Access;
import em.mod.util.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * <em><b>Copyright (c) 2018 Ocelot5836.</b></em>
 * 
 * <br>
 * </br>
 * 
 * Handles all the blocks in the mod.
 * 
 * @author Ocelot5836
 */
public class BlockInit {

	public static List<Block> blocks = new ArrayList<Block>();
	public static List<ItemBlock> items = new ArrayList<ItemBlock>();

	/**
	 * Registers a block.
	 * 
	 * @param block
	 *            The block to register
	 */
	public static void register(Block block) {
		blocks.add(block);
	}

	/**
	 * Registers an item block.
	 * 
	 * @param item
	 *            The item block to register
	 */
	public static void register(ItemBlock item) {
		items.add(item);
	}

	/**
	 * Registers the block specified as well as an item block that represents the block.
	 * 
	 * @param block
	 *            The block to register
	 */
	public static void registerFullBlock(Block block) {
		register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		register(item);
	}

	/**
	 * Registers the block specified as well as a custom specified item block that represents the block.
	 * 
	 * @param block
	 *            The block to register
	 * @param item
	 *            The item block to link to this block
	 */
	public static void registerBlockWithItemBlock(Block block, ItemBlock item) {
		register(block);
		item.setRegistryName(block.getRegistryName());
		register(item);
	}

	/**
	 * Used for registry.
	 * 
	 * @return The list of blocks to be registered
	 */
	public static Block[] getBlocks() {
		return blocks.toArray(new Block[blocks.size()]);
	}

	/**
	 * Used for registry.
	 * 
	 * @return The list of item blocks to be registered
	 */
	public static ItemBlock[] getItems() {
		return items.toArray(new ItemBlock[items.size()]);
	}
}