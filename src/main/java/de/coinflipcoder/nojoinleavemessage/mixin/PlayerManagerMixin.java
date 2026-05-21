package de.coinflipcoder.nojoinleavemessage.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerManagerMixin {
    @Inject(method = "broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V", at = @At("HEAD"), cancellable = true)
    public void broadcast(Component message, boolean overlay, CallbackInfo ci) {
        if (message.toString().contains("multiplayer.player.joined") || message.toString().contains("multiplayer.player.left")) {
            ci.cancel();
        }
    }
}