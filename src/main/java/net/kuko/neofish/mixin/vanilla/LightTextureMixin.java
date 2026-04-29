package net.kuko.neofish.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Fabric Discord server - Julienraptor01│PrideVer ❤ (369179216031383552)
@Mixin(LightTexture.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT) // edit by kuko
public class LightTextureMixin {
    @WrapOperation(
            method = "updateLightTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Double;floatValue()F",
                    ordinal = 1
            )
    )
    private float getGammaFloatValue(Double instance, Operation<Float> original) {
        return original.call((!false ? instance : Double.valueOf(Float.MAX_VALUE)));
    }
}
