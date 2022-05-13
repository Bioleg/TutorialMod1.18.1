package eu.bioleg.tutorialmod.block;

import eu.bioleg.tutorialmod.TutorialMod;
import eu.bioleg.tutorialmod.block.custom.SpeedyBlock;
import eu.bioleg.tutorialmod.item.ModCreativeModTab;
import eu.bioleg.tutorialmod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> CITRINE_BLOCK = registryBlock("citrine_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> SPEED_BLOCK = registryBlock("speedy_block", () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB, "tooltip.tutorialmod.block.speedy_block");

    public static final RegistryObject<Block> RAW_CITRINE_BLOCK = registryBlock("raw_citrine_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_ORE = registryBlock("citrine_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> DEEPSLATE_CITRINE_ORE = registryBlock("deepslate_citrine_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_STAIRS = registryBlock("citrine_stairs", () -> new StairBlock( () -> ModBlocks.CITRINE_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_SLAB = registryBlock("citrine_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_FENCE = registryBlock("citrine_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_FENCE_GATE = registryBlock("citrine_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> CITRINE_WALL = registryBlock("citrine_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(7f).requiresCorrectToolForDrops()), ModCreativeModTab.TUTORIAL_TAB);



    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab, String tooltipKey) {

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;

    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab, String tooltipKey) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)){
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent(tooltipKey));
            }
        });

    }


    //


    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;

    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
