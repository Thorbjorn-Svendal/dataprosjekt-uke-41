package no.hvl.dat100ptc.oppgave4;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		
		for (int i = 1; i < gpspoints.length; i++) {
			
			double d = GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
			distance = distance + d;
		}
		return distance;
		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for (int i = 1; i < gpspoints.length; i++) {
		
			if (gpspoints[i-1].getElevation() < gpspoints[i].getElevation()) {
				elevation += gpspoints[i].getElevation() - gpspoints[i-1].getElevation();
			}
		}
		return elevation;
		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		double distance = 0;
		double speed = 0;
		int totalTime = 0;
		for (int i = 1; i < gpspoints.length; i++) {
			distance = GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
			speed = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			double speedMS = speed/3.6;
			totalTime += (int)distance  / (int)speedMS ;	
		}
		return totalTime;
		
		
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double speeds[] = new double[gpspoints.length -1];
		double speed = 0;
		for (int i = 1 ; i < gpspoints.length; i++) {
			speed = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			speeds[i-1] = speed;
		}

		return speeds;
		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		
		for (int i = 0; i < gpspoints.length; i++) {
			maxspeed = GPSUtils.findMax(speeds());
		}
		return maxspeed;
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		double distance = totalDistance();
		int time = totalTime();
		 
		average = (distance/time) * 3.6;
		return average;
		
		
		// TODO - SLUTT
		
	
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		double time = (double)secs/3600;
		
		// TODO - START
		
		if (speedmph < 10) {
			met = 4.0;
		}
		if (speedmph < 12) {
			met = 6.0;
		}
		if (speedmph < 14) {
			met = 8.0;
		}
		if (speedmph < 16) {
			met = 10.0;
		}
		if (speedmph < 20) {
			met = 12.0;
		}
		if (speedmph >= 20) {
			met = 16.0;
		}
		
		kcal = met * weight * time;
		return kcal;
		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal;

		// TODO - START
		
		int secs = totalTime();
		double speed = averageSpeed();
		
		totalkcal = kcal(weight, secs, speed);
		return totalkcal;
		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		
		System.out.println("Total Time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :" + GPSUtils.formatDouble(totalDistance()/1000) + " km");
		System.out.println("Total elevation:" + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      :" + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average Speed  :" + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         :" + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		
		System.out.println("==============================================");
		// TODO - SLUTT
		
	}
	
}
