package em.mod.init;

import java.util.ArrayList;
import java.util.List;

import em.mod.objects.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemInit {
	
	private static List<Item> items = new ArrayList<Item>();

	public static final Item CHEERIOS = new ItemBase("cheerios").setCreativeTab(CreativeTabs.MISC);
	
	/**
	 * Registers an item and it's render.
	 * 
	 * @param item
	 *            The item to register
	 */
	public static void register(Item item) {
		items.add(item);
	}

	/**
	 * Used for registering the items.
	 * 
	 * @return The items in the form of an array
	 */
	public static Item[] getItems() {
		return items.toArray(new Item[items.size()]);
	}
}