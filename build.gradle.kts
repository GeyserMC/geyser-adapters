
plugins {
    `java-library`
    `maven-publish`
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

allprojects {
    group = "org.geysermc.geyser.adapters"
    version = "1.9-SNAPSHOT"
    description = "Adapters for Geyser"

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }
}

subprojects {
    apply(plugin = "java-library")

    dependencies {
        compileOnly("com.nukkitx.fastutil:fastutil-int-object-maps:8.3.1")
    }

    repositories {
        mavenCentral()
        maven("https://repo.opencollab.dev/main/")
    }
}

/*
publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
 */
