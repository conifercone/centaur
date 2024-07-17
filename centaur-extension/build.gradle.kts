dependencies {
    api(project(":centaur-basis"))
    implementation(project(":centaur-log:log-client"))
    implementation(libs.springBootStarterWeb)
    implementation(libs.grpcSpringBootStarter)
    implementation(libs.curatorRecipes)
    implementation(libs.springDataCommons)
    implementation(libs.springSecurityCore)
    implementation(libs.p6spy)
    compileOnly(libs.springBootDataJpa)
    implementation(libs.micrometerTracing)
}
