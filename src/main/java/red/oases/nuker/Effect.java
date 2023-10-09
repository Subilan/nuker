package red.oases.nuker;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

public class Effect {
    public static @Nullable PotionEffect from(String name) {
        var splitted = name.split(":");
        if (splitted.length != 2) {
            Nuker.logger.warning("Illegal effect name: " + name + " in effect layer.");
            return null;
        }
        var effectName = splitted[0];
        int effectLevel;
        try {
            effectLevel = Integer.parseUnsignedInt(splitted[1]);
        } catch (NumberFormatException e) {
            return null;
        }
        return switch (effectName.toLowerCase()) {
            case "wither" -> persist(PotionEffectType.WITHER, effectLevel);
            case "nausea" -> persist(PotionEffectType.CONFUSION, effectLevel);
            case "blindness" -> persist(PotionEffectType.BLINDNESS, effectLevel);
            case "invisibility" -> persist(PotionEffectType.INVISIBILITY, effectLevel);
            case "hunger" -> persist(PotionEffectType.HUNGER, effectLevel);
            case "poison" -> persist(PotionEffectType.POISON, effectLevel);
            case "weakness" -> persist(PotionEffectType.WEAKNESS, effectLevel);
            case "slow" -> persist(PotionEffectType.SLOW, effectLevel);
            case "slow_digging" -> persist(PotionEffectType.SLOW_DIGGING, effectLevel);
            case "darkness" -> persist(PotionEffectType.DARKNESS, effectLevel);
            case "unluck" -> persist(PotionEffectType.UNLUCK, effectLevel);
            default -> null;
        };
    }

    public static PotionEffect persist(PotionEffectType type, int level) {
        return new PotionEffect(type, -1, level);
    }
}
