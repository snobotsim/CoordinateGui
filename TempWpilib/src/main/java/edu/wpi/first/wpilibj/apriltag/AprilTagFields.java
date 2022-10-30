package edu.wpi.first.wpilibj.apriltag;

@SuppressWarnings("PMD")
public enum AprilTagFields {
    k2022RapidReact("2022-rapidreact.json");

    public static final String kBaseResourceDir = "/edu/wpi/first/wpilibj/apriltag/";

    public final String m_resourceFile;

    AprilTagFields(String resourceFile) {
        m_resourceFile = kBaseResourceDir + resourceFile;
    }
}
