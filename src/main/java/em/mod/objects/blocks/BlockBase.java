package em.mod.objects.blocks;

import em.mod.init.BlockInit;
import em.mod.objects.blocks.items.ItemBlockMeta;
import em.mod.util.handlers.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * <em><b>Copyright (c) 2018 The Starcraft Minecraft (SCMC) Mod Team.</b></em>
 * 
 * <br>
 * </br>
 * 
 * @author Hypeirochus
 */
public class BlockBase extends Block {

	private ItemBlock item;

	public BlockBase(Material material) {
		super(material, material.getMaterialMapColor());
	}

	public BlockBase(Material material, MapColor color) {
		super(material, color);
	}

	public BlockBase(String name, RegistryType type, Material material) {
		this(name, type, material, material.getMaterialMapColor());
	}

	public BlockBase(RegistryType type, Material material) {
		this(type, material, material.getMaterialMapColor());
	}

	public BlockBase(String name, RegistryType type, Material material, MapColor color) {
		super(material, color);
		this.setNames(name);
		this.registerPre(type);
	}

	public BlockBase(RegistryType type, Material material, MapColor color) {
		super(material, color);
		this.registerPre(type);
	}

	public void registerPre(RegistryType type) {
		if (type == RegistryType.FULL) {
			if (this.item == null) {
				BlockInit.registerFullBlock(this);
			} else {
				BlockInit.registerBlockWithItemBlock(this, this.item);
			}
		} else if (type == RegistryType.BLOCK) {
			BlockInit.register(this);
		} else if (type == RegistryType.META) {
			setItemBlock(new ItemBlockMeta(this));
			BlockInit.registerBlockWithItemBlock(this, this.item);
		} else {
			throw new IllegalArgumentException(String.format("The registry type %s for block %s is not currently supported. Either add support for it or change the type of registry.", type, this.getRegistryName()));
		}
	}

	public void setNames(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public BlockBase setSoundType(SoundType sound) {
		return (BlockBase) super.setSoundType(sound);
	}

	/**
	 * Sets or removes the tool and level required to harvest this block.
	 *
	 * @param toolClass
	 *            Class
	 * @param level
	 *            Harvest level: Wood: 0 Stone: 1 Iron: 2 Diamond: 3 Gold: 0
	 */
	public BlockBase setBlockHarvestLevel(String toolClass, int level) {
		setHarvestLevel(toolClass, level);
		return this;
	}

	/**
	 * Sets or removes the tool and level required to harvest this block.
	 *
	 * @param toolClass
	 *            Class
	 * @param level
	 *            Harvest level: Wood: 0 Stone: 1 Iron: 2 Diamond: 3 Gold: 0
	 * @param state
	 *            The specific state.
	 */
	public BlockBase setBlockHarvestLevel(String toolClass, int level, IBlockState state) {
		setHarvestLevel(toolClass, level, state);
		return this;
	}

	public BlockBase setItemBlock(ItemBlock item) {
		this.item = item;
		return this;
	}
}