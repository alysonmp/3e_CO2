/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class ControlMassa {
 
    double Tf2, m;
    
    public ControlMassa(double H4, double H1, double H6, double p1, double Pref, double Tref, double T1, double T6, double DTT, double Tf, int compressor, Session session){
        
        Tf2 = T6 + DTT;
        ControlCalor calor = new ControlCalor(compressor, Tf, Tf2, session);
        double QTf = calor.getQfon1();
        
        m = QTf/(H1-H6);
        
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getTf2() {
        return Tf2;
    }

    public void setTf2(double Tf2) {
        this.Tf2 = Tf2;
    }
}