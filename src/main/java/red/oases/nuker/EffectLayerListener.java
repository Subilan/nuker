package red.oases.nuker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EffectLayerListener implements Listener {
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockY() == e.getFrom().getBlockY()) return; // Ignore motion on X and Z direction
        var player = e.getPlayer();
        var armors = player.getInventory().getArmorContents();
        for (var arm : armors) {
            if (ItemManager.isEffectProof(arm)) return; // Ignore player with effect-proof armors on
        }
        var currentBlockY = e.getTo().getBlockY();
        for (var layer : Layers.getAll()) {
            if (layer.contains(currentBlockY)) {
                layer.applyAllNotPresent(e.getPlayer());
            } else {
                layer.cancelAllPresent(e.getPlayer());
            }
        }
    }
}
