package net.kuko.neofish.event;

import net.kuko.neofish.ModNeoFish;
import net.minecraft.server.level.ServerPlayer;

import java.util.Objects;

public class FishEventHandler {

	public static void onPlayerHurt(ServerPlayer player) {
		//? if > 1.19.2 {
		// MinecraftServer.pvp is private... only here to test ATs/AWs
		if (Objects.requireNonNull(player.getServer()).pvp) {
			ModNeoFish.LOGGER.info("{} took damage. PVP is allowed.", player.getDisplayName());
		} else {
			ModNeoFish.LOGGER.info("{} took damage. PVP is disallowed.", player.getDisplayName());
		}
		//?}
	}
}
