dependencies {
    implementation(project(":centaur-log:log-client"))
    implementation(project(":centaur-log:log-domain"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.swagger3Ui)
    implementation(libs.spring.data.commons)
}
