package net.kuko.neofish.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kuko.neofish.registries.ModDataComponents;
import net.kuko.neofish.registries.item.MarkerItem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

import static net.kuko.neofish.client.HighlighterRenderer.drawBlockOutline;

public class Highlight {
	private static final List<BlockOutline> list = new ArrayList<>();

	public static void render(PoseStack poseStack, Camera camera) {
		Minecraft mc = Minecraft.getInstance();

		ClientLevel level = mc.level;
		if (level == null) return;

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.lineWidth(2.0F);

		MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();

		double cx = camera.getPosition().x;
		double cy = camera.getPosition().y;
		double cz = camera.getPosition().z;


		// List<BlockOutline> snapshot = List.copyOf(list); // stupid AI
		Player player = Minecraft.getInstance().player;
		if (player == null) return;

		// Check Main Hand
		ItemStack stack = player.getMainHandItem();

		// If Main Hand isn't a marker, check Off Hand
		if (!(stack.getItem() instanceof MarkerItem)) { // Tight-toppling wowvie
			stack = player.getOffhandItem();
		}
		if (!(stack.getItem() instanceof MarkerItem)) return;
		List<BlockOutline> snapshot = stack.get(ModDataComponents.SELECTED_BLOCKS);
		if (snapshot == null) return;

		snapshot.forEach(outline -> {
			drawBlockOutline(
					outline.getPos(),
					poseStack,
					buffer,
					cx, cy, cz,
					outline.getR(),
					outline.getG(),
					outline.getB(),
					outline.getA()
			);
		});


		RenderSystem.disableBlend();
		buffer.endBatch(RenderType.lines());
	}

	public static List<BlockOutline> getLists() {
		return list;
	}

	public static void replaceList(List<BlockOutline> newList) {
		list.clear();
		list.addAll(newList);
	}
}
