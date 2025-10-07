@file:JvmName("HextraceAbstractions")

package trans.ducklingvivi.hextrace

import dev.architectury.injectables.annotations.ExpectPlatform
import trans.ducklingvivi.hextrace.registry.HextraceRegistrar

fun initRegistries(vararg registries: HextraceRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HextraceRegistrar<T>) {
    throw AssertionError()
}
