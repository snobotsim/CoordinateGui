load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_pmd//pmd:toolchains.bzl", "rules_pmd_toolchains")

def activate_dependencies():
    PMD_VERSION = "6.39.0"
    rules_pmd_toolchains(pmd_version = PMD_VERSION)

    repositories = ["https://repo1.maven.org/maven2"]

    jupiter_version = "5.6.1"
    platform_version = "1.6.1"

    artifacts = [
        "org.apache.logging.log4j:log4j-api:2.11.0",
        "org.apache.logging.log4j:log4j-core:2.11.0",
        "org.yaml:snakeyaml:1.18",
        "org.fxmisc.easybind:easybind:1.0.3",
        "com.google.guava:guava:21.0",

        "org.junit.jupiter:junit-jupiter-api:" + jupiter_version,
        "org.junit.jupiter:junit-jupiter-params:" + jupiter_version,
        "org.junit.jupiter:junit-jupiter-engine:" + jupiter_version,
        "org.junit.platform:junit-platform-commons:" + platform_version,
        "org.junit.platform:junit-platform-console:" + platform_version,
        "org.junit.platform:junit-platform-engine:" + platform_version,
        "org.junit.platform:junit-platform-launcher:" + platform_version,
        "org.junit.platform:junit-platform-suite-api:" + platform_version,
    ]

    maven_install(
        name = "maven",
        artifacts = artifacts,
        repositories = repositories,
        #        maven_install_json = "//build_scripts/bazel/deps:maven_install.json",
    )

    maven_install(
        name = "maven_javafx",
        artifacts = [
            "org.openjfx:javafx-base:11",
            "org.openjfx:javafx-controls:11",
            "org.openjfx:javafx-fxml:11",
            "org.openjfx:javafx-graphics:11",
            "org.openjfx:javafx-swing:11",
            "org.openjfx:javafx-media:11",
            "org.openjfx:javafx-web:11",
        ],
        repositories = [
            "https://repo1.maven.org/maven2",
            "https://repo.maven.apache.org/maven2/",
        ],
    )
