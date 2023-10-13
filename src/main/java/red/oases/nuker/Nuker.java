package red.oases.nuker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Nuker extends JavaPlugin {
    public static Logger logger;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Files.load(this);
        logger = getLogger();
        getServer().getPluginManager().registerEvents(new EffectLayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
