
abstract class Employe {
    private String matricule;
    private String nom;
    private String prenom;
    private int age;
    private String date;
    public final int salairebase = 110000;
  
    public Employe(String matricule, String nom, String prenom, int age, String date) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom= prenom
                ;
        this.age = age;
        this.date = date;
    }
 
    public abstract double calculerSalaire();
 
    public String getTitre()
        {
            return "L'employé " ;
        }
 
    public String getNom() {
        return getTitre() + matricule +" " +  nom + " " + prenom;
    }
}
// La classe Commercial (regroupe Vendeur et Représentant)//
abstract class Commercial extends Employe {
    private double chiffreAffaire;
 
    public Commercial(String matricule, String nom, String prenom, int age, String date,
               double chiffreAffaire) {
        super(matricule, nom, prenom, age, date);
        this.chiffreAffaire = chiffreAffaire;
    }
 
    public double getChiffreAffaire()
        {
            return chiffreAffaire;
        }
 
}
 
//La classe Vendeur//
class Vendeur extends Commercial {
    private final static double POURCENT_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
 
    public Vendeur(String matricule, String nom, String prenom, int age, String date,
            double chiffreAffaire) {
        super(matricule, nom, prenom, age, date, chiffreAffaire);
    }
 
    public double calculerSalaire() {
        return (POURCENT_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR +salairebase;
    }
 
    public String getTitre()
        {
            return "Le vendeur ";
        }
 
}
 
//La classe Représentant//
class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 200;
 
    public Representant(String matricule, String nom, String prenom, int age, String date, double chiffreAffaire) {
        super(matricule, nom, prenom, age, date, chiffreAffaire);
    }
 
    public double calculerSalaire() {
        return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT + +salairebase;
    }
 
    public String getTitre()
        {
            return "Le représentant ";
        }
}
//La classe Technicien (Production)//
class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    private int unites;
 
    public Technicien(String matricule, String nom, String prenom, int age, String date, int unites) {
        super(matricule, nom, prenom, age, date);
        this.unites = unites;
    }
 
    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }
 
    public String getTitre()
        {
            return "Le technicien ";
        }
}
 
//La classe Manutentionnaire//
class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private int heures;
 
    public Manutentionnaire(String matricule, String prenom, String nom, int age, String date,
                     int heures) {
        super(matricule, prenom, nom, age, date);
        this.heures = heures;
    }
 
    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures;
    }
 
    public String getTitre()
        {
            return "Le manut. " ;
        }
}
 
//L'interface d'employés à risque//
interface ARisque {
    int PRIME = 25000;
}
 
//Une première sous-classe d'employé à risque//
class TechnARisque extends Technicien implements ARisque {
 
    public TechnARisque(String matricule, String prenom, String nom, int age, String date, int unites) {
        super(matricule, prenom, nom, age, date, unites);
    }
 
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
 
//Une autre sous-classe d'employé à risque//
class ManutARisque extends Manutentionnaire implements ARisque {
 
    public ManutARisque(String matricule, String prenom, String nom, int age, String date, int heures) {
        super(matricule, prenom, nom, age, date, heures);
    }
 
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
 
//La classe Personnel//
class Personnel {
    private Employe[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 200;
 
    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nbreEmploye = 0;
    }
 
    public void ajouterEmploye(Employe e) {
        ++nbreEmploye;
        if (nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
        }
    }
 
    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nbreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nbreEmploye;
    }
 
    public void afficherSalaires() {
        for (int i = 0; i < nbreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}
 
// ======================================================================
 
class Salary {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur( "de matricule 12D", "Djoro", "boutou", 43, "01 fevrier 1989", 115200.0));
        p.ajouterEmploye(new Representant( "de matricule 099A", "Djibrilla", "seyni", 33, "25 janvier 1999", 40000.0));
        p.ajouterEmploye(new Technicien( "de matricule 19AFS", "sali","macanacien", 28, "19 juin 1994", 110000));
        p.ajouterEmploye(new Manutentionnaire( "de matricule 15AF", "pechou", "karge", 32, "31 aout 1998", 450000));
        p.ajouterEmploye(new TechnARisque( "de matricule 00c", "Fait", "tout", 29, "10 septembre 2000", 330000));
        p.ajouterEmploye(new ManutARisque( "de matricule 0O1g", "oulia", "bosseur", 30, "23 octobre 2001", 455600));
 
        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");
 
    }
 
}