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
        ControlH_Sistema hsistema = new ControlH_Sistema(T1, P1, Pref, Tref, session);
        ControlS_Sistema ssistema = new ControlS_Sistema(T1, P1, Pref, Tref, session);
        
        H1 = hsistema.getHv();
        S1 = ssistema.getSv();
        
        P2 = Pconop;
        
        ControlIsoentropiaTurbina controlIsoTurbina = new ControlIsoentropiaTurbina(Teff, P2, Pref, Tref, S1, H1, T1, session);
        
        T2 = controlIsoTurbina.getT4();
        S2 = controlIsoTurbina.getS4();
        H2 = controlIsoTurbina.getH4();
        H2s = controlIsoTurbina.getH2s;
    } 
}
