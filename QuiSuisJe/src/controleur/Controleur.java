package controleur;

import ihm.Menu;

import java.util.ArrayList;

import jeu.Joueur;

public class Controleur {

	// Instance du menu utilis�e pour l'ihm.
	// Comme il n'existe pas d'objet ihm � proprement parl�, simplement des
	// affichages ou des lectures
	// du clavier, des m�thodes statiques sur Menu feront l'affaire.

	// Les deux joueurs concern�s par le jeu.
	private Joueur joueur1;
	private Joueur joueur2;

	// Liste des constantes priv�es permettant d'enchainer les menus.
	// Menu principal
	private static final int MENU_JOUEUR1 = 0;
	private static final int MENU_JOUEUR2 = 1;
	private static final int MENU_NOUVEAUX_PERSONNAGES = 21;
	private static final int MENU_NOUVELLE_PARTIE = 22;
	private static final int MENU_SCORES = 3;

	// Menu des joueurs
	private static final int LIRE_IDENTITE_J1 = 41;
	private static final int PROPOSER_IDENTITE_J1 = 51;
	private static final int LIRE_IDENTITE_J2 = 42;
	private static final int PROPOSER_IDENTITE_J2 = 52;
	private static final int MENU_PRINCIPAL = 6;

	private int getChoix(int menuAAfficher) {
		int rep;
		switch (menuAAfficher) {
		case MENU_PRINCIPAL:
			ArrayList<String> leMenu = new ArrayList<String>(4);
			leMenu.add("Menu joueur 1");
			leMenu.add("Menu joueur 2");
			leMenu.add("Nouveaux personnages");
			leMenu.add("Nouvelle partie");
			leMenu.add("Score Actuel");
			switch (Menu.getChoix(leMenu)) {
			case 0:
				rep = MENU_JOUEUR1;
				break;
			case 1:
				rep = MENU_JOUEUR2;
				break;
			case 2:
				rep = MENU_NOUVEAUX_PERSONNAGES;
				break;
			case 3:
				rep = MENU_NOUVELLE_PARTIE;
				break;
			case 4:
				rep = MENU_SCORES;
				break;

			default:
				rep = -1;
				break;
			}
			break;
		case MENU_JOUEUR1:
			leMenu = new ArrayList<String>(3);
			leMenu.add("identit� du joueur 2");
			leMenu.add("proposer identit� joueur 1");
			leMenu.add("Menu Principal");
			switch (Menu.getChoix(leMenu)) {
			case 0:
				rep = LIRE_IDENTITE_J2;
				break;
			case 1:
				rep = PROPOSER_IDENTITE_J1;
				break;
			case 2:
				rep = MENU_PRINCIPAL;
				break;
			default:
				rep = -1;
				break;
			}
			break;
		case MENU_JOUEUR2:
			leMenu = new ArrayList<String>(3);
			leMenu.add("identit� du joueur 1");
			leMenu.add("proposer identit� joueur 2");
			leMenu.add("Menu Principal");
			switch (Menu.getChoix(leMenu)) {
			case 0:
				rep = LIRE_IDENTITE_J1;
				break;
			case 1:
				rep = PROPOSER_IDENTITE_J2;
				break;
			case 2:
				rep = MENU_PRINCIPAL;
				break;
			default:
				rep = -1;
				break;
			}
			break;

		default:
			rep = -1;
			break;
		}
		return rep;
	}

	/**
	 * Permet de lire un nom frapp� au clavier
	 * 
	 * @return
	 */
	private String getNom() {
		return Menu.lireString();
	}

	/**
	 * initialise la partie avec de nouveaux joueurs
	 */
	private void initialiserPartie() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
	}

	/**
	 * Affiche l'identit� du joueur qui n'est pas actif et affiche ensuite le menu
	 * du joueur actif
	 * 
	 * @param choixMenuJoueurActif
	 *            contient l'entier correspondant au menu du joueur actif
	 * @param titreJoueurActif
	 *            contient la chaine de caract�res permettant d'identifier qui est
	 *            le joueur actif, � savoir "joueur 1" ou "joueur 2"
	 * @param msgAutreIdentite
	 *            contient la chaine de caract�res indiquant qu'il s'agit bien de
	 *            l'identit� de l'autre joueur, par exemple << "identit� du joueur 2
	 *            = "+nom du joueur 2 >>
	 * @return le nouveau choix frapp� au clavier
	 */
	private int lireIdentite(int choixMenuJoueurActif, String titreJoueurActif, String msgAutreIdentite) {
		Menu.afficheMsg("taper alt tab si la fen�tre n'apparait pas");
		Menu.affMsgBox(titreJoueurActif, msgAutreIdentite);
		return this.getChoix(choixMenuJoueurActif);
	}

	/**
	 * Permet de saisir une identit� et de tester si l'identit� saisie est la bonne.
	 * 
	 * @param choixMenuJoueurActif
	 *            contient l'entier correspondant au menu du joueur actif
	 * @param titreJoueurActif
	 *            contient la chaine de caract�res permettant d'identifier qui est
	 *            le joueur actif � savoir "joueur 1" ou "joueur 2"
	 * @param joueurActif
	 *            le joueur actif qui va �voluer dans son score
	 * @return le nouveau choix frapp� au clavier, -1 si la partie est termin�e
	 */
	private int proposerIdentite(int choixMenuJoueurActif, String titreJoueurActif, Joueur joueurActif) {
		int choix = -1;
		Menu.afficheMsg("qui es-tu" + titreJoueurActif + "?");
		String proposition = getNom();
		if (joueurActif.isIdentiteTrouve(proposition)) {
			Menu.afficheMsg("Le joueur" + titreJoueurActif + "a trouv� la bonne identit�");
		} else {

			if (joueurActif.aUnNomInferieur(proposition)) {
				Menu.afficheMsg(titreJoueurActif + "Votre proposition  est Avant le nom recherch�");
			}
			if ((joueurActif.aUnNomSuperieur(proposition))) {
					Menu.afficheMsg(titreJoueurActif + "Votre proposition est Apr�s le nom recherch�");
				}
			}

		// Si le joueur actif n'a pas gagn� ni perdu, la partie continue
		if (!joueurActif.isGagnant() && !joueurActif.isPerdant()) {
			choix = this.getChoix(choixMenuJoueurActif);
		}
		return choix;
	}

	/**
	 * La m�thode jouer g�re les enchainements des menus. Les modifications des
	 * joueurs (le score) sont effectu�es par le m�tier, ce qui est l'essentiel de
	 * son travail avec le changement d'identit�.
	 */
	public void jouer() {
		boolean fini = false;
		this.initialiserPartie();
		int choix = this.getChoix(Controleur.MENU_PRINCIPAL);
		while (!fini) {

			switch (choix) {
			case -1:
				fini = true;
				break;
			case Controleur.MENU_PRINCIPAL:
				choix = this.getChoix(Controleur.MENU_PRINCIPAL);
				break;

			case Controleur.MENU_JOUEUR1:
				choix = this.getChoix(Controleur.MENU_JOUEUR1);
				break;
			case Controleur.MENU_JOUEUR2:
				choix = this.getChoix(Controleur.MENU_JOUEUR2);
				break;

			case Controleur.LIRE_IDENTITE_J2: // le joueur 1 est actif
				choix = this.lireIdentite(MENU_JOUEUR1, "joueur 1", "identit� du joueur 2 = " + joueur2.getIdentite());
				break;
			case Controleur.LIRE_IDENTITE_J1: // le joueur 2 est actif
				choix = this.lireIdentite(MENU_JOUEUR2, "joueur 1", "identit� du joueur 2 = " + joueur1.getIdentite());
				break;

			case PROPOSER_IDENTITE_J1:
				choix = this.proposerIdentite(MENU_JOUEUR1, "joueur 1", joueur1);
				break;
			case PROPOSER_IDENTITE_J2:
				choix = this.proposerIdentite(MENU_JOUEUR2, "joueur 2", joueur2);
				break;

			case MENU_SCORES: // Afficher Scores
				this.afficherScores();
				choix = this.getChoix(Controleur.MENU_PRINCIPAL);
				break;

			case MENU_NOUVELLE_PARTIE: // On r�-initialise les scores
				initialiserPartie();
				Menu.afficheMsg("nouvelle partie");
				choix = this.getChoix(Controleur.MENU_PRINCIPAL);
				break;
			case MENU_NOUVEAUX_PERSONNAGES: // on change les personnages
				joueur1.setNouvelleIdentite();
				joueur2.setNouvelleIdentite();
				Menu.afficheMsg("nouvelles identit�s attribu�es");
				choix = this.getChoix(Controleur.MENU_PRINCIPAL);
				break;

			default:
				// Code inaccessible selon nos v�rifications
				Menu.afficheMsg(" ## R�-essayez");
				break;
			}
			if (joueur1.isGagnant() || joueur2.isGagnant()) {
				Menu.afficheMsg("Fin de partie, un joueur vient de gagner !");
				fini = true;
			} else if (joueur1.isPerdant() || joueur2.isPerdant()) {
				Menu.afficheMsg("Fin de partie, un joueur vient de perdre !");
				fini = true;
			}
		}
		Menu.afficheMsg("Au revoir");
		this.afficherScores();
	}

	/**
	 * Demande un affichage des scores actuels dans la console. id�e : Vous pouvez
	 * essayer de passer ce message par une petite fenetre (utiliser affMsgBox).
	 */
	private void afficherScores() {

		Menu.affMsgBox("Score final ", "score du joueur 1 = " + joueur1.getScore());
		Menu.affMsgBox("Score final ", "score du joueur 2 = " + joueur2.getScore());
		;
	}

}
