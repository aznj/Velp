apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.corePresentation))
    "implementation"(project(Modules.homeDomain))

    "implementation"(Coil.coilCompose)
}