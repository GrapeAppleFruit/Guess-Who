package grapeapplefruit.main;

import grapeapplefruit.main.DuelCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("accept").setExecutor(new DuelCommand());
        getCommand("done").setExecutor(new DuelCommand());
        getServer().getPluginManager().registerEvents(new DuelCommand(), this);
    }

    @Override
    public void onDisable() {

    }
}
