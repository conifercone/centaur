dependencies {
    implementation(project(":centaur-message:message-client"))
    implementation(project(":centaur-message:message-domain"))
    implementation(project(":centaur-message:message-infrastructure"))
    implementation(project(":centaur-extension"))
    implementation(libs.grpcStub)
    implementation(libs.grpcSpringBootStarter)
    implementation(libs.springBootActuator)
    implementation(libs.springDataCommons)
    implementation(libs.springTx)
}
