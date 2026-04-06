package net.kuko.neofish.registries.block.entity;

import net.kuko.neofish.registries.ModBlocks;
import net.kuko.neofish.registries.ModBlocksEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

//public class InverseBlockEntity extends BlockEntity implements MenuProvider {
//
//    public InverseBlockEntity( BlockPos worldPosition, BlockState blockState) {
//        super(ModBlocksEntities.INVERSE_BE.get(), worldPosition, blockState);
//    }
//
//    @Override
//    public Component getDisplayName() {
//        return Component.literal("Inverse");
//    }
//
//    @Override
//    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
//        return null;
//    }
//}
