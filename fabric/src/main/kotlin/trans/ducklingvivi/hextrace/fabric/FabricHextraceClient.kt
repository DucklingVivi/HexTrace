package trans.ducklingvivi.hextrace.fabric

import trans.ducklingvivi.hextrace.HextraceClient
import net.fabricmc.api.ClientModInitializer

object FabricHextraceClient : ClientModInitializer {
    override fun onInitializeClient() {
        HextraceClient.init()
    }
}
