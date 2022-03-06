package be.razerstorm.rskrasloten.listeners;

import be.razerstorm.rskrasloten.manager.KraslotenMNGR;
import be.razerstorm.rskrasloten.rsKrasloten;
import be.razerstorm.rskrasloten.utils.Format;
import be.razerstorm.rskrasloten.utils.ItemChecks;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerInteract implements Listener {

    JavaPlugin main = JavaPlugin.getPlugin(rsKrasloten.class);


    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getItem() == null) return;
        if(e.getItem().getItemMeta() == null) return;
        if( !(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) )) return;
        if(!(e.getItem().getType().equals(Material.valueOf(main.getConfig().getString("kraslot-item.item"))))) return;

        KraslotenMNGR manager = new KraslotenMNGR(p);

        if(!(ItemChecks.checkItemStackDisplayName(e.getItem(), manager.getItemStack()) || ItemChecks.checkItemStackLores(e.getItem(), manager.getItemStack()))) return;

        if(rsKrasloten.superCooleKrassers.contains(p.getUniqueId())) {
            p.sendMessage(Format.chat(main.getConfig().getString("messages.krassen-error")));
            return;
        }
        rsKrasloten.superCooleKrassers.add(p.getUniqueId());

        ItemStack hand = p.getInventory().getItemInHand();
        hand.setAmount(hand.getAmount() - 1);
        p.getInventory().setItemInHand(hand);

        manager.openKraslot(main.getConfig().getDouble("settings.money.minimum"), main.getConfig().getDouble("settings.money.maximum"));
    }
}
