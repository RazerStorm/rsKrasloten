package be.razerstorm.rskrasloten.listeners;

import be.razerstorm.rskrasloten.manager.KraslotenMNGR;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        KraslotenMNGR manager = new KraslotenMNGR(p);
        manager.openKraslot(1,500);
    }
}
