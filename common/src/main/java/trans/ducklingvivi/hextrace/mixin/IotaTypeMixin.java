package trans.ducklingvivi.hextrace.mixin;


import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.iota.IotaType;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.api.utils.HexUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import trans.ducklingvivi.hextrace.IIotaDuck;

@Mixin(IotaType.class)
public class IotaTypeMixin {


    @Unique
    private static final String TRACER_TAG = "hextrace";

    @WrapMethod(method = "serialize")
    private static CompoundTag hextrace$modifySerialize(Iota iota, Operation<CompoundTag> original){
        var tag = original.call(iota);
        if(((IIotaDuck) iota).isTraced()) {
            ListTag listTag = new ListTag();
            var traces = ((IIotaDuck) iota).getTrace();
            for(var trace : traces){
                listTag.add(trace.serializeToNBT());
            }
            tag.put(TRACER_TAG, listTag);
        }
        return tag;
    }
    @WrapMethod(method = "deserialize(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/server/level/ServerLevel;)Lat/petrak/hexcasting/api/casting/iota/Iota;")
    private static Iota hextrace$modifyDeserialize(CompoundTag tag, ServerLevel world, Operation<Iota> original) {
        var iota = original.call(tag, world);
        if(tag.contains(TRACER_TAG)) {
            ListTag tags = tag.getList(TRACER_TAG, Tag.TAG_COMPOUND);
            for (var t : tags) {
                var pattern = HexPattern.fromNBT(HexUtils.downcast(t, CompoundTag.TYPE));
                ((IIotaDuck) iota).markTraceable(pattern);
            }
        }
        return iota;
    }

    @WrapMethod(method = "getDisplay")
    private static Component hextrace$modifyGetDisplay(CompoundTag tag, Operation<Component> original){
        var base = original.call(tag);
        if(tag.contains(TRACER_TAG))
            return base.copy().append(Component.literal(" (traced: " + tag.getDouble(TRACER_TAG) + ")"));
        return base;
    }

    @WrapOperation(method = "getDisplayWithMaxWidth", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/casting/iota/IotaType;display(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/network/chat/Component;"))
    private static Component hextrace$modifyGetDisplayWithMaxWidth(IotaType<?> instance, Tag tag, Operation<Component> original, @Local(argsOnly = true) CompoundTag compoundTag) {
        var base = original.call(instance, tag);
        if(compoundTag.contains(TRACER_TAG))
            return base.copy().append(Component.literal(" (traced: " + compoundTag.getDouble(TRACER_TAG) + ")"));
        return base;
    }
}
