package trans.ducklingvivi.hextrace.fabric.datagen

import trans.ducklingvivi.hextrace.datagen.HextraceActionTags
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHextraceDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::HextraceActionTags)
    }
}
