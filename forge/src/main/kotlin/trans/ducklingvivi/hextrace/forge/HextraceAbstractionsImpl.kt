@file:JvmName("HextraceAbstractionsImpl")

package trans.ducklingvivi.hextrace.forge

import trans.ducklingvivi.hextrace.registry.HextraceRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HextraceRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
