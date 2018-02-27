package em.mod.objects.items;

import em.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * <em><b>Copyright (c) 2018 Ocelot5836.</b></em>
 * 
 * <br>
 * </br>
 * 
 * A basic item for the mod.
 * 
 * @author Ocelot5836
 */
public class ItemBase extends Item {

	/**
	 * The basic item constructor.
	 */
	public ItemBase() {
	}

	/**
	 * Registers the item with the specified name.
	 * 
	 * @param name
	 *            The name to use
	 */
	public ItemBase(String name) {
		this(name, name);
	}

	/**
	 * Registers the item with the specified names.
	 * 
	 * @param registryName
	 *            The name to use in game
	 * @param unlocalizedName
	 *            The name to use in the language
	 */
	public ItemBase(String registryName, String unlocalizedName) {
		setRegistryName(registryName);
		setUnlocalizedName(unlocalizedName);
		ItemInit.register(this);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		return (ItemBase) super.setCreativeTab(tab);
	}
}