package em.mod;

import org.apache.logging.log4j.Logger;

import em.mod.init.Registry;
import em.mod.proxy.CommonProxy;
import em.mod.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The main mod class.
 * 
 * @author Ocelot5836
 */
@net.minecraftforge.fml.common.Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Mod {

	@Instance(Reference.MODID)
	public static Mod instance;

	@SidedProxy(clientSide = "em.mod.proxy.ClientProxy", serverSide = "em.mod.proxy.ServerProxy")
	public static CommonProxy proxy;

	private static Logger logger;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		MinecraftForge.EVENT_BUS.register(new Registry());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}

	public static Logger logger() {
		return logger;
	}
}