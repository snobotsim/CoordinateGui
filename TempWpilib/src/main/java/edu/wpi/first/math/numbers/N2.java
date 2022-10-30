// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 2.
*/
@SuppressWarnings("PMD")
public final class N2 extends Num implements Nat<N2> {
  private N2() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 2.
  */
  @Override
  public int getNum() {
    return 2;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N2 instance = new N2();
}
