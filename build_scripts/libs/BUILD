java_library(
    name = "javafx_graphics",
    visibility = ["//visibility:public"],
    exports = ["@maven_javafx//:org_openjfx_javafx_graphics"] + select({
        "@bazel_tools//src/conditions:linux_x86_64": ["@maven_javafx//:org_openjfx_javafx_graphics_linux"],
        "@bazel_tools//src/conditions:darwin": ["@maven_javafx//:org_openjfx_javafx_graphics_mac"],
        "@bazel_tools//src/conditions:windows": ["@maven_javafx//:org_openjfx_javafx_graphics_win"],
    }),
)

java_library(
    name = "javafx_fxml",
    visibility = ["//visibility:public"],
    exports = ["@maven_javafx//:org_openjfx_javafx_fxml"] + select({
        "@bazel_tools//src/conditions:linux_x86_64": ["@maven_javafx//:org_openjfx_javafx_fxml_linux"],
        "@bazel_tools//src/conditions:darwin": ["@maven_javafx//:org_openjfx_javafx_fxml_mac"],
        "@bazel_tools//src/conditions:windows": ["@maven_javafx//:org_openjfx_javafx_fxml_win"],
    }),
)

java_library(
    name = "javafx_base",
    visibility = ["//visibility:public"],
    exports = ["@maven_javafx//:org_openjfx_javafx_base"] + select({
        "@bazel_tools//src/conditions:linux_x86_64": ["@maven_javafx//:org_openjfx_javafx_base_linux"],
        "@bazel_tools//src/conditions:darwin": ["@maven_javafx//:org_openjfx_javafx_base_mac"],
        "@bazel_tools//src/conditions:windows": ["@maven_javafx//:org_openjfx_javafx_base_win"],
    }),
)


java_library(
    name = "javafx_controls",
    visibility = ["//visibility:public"],
    exports = ["@maven_javafx//:org_openjfx_javafx_controls"] + select({
        "@bazel_tools//src/conditions:linux_x86_64": ["@maven_javafx//:org_openjfx_javafx_controls_linux"],
        "@bazel_tools//src/conditions:darwin": ["@maven_javafx//:org_openjfx_javafx_controls_mac"],
        "@bazel_tools//src/conditions:windows": ["@maven_javafx//:org_openjfx_javafx_controls_win"],
    }),
)
