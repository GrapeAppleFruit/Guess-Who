package grapeapplefruit.main;

import jdk.javadoc.internal.doclets.toolkit.taglets.SeeTaglet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DuelCommand implements CommandExecutor, Listener {

    Set<String> playerIngame = new HashSet<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("You can't use this on yourself, really?.");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (playerIngame.contains(target.getUniqueId())){
                    p.sendMessage(ChatColor.RED + "Player is already in a game!");
                }
                if (target instanceof Player) {
                    p.sendMessage(p + "Has sent a duel request, /accept (player) to start the game.");
                } else {
                    p.sendMessage("Please insert an online player.");
                }

            }

        } else {
            System.out.println("Please use a client.");
        }
        return false;
    }

    private static boolean on = false;

    public static boolean getOn() {
        return on;
    }

    public boolean onCommand2(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("You can't use this on yourself, really?.");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target instanceof Player) {
                    p.sendMessage("The game has started, you may not see your name in chat.");
                    boolean on = true;
                    playerIngame.add(p.getName());
                } else {
                    p.sendMessage("Please insert an online player.");
                }
            }
        }
        return on;
    }

    public boolean onCommand3(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            boolean on = false;
            player.sendRawMessage("The game has ended.");
            playerIngame.remove(player.getName());
        }
        return on;
    }

    @EventHandler
    public void hideName(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (playerIngame.contains(player.getUniqueId())){
            event.getPlayer().setPlayerListName(ChatColor.YELLOW + "Hidden name");
        }
    }

    @EventHandler
    public void unhideName(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (playerIngame.contains(player.getUniqueId())){
        } else {
            event.getPlayer().setPlayerListName(ChatColor.YELLOW + player.getName());
        }
    }

    @EventHandler
    public void chatName(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (playerIngame.contains(player.getUniqueId())){
            event.setMessage(ChatColor.YELLOW + "Hidden Name " + event.getMessage());
        } else {
            event.setMessage(player + event.getMessage());
        }
    }

}
