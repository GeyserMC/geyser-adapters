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

    repositories {
        maven {
            name = "opencollabRepo"
            credentials(PasswordCredentials::class)

            val releasesRepo = "https://repo.opencollab.dev/maven-releases/"
            val snapshotsRepo = "https://repo.opencollab.dev/maven-snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepo else releasesRepo)
        }
    }
}

/**
 * Subprojects are prefixed with the name of the parent project. e.g. "spigot-all"
 * Projects at the root level use the originalId. e.g. "common"
 */
fun Project.determineArtifactId(originalId: String): String {
    if (project.parent != null && project.parent != rootProject) {
        return "${project.parent!!.name}-${project.name}"
    }
    return originalId
}
