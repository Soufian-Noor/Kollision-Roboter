package MedIT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import koilib.graphics.KGui;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicDesktopIconUI;

public class Robots extends KGui {
	private ArrayList<Roboter> list;

	/**
	 * Erstellt eine Parameterlose GUI, in dieser kann der Anwender beliebig viele Objekte der Klasse Roboter darstellen
	 */
	public Robots() {
		super(800, 600, "Robots", true);
		// Erzeugt eine neue ArrayList mit Roboter-Objekten
		this.list = new ArrayList<>();
		// Endlos-Schleife, die bei der Eingabe 0 abbrichst
		while (true) {
			String s = JOptionPane.showInputDialog(
					"Geben Sie Start-, Ziel Koordinaten und geschwindigkeit an. \n Schema: sx,sy,zx,zy,v \n Für die x-Koordinaten: Eine Zahl zwischen 0-769\n Und für die y-Koordinaten: Eine Zahl zwischen 0-569 \n Mit 0 beenden Sie die Eingabe.");
			// Wenn der Anwender 0 eingibt endet die Schleife
			if (s.equals("0")) {
				break;
			}
			// Aus dem Eingabe-String erzeugen wir ein String-Feld
			String roboterString[] = s.split(",");
			// Wir erzeugen ein double-Feld, mit allen Parametern fuer unser Roboter-Objekt
			double roboterDouble[] = new double[roboterString.length];
			try {
				for (int i = 0; i < roboterString.length; i++) {
					roboterDouble[i] = Double.parseDouble(roboterString[i]);
				}
				Roboter a = new Roboter(roboterDouble[0], roboterDouble[1], roboterDouble[2], roboterDouble[3],
						roboterDouble[4]);
				this.list.add(a);
			// Bei einer falschen Eingabe soll die Eingabe nicht akzeptiert und wiederholt werden
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Leider falsche Eingabe!");
			}
		}
	}

	/**
	 * Zeichnet die Roboter(als Rechteck) in die Grafik
	 */
	public void render(Graphics g) {
		g.setColor(getBackground());
		// uebermalt bei jedem durchlauf das alte Bild mit einem Schwarzen Rechteck, der groesse des Grafikfensters
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
// geht die ArrayList mit den Roboterobjekten durch, und zeichnet diese anschließend
// ueberprueft dabei ob die Roboter kollidieren, falls ja wird die farbe des Rechtecks geaendert
		for (int i = 0; i < list.size(); i++) {
			g.setColor(Color.WHITE);
			for (int j = 0; j < list.size(); j++) {
				if (i == j) {
					continue;
				}
				if (list.get(i).collision(list.get(j))) {
					g.setColor(Color.MAGENTA);
				}
			}			
			g.fillRect((int) list.get(i).x, (int) list.get(i).y, Roboter.LAENGE, Roboter.LAENGE);
			g.drawRect((int) list.get(i).getZx(), (int) list.get(i).getZy(), Roboter.LAENGE, Roboter.LAENGE);
		}
	}

	/**
	 * Wenn die Taste k gedrueckt wird,
	 * sollen auf die Roboter die Methode move() aufgerufen werden
	 * Wenn die Taste l gedrueckt wird,
	 * sollen auf die Roboter die Methode backmove() aufgerufen werden
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_K) {
			for (Roboter a : list) {
				a.move();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			for (Roboter a : list) {
				a.backmove();
			}
		}
		renderToBuffered();
	}

}
