/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import Control.Ciclo3.ControlConstantes;
import org.hibernate.Session;

/**
 *
 * @author leonardo
 */
public class ControlBomba {
    private double S4, H4, S5, H5, T5;
    
    public ControlBomba(double Beff, double P1, double Pconop, double Tconop, double Pref, double Tref, Session session){
        double T4=Tconop;
        double P4=Pconop;
        double P5=P1;
        
        //[R, Pci, Tci, Zci, Tri, Pri, Fi, Omegabi, Omegaai, Omegaci, ai, bi, ci, Ai, Bi, Ci, Ni, Mi, Qi, alfai] = constantes(T4, P4);
        ControlConstantes constantes = new ControlConstantes(T4, P4);
        //[Zl, Zv, Z] =Zeta(Ai, Bi, Ci);
        ControlZeta zeta = new ControlZeta(constantes.getAi(),constantes.getBi(),constantes.getCi());
        //[HL4, HV4] = H_sistema(T4, P4, Pref, Tref);
        ControlH_Sistema h_sistema = new ControlH_Sistema(T4, P4, Pref, Tref,session);
        H4 = h_sistema.getHV();
        
        //[SL4, SV4] = S_sistema(T4, P4, Pref, Tref);
        ControlS_Sistema s_sistema = new ControlS_Sistema(T4, P4, Pref, Tref);
        S4 = s_sistema.getSv;
        
        double Test=T4; 
        double erro=1;
        double DT=10;
        double S,Burbuja;
        
        while(erro >= 0.0001){
            s_sistema = new ControlS_Sistema(Test, P5, Pref, Tref);
            S = s_sistema.getSv();
            erro = Math.abs((S4-S)/S4);
            Burbuja = S4-S;
            if(erro>0.0001 & Burbuja<0){
                Test=Test-DT;
                DT=DT/2;
                if(DT<0.005){
                    DT=0.004987569731;
                }
            }else if(erro>0.0001 & Burbuja>0){
                Test=Test+DT;
                DT=DT/2;
                if(DT<0.005){
                    DT=0.002933254;
                }
            }
        }
        
        double T5s=Test;
        //[HL5is, HV5is] = H_sistema(T5s, P5, Pref, Tref);
        h_sistema = new ControlH_Sistema(T5s, P5, Pref, Tref, session);
        double H5s= h_sistema.getHV();
        H5=((H5s-H4)/Beff)+H4;
        
        double H;
        Test=T5s;
        erro=1;
        DT=10;
        
        while(erro>0.0005){
            h_sistema = new ControlH_Sistema(Test, P5, Pref, Tref, session);
            H = h_sistema.getHV();
            erro = Math.abs((H5-H)/H5);
            Burbuja=H5-H;
            if(erro>0.0005 & Burbuja<0){
                Test=Test-DT;
                DT=DT/2;
                if(DT<0.0005){
                    DT=0.0005456321;
                }
            }else if(erro>0.0005 & Burbuja>0){
                    Test=Test+DT;
                    DT=DT/2;
                    if(DT<0.0005){
                        DT=0.0003975313;
                    }
            }
        }
        
        T5=Test;
        s_sistema = new ControlS_Sistema(T5, P5, Pref, Tref);
        S5=s_sistema.getSv();
    }
}