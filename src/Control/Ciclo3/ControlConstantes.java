/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

/**
 *
 * @author leonardo
 */
public class ControlConstantes {

    private double R, Pci, Tci, Zci, Tri, Pri, Fi, Omegabi, Omegaai, Omegaci, ai, bi, ci, Ai, Bi, Ci, Ni, Mi, Qi, alfai;
    
    public ControlConstantes(double T, double P) {
        R=8.3144621/100000;//m3 * bar /kmol K
        Pci=73.825 ;
        Tci=304.2;
        Zci=0.309;
        Tri=T/Tci;
        Pri=P/Pci;
        Fi=0.707727;
        
        Omegabi=0.069606085208489;
        Omegaai=(3*Math.pow(Zci,2))+((3*(1-2*Zci))*Omegabi)+(Math.pow(Omegabi,2))+1-(3*Zci);
        Omegaci=1-(3*Zci);
        ai=Omegaai*(Math.pow(R,2)*Math.pow(Tci,2)/Pci)*Math.pow((1+(Fi*(1-(Math.pow(Tri,0.5))))),2);
        alfai=Math.pow((1+(Fi*(1-Math.pow(Tri,0.5)))),2);
        bi=Omegabi*(R*Tci/Pci);
        ci=Omegaci*(R*Tci/Pci);
        Ai=(ai*P)/Math.pow((R*T),2);
        Bi=bi*P/(R*T) ;
        Ci=ci*P/(R*T) ;
        Ni=Math.pow(((bi*ci)+Math.pow(((bi+ci)/2),2)),0.5);
        Mi=(((bi+ci)/2)-Ni)*(P/(R*T)) ;
        Qi=(((bi+ci)/2)+Ni)*P/(R*T) ;
    }

    public double getR() {
        return R;
    }

    public void setR(double R) {
        this.R = R;
    }

    public double getPci() {
        return Pci;
    }

    public void setPci(double Pci) {
        this.Pci = Pci;
    }

    public double getTci() {
        return Tci;
    }

    public void setTci(double Tci) {
        this.Tci = Tci;
    }

    public double getZci() {
        return Zci;
    }

    public void setZci(double Zci) {
        this.Zci = Zci;
    }

    public double getTri() {
        return Tri;
    }

    public void setTri(double Tri) {
        this.Tri = Tri;
    }

    public double getPri() {
        return Pri;
    }

    public void setPri(double Pri) {
        this.Pri = Pri;
    }

    public double getFi() {
        return Fi;
    }

    public void setFi(double Fi) {
        this.Fi = Fi;
    }

    public double getOmegabi() {
        return Omegabi;
    }

    public void setOmegabi(double Omegabi) {
        this.Omegabi = Omegabi;
    }

    public double getOmegaai() {
        return Omegaai;
    }

    public void setOmegaai(double Omegaai) {
        this.Omegaai = Omegaai;
    }

    public double getOmegaci() {
        return Omegaci;
    }

    public void setOmegaci(double Omegaci) {
        this.Omegaci = Omegaci;
    }

    public double getai() {
        return ai;
    }

    public void setai(double ai) {
        this.ai = ai;
    }

    public double getbi() {
        return bi;
    }

    public void setbi(double bi) {
        this.bi = bi;
    }

    public double getci() {
        return ci;
    }

    public void setci(double ci) {
        this.ci = ci;
    }

    public double getAi() {
        return Ai;
    }

    public void setAi(double Ai) {
        this.Ai = Ai;
    }

    public double getBi() {
        return Bi;
    }

    public void setBi(double Bi) {
        this.Bi = Bi;
    }

    public double getCi() {
        return Ci;
    }

    public void setCi(double Ci) {
        this.Ci = Ci;
    }

    public double getNi() {
        return Ni;
    }

    public void setNi(double Ni) {
        this.Ni = Ni;
    }

    public double getMi() {
        return Mi;
    }

    public void setMi(double Mi) {
        this.Mi = Mi;
    }

    public double getQi() {
        return Qi;
    }

    public void setQi(double Qi) {
        this.Qi = Qi;
    }

    public double getAlfai() {
        return alfai;
    }

    public void setAlfai(double alfai) {
        this.alfai = alfai;
    }
    
    
}

