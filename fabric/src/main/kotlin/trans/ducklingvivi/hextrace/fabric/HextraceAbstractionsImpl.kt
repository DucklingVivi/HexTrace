@file:JvmName("HextraceAbstractionsImpl")

package trans.ducklingvivi.hextrace.fabric

import trans.ducklingvivi.hextrace.registry.HextraceRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HextraceRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
