package nl.armeagle.minecraft.LavaFurnace;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class FurnaceBurnListener implements Listener {
	private LavaFurnace plugin;
	
	public FurnaceBurnListener(LavaFurnace plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onFurnaceBurn(FurnaceBurnEvent event) {
		if ( !this.plugin.isEnabled() ) {
			return;
		}
		
		if (event.getFuel().getType() == Material.LAVA_BUCKET) {
			try {
				Furnace furnaceBlock = (Furnace) event.getBlock().getState();
				org.bukkit.material.Furnace furnace = (org.bukkit.material.Furnace) furnaceBlock.getData();
				
				Location front = furnaceBlock.getBlock().getRelative(furnace.getFacing()).getLocation();
				furnaceBlock.getWorld().dropItem(front, new ItemStack(Material.BUCKET));
			} catch (Exception e) {
				// ignore, furnace probably was no furnace anymore.
			}
		}
	}
}