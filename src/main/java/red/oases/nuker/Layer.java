package red.oases.nuker;

import org.bukkit.entity.Player;

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

    public void applyAllNotPresent(Player p) {
        for (var ef : effects) {
            var targetEffect = Effect.from(ef);
            if (targetEffect == null) continue;
            if (Utils.isEffectEqual(
                    p.getPotionEffect(targetEffect.getType()),
                    targetEffect
            )) continue;
            p.addPotionEffect(targetEffect);
        }
    }

    public void cancelAllPresent(Player p) {
        for (var ef : effects) {
            var targetEffect = Effect.from(ef);
            if (targetEffect == null) continue;
            if (Utils.isEffectEqual(
                    p.getPotionEffect(targetEffect.getType()),
                    targetEffect
            )) {
                p.removePotionEffect(targetEffect.getType());
            }
        }
    }
}
