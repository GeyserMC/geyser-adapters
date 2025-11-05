
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "geyser-adapters"

include(":common")

// Spigot
//include(":spigot")
//include(":spigot:all")
//include(":spigot:base")
//include(":spigot:v1_17_R1")
//include(":spigot:v1_18_R1")
//include(":spigot:v1_18_R2")
//include(":spigot:v1_19_R1")
//include(":spigot:v1_19_R2")
//include(":spigot:v1_19_R3")
//include(":spigot:v1_20_R1")
//include(":spigot:v1_20_R2")
//include(":spigot:v1_20_R3")
//include(":spigot:v1_20_R4")
//include(":spigot:v1_21_R1")
//include(":spigot:v1_21_R2")
//include(":spigot:v1_21_R3")

// Paper
//include(":paper")
//include(":paper:all")
//include(":paper:base")
//include(":paper:v766")
//include(":paper:v768")

// Modded
// Modded Adapters are split up since it makes more sense when you consider the versions required for Command Adapters (a lot)
// and which are required for World Adapters (2 currently), it starts to become a bit of a management mess.

fun defineModdedAdapters(version: String, type: String) {
    // We have different names since the fabric and neoforge module get a little angry finding the common module otherwise.
    val versionString = version.replace(".", "_")
    include(":modded_${type}_common_v${versionString}")
    project(":modded_${type}_common_v${versionString}").projectDir = file("modded/${type}/v${versionString}/common")
    include(":modded_${type}_fabric_v${versionString}")
    project(":modded_${type}_fabric_v${versionString}").projectDir = file("modded/${type}/v${versionString}/fabric")
    include(":modded_${type}_neoforge_v${versionString}")
    project(":modded_${type}_neoforge_v${versionString}").projectDir = file("modded/${type}/v${versionString}/neoforge")
}

// Modded World Adapters
// 1.21.3+
defineModdedAdapters("1.21.3", "world")
// 1.18.2 - 1.21.2 (excellent comptibility! thanks Mojang :3)
// Use 1.20.6, since we *can* benefit from biome suggestions and min world height,
// but on older versions we can leave it/use a default
defineModdedAdapters("1.20.6", "world")

// Modded Command Adapters
// 1.21.5+
defineModdedAdapters("1.21.5", "command")
// 1.20.6 - 1.21.4
defineModdedAdapters("1.20.6", "command")