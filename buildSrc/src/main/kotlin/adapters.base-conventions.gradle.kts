
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

    named<Jar>("jar") {
        archiveClassifier.set("unshaded")
    }
}

dependencies {
    compileOnly("com.nukkitx.fastutil:fastutil-int-object-maps:8.3.1")
}

repositories {
    mavenCentral()
    maven("https://repo.opencollab.dev/main/")
}
