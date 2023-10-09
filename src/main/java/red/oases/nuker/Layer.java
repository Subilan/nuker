package red.oases.nuker;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.regex.Pattern;

public record Layer(String yExpression, List<String> effects) {
    public static final Pattern interval = Pattern.compile("([\\[(])(\\d+)\\s?,\\s?(\\d+)?([)\\]])");

    public boolean contains(int playerY) {
        var matcher = interval.matcher(this.yExpression);
        if (!matcher.matches()) {
            Nuker.logger.warning("Invalid y-expression. Match failed of pattern `interval`.");
            return false;
        }
        var bracketLeft = matcher.group(1);
        Integer numberFirst = null, numberSecond = null;
        try {
            numberFirst = Integer.parseInt(matcher.group(2));
            numberSecond = Integer.parseInt(matcher.group(3));
        } catch (NumberFormatException ignored) {
        }
        var bracketRight = matcher.group(4);

        return
                (
                        numberFirst == null || ((bracketLeft.equals("["))
                                ? playerY >= numberFirst
                                : playerY > numberFirst)
                ) && (
                        numberSecond == null || ((bracketRight.equals("]"))
                                ? playerY <= numberSecond
                                : playerY < numberSecond)
                );
    }

    public void applyAll(Player p) {
        PotionEffect targetEffect, currentEffect;
        for (var ef : effects) {
            targetEffect = Effect.from(ef);
            if (targetEffect == null) continue;
            currentEffect = p.getPotionEffect(targetEffect.getType());
            if (currentEffect != null) {
                if (currentEffect.getAmplifier() == targetEffect.getAmplifier()) continue;
            }
            p.addPotionEffect(targetEffect);
        }
    }
}
