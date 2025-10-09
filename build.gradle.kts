plugins {
    id("hextrace.java")
    id("hextrace.mod-publish")
}

val minecraftVersion: String by project

// scuffed sanity check, because we need minecraftVersion to be in gradle.properties for the hexdoc plugin
libs.versions.minecraft.get().also {
    if (minecraftVersion != it) {
        throw IllegalArgumentException("Mismatched Minecraft version: gradle.properties ($minecraftVersion) != libs.versions.toml ($it)")
    }
}

architectury {
    // this looks up the value from gradle/libs.versions.toml
    minecraft = libs.versions.minecraft.get()
}

subprojects {
    apply(plugin = "dev.architectury.loom")
}

tasks {
    register("runAllDatagen") {
        dependsOn(":fabric:runDatagen")
    }
}

publishMods {
    displayName = "v${project.version}"

    github {
        repository = System.getenv("GITHUB_REPOSITORY") ?: ""
        commitish = System.getenv("GITHUB_SHA") ?: ""

        // https://modmuss50.github.io/mod-publish-plugin/platforms/github/#parent-releases
        allowEmptyFiles = true
    }
}
