dependencies {
    implementation(project(":centaur-authentication:authentication-client"))
    implementation(project(":centaur-unique:unique-client"))
    implementation(project(":centaur-authentication:authentication-infrastructure"))
    implementation(project(":centaur-authentication:authentication-domain"))
    implementation(project(":centaur-extension"))
    implementation(libs.springSecurityCore)
    implementation(libs.grpcStub)
    implementation(libs.grpcSpringBootStarter)
    implementation(libs.springBootActuator)
    implementation(libs.springDataCommons)
    implementation(libs.springTx)
}
