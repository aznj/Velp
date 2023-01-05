apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.corePresentation))
    "implementation"(project(Modules.searchDomain))
    "implementation"(project(Modules.locationDomain))
    "implementation"(project(Modules.common))

    "implementation"(Coil.coilCompose)
}
