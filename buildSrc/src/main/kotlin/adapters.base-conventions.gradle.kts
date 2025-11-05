
plugins {
    `java-library`
}

java {
    // using the toolchain to set the java version resulted in version errors in the cursed spigot setup.
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }
}

dependencies {
    compileOnly("com.nukkitx.fastutil:fastutil-int-object-maps:8.3.1")
    compileOnly("org.incendo:cloud-core:2.0.0-rc.2")
    compileOnly("net.kyori:adventure-api:4.24.0")
    compileOnly("net.kyori:adventure-text-serializer-gson:4.24.0")
}

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/main/")
}
