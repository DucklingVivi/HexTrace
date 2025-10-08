package trans.ducklingvivi.hextrace.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.castables.SpecialHandler
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.math.HexPattern
import com.sun.jna.platform.win32.Guid
import net.minecraft.network.chat.Component
import trans.ducklingvivi.hextrace.IIotaDuck

class SpecialHandlerTracer(val x: HexPattern) : SpecialHandler {
    override fun act(): Action? {
        return InnerAction(this.x)
    }

    override fun getName(): Component? {
        return Component.translatable("hextrace.action.trace")
    }

    class InnerAction(val x: HexPattern) : ConstMediaAction {
        override val argc: Int = 1
        override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
            val value = args[0]
            (value as IIotaDuck).markTraceable(x)
            return listOf(value)
        }
    }

    class Factory : SpecialHandler.Factory<SpecialHandlerTracer> {
        override fun tryMatch(
            pattern: HexPattern,
            env: CastingEnvironment
        ): SpecialHandlerTracer? {
            val sig = pattern.anglesSignature();
            if(sig.startsWith("qqqaw")) {
                val new = sig.removePrefix("qqqaw")
                if(new.isEmpty()){
                    return null
                }
                return SpecialHandlerTracer(HexPattern.fromAngles(new, HexPattern.fromAngles("qqqaw", pattern.startDir).finalDir()))
            } else {
                return null
            }
        }
    }
}