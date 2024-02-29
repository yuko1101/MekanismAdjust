package io.github.yuko1101.digitalmineradjust;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod(DigitalMinerAdjust.MOD_ID)
public class DigitalMinerAdjust {
    public static final String MOD_ID = "digitalmineradjust";

    public static final ArrayList<TagKey<Block>> disallowedBlocks = new ArrayList<>();

    static {
        disallowedBlocks.add(Tags.Blocks.ORES);
        disallowedBlocks.add(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        disallowedBlocks.add(Tags.Blocks.NEEDS_GOLD_TOOL);
        disallowedBlocks.add(BlockTags.NEEDS_DIAMOND_TOOL);
        disallowedBlocks.add(BlockTags.NEEDS_IRON_TOOL);
    }
}
