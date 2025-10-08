package trans.ducklingvivi.hextrace.registry;

import at.petrak.hexcasting.api.casting.castables.SpecialHandler;
import at.petrak.hexcasting.common.lib.HexRegistries;
import at.petrak.hexcasting.common.lib.hex.HexSpecialHandlers
import at.petrak.hexcasting.xplat.IXplatAbstractions
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import trans.ducklingvivi.hextrace.Hextrace;
import trans.ducklingvivi.hextrace.casting.actions.spells.SpecialHandlerTracer;

object  HextraceSpecialHanders: HextraceRegistrar<SpecialHandler.Factory<*>>(
    HexRegistries.SPECIAL_HANDLER,
    { IXplatAbstractions.INSTANCE.specialHandlerRegistry },
){
    var TRACE_HANDLER = register("tracer"){
        SpecialHandlerTracer.Factory()
    };
}
