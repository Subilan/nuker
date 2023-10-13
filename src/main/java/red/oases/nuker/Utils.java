package red.oases.nuker;

import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class Utils {

    public static boolean isEffectEqual(PotionEffect a, PotionEffect b) {
        if (a == null || b == null) {
            return a == b;
        } else {
            return a.getAmplifier() == b.getAmplifier()
                    && Objects.equals(a.getType().toString(), b.getType().toString());
        }
    }
}
