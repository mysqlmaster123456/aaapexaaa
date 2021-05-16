package pexeso;

public class Pexeso {

    public static void main(String[] args) {
        Hra hra = new Hra();
        try {
            hra.init(4);
        } catch (Exception ex) {
            System.out.println("Velikost herního pole není sudá");
        }
        hra.vypis_pole();
        
        new HerniOkno(hra).setVisible(true);
    }
    
}
