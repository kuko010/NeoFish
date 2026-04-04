import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

modSettings {
    clientOptions {
        fov = 90
        guiScale = 3
        narrator = false
        darkBackground = true
        guiScale = 3
        musicVolume = 0.0
    }
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}



tasks.processResources {
    if (mod.isNeoforge) {
        val props = mapOf(
            "neoforge_version" to (findProperty("neoforge_version")?.toString() ?: ""),
            "id" to mod.id,
            "version" to mod.version,
            "name" to mod.name,
            "description" to mod.description,
            "minecraftVersion" to (findProperty("minecraft_version")?.toString() ?: "")
        )

        inputs.properties(props)

        filesMatching("META-INF/neoforge.mods.toml") {
            expand(props)
        }
    }
}

configurations {
    named("mappings") {
        dependencies.clear()
    }
}


repositories {
    mavenCentral()
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
    }
}

dependencies {
    mappings(loom.layered {
        officialMojangMappings()
        if (mod.minecraftVersion == "1.20.1") { parchment("org.parchmentmc.data:parchment-${mod.minecraftVersion}:2023.09.03@zip") }
        if (mod.minecraftVersion == "1.21.1") { parchment("org.parchmentmc.data:parchment-${mod.minecraftVersion}:2024.11.17@zip") }
    })
}


// Example of overriding publishing settings
publishMods {
    modrinth {
        if (mod.isFabric) requires("fabric-api")
    }

    curseforge {
        clientRequired = true // Set as needed
        serverRequired = false // Set as needed
        if (mod.isFabric) requires("fabric-api")
    }
}
