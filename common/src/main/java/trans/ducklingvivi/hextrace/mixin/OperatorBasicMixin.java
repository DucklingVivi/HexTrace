package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.arithmetic.operator.OperatorBasic;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trans.ducklingvivi.hextrace.IIotaDuck;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(OperatorBasic.class)
public abstract class OperatorBasicMixin {

    @WrapOperation(method ="operate", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/casting/arithmetic/operator/OperatorBasic;apply(Ljava/lang/Iterable;Lat/petrak/hexcasting/api/casting/eval/CastingEnvironment;)Ljava/lang/Iterable;"), remap = false)
    private static Iterable<Iota> hextrace$logOperator(OperatorBasic instance, Iterable<? extends Iota> iotas, CastingEnvironment castingEnvironment, Operation<Iterable<Iota>> original) {
        var result = original.call(instance, iotas, castingEnvironment);
        var old = new ArrayList<Iota>((Collection<? extends Iota>) iotas);
        old.removeAll((Collection<? extends Iota>) result);
        old.forEach(iota -> {
            if(((IIotaDuck)iota).isTraced()) {
                var trace = ((IIotaDuck) iota).getTrace();
                result.forEach(i -> {
                    ((IIotaDuck) i).markTraceable(trace);
                });
            }
        });
        return result;
    }


}
