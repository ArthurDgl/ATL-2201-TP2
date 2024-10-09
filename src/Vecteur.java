import java.util.Arrays;

public class Vecteur {
    public static double EPSILON = 0.00000000001;
    public static boolean sontEnvironEgales(final double premier, final double deuxieme) {
        return Math.abs(premier - deuxieme) < EPSILON;
    }

    private double[] composantes;
    private int dimension;

    /**
     * Constructeur permettant de créer une instance de la classe Vecteur grâce à un tableau de valeurs.
     * @param composantes Le tableau de valeurs qui deviendra les composantes du vecteur
     */
    public Vecteur(final double[] composantes) throws Exception {
        this.dimension = composantes.length;
        if (dimension < 2) throw new Exception("La dimension doit être supérieure ou égale à 2");
        this.composantes = Arrays.copyOf(composantes, composantes.length);
    }

    /**
     * Constructeur permettant de créer une instance de la classe Vecteur grâce à une dimension, toutes les composantes seront initialisées à 0.
     * @param dimension L'entier représentant la dimension du vecteur
     */
    public Vecteur(final int dimension) throws Exception {
        this.dimension = dimension;
        if (dimension < 2) throw new Exception("La dimension doit être supérieure ou égale à 2, dimension utilisée : " + dimension);
        this.composantes = new double[dimension];
        java.util.Arrays.fill(this.composantes, 0);
    }

    /**
     * Constructeur permettant d'effectuer la copie d'un vecteur en copiant ses composantes.
     * @param autre Le vecteur à copier
     */
    public Vecteur(final Vecteur autre) throws Exception {
        this(autre.composantes);
    }

    /**
     * Accesseur de la dimension du vecteur.
     * @return L'entier représentant la dimension du vecteur
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Accesseur pour une coordonnée (ou composante) du vecteur à un certain indice.
     * @param indice L'indice de la coordonnée en question
     * @return La coordonnée à l'indice 'indice'
     */
    public double getCoordonnee(final int indice) {
        return this.composantes[indice];
    }

    /**
     * Modificateur pour une coordonnée (ou composante) du vecteur à un certain indice.
     * @param indice L'indice de la coordonnée en question
     * @param coordonnee La nouvelle valeur de la coordonnée en question
     */
    public void setCoordonnee(final int indice, final double coordonnee) {
        this.composantes[indice] = coordonnee;
    }

    /**
     * Accesseur de la 1ère coordonnée du vecteur (l'abscisse X)
     * @return La 1ère coordonnée du vecteur (l'abscisse X)
     */
    public double getX() {
        return this.composantes[0];
    }

    /**
     * Accesseur de la 2ème coordonnée du vecteur (l'ordonnée Y)
     * @return La 2ème coordonnée du vecteur (l'ordonnée Y)
     */
    public double getY() {
        return this.composantes[1];
    }

    /**
     * Procédure permettant de multiplier toutes les composantes du vecteur par un scalaire.
     * @param scalaire Le facteur scalaire par lequel on multiplie les composantes du vecteur.
     */
    public void multiplicationScalaire(double scalaire) {
        for (int i = 0; i < this.dimension; i++) {
            this.composantes[i] *= scalaire;
        }
    }

    /**
     * Procédure permettant de diviser toutes les composantes du vecteur par un scalaire.
     * @param diviseur Le diviseur scalaire par lequel on divise les composantes du vecteur.
     */
    public void divisionScalaire(double diviseur) {
        this.multiplicationScalaire(1/diviseur);
    }

    /**
     * Méthode permettant de calculer la norme du vecteur au carré.
     * @return La norme au carré
     */
    public double normeCarree() {
        double somme = 0;
        for (int i = 0; i < this.dimension; i++) {
            somme += this.composantes[i]*this.composantes[i];
        }
        return somme;
    }

    /**
     * Méthode permettant de calculer la norme du vecteur.
     * @return La norme
     */
    public double norme() {
        return Math.sqrt(this.normeCarree());
    }

    /**
     * Procédure permettant de normaliser le vecteur, càd de le rendre unitaire (|v| = 1).
     * Cela se fait par une division de chaque composante par la norme du vecteur.
     */
    public void normaliser() {
        double norme = this.norme();
        if (norme == 0) return;
        this.divisionScalaire(norme);
    }

    /**
     * Procédure permettant d'ajouter un vecteur à l'instance actuelle par somme vectorielle.
     * @param autre Le vecteur à ajouter
     */
    public void sommeVectorielle(final Vecteur autre) throws Exception {
        if (this.dimension != autre.dimension) throw new Exception("Les deux vecteurs doivent avoir la même dimension");
        for (int i = 0; i < this.dimension; i++) {
            this.composantes[i] += autre.composantes[i];
        }
    }

    /**
     * Méthode permettant de calculer le produit scalaire entre l'instance actuelle et un autre vecteur.
     * @param autre L'autre vecteur
     * @return Le produit scalaire
     */
    public double produitScalaire(final Vecteur autre) throws Exception {
        if (this.dimension != autre.dimension) throw new Exception("Les deux vecteurs doivent avoir la même dimension");
        double somme = 0;
        for (int i = 0; i < this.dimension; i++) {
            somme += this.composantes[i] * autre.composantes[i];
        }
        return somme;
    }

    /**
     * Méthode permettant de calculer le produit vectoriel entre l'instance actuelle et un autre vecteur.
     * Ne marche seulement pour des vecteurs de dimension 3 (ex: (1, 1, 0) ^ (0, 2, -1))
     * @param autre L'autre vecteur
     * @return Le produit vectoriel (un vecteur de dimension 3)
     */
    public Vecteur produitVectoriel3d(final Vecteur autre) throws Exception {
        if (this.dimension != 3 || autre.dimension != 3) return null;
        
        double[] produit = {this.composantes[1]*autre.composantes[2]-autre.composantes[1]*this.composantes[2],this.composantes[2]*autre.composantes[0]-this.composantes[0]*autre.composantes[2],this.composantes[0]*autre.composantes[1]-this.composantes[1]*autre.composantes[0]};
        return new Vecteur(produit);
    }

    /**
     * Méthode permettant de savoir si un autre vecteur est environ égal à l'instance actuelle.
     * @param autre L'autre vecteur
     * @return La valeur booléenne du test d'égalité
     */
    public boolean estEgal(final Vecteur autre) throws Exception {
        if (this.dimension != autre.dimension) throw new Exception("Les deux vecteurs doivent avoir la même dimension");
        for (int i = 0; i < this.dimension; i++) {
            if (!sontEnvironEgales(this.composantes[i], autre.composantes[i])) return false;
        }
        return true;
    }

    /**
     * Méthode permettant de savoir si le vecteur est nul.
     * @return La valeur booléenne du test de nullité
     */
    public boolean estNul() throws Exception {
        return this.estEgal(new Vecteur(this.dimension));
    }

    /**
     * Méthode permettant de savoir si un autre vecteur est orthogonal à l'instance actuelle.
     * @param autre L'autre vecteur
     * @return La valeur booléenne du test d'orthogonalité
     */
    public boolean estOrthogonal(final Vecteur autre) throws Exception {
        return Vecteur.sontEnvironEgales(0.0, this.produitScalaire(autre));
    }

    /**
     * PRIVATE
     * Méthode permettant de trouver un ratio entre deux composantes de même indice de chaque vecteur.
     * @param autre L'autre vecteur
     * @return Le ratio trouvé
     */
    private double findRatio(final Vecteur autre) {
        for (int i = 0; i < this.dimension; i++) {
            if (autre.composantes[i] != 0) return this.composantes[i] / autre.composantes[i];
        }
        return 0;
    }

    /**
     * Méthode permettand de savoir si un autre vecteur est colinéaire à l'instance actuelle.
     * @param autre L'autre vecteur
     * @return La valeur booléenne du test de colinéarité
     */
    public boolean estColineaire(final Vecteur autre) throws Exception {
        return Vecteur.sontEnvironEgales(Math.abs(this.produitScalaire(autre)), this.norme()*autre.norme());
    }
//    VERSION EN CALCULANT LES RATIOS ENTRE LES COMPOSANTES
//    public boolean estColineaire(final Vecteur autre) {
//        if (this.estNul() || autre.estNul()) return true;
//
//        double ratio = findRatio(autre);
//        Vecteur toCheck = new Vecteur(autre);
//        toCheck.multiplicationScalaire(ratio);
//        return this.estEgal(toCheck);
//    }



    /**
     * Méthode permettant de savoir si l'instance actuelle est coplanaire avec deux autres vecteurs.
     * @param autre1 Le premier autre vecteur
     * @param autre2 Le deuxième autre vecteur
     * @return La valeur booléenne du test de coplanarité
     */
    public boolean estCoplanaire3d(final Vecteur autre1, final Vecteur autre2) throws Exception {
        Vecteur normal = autre1.produitVectoriel3d(autre2);
        return this.estOrthogonal(normal);
    }
}
