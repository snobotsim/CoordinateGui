load("@rules_pmd//pmd:defs.bzl", "pmd")
load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")


def _generate_version_file_impl(ctx):
    out = ctx.actions.declare_file(ctx.attr.output_file)
    ctx.actions.expand_template(
        output = out,
        template = ctx.file.template,
        substitutions = {
            "${plugin_version}": "TODO - Built with bazel",
            "${package_name}": ctx.attr.package,
        },
    )
    return [DefaultInfo(files = depset([out]))]

generate_version_file = rule(
    implementation = _generate_version_file_impl,
    attrs = {
        "output_file": attr.string(mandatory = True),
        "package": attr.string(mandatory = True),
        "template": attr.label(
            allow_single_file = True,
            mandatory = True,
        ),
    },
)


def __styleguide(name, srcs):
    pass
    pmd(
        name = name + "-pmd_analysis",
        srcs = srcs,
        rulesets = ["//styleguide:pmd_rules"],
    )

def coordinate_gui_library(name, srcs, **kwargs):
    java_library(
        name = name,
        srcs = srcs,
        **kwargs
    )

    __styleguide(name, srcs)

def coordinate_gui_binary(name, srcs = [], **kwargs):
    java_binary(
        name = name,
        srcs = srcs,
        **kwargs
    )

    if srcs:
        __styleguide(name, srcs)

def coordinate_gui_test(name, srcs, deps = [], runtime_deps = [], **kwargs):

    junit_deps = [
        "@maven//:org_junit_jupiter_junit_jupiter_api",
        "@maven//:org_junit_jupiter_junit_jupiter_params",
        "@maven//:org_junit_jupiter_junit_jupiter_engine",
    ]

    junit_runtime_deps = [
        "@maven//:org_junit_platform_junit_platform_commons",
        "@maven//:org_junit_platform_junit_platform_console",
        "@maven//:org_junit_platform_junit_platform_engine",
        "@maven//:org_junit_platform_junit_platform_launcher",
        "@maven//:org_junit_platform_junit_platform_suite_api",
    ]

    java_test(
        name = name,
        srcs = srcs,
        deps = deps + junit_deps,
        runtime_deps = runtime_deps + junit_runtime_deps,
        args = ["--select-package", "org"],
        main_class = "org.junit.platform.console.ConsoleLauncher",
        use_testrunner = False,
        **kwargs
    )

    __styleguide(name, srcs)


def coordinate_gui_widget(name, package, srcs = [], **kwargs):
    generate_version_file(
        name = "generate-version",
        output_file = "PluginVersion.java",
        package = package,
        template = "//build_scripts:version_template",
    )

    coordinate_gui_library(
        name = name,
        srcs = srcs + [":generate-version"],
        **kwargs
    )