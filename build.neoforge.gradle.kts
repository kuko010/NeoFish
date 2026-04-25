plugins {
	id("mod-platform")
	id("net.neoforged.moddev")
}

platform {
	loader = "neoforge"
	dependencies {
		required("minecraft") {
			forgeVersionRange = "[${prop("deps.minecraft")}]"
		}
		required("neoforge") {
			forgeVersionRange = "[1,)"
		}
	}
}


//java {
//	property("deps.java")
//}

neoForge {
	version = property("deps.neoforge") as String
	accessTransformers.from(rootProject.file("src/main/resources/aw/${stonecutter.current.version}.cfg"))
	validateAccessTransformers = true

	if (stonecutter.current.parsed < "26.1") {
		if (hasProperty("deps.parchment")) parchment {
			val (mc, ver) = (property("deps.parchment") as String).split(':')
			mappingsVersion = ver
			minecraftVersion = mc
		}
	}
	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "NeoForge Client (${stonecutter.active?.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "NeoForge Server (${stonecutter.active?.version})"
		}

		register("data") {
			data()
			gameDirectory = file("run/")
			ideName = "NeoForge Data (${stonecutter.active?.version})"
			programArguments.addAll(
				"--mod", prop("mod.id"),
				"--all",
				"--output", "${rootDir}/versions/datagen/${stonecutter.current.version.split("-")[0]}/src/main/generated",
				"--existing", "src/main/resources"
			)
		}

	}

	mods {
		register(property("mod.id") as String) {
			sourceSet(sourceSets["main"])
		}
	}
	sourceSets["main"].resources.srcDir("${rootDir}/versions/datagen/${stonecutter.current.version.split("-")[0]}/src/main/generated")
}

repositories {
	mavenCentral()
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
	maven {
		name = "RyanHCode Maven"
		url = uri("https://maven.ryanhcode.dev/releases")
	}

	maven {
		name = "BlameJared Maven (CrT / Bookshelf)"
		url = uri("https://maven.blamejared.com")
	}

	maven {
		name = "Fuzs Mod Resources"
		url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
	}
}

dependencies {
	implementation(libs.moulberry.mixinconstraints)
	jarJar(libs.moulberry.mixinconstraints)
	if (stonecutter.current.version == "1.21.1") {
		implementation(libs.starcatcher)
		implementation(libs.sable)
		implementation("maven.modrinth:create-aeronautics:1.0.3+mc1.21.1")
		implementation("maven.modrinth:create:mc1.21.1-6.0.9")
	}
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}



stonecutter {
/*	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}*/
}
