package trans.ducklingvivi.hextrace.registry;

import at.petrak.hexcasting.api.casting.castables.SpecialHandler;
import at.petrak.hexcasting.common.lib.HexRegistries;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import trans.ducklingvivi.hextrace.Hextrace;
import trans.ducklingvivi.hextrace.casting.actions.spells.SpecialHandlerTracer;

public class HextraceSpecialHanders {
    public static DeferredRegister<SpecialHandler.Factory<?>> REG = DeferredRegister.create(Hextrace.MODID,HexRegistries.SPECIAL_HANDLER);

    public static RegistrySupplier<SpecialHandler.Factory<SpecialHandlerTracer>> TRACE_HANDLER = REG.register("tracer", SpecialHandlerTracer.Factory::new);

    public static void register(){
        REG.register();
    }
}
