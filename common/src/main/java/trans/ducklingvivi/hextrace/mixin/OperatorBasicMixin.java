package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.arithmetic.operator.OperatorBasic;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trans.ducklingvivi.hextrace.IIotaDuck;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mixin(value = OperatorBasic.class, remap = false)
public abstract class OperatorBasicMixin {

    @ModifyExpressionValue(method ="operate", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/casting/arithmetic/operator/OperatorBasic;apply(Ljava/lang/Iterable;Lat/petrak/hexcasting/api/casting/eval/CastingEnvironment;)Ljava/lang/Iterable;"))
    private static Iterable<Iota> hextrace$logOperator(Iterable<Iota> result , @Local(ordinal = 1) List<Iota> iotas) {
        ArrayList<Iota> old = new ArrayList<>(iotas);
        result.forEach(old::remove);
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
