
plugins {
    `maven-publish`
    id("adapters.base-conventions")
}

publishing {
    publications.create<MavenPublication>("maven") {
        if (project.parent != null && project.parent != rootProject) {
            artifactId = "${project.parent!!.name}-${project.name}"
        }

        from(components["java"])
    }
}
