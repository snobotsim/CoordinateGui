// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 16.
*/
public final class N16 extends Num implements Nat<N16> {
  private N16() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 16.
  */
  @Override
  public int getNum() {
    return 16;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N16 instance = new N16();
}
