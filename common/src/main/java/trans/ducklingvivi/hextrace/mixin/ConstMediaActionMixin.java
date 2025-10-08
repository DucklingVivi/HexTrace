package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.castables.ConstMediaAction;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import trans.ducklingvivi.hextrace.Hextrace;
import trans.ducklingvivi.hextrace.IIotaDuck;

import java.util.ArrayList;
import java.util.List;

@Mixin(ConstMediaAction.DefaultImpls.class)
public class ConstMediaActionMixin {

    @WrapOperation(method = "operate", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/casting/castables/ConstMediaAction;executeWithOpCount(Ljava/util/List;Lat/petrak/hexcasting/api/casting/eval/CastingEnvironment;)Lat/petrak/hexcasting/api/casting/castables/ConstMediaAction$CostMediaActionResult;"),remap = false)
    private static ConstMediaAction.CostMediaActionResult hextrace$logConstMediaAction(ConstMediaAction instance, List<? extends Iota> args, CastingEnvironment castingEnvironment, Operation<ConstMediaAction.CostMediaActionResult> original) {
        var old = new ArrayList<Iota>(args);
        var result = original.call(instance, args, castingEnvironment);

        var added = result.getResultStack();
        old.removeAll(added);
        for (Iota iota : old) {
            if (((IIotaDuck) iota).isTraced()) {
                var trace = ((IIotaDuck) iota).getTrace();
                added.forEach(i -> {
                    ((IIotaDuck) i).markTraceable(trace);
                });
            }
        }

        return result;
    }
}
