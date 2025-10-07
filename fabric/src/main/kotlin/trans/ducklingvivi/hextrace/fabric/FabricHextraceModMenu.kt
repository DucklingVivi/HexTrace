package trans.ducklingvivi.hextrace.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import trans.ducklingvivi.hextrace.HextraceClient

object FabricHextraceModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HextraceClient::getConfigScreen)
}
