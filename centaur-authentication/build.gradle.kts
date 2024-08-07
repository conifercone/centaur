apply(from = "../scripts/springboot.gradle")

dependencies {
    implementation(project(":centaur-authentication:authentication-adapter"))
    implementation(project(":centaur-authentication:authentication-client"))
    implementation(project(":centaur-authentication:authentication-application"))
    implementation(project(":centaur-authentication:authentication-infrastructure"))
    implementation(project(":centaur-authentication:authentication-domain"))
    implementation(project(":centaur-extension"))
    implementation(project(":centaur-log:log-client"))
    implementation(libs.spring.boot.starter.oauth2.authorization.server)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.postgresql)
    implementation(libs.jasypt)
    implementation(libs.spring.cloud.starter.consul.discovery)
    implementation(libs.spring.cloud.starter.consul.config)
    implementation(libs.swagger3Ui)
    implementation(libs.bundles.micrometer)
    implementation(libs.grpc.spring.boot.starter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.jasypt)
    testImplementation(libs.spring.security.test)
    testImplementation(libs.grpc.client.spring.boot.starter)
    testImplementation(project(":centaur-unique:unique-client"))
    implementation(libs.flyway.core)
    implementation(libs.flyway.database.postgresql)
    implementation(libs.hypersistence.utils.hibernate63)
    implementation(libs.redis.om.spring)
    implementation(libs.jobrunr.spring.boot3.starter)
    annotationProcessor(libs.redis.om.spring)
    implementation(libs.caffeine)
}
