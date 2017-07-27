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
public class ControlS_Sistema {
    
    double SDrefL, SDrefV, SDL, SDV, SL, SV, Sig;
    
    public ControlS_Sistema(double T, double P, double Pref, double Tref, Session session){
        
        ControlS_Dep sdep = new ControlS_Dep(Tref, Pref);
        SDrefL = sdep.getSl();
        SDrefV = sdep.getSv();
        
        ControlS_Ideal_Gas ideal_gas = new ControlS_Ideal_Gas(T, Tref, P, Pref, session);
        Sig = ideal_gas.getSig();
        
        sdep = new ControlS_Dep(T, P);
        
        SDL = sdep.getSl();
        SDV = sdep.getSv();
        
        SL=-SDrefL+Sig+SDL;
        SV=-SDrefL+Sig+SDV;
    }
}