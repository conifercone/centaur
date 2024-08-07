dependencies {
    api(project(":centaur-basis"))
    implementation(project(":centaur-log:log-client"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.grpc.spring.boot.starter)
    implementation(libs.curator.recipes)
    implementation(libs.spring.data.commons)
    implementation(libs.spring.security.core)
    implementation(libs.p6spy)
    compileOnly(libs.spring.boot.starter.data.jpa)
    implementation(libs.micrometer.tracing)
    implementation(libs.alimt)
    implementation(libs.asciitable)
    implementation(libs.deepl)
}
