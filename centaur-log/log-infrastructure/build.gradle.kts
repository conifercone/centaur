dependencies {
    implementation(project(":centaur-log:log-domain"))
    implementation(project(":centaur-log:log-client"))
    implementation(project(":centaur-unique:unique-client"))
    implementation(project(":centaur-extension"))
    implementation(libs.springKafka)
    implementation(libs.springBootDataElasticsearch)
    implementation(libs.micrometerTracing)
}
