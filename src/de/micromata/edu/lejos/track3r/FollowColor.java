package de.micromata.edu.lejos.track3r;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * TODO Dokumentation
 * 
 */
public class FollowColor {
	private static final int COLOR_WHITE = 6;

	public static void main(String[] args) {
		// Neue Track ertellen
		Track3R track3r = new Track3R();
		//
		DifferentialPilot pilot = new DifferentialPilot(DifferentialPilot.WHEEL_SIZE_EV3, 17f, track3r.getRightMotor(),
				track3r.getLeftMotor());

		pilot.setTravelSpeed(15);

		SampleProvider light = track3r.getColorSensor().getColorIDMode();
		float[] sample = new float[light.sampleSize()];

		while (Button.ESCAPE.isUp()) {
			light.fetchSample(sample, 0);
			float color = sample[0];
			LCD.drawString(String.valueOf(color), 1, 2);
			if (color == COLOR_WHITE) { // Fabe weiss
				if (pilot.isMoving() == false) { // muss sein, sonst fährt der Tracktor nicht
					pilot.forward();
				}
				Button.LEDPattern(3);				
			} else if (color < 6) { // 
				Button.LEDPattern(1); // Setze fabe auf grün
				pilot.arc(0, 10);  // drehe 10° nach rechts				
			} else { // < 6
				Button.LEDPattern(2);
				pilot.arc(0, -10);
				
			}
		}
		track3r.getColorSensor().close();
	}

}
