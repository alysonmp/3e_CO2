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
public class ControlIsoentropiaTurbina {

    private double T2, S2, H2, H2s;
    
    private String mensagem = "";
    
    public ControlIsoentropiaTurbina(double Teff, double P2, double Pref, double Tref, double S1, double H1, double T1, Session session) {
    
        double Test = T1-20;
        
        // [SL2, SV2] = S_sistema(Test, P2, Pref, Tref);
        ControlS_Sistema s_sistema = new ControlS_Sistema(Test, P2, Pref, Tref,session); 
        
        if(s_sistema.getSV() >= S1){
            double erro=1;
            double DT=T1*0.1;
            while(erro >= 0.0001){
                s_sistema = new ControlS_Sistema(Test, P2, Pref, Tref,session);
                S2=s_sistema.getSV();
                erro = Math.abs((S2-S1)/S2);
                double Burbuja=S2-S1;
                if(erro>0.0001 && Burbuja<0){
                    Test=Test+DT;
                    DT=DT/2;
                    if(DT<0.005){
                        DT=0.004987569731;
                    }
                }else if(erro>0.0001 && Burbuja>0){
                    Test=Test-DT;
                    DT=DT/2;
                    if(DT<0.005){
                        DT=0.002933254;
                    }
                }
            }
          
            ControlH_Sistema h_sistema = new ControlH_Sistema(Test, P2, Pref, Tref,session);
            H2s=h_sistema.getHV();
            H2=(-(H1-H2s)*Teff)+H1;
            if(H2>H2s){
                erro=1;
                DT=5;
                double H,Burbuja;
                while(erro>0.0001){
                    h_sistema = new ControlH_Sistema(Test, P2, Pref, Tref,session);
                    H = h_sistema.getHV();
                    erro = Math.abs((H2-H)/H2);
                    Burbuja=H2-H;
                    if(erro>0.0001 && Burbuja<0){
                        Test=Test-DT;
                        DT=DT/2;
                        if(DT<0.005){
                            DT=0.004978953;
                        }
                    }else if(erro>0.0001 && Burbuja>0){
                        Test=Test+DT;
                        DT=DT/2;
                        if(DT<0.005){
                            DT=0.00333695;
                        }
                    }
                }
            }
            T2=Test;
            h_sistema = new ControlH_Sistema(T2, P2, Pref, Tref,session);
            s_sistema = new ControlS_Sistema(T2, P2, Pref, Tref,session);
            H2=h_sistema.getHV();
            S2=s_sistema.getSV();
        }else{
            double x=(S1-s_sistema.getSL())/(s_sistema.getSV()-s_sistema.getSL());
            ControlH_Sistema h_sistema = new ControlH_Sistema(Test, P2, Pref, Tref,session);
            H2s=(h_sistema.getHV()*x)+(h_sistema.getHL()*(1-x));
            H2=(-(H1-H2s)*Teff)+H1;
            double DT=120;
            double H,Burbuja;
            
            if(H2>h_sistema.getHV()){
                double erro=1;
                while(erro>0.0001){
                    h_sistema = new ControlH_Sistema(Test, P2, Pref, Tref,session);
                    H = h_sistema.getHV();
                    erro = Math.abs((H2-H)/H2);
                    Burbuja = H2-H;
                    if(erro>0.0001 && Burbuja<0){
                        Test=Test-DT;
                        DT=DT/2;
                        if(DT<0.005){
                            DT=0.005;
                        }
                    }else if(erro>0.0001 && Burbuja>0){
                        Test=Test+DT;
                        DT=DT/2;
                        if(DT<0.005){
                            DT=0.005;
                        }
                    }
                }
                T2=Test;
                h_sistema = new ControlH_Sistema(T2, P2, Pref, Tref,session);
                s_sistema = new ControlS_Sistema(T2, P2, Pref, Tref,session) ;
                H2=h_sistema.getHV();
                S2=s_sistema.getSV();
            }else{
                double x2=(H2-h_sistema.getHL())/(h_sistema.getHV()-h_sistema.getHL());
                if(x2 < 0.99){
                    //Pasar=oiu*uy6r7*988
                    mensagem = "Erro Isoentropia Turbina";
                    return;
                }else{
                    T2=Test;
                   h_sistema = new ControlH_Sistema(T2, P2, Pref, Tref,session);
                   s_sistema = new ControlS_Sistema(T2, P2, Pref, Tref,session) ;
                   H2=(h_sistema.getHV()*x2)+(h_sistema.getHL()*(1-x2));
                   S2=(s_sistema.getSV()*x2)+(s_sistema.getSL()*(1-x2));    
                }
            }
        }           
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

    public double getH2s() {
        return H2s;
    }

    public void setH2s(double H2s) {
        this.H2s = H2s;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
