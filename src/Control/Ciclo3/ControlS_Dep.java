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
public class ControlS_Dep {
    
    double Pc, Tc, Tr, Pr, F, Omegab, Omegaa, Omegac, A, B, C, N, M, Q, alfa, a, da_dT, Ps2, dif, SDL, SDV;
    
    public ControlS_Dep(double T, double P,Session session){
        
        ControlConstantes constantes = new ControlConstantes(T, P);
        
        ControlZeta zeta = new ControlZeta(constantes.getAi(), constantes.getBi(), constantes.getCi());
        
        Pc = constantes.getPci();
        Tc = constantes.getTci();
        Tr = constantes.getTri();
        Pr = constantes.getPri();
        F = constantes.getFi();
        Omegab = constantes.getOmegabi();
        Omegaa = constantes.getOmegaai();
        Omegac = constantes.getOmegaci();
        A = constantes.getAi();
        B = constantes.getBi();
        C = constantes.getCi();
        N = constantes.getNi();
        M = constantes.getMi();
        Q = constantes.getQi();
        alfa = constantes.getAlfai();
        a = constantes.getai();
        
        da_dT = Omegaa*(Math.pow(constantes.getR(),2)*Tc/Pc)*(-F*(Math.pow(alfa,0.5))/(Math.pow(Tr, 0.5)));
        
        ControlPdeVapor pdevapor = new ControlPdeVapor(T,session);
        Ps2 = pdevapor.getPsi();
        
        if(Ps2 <= 73.825){
            dif = Math.abs((Ps2-P)/P);
            if(dif >= 0.001 && Ps2<P){
                //%disp('Esta liquido') ;
                SDL=((-constantes.getR()*Math.log(P/(zeta.getZl()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q))))))*100000;
                SDV=0;
            }else{
                if(dif >= 0.001 && Ps2>P){
                    //% disp('Esta como vapor') ;
                    SDL=0;
                    SDV=((-constantes.getR()*Math.log(P/(zeta.getZv()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q))))))*100000;
                }else{
                    //%disp('Esta no Ponto de saturaÁ„o') ;
                    SDL=((-constantes.getR()*Math.log(P/(zeta.getZl()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q))))))*100000;
                    SDV=((-constantes.getR()*Math.log(P/(zeta.getZv()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q))))))*100000;
                }
            }
        }else{
            //PERGUNTAR CESAR SE É ZL OU ZV
            SDL=((-constantes.getR()*Math.log(P/(zeta.getZl()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q))))))*100000;
            SDV=((-constantes.getR()*Math.log(P/(zeta.getZv()-B)))-(da_dT*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q))))))*100000;
        }
    } 

    public double getSDL() {
        return SDL;
    }

    public void setSDL(double SDL) {
        this.SDL = SDL;
    }

    public double getSDV() {
        return SDV;
    }

    public void setSDV(double SDV) {
        this.SDV = SDV;
    }
}