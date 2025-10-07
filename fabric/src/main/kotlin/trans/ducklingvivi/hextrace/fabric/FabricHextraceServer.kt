package trans.ducklingvivi.hextrace.fabric

import trans.ducklingvivi.hextrace.Hextrace
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHextraceServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Hextrace.initServer()
    }
}
