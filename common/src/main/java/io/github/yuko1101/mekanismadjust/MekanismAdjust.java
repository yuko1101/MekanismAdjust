package io.github.yuko1101.mekanismadjust;

import com.google.gson.JsonParser;
import io.github.yuko1101.mekanismadjust.resource.ResourceModifier;

public class MekanismAdjust {
    public static final String MOD_ID = "mekanismadjust";

    public static void init() {

    }

    // how many ticks takes to look up a block in the digital miner's range
    public static final int BASE_TICKS_PER_MINE = 20;

    public static void registerModifiers() {
        ResourceModifier.registerQuickModifier("data/mekanism/recipe/processing/netherite/ancient_debris_to_dirty_scrap.json", data -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            json.get("input").getAsJsonObject().addProperty("count", 3);
            json.get("output").getAsJsonObject().addProperty("count", 4);

            return json.toString().getBytes();
        });
        ResourceModifier.registerQuickModifier("data/mekanism/recipe/processing/netherite/ancient_debris_to_scrap.json", data -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            json.get("input").getAsJsonObject().addProperty("count", 3);
            json.get("output").getAsJsonObject().addProperty("count", 4);

            return json.toString().getBytes();
        });
    }
}
