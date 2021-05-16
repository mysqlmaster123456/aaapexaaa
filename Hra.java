package pexeso;

import java.util.ArrayList;
import java.util.Collections;

public class Hra {
    
    private int pole[];
    private int odkryto[];
    int ukazatel_odkryto = 0;
    int size;
    // false = hrac 0
    // true = hrac 1
    boolean hrac = false;
    boolean druha_karta = true;
    
    int score_hrac1 = 0;
    int score_hrac2 = 0;
    
    int karta_prvni_pozice = 0;
    int karta_druha_pozice = 0;
    
    public Hra() {
        
    }
    
    public void init(int size) throws Exception {
        if (size % 2 != 0) {
            throw new Exception("Chyba, velikost pole musí být sudá");
        }
        this.size = size;
        int dvojice = size * (size/2);
        this.pole = new int[size*size];
        this.odkryto = new int[dvojice];
        
        for ( int i = 0; i < size*size; i++ ) {
            this.pole[i] = i % dvojice;
        }  
        
        ArrayList<Integer> shuffle = new ArrayList<>();
        for (int i = 0; i < this.pole.length; i++) {
            shuffle.add( this.pole[i] );
        }
        Collections.shuffle(shuffle);
        
        for (int i = 0; i < this.pole.length; i++) {
            this.pole[i] = shuffle.get( i );
        }
    }
    
    // true pokud jsou dvojice
    private boolean porovnej_karty(int karta1, int karta2) {
        if ( this.pole[karta1] == this.pole[karta2] ) {
            return true;
        }
        return false;
    }

    public boolean isDruha_karta() {
        return druha_karta;
    }

    public int getKarta_prvni_pozice() {
        return karta_prvni_pozice;
    }

    public int[] getPole() {
        return pole;
    }

    public int[] getOdkryto() {
        return odkryto;
    }

    public int getSize() {
        return size;
    }
    
    
    
    
    
    public boolean klik(int i) {
        //System.out.print("KLIK: ");
        //System.out.println(i);
        // První karta
        if ( this.druha_karta == true ) {
            this.karta_prvni_pozice = i;
            this.druha_karta = false;
        // Druhá karta
        } else {
            if ( i == this.karta_prvni_pozice ) {
                return false;
            }
            this.karta_druha_pozice = i;
            this.druha_karta = true;
            // Pokud jsou karty dvojice
            if ( this.porovnej_karty( this.karta_prvni_pozice, this.karta_druha_pozice ) ) {
                this.odkryto[this.ukazatel_odkryto++] = this.karta_prvni_pozice;
                this.odkryto[this.ukazatel_odkryto++] = this.karta_druha_pozice;
                if (this.hrac == false) {
                    this.score_hrac1 += 1;
                    System.out.println("Hraje hráč2");
                } else {
                    this.score_hrac2 += 1;
                    System.out.println("Hraje hráč1");
                }
                System.out.println("Uhodnuto");
                System.out.println(this.score_hrac1);
                System.out.println(this.score_hrac2);
                this.hrac = !this.hrac;
                
                return true;
            }
            if (this.hrac == false) {
                    System.out.println("Hraje hráč2");;
                } else {
                    System.out.println("Hraje hráč1");
                }
            this.hrac = !this.hrac;
        }
        
       return false;
    }
    
    
    
    public void vypis_pole() {
        for ( int i = 0; i < this.pole.length; i++ ) {
            System.out.print( this.pole[i] );
            System.out.print(" | ");
            if ( i % this.size == this.size-1 ) {
                System.out.println("");
            }
        }
    }

}
