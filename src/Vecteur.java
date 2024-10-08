public class Vecteur {
    public static double EPSILON = 0.00000000001;
    public static boolean sontEnvironEgales(final double premier, final double deuxieme) {
        return Math.abs(premier - deuxieme) < EPSILON;
    }

    private double[] composantes;
    private int dimension;

    public Vecteur(final double[] composantes) {
        this.dimension = composantes.length;
        this.composantes = java.util.Arrays.copyOf(composantes, composantes.length);
    }

    public Vecteur(final int dimension) {
        this.dimension = dimension;
        this.composantes = new double[dimension];
        java.util.Arrays.fill(this.composantes, 0);
    }

    public Vecteur(final Vecteur autre) {
        this(autre.composantes);
    }

    public int getDimension() {
        return this.dimension;
    }

    public double getCoordonnee(final int indice) {
        return this.composantes[indice];
    }

    public void setCoordonnee(final int indice, final double coordonnee) {
        this.composantes[indice] = coordonnee;
    }

    public double getX() {
        return this.composantes[0];
    }

    public double getY() {
        return this.composantes[1];
    }

    public void multiplicationScalaire(double scalaire) {
        for (int i = 0; i < this.dimension; i++) {
            this.composantes[i] *= scalaire;
        }
    }

    public void divisionScalaire(double diviseur) {
        this.multiplicationScalaire(1/diviseur);
    }

    public double normeCarree() {
        double somme = 0;
        for (int i = 0; i < this.dimension; i++) {
            somme += this.composantes[i]*this.composantes[i];
        }
        return somme;
    }

    public double norme() {
        return Math.sqrt(this.normeCarree());
    }

    public void normaliser() {
        double norme = this.norme();
        if (norme == 0) return;
        this.divisionScalaire(norme);
    }

    public void sommeVectorielle(final Vecteur autre) {
        for (int i = 0; i < this.dimension; i++) {
            this.composantes[i] += autre.composantes[i];
        }
    }

    public double produitScalaire(final Vecteur autre) {
        double somme = 0;
        for (int i = 0; i < this.dimension; i++) {
            somme += this.composantes[i] * autre.composantes[i];
        }
        return somme;
    }
    
    public Vecteur produitVectoriel3d(final Vecteur autre) {
        if (this.dimension != 3 || autre.dimension != 3) return null;
        
        double[] produit = {this.composantes[1]*autre.composantes[2]-autre.composantes[1]*this.composantes[2],this.composantes[2]*autre.composantes[0]-this.composantes[0]*autre.composantes[2],this.composantes[0]*autre.composantes[1]-this.composantes[1]*autre.composantes[0]};
        return new Vecteur(produit);
    }

    public boolean estEgal(final Vecteur autre) {
        for (int i = 0; i < this.dimension; i++) {
            if (!sontEnvironEgales(this.composantes[i], autre.composantes[i])) return false;
        }
        return true;
    }

    public boolean estNul() {
        return this.estEgal(new Vecteur(this.dimension));
    }

    public boolean estOrthogonal(final Vecteur autre) {
        return Vecteur.sontEnvironEgales(0.0, this.produitScalaire(autre));
    }

    public double findRatio(final Vecteur autre) {
        for (int i = 0; i < this.dimension; i++) {
            if (autre.composantes[i] != 0) return this.composantes[i] / autre.composantes[i];
        }
        return 0;
    }

    public boolean estColineaire(final Vecteur autre) {
        if (this.estNul() || autre.estNul()) return true;

        double ratio = findRatio(autre);
        Vecteur toCheck = new Vecteur(autre);
        toCheck.multiplicationScalaire(ratio);
        return this.estEgal(toCheck);
    }

    public boolean estCoplanaire3d(final Vecteur autre1, final Vecteur autre2) {
        Vecteur normal = autre1.produitVectoriel3d(autre2);
        return this.estOrthogonal(normal);
    }
}
