package jeu;

import utile.Personnage;

public class Joueur {
	private static final int TROUVE_ID = 10;
	private static final int COUT_ESSAI = 1;
	private static final int SCORE_INIT = 30;
	private static final int GAIN_PARTIE = 40;
	private static final int PERTE_PARTIE = 0;
	

	private String identite;
	private int score;

	public void setNouvelleIdentite() {
		identite = Personnage.donnerPerso();
	}

	public void initScore() {
		score = SCORE_INIT;
	}
	public boolean aLeMemeNom(String nom) {
		return (nom).compareTo(identite)==0;
		}
	
	
	
	public  boolean aUnNomInferieur(String nom) {
	return (nom).compareTo(identite)<0;
	}
	
	public boolean aUnNomSuperieur(String nom) {
	return (nom).compareTo(identite)>0;
	}
	// Les constantes statiques correspondant aux points du jeu

	// Les attributs d'un joueur

	// Constructeur affectant une identité au joueur
	public Joueur() {
		super();
		initScore();
		setNouvelleIdentite();
	}

	// Accesseurs, il n'y a pas besoin de mutateur car seule Joueur manipule ses
	// attributs.

	public String getIdentite() {
		return identite;
	}

	public int getScore() {
		return score;
	}

	/**
	 * Ajoute les points associés à une découverte de sa propre identité et affecte
	 * une nouvelle identité au joueur
	 */
	public void gererIdTrouvee() {
		score = score + TROUVE_ID;
		setNouvelleIdentite();
	}

	/**
	 * Compter une tentative râtée de découverte de sa propre identité
	 */
	public void compterTentative() {
		score = score - COUT_ESSAI;
	}

	/**
	 * 
	 * @param proposition
	 * @return
	 */
	public boolean isIdentiteTrouve(String proposition) {
		boolean rep = false;
		if (proposition.equals(identite)) {
			rep = true;
			gererIdTrouvee();
		}
		compterTentative();
		return rep;
	}

	/**
	 * fonction booléenne liée au score
	 * 
	 * @return si le joueur a gagné ou non
	 */
	public boolean isGagnant() {
		return (score >= GAIN_PARTIE);
	}

	/**
	 * fonction booléenne liée au score
	 * 
	 * @return si le joueur a perdu ou non
	 */
	public boolean isPerdant() {
		return (score <= PERTE_PARTIE);
	}

	@Override
	public String toString() {
		return "Joueur [identite=" + identite + ", score=" + score + "]";
	}

}
