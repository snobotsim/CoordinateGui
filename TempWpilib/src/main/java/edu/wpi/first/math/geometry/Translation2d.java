// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.interpolation.Interpolatable;

import java.util.Objects;

/**
 * Represents a translation in 2D space. This object can be used to represent a point or a vector.
 *
 * <p>This assumes that you are using conventional mathematical axes. When the robot is at the
 * origin facing in the positive X direction, forward is positive X and left is positive Y.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@SuppressWarnings("PMD")
public class Translation2d implements Interpolatable<Translation2d> {
  private final double m_x;
  private final double m_y;

  /** Constructs a Translation2d with X and Y components equal to zero. */
  public Translation2d() {
    this(0.0, 0.0);
  }

  /**
   * Constructs a Translation2d with the X and Y components equal to the provided values.
   *
   * @param x The x component of the translation.
   * @param y The y component of the translation.
   */
  @JsonCreator
  public Translation2d(
      @JsonProperty(required = true, value = "x") double x,
      @JsonProperty(required = true, value = "y") double y) {
    m_x = x;
    m_y = y;
  }

  /**
   * Constructs a Translation2d with the provided distance and angle. This is essentially converting
   * from polar coordinates to Cartesian coordinates.
   *
   * @param distance The distance from the origin to the end of the translation.
   * @param angle The angle between the x-axis and the translation vector.
   */
  public Translation2d(double distance, Rotation2d angle) {
    m_x = distance * angle.getCos();
    m_y = distance * angle.getSin();
  }

  /**
   * Calculates the distance between two translations in 2D space.
   *
   *
   * @param other The translation to compute the distance to.
   * @return The distance between the two translations.
   */
  public double getDistance(Translation2d other) {
    return Math.hypot(other.m_x - m_x, other.m_y - m_y);
  }

  /**
   * Returns the X component of the translation.
   *
   * @return The X component of the translation.
   */
  @JsonProperty
  public double getX() {
    return m_x;
  }

  /**
   * Returns the Y component of the translation.
   *
   * @return The Y component of the translation.
   */
  @JsonProperty
  public double getY() {
    return m_y;
  }

  /**
   * Returns the norm, or distance from the origin to the translation.
   *
   * @return The norm of the translation.
   */
  public double getNorm() {
    return Math.hypot(m_x, m_y);
  }

  /**
   * Returns the angle this translation forms with the positive X axis.
   *
   * @return The angle of the translation
   */
  public Rotation2d getAngle() {
    return new Rotation2d(m_x, m_y);
  }

  /**
   * Applies a rotation to the translation in 2D space.
   *
   * <p>This multiplies the translation vector by a counterclockwise rotation matrix of the given
   * angle.
   *
   * <pre>
   * [x_new]   [other.cos, -other.sin][x]
   * [y_new] = [other.sin,  other.cos][y]
   * </pre>
   *
   * <p>For example, rotating a Translation2d of &lt;2, 0&gt; by 90 degrees will return a
   * Translation2d of &lt;0, 2&gt;.
   *
   * @param other The rotation to rotate the translation by.
   * @return The new rotated translation.
   */
  public Translation2d rotateBy(Rotation2d other) {
    return new Translation2d(
        m_x * other.getCos() - m_y * other.getSin(), m_x * other.getSin() + m_y * other.getCos());
  }

  /**
   * Returns the sum of two translations in 2D space.
   *
   * <p>For example, Translation3d(1.0, 2.5) + Translation3d(2.0, 5.5) = Translation3d{3.0, 8.0).
   *
   * @param other The translation to add.
   * @return The sum of the translations.
   */
  public Translation2d plus(Translation2d other) {
    return new Translation2d(m_x + other.m_x, m_y + other.m_y);
  }

  /**
   * Returns the difference between two translations.
   *
   * <p>For example, Translation2d(5.0, 4.0) - Translation2d(1.0, 2.0) = Translation2d(4.0, 2.0).
   *
   * @param other The translation to subtract.
   * @return The difference between the two translations.
   */
  public Translation2d minus(Translation2d other) {
    return new Translation2d(m_x - other.m_x, m_y - other.m_y);
  }

  /**
   * Returns the inverse of the current translation. This is equivalent to rotating by 180 degrees,
   * flipping the point over both axes, or negating all components of the translation.
   *
   * @return The inverse of the current translation.
   */
  public Translation2d unaryMinus() {
    return new Translation2d(-m_x, -m_y);
  }

  /**
   * Returns the translation multiplied by a scalar.
   *
   * <p>For example, Translation2d(2.0, 2.5) * 2 = Translation2d(4.0, 5.0).
   *
   * @param scalar The scalar to multiply by.
   * @return The scaled translation.
   */
  public Translation2d times(double scalar) {
    return new Translation2d(m_x * scalar, m_y * scalar);
  }

  /**
   * Returns the translation divided by a scalar.
   *
   * <p>For example, Translation3d(2.0, 2.5) / 2 = Translation3d(1.0, 1.25).
   *
   * @param scalar The scalar to multiply by.
   * @return The reference to the new mutated object.
   */
  public Translation2d div(double scalar) {
    return new Translation2d(m_x / scalar, m_y / scalar);
  }

  @Override
  public String toString() {
    return String.format("Translation2d(X: %.2f, Y: %.2f)", m_x, m_y);
  }

  /**
   * Checks equality between this Translation2d and another object.
   *
   * @param obj The other object.
   * @return Whether the two objects are equal or not.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Translation2d) {
      return Math.abs(((Translation2d) obj).m_x - m_x) < 1E-9
          && Math.abs(((Translation2d) obj).m_y - m_y) < 1E-9;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(m_x, m_y);
  }

  @Override
  public Translation2d interpolate(Translation2d endValue, double t) {
    return new Translation2d(
        MathUtil.interpolate(this.getX(), endValue.getX(), t),
        MathUtil.interpolate(this.getY(), endValue.getY(), t));
  }
}
