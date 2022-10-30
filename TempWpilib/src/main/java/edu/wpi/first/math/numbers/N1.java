// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 1.
*/
@SuppressWarnings("PMD")
public final class N1 extends Num implements Nat<N1> {
  private N1() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 1.
  */
  @Override
  public int getNum() {
    return 1;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N1 instance = new N1();
}
