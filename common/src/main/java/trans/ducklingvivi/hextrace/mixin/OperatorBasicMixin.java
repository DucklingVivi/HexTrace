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
    private static Iterable<Iota> hextrace$traceOperator(Iterable<Iota> result , @Local(ordinal = 1) List<Iota> iotas) {
        ArrayList<Iota> op_result = new ArrayList<>();
        for (Iota iota : result) {
            op_result.add(iota);
        }
        var to_apply = new ArrayList<>(iotas);
        to_apply.removeAll(op_result);
        for(Iota iota : to_apply) {
            if (((IIotaDuck) iota).isTraced()) {
                var traces = ((IIotaDuck) iota).getTrace();
                op_result.forEach(
                        i -> ((IIotaDuck) i).markTraceable(traces)
                );
            }
        }
        return op_result;
    }
}
