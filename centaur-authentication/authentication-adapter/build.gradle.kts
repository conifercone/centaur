dependencies {
    implementation(project(":centaur-authentication:authentication-client"))
    implementation(project(":centaur-authentication:authentication-domain"))
    implementation(project(":centaur-basis"))
    implementation(libs.springSecurityCore)
    implementation(libs.springBootStarterWeb)
    implementation(libs.swagger3Ui)
    implementation(libs.springDataCommons)
}
