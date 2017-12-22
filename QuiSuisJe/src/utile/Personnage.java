package utile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Personnage {

	// Le nom du fichier utilisé pour initialiser les personnages obtenu en effectuant un
	// clic droit sur le fichier Personnage.txt, dans la rubrique Propriété.
	private static final String CHEMIN_FICHIER = "/donnees/Personnages.txt";

	// la liste de personnages qui aura été chargée une première fois à partir d'un fichier txt
	private static ArrayList<String> personnagesDispos = null;

	/**
	 * Retourne un personnage tiré au hasard dans une liste de personnages, initialisé par le fichier associé
	 * au nom de fichier placé en constante.  
	 * @return le nom de ce personnage
	 */
	static public String donnerPerso() {
		String perso=null;

		if (personnagesDispos==null) {

			personnagesDispos = new ArrayList<String>();
			BufferedReader lecteurAvecBuffer = null;
			String ligne;

			lecteurAvecBuffer = new BufferedReader(new InputStreamReader(Personnage.class.getResourceAsStream(CHEMIN_FICHIER)));

			try {
				while ((ligne = lecteurAvecBuffer.readLine()) != null) {
					personnagesDispos.add(ligne);
				}
				lecteurAvecBuffer.close();

			}catch(Exception e){
				System.out.println("erreur : "+e.getMessage());
			}
		}

		/**
		 * choix d'un personnage au hasard
		 */

		perso = personnagesDispos.get((int)(Math.random()*personnagesDispos.size()));

		return perso;
	}

}
