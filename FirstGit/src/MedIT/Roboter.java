package MedIT;

/**
 * @author Noor
 *
 */
public class Roboter {

	// Laenge beschreibt die laenge des Quadrats ( fuer die grafische Abbildungen )
	// Mindestabstand für die Methode collision
	public static final int MINDESTABSTAND = 0;
	public static final int LAENGE = 30;
	// Momentane Position des Roboters
	public double x, y;
	// Start- und Zielkoordinaten des Roboters
	private double start_x, start_y, ziel_x, ziel_y;
	// Geschwindigkeit
	private double geschwindigkeit;

	/**
	 * Erzeugt ein Roboter-Objekt mit den uebergebenen Parametern
	 * 
	 * @param sx x-Koordinate des Startpunkts
	 * @param sy y-Koordinate des Startpunkts
	 * @param zx x-Koordinate des Zielpunkts
	 * @param zy y-Koordinate des Zielpunkts
	 * @param v  geschwindigkeit
	 */
	public Roboter(double sx, double sy, double zx, double zy, double v) {
		this.x = sx;
		this.y = sy;
		this.start_x = sx;
		this.start_y = sy;
		this.ziel_x = zx;
		this.ziel_y = zy;
		this.geschwindigkeit = v;
	}

	/**
	 * Ändert die Position des Roboters. In Richtung des Ziels
	 */
	public void move() {
		// Falls der Roboter im Ziel ankommt, soll er stoppen
		if (((int) this.x == (int) this.ziel_x) && ((int) this.y == (int) this.ziel_y)) {

		} else if (((Math.round(this.x) == (int) this.ziel_x) && (Math.round(this.y) == (int) this.ziel_y))
				&& (((this.start_x > this.ziel_x) && (this.start_y < this.ziel_y))
						|| ((this.start_y > this.ziel_y) && (this.start_x < this.ziel_x)))) {

		}
		//
		else {

			// Wir bestimmen, wie flüssig, die Bewegung eines Roboter dargestellt werden
			// soll
			double genauigkeit = 0.01;
			// Strecke, die der Roboter fahren muss
			double strecke = Math.sqrt(Math.pow((this.getSx() - this.getZx()), 2) + Math.pow((this.start_y - this.ziel_y), 2));
			// Formel fuer den Faktor
			double faktor = (genauigkeit / strecke) * this.geschwindigkeit;
			// Richtungsvektor bestimmen
			double richtungs_vektor_x = this.ziel_x - this.start_x;
			double richtungs_vektor_y = this.ziel_y - this.start_y;

			// Die neue position des Roboter
			this.x += faktor * richtungs_vektor_x;
			this.y += faktor * richtungs_vektor_y;

		}
	}

	/**
	 * Wie move(). Nur in die  Rueckrichtung.
	 */
	public void backmove() {

		if (((int) this.x == (int) this.start_x) && ((int) this.y == (int) this.start_y)) {

		} else {

			// Wir bestimmen, wie flüssig, die Bewegung eines Roboter dargestellt werden
			// soll
			double genauigkeit = 0.01;
			// Strecke, die der Roboter fahren muss
			double strecke = Math.sqrt(Math.pow((this.getSx() - this.getZx()), 2) + Math.pow((this.start_y - this.ziel_y), 2));
			// Formel fuer den Faktor
			double faktor = (genauigkeit / strecke) * this.geschwindigkeit;
			// Richtungsvektor bestimmen
			double richtungs_vektor_x = this.ziel_x - this.start_x;
			double richtungs_vektor_y = this.ziel_y - this.start_y;

			// Die neue position des Roboter
			this.x -= faktor * richtungs_vektor_x;
			this.y -= faktor * richtungs_vektor_y;

		}

	}

	/**
	 * Ueberprueft ob es zu einer Kollision mit dem uebergebenem Roboter kommt k
	 * @param k
	 *        der uebergebene Roboter
	 * @return
	 * true: wenn die Roboter den Mindestabstand unterschreiten
	 * false: wenn die Roboter den Mindestabstand nicht unterschreiten
	 */
	public boolean collision(Roboter k) {
		return Math.abs(k.x - this.x) < LAENGE + MINDESTABSTAND && Math.abs(k.y - this.y) < LAENGE + MINDESTABSTAND;

	}

	// Getter- und Settermethoden
	public double getZx() {
		return this.ziel_x;
	}

	public void setZx(double zx) {
		this.ziel_x = zx;
	}

	public double getSx() {
		return this.start_x;
	}

	public void setSx(double sx) {
		this.start_x = sx;
	}

	public double getZy() {
		return this.ziel_y;
	}

	public void setZy(double zy) {
		this.ziel_y = zy;
	}

	public double getSy() {
		return this.start_y;
	}

	public void setSy(double sy) {
		this.start_y = sy;
	}

}
