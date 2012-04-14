package nl.armeagle.minecraft.LavaFurnace;

import java.util.logging.Logger;

import nl.armeagle.minecraft.LavaFurnace.FurnaceBurnListener;
import nl.armeagle.minecraft.LavaFurnace.LavaFurnace;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LavaFurnace extends JavaPlugin{

	private static final Logger log = Logger.getLogger("Minecraft");
	
	private FurnaceBurnListener furnaceBurnListener;
	private boolean hasRegistered = false;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		LavaFurnace.log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
	}

	@Override
	public void onEnable() {
		if ( !this.hasRegistered ) {
			this.hasRegistered = true;
			PluginManager pm = this.getServer().getPluginManager();
			this.furnaceBurnListener = new FurnaceBurnListener(this);
			pm.registerEvents(this.furnaceBurnListener, this);
		}
		PluginDescriptionFile pdfFile = this.getDescription();
		LavaFurnace.log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}
	
	public static void log(String string) {
		log.info("LavaFurnace: "+ string);
	}
}