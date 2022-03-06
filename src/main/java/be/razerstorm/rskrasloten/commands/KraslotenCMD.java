package be.razerstorm.rskrasloten.commands;

import be.razerstorm.rskrasloten.manager.KraslotenMNGR;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KraslotenCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        KraslotenMNGR manager = new KraslotenMNGR(p);
        ItemStack kraslot = manager.getItemStack();

        return false;
    }
}
