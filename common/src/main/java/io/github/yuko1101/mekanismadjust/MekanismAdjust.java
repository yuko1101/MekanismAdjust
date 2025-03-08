package io.github.yuko1101.mekanismadjust;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
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

            json.getAsJsonObject("input").addProperty("count", 3);
            json.getAsJsonObject("output").addProperty("count", 4);

            return json.toString().getBytes();
        });
        ResourceModifier.registerQuickModifier("data/mekanism/recipe/processing/netherite/ancient_debris_to_scrap.json", data -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            json.getAsJsonObject("input").addProperty("count", 3);
            json.getAsJsonObject("output").addProperty("count", 4);

            return json.toString().getBytes();
        });

        ResourceModifier.registerQuickModifier("data/mekanism/recipe/nucleosynthesizing/enchanted_golden_apple.json", data -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            json.getAsJsonObject("chemical_input").addProperty("amount", 15);

            return json.toString().getBytes();
        });

        ResourceModifier.registerStartsWithModifier("data/mekanism/recipe/mekasuit_", (path, data) -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            var pattern = json.getAsJsonArray("pattern");
            pattern.set(1, new JsonPrimitive(pattern.get(1).getAsString().replace("P", "N")));

            var netheriteBlock = new JsonObject();
            netheriteBlock.addProperty("item", "minecraft:netherite_block");
            json.getAsJsonObject("key").add("N", netheriteBlock);

            return json.toString().getBytes();
        });

        ResourceModifier.registerQuickModifier("data/mekanism/recipe/meka_tool.json", data -> {
            var json = JsonParser.parseString(new String(data)).getAsJsonObject();

            var pattern = json.getAsJsonArray("pattern");
            pattern.set(0, new JsonPrimitive("PCP"));
            pattern.set(1, new JsonPrimitive("N#N"));

            var netheriteBlock = new JsonObject();
            netheriteBlock.addProperty("item", "minecraft:netherite_block");

            var key = json.getAsJsonObject("key");
            key.add("N", netheriteBlock);
            key.remove("o");

            System.out.println(json);

            return json.toString().getBytes();
        });
    }
}
