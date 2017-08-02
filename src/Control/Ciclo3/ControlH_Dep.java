/*
 * To change this license header, choose License Headers in Proiect Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class ControlH_Dep {
    
    private double  HDL, HDV;
    private Session session;
    private String mensagem = "";
    
    public ControlH_Dep(double T, double P, Session session){
        this.session = session;
        ControlConstantes constantes = new ControlConstantes(T, P);
        
        ControlZeta zeta = new ControlZeta(constantes.getAi(), constantes.getBi(), constantes.getCi());

        double Pc = constantes.getPci();
        double Tc = constantes.getTci();
        double Tr = constantes.getTri();
        double Pr = constantes.getPri();
        double F = constantes.getFi();
        double Omegab = constantes.getOmegabi();
        double Omegaa = constantes.getOmegaai();
        double Omegac = constantes.getOmegaci();
        double A = constantes.getAi();
        double B = constantes.getBi();
        double C = constantes.getCi();
        double N = constantes.getNi();
        double M = constantes.getMi();
        double Q = constantes.getQi();
        double alfa = constantes.getAlfai();   
        double a = constantes.getai();
        
        //da_dT=Omegaa*(R^2*Tc/Pc)*(-F*(alfa^0.5)/(Tr^0.5));
        double da_dT=Omegaa*(Math.pow(constantes.getR(), 2)*Tc/Pc)*(-F*(Math.pow(alfa, 0.5))/(Math.pow(Tr,0.5)));
        
        ControlPdeVapor pVapor = new ControlPdeVapor(T, this.session);
        if(!pVapor.getMensagem().equals("")) {
        		this.mensagem = pVapor.getMensagem();
        		return;
        }
        double Ps2 = pVapor.getPsi();
        
        if(Ps2 <=73.825){
            double dif = Math.abs((Ps2-P)/P);
            if(dif >= 0.001 && Ps2 < P){
                //HDL=(R*T*(Zl-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zl+M)/(Zl+Q)))))*100000;
                HDL = (constantes.getR()*T*(zeta.getZl()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q)))))*100000;
                HDV = 0;
            }else{
                if(dif >= 0.001 && Ps2 > P){
                    HDL=0;
                    //HDV=(R*T*(Zv-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zv+M)/(Zv+Q)))))*100000;
                    HDV=(constantes.getR()*T*(zeta.getZv()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q)))))*100000;
                }else{
                    if(dif<0.001){
                        //HDL=(R*T*(Zl-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zl+M)/(Zl+Q)))))*100000;
                        HDL=(constantes.getR()*T*(zeta.getZl()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q)))))*100000;
                        //HDV=(R*T*(Zv-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zv+M)/(Zv+Q)))))*100000;
                        HDV=(constantes.getR()*T*(zeta.getZv()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q)))))*100000;
                    }
                }
            }
        }else{
            //HDL=(R*T*(Zl-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zl+M)/(Zl+Q)))))*100000;
            HDL=(constantes.getR()*T*(zeta.getZl()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZl()+M)/(zeta.getZl()+Q)))))*100000;
            //HDV=(R*T*(Zv-1)-((T*da_dT)-a)*((1/(2*N))*(log((Zv+M)/(Zv+Q)))))*100000;
            HDV=(constantes.getR()*T*(zeta.getZv()-1)-((T*da_dT)-a)*((1/(2*N))*(Math.log((zeta.getZv()+M)/(zeta.getZv()+Q)))))*100000;
        }
    }   

    public double getHDL() {
        return HDL;
    }

    public void setHDL(double HDL) {
        this.HDL = HDL;
    }

    public double getHDV() {
        return HDV;
    }

    public void setHDV(double HDV) {
        this.HDV = HDV;
    }
    
    public String getMensagem() {
    		return mensagem;
    }
    
    public void setMensagem(String mensagem) {
    		this.mensagem = mensagem;
    }
}