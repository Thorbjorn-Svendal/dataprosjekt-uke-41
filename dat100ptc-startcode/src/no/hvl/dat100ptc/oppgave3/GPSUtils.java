package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
			
		}
		return min;
		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			
			latitude[i] = gpspoints[i].getLatitude();
			
		}
		return latitude;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double longDiff = longitude2 - longitude1;
		double latDiff = latitude2 - latitude1;
		
		double a = Math.pow(Math.sin(latDiff/2),2) + Math.cos(latitude1) 
				   * Math.cos(latitude2) 
				   * Math.pow(Math.sin(longDiff/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = R * c;
		
		return d;
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		int a = gpspoint1.getTime();
		int b = gpspoint2.getTime();
		secs = b - a;
		double distance = distance(gpspoint1, gpspoint2);
		
		double metersec = distance/secs;
		speed = metersec * 3.6;

		return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		
		int hh = secs / 3600;
		int mm = (secs % 3600) / 60;
		int ss = (secs % 3600) % 60;
		
		timestr = String.format("%2s%02d:%02d:%02d", " ", hh, mm, ss);
				
		return timestr;
		//TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		// TODO - START
		
		String s;
		s = String.format(Locale.ROOT, "%.2f", d);
		str = String.format("%1$"+TEXTWIDTH+ "s", s);
	
		return str;
		
		// TODO - SLUTT
		
	}
}
