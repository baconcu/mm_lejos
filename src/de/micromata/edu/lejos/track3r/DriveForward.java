package de.micromata.edu.lejos.track3r;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

/**
 * 
 * Main Class
 * 
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DriveForward {
  Track3R tract3r = new Track3R();

  public static void main(String[] args) {
    DriveForward driveForward = new DriveForward();
    while (Button.ESCAPE.isUp()) { // Press back button to exit
      driveForward.demoPilotStuff();
      driveForward.tryUSStuff();
    }
  }

  /**
   * Trying to get the Ultrasonic Sensor to work but my batteries just died. 🙈
   */
  void tryUSStuff() {
    // TODO testing

    LCD.drawString("Ultrasonic stuff", 0, 0);
    LCD.drawString("incoming. ESC to leave", 0, 0);
    Delay.msDelay(100);
    // create a new sensor object on port 1
    // get an instance of this sensor in measurement mode
    SampleProvider distance = tract3r.getIrSensor1().getMode("Distance");

    // initialize an array of floats for fetching samples.
    // ask the SampleProvider how long the array should be
    float[] sample = new float[distance.sampleSize()]; // omg. The solution
    // to all my
    // problems with
    // IndexOuutOfBounds!

    // fetch a sample
    while (Button.ESCAPE.isUp()) { // esc to leave
      distance.fetchSample(sample, 0); // fetch distance (FIXME unkown
      // interval)
      LCD.drawString(String.valueOf(sample[0]), 0, 0); // draw sensor
      // value
    }

    tract3r.getIrSensor1().close(); // closing sensor after using it
    // TODO you could go on and code some multithreaded AI piloting stuff...
  }

  /**
   * Demonstrates pilot stuff
   */
  void demoPilotStuff() {

    LCD.drawString("DifferentialPilot Demo", 0, 0);
    DifferentialPilot testPilot = new DifferentialPilot(4.32f, 16f, Motor.D,
        Motor.A);
    testPilot.setRotateSpeed(30); // cm per second
    testPilot.travel(50); // cm
    testPilot.rotate(-90); // degree clockwise
    testPilot.travel(-50, true); // move backward for 50 cm
    while (testPilot.isMoving())
      Thread.yield(); // Thread stuff dunno what to do
    testPilot.rotate(-90); // rotate
    testPilot.rotate(270); // rotate
    testPilot.steer(-50, 180, true); // turn 180 degrees to the right
    testPilot.steer(100); // turns with left wheel stationary
    Delay.msDelay(1000); // Do this stuff above for that time
    testPilot.stop(); // exit
  }
}
