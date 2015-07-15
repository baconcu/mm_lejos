package de.micromata.edu.lejos.track3r;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;

public class FollowWall {
	public static void main(String[] args) {
		Track3R track3r = new Track3R();
		DifferentialPilot testPilot = new DifferentialPilot(4.32f, 16f, Motor.D, Motor.A);
		LCD.drawString("start", 0, 0);
		SampleProvider distancestraight = track3r.getIrSensor1().getDistanceMode();
		EV3IRSensor irSensor2 = track3r.getIrSensor2();
		SampleProvider distanceside = null;
		if (irSensor2 != null) {
			distanceside = irSensor2.getDistanceMode();
		}

		float[] sample1 = new float[distancestraight.sampleSize()];
		float[] sample2 = new float[distanceside.sampleSize()];
		while (Button.ESCAPE.isUp()) {
			distancestraight.fetchSample(sample1, 0);
			distanceside.fetchSample(sample2, 0);
			testPilot.forward();
			while (sample1[0] >= 30f) {
				LCD.drawString("Driving", 0, 0);
				distancestraight.fetchSample(sample1, 0);
			}
			testPilot.stop();
			Motor.B.setSpeed(900);
			Motor.B.rotate(1000);
			if (distanceside != null) {
				distanceside.fetchSample(sample2, 0);
				if (sample2[0] >= 10f) {
					testPilot.rotate(90);
				} else {
					testPilot.rotate(-90);
				}
			}
		}
		track3r.getIrSensor1().close();
		if (irSensor2 != null) {
			irSensor2.close();
		}

	}
}