package red.oases.nuker;

import java.util.ArrayList;
import java.util.List;

public class Layers {
    public static List<Layer> getAll() {
        var list = new ArrayList<Layer>();
        var section = Files.config.getConfigurationSection("effect-layers");
        if (section == null) {
            return List.of();
        }
        for (String yExp : section.getKeys(false)) {
            list.add(new Layer(yExp, section.getStringList(yExp)));
        }
        return list;
    }
}
