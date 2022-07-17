package dev.enderteam.moneytrans;

import dev.enderteam.moneytrans.command.TransCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MoneyTrans extends JavaPlugin {

    public static Logger LOGGER = Bukkit.getLogger();
    public static Economy ECON = null;
    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            LOGGER.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getPluginCommand("moneytrans").setExecutor(new TransCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        ECON = rsp.getProvider();
        return ECON != null;
    }
}
