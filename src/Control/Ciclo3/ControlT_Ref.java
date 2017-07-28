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
public class ControlT_Ref {
    
    double Te, erro, DT, it, Ps2, Burbuja, Tref;
    
    public ControlT_Ref(double P, Session session){
        Te = 300;
        erro = 1;
        DT = 80;
        it = 0;
        while(erro>0.001){
            it++;
            if(it>10000){
                //ksakfdsak*ksadksda;
            }
            
            ControlPdeVapor pdevapor = new ControlPdeVapor(Te,session);    
            Ps2 = pdevapor.getPsi();
            Burbuja = Ps2-P;
            erro = Math.abs((Ps2-P)/Ps2);
            if(erro>0.001 && Burbuja>0){
                Te = Te-DT;
                DT = DT/2;
                if(DT<0.005){
                   DT = 0.0047953543;
                }
            }else{
                if(erro>0.001 && Burbuja<0){
                    Te = Te+DT;
                    DT = DT/2;
                    if(DT<0.005){
                        DT=0.00393845211111;
                    }
                }
            }
        }
        Tref = Te;
    }   
}

