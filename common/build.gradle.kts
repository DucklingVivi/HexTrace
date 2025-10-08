import gradle.kotlin.dsl.accessors._2107f45011cf34878cdd9d11b4478488.modCompileOnly

plugins {
    id("hextrace.minecraft")
}

architectury {
    common("fabric", "forge")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(kotlin("reflect"))

    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation(libs.fabric.loader)
    modApi(libs.architectury)
    modApi(libs.clothConfig.common)

    libs.mixinExtras.common.also {
        implementation(it)
        annotationProcessor(it)
    }
    modApi(libs.inline.common)
    modCompileOnly( libs.hexcasting.common)
    modLocalRuntime( libs.hexcasting.common)
}
