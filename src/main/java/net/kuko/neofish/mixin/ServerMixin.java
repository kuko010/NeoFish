package net.kuko.neofish.mixin;

import net.kuko.neofish.ModNeoFish;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
public class ServerMixin {

	@Inject(method = "loadLevel", at = @At("RETURN"))
	private void afterLoadLevel(CallbackInfo ci) {
		ModNeoFish.LOGGER.info("Level Loaded!");
	}

}
