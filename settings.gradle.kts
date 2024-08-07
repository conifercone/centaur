pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "centaur"
include("centaur-authentication")
include("centaur-authentication:authentication-adapter")
findProject(":centaur-authentication:authentication-adapter")?.name = "authentication-adapter"
include("centaur-authentication:authentication-application")
findProject(":centaur-authentication:authentication-application")?.name =
    "authentication-application"
include("centaur-authentication:authentication-domain")
findProject(":centaur-authentication:authentication-domain")?.name = "authentication-domain"
include("centaur-authentication:authentication-infrastructure")
findProject(":centaur-authentication:authentication-infrastructure")?.name =
    "authentication-infrastructure"
include("centaur-authentication:authentication-client")
findProject(":centaur-authentication:authentication-client")?.name = "authentication-client"
include("centaur-extension")
include("centaur-log")
include("centaur-log:log-adapter")
findProject(":centaur-log:log-adapter")?.name = "log-adapter"
include("centaur-log:log-application")
findProject(":centaur-log:log-application")?.name = "log-application"
include("centaur-log:log-client")
findProject(":centaur-log:log-client")?.name = "log-client"
include("centaur-log:log-domain")
findProject(":centaur-log:log-domain")?.name = "log-domain"
include("centaur-log:log-infrastructure")
findProject(":centaur-log:log-infrastructure")?.name = "log-infrastructure"
include("centaur-basis")
include("centaur-unique")
include("centaur-unique:unique-adapter")
findProject(":centaur-unique:unique-adapter")?.name = "unique-adapter"
include("centaur-unique:unique-client")
findProject(":centaur-unique:unique-client")?.name = "unique-client"
include("centaur-unique:unique-application")
findProject(":centaur-unique:unique-application")?.name = "unique-application"
include("centaur-unique:unique-domain")
findProject(":centaur-unique:unique-domain")?.name = "unique-domain"
include("centaur-unique:unique-infrastructure")
findProject(":centaur-unique:unique-infrastructure")?.name = "unique-infrastructure"
include("centaur-mail")
include("centaur-mail:mail-adapter")
findProject(":centaur-mail:mail-adapter")?.name = "mail-adapter"
include("centaur-mail:mail-application")
findProject(":centaur-mail:mail-application")?.name = "mail-application"
include("centaur-mail:mail-client")
findProject(":centaur-mail:mail-client")?.name = "mail-client"
include("centaur-mail:mail-domain")
findProject(":centaur-mail:mail-domain")?.name = "mail-domain"
include("centaur-mail:mail-infrastructure")
findProject(":centaur-mail:mail-infrastructure")?.name = "mail-infrastructure"
include("centaur-file")
include("centaur-file:file-adapter")
findProject(":centaur-file:file-adapter")?.name = "file-adapter"
include("centaur-file:file-application")
findProject(":centaur-file:file-application")?.name = "file-application"
include("centaur-file:file-client")
findProject(":centaur-file:file-client")?.name = "file-client"
include("centaur-file:file-domain")
findProject(":centaur-file:file-domain")?.name = "file-domain"
include("centaur-file:file-infrastructure")
findProject(":centaur-file:file-infrastructure")?.name = "file-infrastructure"
include("centaur-sms")
include("centaur-sms:sms-adapter")
findProject(":centaur-sms:sms-adapter")?.name = "sms-adapter"
include("centaur-sms:sms-application")
findProject(":centaur-sms:sms-application")?.name = "sms-application"
include("centaur-sms:sms-client")
findProject(":centaur-sms:sms-client")?.name = "sms-client"
include("centaur-sms:sms-domain")
findProject(":centaur-sms:sms-domain")?.name = "sms-domain"
include("centaur-sms:sms-infrastructure")
findProject(":centaur-sms:sms-infrastructure")?.name = "sms-infrastructure"
include("centaur-message")
include("centaur-message:message-adapter")
findProject(":centaur-message:message-adapter")?.name = "message-adapter"
include("centaur-message:message-application")
findProject(":centaur-message:message-application")?.name = "message-application"
include("centaur-message:message-domain")
findProject(":centaur-message:message-domain")?.name = "message-domain"
include("centaur-message:message-client")
findProject(":centaur-message:message-client")?.name = "message-client"
include("centaur-message:message-infrastructure")
findProject(":centaur-message:message-infrastructure")?.name = "message-infrastructure"
