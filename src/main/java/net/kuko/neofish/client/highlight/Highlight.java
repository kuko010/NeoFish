package net.kuko.neofish.client.highlight;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

import static net.kuko.neofish.client.highlight.HighlighterRenderer.drawBlockOutline;

public class Highlight {
    private static List<BlockPos> posList = new ArrayList<>();

    public static void render(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;

        Camera camera = event.getCamera();
        PoseStack poseStack = event.getPoseStack();

        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(2.0F);

        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();

        double cx = camera.getPosition().x;
        double cy = camera.getPosition().y;
        double cz = camera.getPosition().z;

        for (BlockPos blockPos : posList) {
            drawBlockOutline(blockPos, poseStack, buffer, cx, cy, cz, 1f, 0f, 0f, 1f);
        }

        RenderSystem.disableBlend();
        buffer.endBatch(RenderType.lines());
    }

    public static List<BlockPos> getPosList() {
        return posList;
    }

    public static void setPosList(List<BlockPos> pos) {
        Highlight.posList = pos;
    }

    public static boolean addPos(BlockPos pos) {
       return Highlight.posList.add(pos);
    }

    public static boolean delPos(BlockPos pos) {
        return Highlight.posList.remove(pos);
    }
}
