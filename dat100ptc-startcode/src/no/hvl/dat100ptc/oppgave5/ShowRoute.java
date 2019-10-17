package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 
		
		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 
		
		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double latitude = 0;
		double longitude = 0;
		double latitude2 = 0;
		double longitude2 = 0;
		for (int i = 1; i < gpspoints.length; i++) {
			longitude = ( gpspoints[i-1].getLongitude() * xstep() - 31108 ); //høyest 850 lavest 50
			latitude = 800 - ( gpspoints[i-1].getLatitude() * ystep() - 1502307 ); //høyest 800 lavest 0
			longitude2 = ( gpspoints[i].getLongitude() * xstep() - 31108 );
			latitude2 = 800 - ( gpspoints[i].getLatitude() * ystep() - 1502307 );
			fillCircle((int)longitude ,(int)latitude, 3);
			drawLine((int)longitude, (int)latitude, (int)longitude2, (int)latitude2);
			setColor(055,200,031);
			pause(25);
		}
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
	 
		
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
	
		}
		// TODO - SLUTT
	

}
