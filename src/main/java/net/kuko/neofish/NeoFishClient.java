package net.kuko.neofish;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

@Mod(value = NeoFish.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NeoFish.MOD_ID, value = Dist.CLIENT)
public class NeoFishClient {

    private static final List<BlockPos> pos = List.of(
            new BlockPos(0,0,0),
            new BlockPos(8,8,16)
    );


    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
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

        for (BlockPos blockPos : pos) {
            drawBlockOutline(blockPos, poseStack, buffer, cx, cy, cz, 1f, 0f, 0f, 1f);
        }




        RenderSystem.disableBlend();
        buffer.endBatch(RenderType.lines());
    }


    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {

    }

    private static void drawBlockOutline(BlockPos pos, PoseStack poseStack, MultiBufferSource buffer,
                                         double cx, double cy, double cz, float r, float g, float b, float a) {
        float x = (float)(pos.getX() - cx);
        float y = (float)(pos.getY() - cy);
        float z = (float)(pos.getZ() - cz);

        VertexConsumer vertex = buffer.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        float[][] corners = {
                {x,y,z},{x+1,y,z},{x+1,y,z+1},{x,y,z+1},
                {x,y+1,z},{x+1,y+1,z},{x+1,y+1,z+1},{x,y+1,z+1}
        };
        int[][] edges = {
                {0,1},{1,2},{2,3},{3,0},
                {4,5},{5,6},{6,7},{7,4},
                {0,4},{1,5},{2,6},{3,7}
        };

        for (int[] edge : edges) {
            float[] c1 = corners[edge[0]];
            float[] c2 = corners[edge[1]];
            float nx = c2[0]-c1[0], ny = c2[1]-c1[1], nz = c2[2]-c1[2];
            vertex.addVertex(matrix, c1[0], c1[1], c1[2]).setColor(r,g,b,a).setNormal(poseStack.last(), nx, ny, nz);
            vertex.addVertex(matrix, c2[0], c2[1], c2[2]).setColor(r,g,b,a).setNormal(poseStack.last(), nx, ny, nz);
        }
    }

}
