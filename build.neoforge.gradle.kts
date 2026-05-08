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



java {
	toolchain.languageVersion = JavaLanguageVersion.of(
		if (stonecutter.current.parsed >= "26.1") 25
		else (findProperty("deps.java") as String).toInt()
	)
}

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

	exclusiveContent { // Sable
		forRepository {
			maven {
				url = uri("https://maven.ryanhcode.dev/releases")
				name = "RyanHCode Maven"
			}
		}
		filter {
			includeGroup("dev.ryanhcode.sable")
			includeGroup("dev.ryanhcode.sable-companion")
		}
	}

	maven { url = uri("https://maven.createmod.net") } // Create, Ponder, Flywheel
	maven { url = uri("https://maven.ithundxr.dev/snapshots") } // Registrate

	//cc:t
	maven {
		url = uri("https://maven.squiddev.cc")
		content {
			includeGroup("cc.tweaked")
		}
	}

	//curse maven
	repositories {
		exclusiveContent {
			forRepository {
				maven {
					url = uri("https://cursemaven.com")
				}
			}
			filter {
				includeGroup( "curse.maven")
			}
		}
	}
}

dependencies {
	implementation(libs.moulberry.mixinconstraints)
	jarJar(libs.moulberry.mixinconstraints)

	// Use findProperty to avoid "unknown property" exceptions
	val mcVer = findProperty("deps.minecraft") ?: "unknown"
	val sableVer = findProperty("deps.sable") ?: "0.0.0"
	val flywheelVersion = findProperty("create.flywheel") ?: "0.0.0"
	val cctVer = findProperty("deps.cct") ?: "0.0.0"

	compileOnly("cc.tweaked:cc-tweaked-$mcVer-core-api:$cctVer")
	compileOnly("cc.tweaked:cc-tweaked-$mcVer-forge-api:$cctVer")
	runtimeOnly("cc.tweaked:cc-tweaked-$mcVer-forge:$cctVer")


	if (findProperty("deps.fancy-tab-sections.edition") == "curseforge" && mcVer == "1.21.1") {
		implementation("curse.maven:fancy-tab-sections-1537163:8057615")
	}

	if (stonecutter.current.parsed >= "26.1") return@dependencies

	implementation("maven.modrinth:starcatcher:${findProperty("deps.starcatcher")}-NEOFORGE-$mcVer")
	//implementation("maven.modrinth:create-aeronautics:${findProperty("deps.create-aeronautics")}+mc$mcVer")

	// sable
	//api("dev.ryanhcode.sable-companion:sable-companion-common-$mcVer:${findProperty("deps.sable-companion")}")
	//runtimeOnly("dev.ryanhcode.sable:sable-neoforge-$mcVer:$sableVer")
	//compileOnly("dev.ryanhcode.sable:sable-neoforge-$mcVer:$sableVer")

	// create
	//implementation("com.simibubi.create:create-$mcVer:${findProperty("deps.create")}:slim") { isTransitive = false }
	implementation ("net.createmod.ponder:ponder-neoforge:${findProperty("create.ponder")}+mc$mcVer")
	compileOnly("dev.engine-room.flywheel:flywheel-neoforge-api-$mcVer:${flywheelVersion}")
	runtimeOnly("dev.engine-room.flywheel:flywheel-neoforge-$mcVer:${flywheelVersion}")

	//implementation(files(".libs/create_submarine-1.0.7.jar"))
	runtimeOnly(files(".libs/shutupgl-1.0.0.jar"))


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
