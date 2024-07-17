apply(from = "../scripts/springboot.gradle")

dependencies {
    implementation(project(":centaur-authentication:authentication-client"))
    implementation(project(":centaur-log:log-infrastructure"))
    implementation(project(":centaur-log:log-application"))
    implementation(project(":centaur-extension"))
    implementation(project(":centaur-log:log-adapter"))
    implementation(libs.jasypt)
    implementation(libs.consulDiscovery)
    implementation(libs.consulConfig)
    implementation(libs.swagger3Ui)
    implementation(libs.springBootDataElasticsearch)
    implementation(libs.springKafka)
    implementation(libs.grpcSpringBootStarter)
    implementation(libs.bundles.micrometer)
    implementation(libs.caffeine)
    testImplementation(libs.springbootTest)
    testImplementation(libs.jasypt)
}
