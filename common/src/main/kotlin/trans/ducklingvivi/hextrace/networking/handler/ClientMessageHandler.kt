package trans.ducklingvivi.hextrace.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import trans.ducklingvivi.hextrace.config.HextraceServerConfig
import trans.ducklingvivi.hextrace.networking.msg.*

fun HextraceMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HextraceServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
