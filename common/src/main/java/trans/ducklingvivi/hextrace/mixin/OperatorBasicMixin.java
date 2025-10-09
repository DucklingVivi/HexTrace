package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.arithmetic.operator.OperatorBasic;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trans.ducklingvivi.hextrace.IIotaDuck;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = OperatorBasic.class, remap = false)
public abstract class OperatorBasicMixin {

    @ModifyExpressionValue(method ="operate", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/casting/arithmetic/operator/OperatorBasic;apply(Ljava/lang/Iterable;Lat/petrak/hexcasting/api/casting/eval/CastingEnvironment;)Ljava/lang/Iterable;"))
    private static Iterable<Iota> hextrace$logOperator(Iterable<Iota> result , @Local(ordinal = 1) List<Iota> iotas, @Share("added") LocalRef<List<HexPattern>> trace) {
        ArrayList<Iota> old = new ArrayList<>();
        ArrayList<HexPattern> new_trace = new ArrayList<>();
        for (Iota value : result) {
            old.add(value);
        }
        result.forEach(old::remove);
        old.forEach(iota -> {
            if(((IIotaDuck)iota).isTraced()) {
                new_trace.addAll(((IIotaDuck) iota).getTrace());
            }
        });
        trace.set(new_trace);
        return result;
    }

    @WrapOperation(method = "operate$lambda$1", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static boolean hextrace$applyTrace(List<?> instance, Object e, Operation<Boolean> original, @Share("added") LocalRef<List<HexPattern>> trace){
        if(e instanceof Iota iota) {
            ((IIotaDuck) iota).markTraceable(trace.get());
        }
        original.call(instance,e);
        return false;
    }


}
