/*
 * Copyright (c) 2019-2025 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

plugins {
    id("adapters.modded-conventions")
}

val neoVersion = "20.6.138"
val metaProperties = minecraftVersion("1.20.6", "command", mapOf("neo_version" to neoVersion))

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

dependencies {
    neoForge("net.neoforged:neoforge:$neoVersion")

    api(project(":modded_command_common_v1_20_6", configuration = "namedElements"))
    shadowCommon(project(":modded_command_common_v1_20_6", configuration = "transformProductionNeoForge"))

    compileOnly(project(":common"))
    modImplementation("org.incendo:cloud-neoforge:2.0.0-beta.10")
    include("org.incendo:cloud-neoforge:2.0.0-beta.10")
}

tasks {
    withType<ProcessResources> {
        filteringCharset = "UTF-8"
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(metaProperties)
        }
    }
}
