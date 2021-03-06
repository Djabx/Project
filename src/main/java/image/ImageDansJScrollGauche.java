package image;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jxmapviewer.viewer.GeoPosition;

import carte.Carte;
import projet.JLabelModif;

public class ImageDansJScrollGauche extends JFrame { // Création du JScollPane

	public static double poids;

	public static JScrollPane method(final ArrayList<image.Image> imagList, final Carte _carte) {

		// GridBacContains pour afficher que sur une colonne
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.0;
		c.gridx = 0;
		c.insets = new Insets(1, 0, 1, 0);

		// Jpanel qui contiendra les labels
		JPanel panel1 = new JPanel(new GridBagLayout());
		panel1.setLayout(new GridBagLayout());

		for (int i = 0; i < imagList.size(); i++) {
			String Path = imagList.get(i).file.getAbsolutePath();

			JLabel labe = new JLabelModif(i);

			labe.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// Permet de récupérer l'image qu'on vient de sélectionner
					// et
					// centre la carte sur sa position

					/* int indice = (((JLabelModif) e.getSource()).ind); */

					int indice = (((JLabelModif) e.getSource()).ind);

					_carte.centrerImag(new GeoPosition(imagList.get(indice).Latitude, imagList.get(indice).Longitude));

					poids = imagList.get(indice).taille * Math.pow(2, -20);
					java.text.DecimalFormat df = new java.text.DecimalFormat("0.###");

					JFrame fenetre = new JFrame();
					fenetre.setSize(210, 150);
					fenetre.setLocation(60, 50);
					fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					fenetre.setTitle("Info");
					fenetre.setVisible(true);

					/*
					 * JLabel Infolat = new
					 * JLabel("Latittude : "+imagList.get(indice_jlabel).
					 * Latittude); JLabel Infolon = new
					 * JLabel("Longitude :"+imagList.get(indice_jlabel).
					 * Longitude); JLabel Infonom = new
					 * JLabel("Nom :"+imagList.get(indice_jlabel).nom); JLabel
					 * Infopoids = new JLabel("Poids :"+df.format(poids)+" Mo");
					 */

					// Création d'une Jlabel qui affichera les infos de l'image
					// cliquée
					JLabel Info = new JLabel();
					Info.setText("<html>Latitude : " + imagList.get(indice).Latitude + "<br>" + "Longitude : "
							+ imagList.get(indice).Longitude + "<br>" + "Nom : " + imagList.get(indice).nom + "<br>"
							+ "Poids : " + df.format(poids) + " Mo" + "</html>");
					fenetre.add(Info);

					/*
					 * System.out.println("Latittude : "+imagList.get(indice).
					 * Latitude);
					 * System.out.println("Longitude : "+imagList.get(indice).
					 * Longitude);
					 * System.out.println("Nom : "+imagList.get(indice).nom);
					 * 
					 * System.out.println("Poids : "+df.format(poids)+" Mo");
					 * System.out.println("");
					 */
				}

			});

			// Changement de la taille de l'image
			ImageIcon imag = new ImageIcon(Path);
			Image imag2 = imag.getImage();
			Image nouvelle_image = imag2.getScaledInstance(270, 200, java.awt.Image.SCALE_SMOOTH);

			imag = new ImageIcon(nouvelle_image);
			labe.setIcon(imag);

			panel1.add(labe, c);// Ajout au label

		}

		// création du JScrollPane
		JScrollPane panel_scroll_l = new JScrollPane(panel1);

		panel_scroll_l.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_scroll_l.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JScrollPane imageScroll = new JScrollPane(panel1);
		imageScroll.setPreferredSize(new Dimension(300, 700));

		return imageScroll;
	}
}
