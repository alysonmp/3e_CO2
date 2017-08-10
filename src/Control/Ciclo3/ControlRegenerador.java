/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author leonardo
 */
public class ControlRegenerador {

    private double S3, H3, S6, H6, T3, T6, IHR;
    
    private String mensagem = "";
    
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
                        mensagem = "Com os parâmetros inseridos não é possível atingir a convergência.";
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
                mensagem = "Não foi possível fazer regeneração.";
                return;
            }
            
            double DT11=T6+5;
            double DT12=T5+5;
            if(T2<=DT11 && IHR>0){
                mensagem = "Temperatura de saída do regenerador muito elevada. Diminua a efetividade do recuperador de calor.";
                return;
            }
            if(IHR>0 && T3<=DT12){
                mensagem = "Temperatura de saída do regenerador muito elevada. Diminua a efetividade do recuperador de calor.";
                return;
            }
        }
    }

    public double getS3() {
        return S3;
    }

    public void setS3(double S3) {
        this.S3 = S3;
    }

    public double getH3() {
        return H3;
    }

    public void setH3(double H3) {
        this.H3 = H3;
    }

    public double getS6() {
        return S6;
    }

    public void setS6(double S6) {
        this.S6 = S6;
    }

    public double getH6() {
        return H6;
    }

    public void setH6(double H6) {
        this.H6 = H6;
    }

    public double getT3() {
        return T3;
    }

    public void setT3(double T3) {
        this.T3 = T3;
    }

    public double getT6() {
        return T6;
    }

    public void setT6(double T6) {
        this.T6 = T6;
    }

    public double getIHR() {
        return IHR;
    }

    public void setIHR(double IHR) {
        this.IHR = IHR;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
