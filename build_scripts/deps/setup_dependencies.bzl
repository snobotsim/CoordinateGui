load("@bazelrio//:deps.bzl", "setup_bazelrio_dependencies")
load("@rules_pmd//pmd:dependencies.bzl", "rules_pmd_dependencies")

def setup_dependencies():
    setup_bazelrio_dependencies()

    rules_pmd_dependencies()
