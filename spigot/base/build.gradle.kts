
dependencies {
    api(projects.adapters)
    compileOnly("io.papermc.paper", "paper-api", "1.17.1-R0.1-SNAPSHOT")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

description = "base"
