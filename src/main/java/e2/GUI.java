package e2;

import javax.swing.*;
import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>(); /* dichiara mappa di buttons
                                                                                aventi JButton come Key
                                                                                e coordinate come Value*/
    private final Logics logics;                                //dichiara interfaccia Logics
    private final static int SIZE = 5;                          //setta a 5 la dimensione della griglia
    
    public GUI() {
        this.logics = new LogicsImpl(SIZE);                     //crea istanza dell'interfaccia Logics
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*SIZE, 100*SIZE);           //setta la dimensione del frame a 500x500
        
        JPanel panel = new JPanel(new GridLayout(SIZE,SIZE));   //crea pannello con griglia 5x5
        this.getContentPane().add(BorderLayout.CENTER,panel);   //aggiunge la griglia al pannello
        
        ActionListener al = (e)->{                              //genera un actionListener per ogni oggetto 'e'
            final JButton bt = (JButton)e.getSource();          //restituisce Key, oggetto effettivo (button) che ha attivato action
            final Pair<Integer,Integer> pos = buttons.get(bt);  //restituisce Value dell'oggetto (posizione)
            if (logics.hit(pos.getX(),pos.getY())) {            //chiama funzione hit() di Logics,
            	System.exit(0);
            } else {
                draw();            	
            }
        };
                
        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                final JButton jb = new JButton(" ");    //crea una button per ogni casella
                jb.addActionListener(al);                    //aggiunge actionListener ad ogni button
                this.buttons.put(jb,new Pair<>(i,j));        //setta la posizione di ogni button
                panel.add(jb);                               //aggiunge ogni button al pannello
            }
        }
        this.draw();            //stampa sulle caselle * e K
        this.setVisible(true);  //rende visibile il frame
    }
    
    private void draw() {
        //Entry è un'interfaccia annidata di Map
    	for (Entry<JButton,Pair<Integer,Integer>> entry: this.buttons.entrySet()) { /*itera le coppie <Jbutton,Pair>,
                                                                                      e ritorna tali coppie  */

            /*se un button deve avere Pawn, setta la stringa * da stampare sul button
              se un button deve avere Knight, setta la stringa K da stampare  sul button
              altrimenti nulla
             */
    		String str = logics.hasPawn(entry.getValue().getX(), entry.getValue().getY()) ? "*" :
    					 logics.hasKnight(entry.getValue().getX(), entry.getValue().getY()) ? "K" : " ";
    		entry.getKey().setText(str);    //stampa la stringa sul button
    	}
    }
    
}
