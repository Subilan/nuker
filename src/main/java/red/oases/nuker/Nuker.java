package red.oases.nuker;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Nuker extends JavaPlugin {
    public static Logger logger;

    @Override
    public void onEnable() {
        Files.load(this);
        logger = getLogger();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
