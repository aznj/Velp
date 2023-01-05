apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.corePresentation))
    "implementation"(project(Modules.detailDomain))
    "implementation"(project(Modules.common))

    "implementation"(Coil.coilCompose)
    "implementation"(Google.maps)
    "implementation"(Google.playServicesMaps)
}
