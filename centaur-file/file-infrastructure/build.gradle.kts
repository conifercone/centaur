dependencies {
    implementation(project(":centaur-file:file-domain"))
    implementation(project(":centaur-file:file-client"))
    implementation(project(":centaur-extension"))
    implementation(libs.minio)
    implementation(libs.spring.web)
}
