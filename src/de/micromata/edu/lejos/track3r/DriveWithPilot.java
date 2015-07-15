package de.micromata.edu.lejos.track3r;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * TODO Dokumentation
 *
 */
public class DriveWithPilot {
  public static void main(String[] args) {
    Track3R track3r = new Track3R();
    DifferentialPilot testPilot = new DifferentialPilot(4.32f, 16f, Motor.D,
        Motor.A);
    LCD.drawString("start", 0, 0);
    SampleProvider distance = track3r.getIrSensor1().getDistanceMode();
    while (Button.ESCAPE.isUp()) {
      float[] sample = new float[distance.sampleSize()];
      distance.fetchSample(sample, 0);
      testPilot.forward();
      while (sample[0] >= 30f) {
        distance.fetchSample(sample, 0);
      }
      testPilot.stop();
      Motor.B.setSpeed(900);
      Motor.B.rotate(600);
      testPilot.rotate(-90); // degree clockwise
    }
    track3r.getIrSensor1().close();

  }
}