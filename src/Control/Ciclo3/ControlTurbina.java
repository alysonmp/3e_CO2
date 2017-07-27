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
public class ControlTurbina {
   
    private double P4, T2, S2, H2, P2, H1, S1, H2s;
    
    public ControlTurbina(double Teff, double P1, double T1, double Pconop, double Pref, double Tref, Session session){
        ControlH_Sistema h_sistema = new ControlH_Sistema(T1, P1, Pref, Tref, session);
        ControlS_Sistema s_sistema = new ControlS_Sistema(T1, P1, Pref, Tref, session);
        
        H1 = h_sistema.getHV();
        S1 = s_sistema.getSL();
        
        P2 = Pconop;
        
        ControlIsoentropiaTurbina controlIsoTurbina = new ControlIsoentropiaTurbina(Teff, P2, Pref, Tref, S1, H1, T1, session);
        
        T2 = controlIsoTurbina.getT2();
        S2 = controlIsoTurbina.getS2();
        H2 = controlIsoTurbina.getH2();
        H2s = controlIsoTurbina.getH2s();
    } 

    public double getP4() {
        return P4;
    }

    public void setP4(double P4) {
        this.P4 = P4;
    }

    public double getT2() {
        return T2;
    }

    public void setT2(double T2) {
        this.T2 = T2;
    }

    public double getS2() {
        return S2;
    }

    public void setS2(double S2) {
        this.S2 = S2;
    }

    public double getH2() {
        return H2;
    }

    public void setH2(double H2) {
        this.H2 = H2;
    }

    public double getP2() {
        return P2;
    }

    public void setP2(double P2) {
        this.P2 = P2;
    }

    public double getH1() {
        return H1;
    }

    public void setH1(double H1) {
        this.H1 = H1;
    }

    public double getS1() {
        return S1;
    }

    public void setS1(double S1) {
        this.S1 = S1;
    }

    public double getH2s() {
        return H2s;
    }

    public void setH2s(double H2s) {
        this.H2s = H2s;
    }
    
    
}
