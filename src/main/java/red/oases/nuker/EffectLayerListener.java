package red.oases.nuker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EffectLayerListener implements Listener {
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockY() == e.getFrom().getBlockY()) return; // Ignore motion on X and Z direction
        var currentBlockY = e.getTo().getBlockY();
        for (var layer : Layers.getAll()) {
            if (layer.contains(currentBlockY)) {
                layer.applyAll(e.getPlayer());
            }
        }
    }
}
