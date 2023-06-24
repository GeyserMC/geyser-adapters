import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `maven-publish`
    id("adapters.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    named<Jar>("jar") {
        archiveClassifier.set("unshaded")
    }

    val shadowJar = named<ShadowJar>("shadowJar") {
        archiveBaseName.set(determineArtifactId(archiveBaseName.get()))
        archiveClassifier.set("all")
    }
    assemble {
        dependsOn(shadowJar)
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        artifactId = determineArtifactId(artifactId)

        from(components["java"])
    }
}
