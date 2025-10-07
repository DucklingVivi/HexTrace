package trans.ducklingvivi.hextrace

import trans.ducklingvivi.hextrace.config.HextraceClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HextraceClient {
    fun init() {
        HextraceClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HextraceClientConfig.GlobalConfig::class.java, parent).get()
    }
}
