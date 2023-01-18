
from jinja2 import Template
import os
import re


def camel_to_snake(input_str):
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', input_str)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()

class GenConfig:
    def __init__(self, year, game_name, subfields=None):
        self.year = year
        self.game_package = "org.snobot.coordinate_gui.game." + camel_to_snake(game_name)
        self.shuffleboard_package = "org.snobot.coordinate_gui.shuffleboard." + camel_to_snake(game_name)
        self.game_name = game_name

        if subfields is None:
            self.subfields = [game_name]
        else:
            self.subfields = subfields


def load_template(template_dir, template_name):

    template_path = os.path.join(template_dir, template_name)

    with open(template_path, 'r') as f:
        template_input = f.read()
        template = Template(template_input)
        template.globals['camel_to_snake'] = camel_to_snake

    return template


def render_and_write_template(config, template_dir, template_name, output_file, **kwargs):
    if not os.path.exists(os.path.dirname(output_file)):
        os.makedirs(os.path.dirname(output_file))

    # print(f"Writing {output_file}")

    if output_file.endswith(".fxml"):
        with(open(output_file, 'w')) as f:
            template = load_template(template_dir, template_name)
            f.write(template.render(config=config, **kwargs))
    else:
        with(open(output_file, 'wb')) as f:
            template = load_template(template_dir, template_name)
            f.write(template.render(config=config, **kwargs).encode())


def generate_core_project(config):
    package_as_dir = config.game_package.replace(".", "/")
    template_dir = "ProjectGenerator/templates/GuiCore"

    project_folder = f"CoordinateGuiCore{config.year}"
    src_root = os.path.join(project_folder, "src", "main", "java")
    resource_root = os.path.join(project_folder, "src", "main", "resources")

    build_output = os.path.join(project_folder, "build.gradle")
    render_and_write_template(config, template_dir, "build.gradle.jinja", build_output)

    main_output = os.path.join(src_root, package_as_dir, f"StandaloneMain.java")
    render_and_write_template(config, template_dir, "StandaloneMain.java.jinja", main_output)

    for subfield in config.subfields:
        controller_output = os.path.join(src_root, package_as_dir, f"{subfield}Controller.java")
        render_and_write_template(config, template_dir, "controller.java.jinja", controller_output, subfield=subfield)

        fxml_file_name = camel_to_snake(subfield) + "_field"
        resource_output = os.path.join(resource_root, package_as_dir, f"{fxml_file_name}.fxml")
        render_and_write_template(config, template_dir, "fxml.jinja", resource_output, subfield=subfield)


def generate_widget_project(config):
    package_as_dir = config.shuffleboard_package.replace(".", "/")
    template_dir = "ProjectGenerator/templates/GuiWidget"

    project_folder = f"CoordinateGuiWidget{config.year}"
    src_root = os.path.join(project_folder, "src", "main", "java")
    resource_root = os.path.join(project_folder, "src", "main", "resources")

    build_output = os.path.join(project_folder, "build.gradle")
    render_and_write_template(config, template_dir, "build.gradle.jinja", build_output)

    data_output = os.path.join(src_root, package_as_dir, "data", f"{config.game_name}CoordinateGuiData.java")
    render_and_write_template(config, template_dir, "Data.java.jinja", data_output)

    data_type_output = os.path.join(src_root, package_as_dir, "data", f"{config.game_name}CoordinateGuiDataType.java")
    render_and_write_template(config, template_dir, "DataType.java.jinja", data_type_output)

    data_type_output = os.path.join(src_root, package_as_dir, f"ShuffleboardPlugin{config.year}.java")
    render_and_write_template(config, template_dir, f"Plugin.java.jinja", data_type_output)

    for subfield in config.subfields:
        resources_output = os.path.join(resource_root, package_as_dir, f"{subfield}CoordinateGuiWidget.fxml")
        render_and_write_template(config, template_dir, f"fxml.jinja", resources_output, subfield=subfield)

        data_type_output = os.path.join(src_root, package_as_dir, f"{subfield}CoordinateGuiWidget.java")
        render_and_write_template(config, template_dir, f"Widget.java.jinja", data_type_output, subfield=subfield)


def main():
    d = os.path.join(os.path.dirname(os.path.realpath(__file__)), "..")
    os.chdir(d)

    configs = []
    configs.append(GenConfig(2018, "Powerup"))
    configs.append(GenConfig(2019, "DeepSpace"))
    configs.append(GenConfig(2020, "InfiniteRecharge"))
    configs.append(GenConfig(2021, "InfiniteRechargeAtHome", subfields = ["Barrel", "Bounce", "GalacticSearchA", "GalacticSearchB", "InfiniteRechargeAtHome", "Slalom"]))
    configs.append(GenConfig(2022, "RapidReact"))
    configs.append(GenConfig(2023, "ChargedUp"))

    for config in configs:
        generate_core_project(config)
        generate_widget_project(config)




if __name__ == "__main__":
    main()