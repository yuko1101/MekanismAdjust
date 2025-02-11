package io.github.yuko1101.mekanismadjust;

import io.github.yuko1101.mekanismadjust.resource.JsonModifier;

import java.util.ArrayList;

public class MekanismAdjust {
    public static final String MOD_ID = "mekanismadjust";

    public static void init() {

    }

    // how many ticks takes to look up a block in the digital miner's range
    public static final int BASE_TICKS_PER_MINE = 20;

    public static void registerJsonModifiers(ArrayList<JsonModifier> modifiers) {
        modifiers.add((path, json) -> {
            if (path.toString().replaceFirst("^/", "").equals("data/mekanism/recipe/processing/netherite/ancient_debris_to_dirty_scrap.json")) {
                var jsonObject = json.getAsJsonObject();

                jsonObject.get("input").getAsJsonObject().addProperty("count", 3);
                jsonObject.get("output").getAsJsonObject().addProperty("count", 4);

                return jsonObject;
            }
            return json;
        });
    }
}
