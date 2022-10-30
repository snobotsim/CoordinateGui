// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.wpilibj.apriltag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class LoadConfigTest {
  @ParameterizedTest
  @EnumSource(AprilTagFields.class)
  void testLoad(AprilTagFields field) {
    AprilTagFieldLayout layout = Assertions.assertDoesNotThrow(() -> AprilTagFieldLayout.fromField(field));
    assertFalse(layout.getTags().isEmpty());

  }
}