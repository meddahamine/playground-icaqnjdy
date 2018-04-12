public class Main {

static int [][]grille = {
    {0,0,0,0,3,0,0,6,2},
    {0,0,0,0,7,2,0,0,1},
    {2,0,0,6,0,0,8,0,0},
    
    {1,0,9,4,0,6,0,8,7},
    {0,0,4,0,0,0,2,0,0},
    {5,8,0,1,0,7,6,0,4},
    
    {0,0,6,0,0,1,0,0,3},
    {4,0,0,3,8,0,0,0,0},
    {7,3,0,0,6,0,0,0,0}
  };
  public static boolean estDansLaColonne(int valeur,int colonne) {
    for (int ligne=0; ligne<9; ligne++) {
      if (grille[ligne][colonne]==valeur) return true;
    }
    return false;
  }
  public static boolean estDansLaLigne(int valeur,int ligne) {
    for (int colonne=0; colonne<9; colonne++) {
      if (grille[ligne][colonne]==valeur) return true;
    }
    return false;
  }
  public static boolean estDansLeCarre(int valeur,int ligne,int colonne) {
    int pointGauche = 3*(colonne/3);
    int pointHaut = 3*(ligne/3);
    for (int c=pointGauche; c<pointGauche+3; c++) {
      for (int l=pointHaut; l<pointHaut+3; l++) {
        if (grille[l][c]==valeur) return true;
      }
    }
    return false;
  }
  public static int compteur;
  public static boolean estValeurPossible(int valeur,int ligne,int colonne) {
    return !estDansLaColonne(valeur,colonne)
      && !estDansLaLigne(valeur,ligne)
      && !estDansLeCarre(valeur,ligne,colonne);
  }
  public static void affiche() {
    for (int l=0; l<9; l++) {
      for (int c=0; c<9; c++) {
        if (grille[l][c]!=0) System.out.print(grille[l][c]);
        else System.out.print(' ');
      }
      System.out.println();
    }
      System.out.println();
  }
  public static boolean trouveSolution(int ligne,int colonne) {
    compteur++;
    // calcul de la position suivante
    int ligneSuivante;
    int colonneSuivante;
    if (colonne==8) { ligneSuivante = ligne+1; colonneSuivante=0; }
    else            { ligneSuivante = ligne;   colonneSuivante = colonne+1; }
    
    // Est-ce que j'ai parcouru toutes les cases du tableau ?
    if (ligne==9) { affiche(); return true; }
    
    if (grille[ligne][colonne]!=0) { // case déjà remplie ==> case suivante
      return trouveSolution(ligneSuivante,colonneSuivante);
    } else { // case non remplie ==> je dois essayer les différentes valeurs
      for (int valeur=1; valeur<10; valeur++) {
        // si la je ne peux pas poser la valeur, je passe à la suivante
        if (!estValeurPossible(valeur,ligne,colonne)) continue;
        grille[ligne][colonne] = valeur; // je pose une valeur (hypothèse)
        boolean correct = trouveSolution(ligneSuivante,colonneSuivante);
        if (correct) return true; // c'est bon, la valeur était correcte
      }
      grille[ligne][colonne] = 0; // c'était une case "vide", on la remet
      return false; // Je n'ai pas trouvé de valeur correcte
    }
  }

public static void main(String[] args) {
// }
String message = "Hello QLF!";
System.out.println(message);
affiche();
compteur = 0;
System.out.println(trouveSolution(0,0)+" nombre d'appels="+compteur);
//{ autofold
}

}
