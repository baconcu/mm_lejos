package de.micromata.edu.lejos.track3r;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * TODO Dokumentation
 */
public class FollowColor {
  
  public static void main(String[] args) {
    Track3R track3r = new Track3R();
    DifferentialPilot pilot = new DifferentialPilot(DifferentialPilot.WHEEL_SIZE_EV3, 17f, track3r.getRightMotor(), track3r.getLeftMotor());
    pilot.setTravelSpeed(15);
    SampleProvider light = track3r.getColorSensor().getColorIDMode();

    float[] sample = new float[light.sampleSize()];
    while (Button.ESCAPE.isUp()) {
        light.fetchSample(sample, 0);
        LCD.drawString(String.valueOf(sample[0]), 1, 2);
      if (sample[0] < 6) {
            Button.LEDPattern(1);
            pilot.arc(0, 13);
        } else if (sample[0] > 6) {
            Button.LEDPattern(2);
            pilot.arc(0, -13);
        } else {
            if (pilot.isMoving() == false) {
                pilot.forward();
            }
            Button.LEDPattern(3);
        }
    }
    track3r.getColorSensor().close();
}

}
