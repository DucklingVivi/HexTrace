package trans.ducklingvivi.hextrace

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import trans.ducklingvivi.hextrace.config.HextraceServerConfig
import trans.ducklingvivi.hextrace.networking.HextraceNetworking
import trans.ducklingvivi.hextrace.registry.HextraceActions

object Hextrace {
    const val MODID = "hextrace"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HextraceServerConfig.init()
        initRegistries(
            HextraceActions,
        )
        HextraceNetworking.init()
    }

    fun initServer() {
        HextraceServerConfig.initServer()
    }
}
