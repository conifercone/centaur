dependencies {
    implementation(project(":centaur-mail:mail-domain"))
    implementation(project(":centaur-mail:mail-client"))
    implementation(project(":centaur-file:file-client"))
    implementation(project(":centaur-extension"))
    implementation(libs.springBootThymeleaf)
    implementation(libs.springBootMail)
    implementation(libs.protobufJava)
    implementation(libs.grpcClientSpringBootStarter)
    implementation(libs.commonsIo)
}
