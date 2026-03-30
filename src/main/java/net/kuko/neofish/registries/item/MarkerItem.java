package net.kuko.neofish.registries.item;

import net.kuko.neofish.client.highlight.Highlight;
import net.kuko.neofish.client.highlight.HighlighterRenderer;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.kuko.neofish.client.highlight.Highlight.delPos;

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




    private InteractionResultHolder<ItemStack> sharedUse(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
//            Highlight.addPos(player.getOnPos());

        } else {

        }

        return super.use(level, player, hand);
    }
}
