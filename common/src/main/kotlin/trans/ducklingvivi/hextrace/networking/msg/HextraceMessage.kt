package trans.ducklingvivi.hextrace.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import trans.ducklingvivi.hextrace.Hextrace
import trans.ducklingvivi.hextrace.networking.HextraceNetworking
import trans.ducklingvivi.hextrace.networking.handler.applyOnClient
import trans.ducklingvivi.hextrace.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HextraceMessage

sealed interface HextraceMessageC2S : HextraceMessage {
    fun sendToServer() {
        HextraceNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HextraceMessageS2C : HextraceMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HextraceNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HextraceNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HextraceMessageCompanion<T : HextraceMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hextrace.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HextraceMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hextrace.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hextrace.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HextraceMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hextrace.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
