
plugins {
    `maven-publish`
    id("adapters.base-conventions")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
