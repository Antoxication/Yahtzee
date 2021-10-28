/*
 * Yahtzee.java													juillet 2021
 * INFO 1, pas de copyright, aucun droit
 */
package iut.jeux;
import java.util.Scanner;
import java.util.Arrays;

/**
 * permet de jouer une partie de Yahtzee
 * à chaque tour, le joueur va lancer ses 5 dés
 * et tenter d'obtenir une combinaison
 * le joueur marque des points allant de 0 à 50 points
 * selon la combinaison obtenue au bout de 3 lancers
 * @author Antonin
 */
public class Yahtzee {
	
	/**
	 * le nombre de lancers autorisés par tour
	 */
	public static final int NBRE_LANCERS_MAX = 3;
	
	/**
	 * le nombre de faces pour un dé
	 */
	public static final int NBRE_FACES_UN_DE = 6;
	
	/**
	 * le nombre de dés à lancer au maximum en 1 tour 
	 */
	public static final int NBRE_DES_MAX_A_LANCER = 5;
	
	/**
	 * le nombre de dés que le joueur veut relancer
	 * en 1 tour de jeu
	 */
	public int desALancer;
	/**
	 * nombre de lancers effectués
	 */
	public int nombreLancers;
	
	/**
	 * booléen déterminant si le joueur veut 
	 * toujours relancer des dés
	 */
	public boolean deAConserver;
	
	/**
	 * tableau des résultats du tour joué
	 */
	public int[] resultats = new int[5];
	
	/**
	 * tableau récaputilant les dés à lancer
	 * les valeurs -1 indiquent les dés qui vont être relancés
	 */
	public int[] aLancer = new int[5];
	
	/**
	 * le constructeur par défaut pour le yahtzee 
	 * initialise tous les dés à -1 (non lancé)
	 */
	public Yahtzee() {
		for (int i = 0 ; i < NBRE_DES_MAX_A_LANCER ; i++) {
			resultats[i] = (int) (Math.random() * NBRE_FACES_UN_DE) + 1;
			aLancer[i] = -1;
		}
	}
	
	/**
	 * le constructeur pour le Yahtzee avec en paramètre
	 * le nombre de dés à relancer
	 */
	public Yahtzee(int desALancer) {
		for (int i = 0 ; i < desALancer ; i++) {
			resultats[i] = (int) (Math.random() * NBRE_FACES_UN_DE) +1;
		}
	}
	
	public void conserver(int[] resultats) {
		
		/* au deuxième tour de jeu et après, */
		/* on demande au joueur s'il veut relancer un ou plusieurs dés */
		System.out.println("voulez-vous relancer des dés ?"
						 + "\n1 = oui , 0 = non");
		
		/* déclaration d'un objet Scanner pour */ 
		/* déterminer si le joueur rejoue */
		Scanner saisie = new Scanner(System.in);
		
		/* déclaration d'un booléen qui sera */
		/* le résultat du "hasNextInt" */
		boolean nombreEntierChoix;
		
		/* nombre saisi à analyser */
		int chiffre;

		/* l'action est recommencée tant que */
		/* le joueur n'aura pas saisi un entier entre 0 et 1 */
		do {
			
			/* vérification du type de variable */
			/* (doit être un nombre entier) */
			nombreEntierChoix = saisie.hasNextInt();

			/* si le nombre saisi n'est pas entier, chiffre */
			/* prend la valeur 0 */

			chiffre = nombreEntierChoix ? saisie.nextInt() : 0;

			/* si le joueur n'a pas saisi d'entier entre 0 et 1 */
			/* alors on lui dit de recommencer sa saisie */
			if (!nombreEntierChoix) {
				System.out.println("veuillez saisir un nombre entier");
			}

			if (chiffre < 0 || chiffre > 1) {
				System.out.println("votre nombre entier doit être"
						+ " compris entre 0 et 1 inclus"
						+ "\nveuillez entrer un nouveau nombre");
			}
			
			/* on vide le tampon !! */
			saisie.nextLine();

		} while(!nombreEntierChoix || chiffre < 0 || chiffre > 1);

		/* si le joueur saisit 1 alors on lui propose */
		/* de conserver 1 à 4 dés */
		if (chiffre == 1) {
			/* remise à zéro du nombre de dés conservés */
			/* et de la variable booléenne correspondante */
			deAConserver = true;
			desALancer = 0;
			
			/* demande à l'utilisateur le nombre de dés qu'il conserve */
			System.out.println("quels dés voulez-vous conserver ?"
							 + "\nentrer le numéro de chaque case"
							 + "\nsaisir -1 si vous ne voulez pas conserver "
							 + "d'autres dés ");
			
			/* tant qu'il y a des dés à conserver on propose au joueur */
			/* d'en conserver */
			while (deAConserver && desALancer <= 5) {
			
				/* boucle de vérification du chiffre entré */
				do {

					/* vérification du type de variable */
					/* (doit être un nombre entier) */
					nombreEntierChoix = saisie.hasNextInt();

					/* si le nombre saisi n'est pas entier, chiffre */
					/* prend la valeur 6 */

					chiffre = nombreEntierChoix ? saisie.nextInt() : 6;

					/* si le joueur n'a pas saisi d'entier entre -1 et 5 */
					/* alors on lui dit de recommencer sa saisie */
					if (!nombreEntierChoix) {
						System.out.println("veuillez saisir un nombre entier");
					}

					if (chiffre < -1 || chiffre > 5) {
						System.out.println("votre nombre entier doit être"
								+ " compris entre 0 et 5 inclus");
					}

				} while(!nombreEntierChoix || chiffre < -1 || chiffre > 5);
				
				/* les dés à conserver son systématiquement placés */
				/* le plus à droite du tableau des résultats */
				if ((chiffre != 0) && (aLancer[chiffre] != 0)) {
					aLancer[chiffre] = 0;
					desALancer++;
					System.out.println("vous conservez le " + chiffre + "e dé");
				}
				
				/* le joueur peut aussi déselectionner un dé et en est averti */
				if ((chiffre != 0) && (aLancer[chiffre] == 0)) {
					aLancer[chiffre] = resultats[chiffre];
					desALancer--;
					System.out.println("vous ne conservez pas le " + chiffre + "e dé");
				}
				
				/* la boucle de conservation des dés s'arrête */
				/* si le joueur décide de ne pas conserver d'autre dé */
				if (chiffre == -1) {
					deAConserver = false;
					System.out.println("vous vous apprêtez à relancer "
							+ "les dés que vous ne conservez pas ;"
							+ "confirmez-vous ce choix ? 1 = oui ; 0 = non");
					do {

						/* vérification du type de variable */
						/* (doit être un nombre entier) */
						nombreEntierChoix = saisie.hasNextInt();

						/* si le nombre saisi n'est pas entier, chiffre */
						/* prend la valeur 0 */

						chiffre = nombreEntierChoix ? saisie.nextInt() : 0;

						/* si le joueur n'a pas saisi d'entier entre 0 et 1 */
						/* alors on lui dit de recommencer sa saisie */
						if (!nombreEntierChoix) {
							System.out.println("veuillez saisir un nombre entier");
						}

						if (chiffre < 0 || chiffre > 1) {
							System.out.println("votre nombre entier doit être"
									+ " compris entre 0 et 1 inclus"
									+ "\nveuillez entrer un nouveau nombre");
						}

						/* on vide le tampon !! */
						saisie.nextLine();

					} while(!nombreEntierChoix || chiffre < 0 || chiffre > 1);

					/* si le joueur saisit 1 alors le nombre de dés */
					/* correspondant est relancé */
					if (chiffre == 1) {
						System.out.println("vous relancez " + desALancer + " dés");
						Yahtzee lancer2 = new Yahtzee(desALancer);
					} else {
						conserver(resultats);
					}
				}
			}
		}
	}
	
	/**
	 * permet à l'utilisateur de choisir ses dés à conserver
	 * 
	 */
	public void choixUtilisateur(int desALancer) {

		/* variable booléenne pour la conservation des dés */
		boolean deAConserver = true;
		
		/* boucle permettant de relancer les dés jusqu'à trois fois */
		for (nombreLancers = 0 ; nombreLancers < NBRE_LANCERS_MAX && deAConserver ; nombreLancers++) {
			if (nombreLancers == 0) {

				/* au premier tour de jeu, le joueur lance les 5 dés */
				Yahtzee lancer1 = new Yahtzee();

				/* affichage du résultat du lancer */
				System.out.println(Arrays.toString(resultats));
				
				/* la boucle de conservation commence */
				conserver(resultats);
			}
		}
	}

	/**
	 * permet de lancer une partie de Yahtzee
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		Yahtzee defaut = new Yahtzee();
		Yahtzee lancer2;
		defaut.choixUtilisateur(NBRE_FACES_UN_DE);
	}
}
