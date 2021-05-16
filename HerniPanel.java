package pexeso;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;


public class HerniPanel extends JPanel {
    
    Hra hra;
    int vel;
    
    Button pole[];
    
    public HerniPanel() {
        
    }
    
   
    public void init(Hra hra, int vel) {
        this.hra = hra;
        this.vel = vel;
        this.pole = new Button[vel];
        int pos;
        int pos_m;
        for ( int j = 0; j < hra.getSize(); j++ ) {
            for ( int i = 0; i < hra.getSize(); i++ ) {
                pos = i * hra.getSize() + j;
                pos_m = j * hra.getSize() + i;
                Button btn = new Button( String.valueOf( hra.getPole()[pos] ) );
               
                //btn.setLabel( String.valueOf( hra.getPole()[pos_m] ) );
                btn.setActionCommand( String.valueOf( hra.getPole()[pos_m] ) );
                btn.setLabel("");
                btn.setSize(vel, vel);
                btn.setLocation(i *vel, j * vel);
                btn.setName( String.valueOf(j * hra.getSize() + i) );
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                         btn.setLabel( btn.getActionCommand() );
                        boolean win = hra.klik( Integer.valueOf(btn.getName()) );
                        System.out.println(win);
                        if (win) {
                            pole[ Integer.valueOf(btn.getName()) ].setBackground(Color.red);
                            pole[ Integer.valueOf(btn.getName()) ].setEnabled(false);
                            pole[ hra.karta_prvni_pozice ].setBackground(Color.red);
                            pole[ hra.karta_prvni_pozice ].setEnabled(false);
                        } else if ( win == false && hra.druha_karta == true ) {
                            try {
                                Thread.sleep(1000);
                                btn.setLabel( "" );
                                pole[ hra.karta_prvni_pozice ].setLabel( "" );
                            } catch (InterruptedException ex) {
                                
                            }
                            
                        }
                    }
                });
                this.pole[pos_m] = btn;
                this.add( btn );
            }
        }
    }
}
