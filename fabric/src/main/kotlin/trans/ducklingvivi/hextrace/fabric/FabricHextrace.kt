package trans.ducklingvivi.hextrace.fabric

import trans.ducklingvivi.hextrace.Hextrace
import net.fabricmc.api.ModInitializer

object FabricHextrace : ModInitializer {
    override fun onInitialize() {
        Hextrace.init()
    }
}
