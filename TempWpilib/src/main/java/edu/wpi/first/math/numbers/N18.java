// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.numbers;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.Num;

/**
 * A class representing the number 18.
*/
@SuppressWarnings("PMD")
public final class N18 extends Num implements Nat<N18> {
  private N18() {
  }

  /**
   * The integer this class represents.
   *
   * @return The literal number 18.
  */
  @Override
  public int getNum() {
    return 18;
  }

  /**
   * The singleton instance of this class.
  */
  public static final N18 instance = new N18();
}
