package net.kuko.neofish.registries.item;

import net.kuko.neofish.BlockOutline;
import net.kuko.neofish.client.highlight.Highlight;
import net.kuko.neofish.registries.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;


public class MarkerItem extends Item {
    public MarkerItem(Properties properties) {
        super(properties);
    }


    /*? if 26.1 {*/
    /*@Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return sharedUse(level, player, hand);
    }*/
    /*?} elif 1.21.1 {*/
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return sharedUse(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        /*
        if (!level.isClientSide()) return;
        if (!(entity instanceof Player player)) return;
        if (slotId < 0 || slotId > 8) return;

        Highlight.getPosList().removeIf(pos ->
                pos.getCenter().distanceToSqr(player.position()) >= Math.pow(8, 2));*/
    }

    /*?}*/


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos clickedPos = context.getClickedPos();

        // 1. Get the list (or a fresh one if null)
        List<BlockOutline> oldList = stack.getOrDefault(ModDataComponents.SELECTED_BLOCKS, List.of());
        List<BlockOutline> newList = new ArrayList<>(oldList);

        if (player.isCrouching()) {
            // 2. SEARCH by position to remove
            // This works even if the objects are different instances!
            boolean removed = newList.removeIf(outline -> outline.getPos().equals(clickedPos));

            if (removed) {
                // Optional: play a "remove" sound
                if (level.isClientSide())
                    level.playSound(null, clickedPos, SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.PLAYERS, 0.5f, 0.5f);
            }
        } else {
            // 3. Add logic
            // First, prevent duplicates so we don't stack lines
            newList.removeIf(outline -> outline.getPos().equals(clickedPos));

            newList.add(new BlockOutline(clickedPos, 1.0f, 0.0f, 0.0f, 0.8f));

            // Optional: play an "add" sound
            if (level.isClientSide())
                level.playSound(null, clickedPos, SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.PLAYERS, 0.5f, 1.0f);
        }

        // 4. Update the component (this triggers the auto-sync to client)
        stack.set(ModDataComponents.SELECTED_BLOCKS, newList);

        return InteractionResult.SUCCESS;
    }


    private InteractionResultHolder<ItemStack> sharedUse(Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private static BlockHitResult raycastBlock(Player player, double reach) {
        Level level = player.level();

        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 endPos = eyePos.add(lookVec.scale(reach));

        ClipContext context = new ClipContext(
                eyePos,
                endPos,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        );

        return level.clip(context);
    }
}
