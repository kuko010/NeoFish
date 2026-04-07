package net.kuko.neofish.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class BlockOutline {
    private BlockPos pos;
    private float r;
    private float g;
    private float b;
    private float a;

    public static final Codec<BlockOutline> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockPos.CODEC.fieldOf("pos").forGetter(BlockOutline::getPos),
                    Codec.FLOAT.fieldOf("r").forGetter(BlockOutline::getR),
                    Codec.FLOAT.fieldOf("g").forGetter(BlockOutline::getG),
                    Codec.FLOAT.fieldOf("b").forGetter(BlockOutline::getB),
                    Codec.FLOAT.fieldOf("a").forGetter(BlockOutline::getA)
            ).apply(instance, BlockOutline::new)
    );
    public static final StreamCodec<ByteBuf, BlockOutline> STREAM_CODEC =
        StreamCodec.composite(
                BlockPos.STREAM_CODEC, BlockOutline::getPos,
                ByteBufCodecs.FLOAT, BlockOutline::getR,
                ByteBufCodecs.FLOAT, BlockOutline::getG,
                ByteBufCodecs.FLOAT, BlockOutline::getB,
                ByteBufCodecs.FLOAT, BlockOutline::getA,
                BlockOutline::new
        );

    public BlockOutline(BlockPos pos, float r, float g, float b, float a) {
        this.pos = pos;
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
