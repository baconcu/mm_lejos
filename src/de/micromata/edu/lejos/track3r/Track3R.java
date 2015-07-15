package de.micromata.edu.lejos.track3r;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;

public class Track3R {
  private EV3LargeRegulatedMotor leftMotor;
  private EV3LargeRegulatedMotor rightMotor;
  private EV3ColorSensor colorSensor;
  private EV3IRSensor irSensor1;
  private EV3IRSensor irSensor2;

  public EV3LargeRegulatedMotor getLeftMotor() {
    if (leftMotor == null) {
      leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    }
    return leftMotor;
  }

  public EV3LargeRegulatedMotor getRightMotor() {
    if (rightMotor == null) {
      rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
    }
    return rightMotor;
  }

  public EV3ColorSensor getColorSensor() {
    if (colorSensor == null) {
      colorSensor = new EV3ColorSensor(SensorPort.S1);
    }
    return colorSensor;
  }

  public EV3IRSensor getIrSensor1() {
    if (irSensor1 == null) {
      irSensor1 = new EV3IRSensor(SensorPort.S2);
    }
    return irSensor1;
  }

  public EV3IRSensor getIrSensor2() {
    if (irSensor2 == null) {
      irSensor2 = new EV3IRSensor(SensorPort.S3);
    }
    return irSensor2;
  }

}
