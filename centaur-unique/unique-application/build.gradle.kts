dependencies {
    implementation(project(":centaur-basis"))
    implementation(project(":centaur-unique:unique-client"))
    implementation(project(":centaur-unique:unique-domain"))
    implementation(project(":centaur-unique:unique-infrastructure"))
    implementation(libs.grpcStub)
    implementation(libs.grpcSpringBootStarter)
    implementation(libs.springBootActuator)
}
