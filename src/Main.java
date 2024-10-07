public class Main {
    public static void main(String[] args) {
//        Vecteur v1 = new Vecteur(new double[]{2, 0, 1, 4});
//        Vecteur v2 = new Vecteur(new double[]{0,2,0,2});

//        Vecteur v1 = new Vecteur(new double[]{1, 1, 0});
//        Vecteur v2 = new Vecteur(new double[]{-4, -4, 0});

        Vecteur v1 = new Vecteur(new double[]{0, 1});
        Vecteur v2 = new Vecteur(new double[]{0, 0});

        System.out.println(v1.estColineaire(v2));
    }
}