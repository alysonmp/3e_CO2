/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import org.hibernate.Session;

/**
 *
 * @author leonardo
 */
public class ControlRegenerador {

    private double S3, H3, S6, H6, T3, T6, IHR;
    
    public ControlRegenerador(double G, double H2, double  H5, double  S2, double  S5, double  P2, double  T2, double  P5, double  T5, double  P1, double  Pconop, double  Tconop, double  Pref, double  Tref, double  eff, Session session) {
        double P6=P1;
        double P3=Pconop;
        if(eff==0){
            H3=H2;
            T3=T2;
            S3=S2;
            H6=H5;
            T6=T5;
            S6=S5;
            IHR=0;
        }else{
            ControlH_Sistema h_sistema = new ControlH_Sistema(T2, P2, Pref, Tref,session);
            double PP=T5+5;

            if(T2>PP && G==1 && h_sistema.getHV()==H2){
                T3=T2-eff*(T2-T5);
                h_sistema = new ControlH_Sistema(T3, P3, Pref, Tref,session);
                H3=h_sistema.getHV();
                ControlS_Sistema s_sistema = new ControlS_Sistema(T3, P3, Pref, Tref,session);
                S3=s_sistema.getSV();
                H6=H2-H3+H5;
                double Test=T2;
                double erro=1;
                double DT=200;
                double it=0;
                double H,Burbuja;
                
                while(erro>0.0001){
                    it=it+1;
                    if(it > 10000){
                        //kkkkk=kkkkk*PPPPPP
                        return;
                    }
                    h_sistema = new ControlH_Sistema(Test, P6, Pref, Tref,session);
                    H=h_sistema.getHL();
                    erro = Math.abs((H6-H)/H6);
                    Burbuja = H6-H;
                    if(erro>0.0001 && Burbuja<0){
                        Test=Test-DT;
                        DT=DT/2;
                        if(DT<0.0005){
                            DT=0.00035397;
                        }
                    }else if(erro>0.0001 && Burbuja>0){
                        Test=Test+DT;
                        DT=DT/2;
                        if(DT<0.0005){
                            DT=0.000311139;
                        }
                    }
                }
                T6=Test;
                s_sistema = new ControlS_Sistema(T6, P6, Pref, Tref,session);
                S6=s_sistema.getSL();
                IHR=eff;
            }else{ 
                if(G==1){
                    //disp('Não é possivél fazer Regeneração')
                    //disp('T2 é menor o igual que T5')
                    //disp('')
                    //disp('')
                }
                H3=H2;
                T3=T2;
                S3=S2;
                H6=H5;
                T6=T5;
                S6=S5;
                IHR=0;
                //kkk*pppp*ttrfgd*iogffdgfjhj
            }
        }
        

        double DT11=T6+10;
        double DT12=T5+10;
        if(T2<=DT11 && IHR>0){ 
             H3=H2;
                T3=T2;
                S3=S2;
                H6=H5;
                T6=T5;
                S6=S5;
                IHR=0;
                //kkk*pppp*ttrfgd*iogffdgfjhj
        }
        if(IHR>0 && T3<=DT12){
            H3=H2;
            T3=T2;
            S3=S2;
            H6=H5;
            T6=T5;
            S6=S5;
            IHR=0;
            //kkk*pppp*ttrfgd*iogffdgfjhj
        }
        double DT13=T6-T5;
        if(DT13<=10){ 
            H3=H2;
            T3=T2;
            S3=S2;
            H6=H5;
            T6=T5;
            S6=S5;
            IHR=0;
            //kkk*pppp*ttrfgd*iogffdgfjhj
        }
        
    }
}
