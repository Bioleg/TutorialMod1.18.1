package eu.bioleg.tutorialmod.item.custom;

import eu.bioleg.tutorialmod.block.ModBlocks;
import eu.bioleg.tutorialmod.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.awt.desktop.ScreenSleepEvent;
import java.util.List;
import java.util.Scanner;

public class DowsingRodItem extends Item {

//    @Override
//    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
//        context.getPlayer().sendMessage();
//        return super.onItemUseFirst(stack, context);
//    }

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"),
                        player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip.shift"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.dowsing_rod.tooltip"));
        }

    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found " + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block) {
        return Registry.BLOCK.getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get()).is(ModTags.Blocks.DOWSING_ROD_VALUABLES);
    }
}
