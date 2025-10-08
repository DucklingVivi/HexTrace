package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import trans.ducklingvivi.hextrace.IIotaDuck;

import java.util.ArrayList;
import java.util.List;


@Mixin(Iota.class)
@Implements(@Interface(iface = IIotaDuck.class, prefix = "hexTrace$"))
public abstract class IotaMixin {
    @Unique
    private ArrayList<HexPattern> hextrace$tracers = null;


    public void hexTrace$markTraceable(HexPattern i) {
        if(!this.hexTrace$isTraced())
            hextrace$tracers = new ArrayList<>();
        hextrace$tracers.add(i);
    }

    public void hexTrace$markTraceable(List<HexPattern> i) {
        if(!this.hexTrace$isTraced())
            hextrace$tracers = new ArrayList<>();
        hextrace$tracers.addAll(i);
    }

    public boolean hexTrace$isTraced() {
        return hextrace$tracers != null;
    }


    public void hexTrace$clearTrace() {
        hextrace$tracers = null;
    }

    public List<HexPattern> hexTrace$getTrace() {
        return hextrace$tracers;
    }

    @WrapMethod(method = "display")
    private Component hextrace$modifyDisplay(Operation<Component> original) {
        var base = original.call();
        if(this.hexTrace$isTraced())
            return base.copy().append(Component.literal(" (traced: " + this.hexTrace$getTrace() + ")"));
        return base;
    }
}