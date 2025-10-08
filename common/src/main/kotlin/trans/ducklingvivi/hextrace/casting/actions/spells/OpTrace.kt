package trans.ducklingvivi.hextrace.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.getDouble
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.entity.Entity
import trans.ducklingvivi.hextrace.IIotaDuck

object OpTrace : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val number = args.getDouble(1, argc)
        val iota: Iota = args[0]
        return listOf(iota)
    }
}
