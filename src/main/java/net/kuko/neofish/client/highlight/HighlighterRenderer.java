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


public class HighlighterRenderer {

    protected static void drawBlockOutline(BlockPos pos, PoseStack poseStack, MultiBufferSource buffer,
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
