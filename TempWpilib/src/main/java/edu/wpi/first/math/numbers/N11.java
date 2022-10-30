// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 11.
*/
@SuppressWarnings("PMD")
public final class N11 extends Num implements Nat<N11> {
  private N11() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 11.
  */
  @Override
  public int getNum() {
    return 11;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N11 instance = new N11();
}
