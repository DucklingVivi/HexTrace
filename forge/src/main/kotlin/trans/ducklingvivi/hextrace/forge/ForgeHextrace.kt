package trans.ducklingvivi.hextrace.forge

import dev.architectury.platform.forge.EventBuses
import trans.ducklingvivi.hextrace.Hextrace
import trans.ducklingvivi.hextrace.forge.datagen.ForgeHextraceDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hextrace.MODID)
class ForgeHextrace {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hextrace.MODID, this)
            addListener(ForgeHextraceClient::init)
            addListener(ForgeHextraceDatagen::init)
            addListener(ForgeHextraceServer::init)
        }
        Hextrace.init()
    }
}
