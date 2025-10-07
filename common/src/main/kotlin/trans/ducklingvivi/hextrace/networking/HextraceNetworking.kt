package trans.ducklingvivi.hextrace.networking

import dev.architectury.networking.NetworkChannel
import trans.ducklingvivi.hextrace.Hextrace
import trans.ducklingvivi.hextrace.networking.msg.HextraceMessageCompanion

object HextraceNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hextrace.id("networking_channel"))

    fun init() {
        for (subclass in HextraceMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
