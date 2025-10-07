package trans.ducklingvivi.hextrace.forge

import trans.ducklingvivi.hextrace.Hextrace
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHextraceServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        Hextrace.initServer()
    }
}
