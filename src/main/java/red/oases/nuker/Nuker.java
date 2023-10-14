package red.oases.nuker;

import org.bukkit.plugin.java.JavaPlugin;
import red.oases.nuker.Commands.Executor;
import red.oases.nuker.Commands.Tab;

import java.util.Objects;
import java.util.logging.Logger;

public final class Nuker extends JavaPlugin {
    public static Logger logger;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Files.load(this);
        logger = getLogger();
        getServer().getPluginManager().registerEvents(new EffectLayerListener(), this);
        Objects.requireNonNull(getCommand("nuker")).setExecutor(new Executor());
        Objects.requireNonNull(getCommand("nuker")).setTabCompleter(new Tab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
