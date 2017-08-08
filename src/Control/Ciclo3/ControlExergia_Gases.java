/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import Ciclo3.Model.ModelConsEXE_A;
import Ciclo3.Model.ModelConsEXE_B;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alysonmp
 */
public class ControlExergia_Gases {

    private double T, To, XTOTAL, DHCO2, DHH2O, DHSO2, DHO2, DHN2, DHCO, DHH2, DHNO, DSCO2, DSH2O, DSSO2, DSO2, DSN2, DSCO, DSH2, DSNO, DHECO2, DHEH2O, DHESO2, DHEO2, DHEN2, DHECO, DHEH2, DHENO, Hin, DSECO2, DSEH2O, DSESO2, DSEO2, DSEN2, DSECO, DSEH2, DSENO, Sin, ECO2, EH2O, ESO2, EO2, EN2, ECO, EH2, ENO, Ein, bgas, ET;
   
    public ControlExergia_Gases(double mf, double Tf, double[] X, Session session){
        Criteria cr = session.createCriteria(ModelConsEXE_A.class);
        List results = cr.list();
        
        double[][] C = new double[8][4];
        for(int i = 0; i < results.size(); i++){
            ModelConsEXE_A A = (ModelConsEXE_A) results.get(i);
            C[i] = A.getA(); //CRIAR TABELA A;
        }
        
        T = Tf;
        To = 298.15;
        XTOTAL = X[0] + X[1] + X[2] + X[3] + X[4] + X[5] + X[6] + X[7];
        
        DHCO2 = ((C[0][0]*T)+((C[0][1]/1000)*Math.pow(T, 2))+((C[0][2]*100000)/T)+(C[0][3]))*4.186;
        DHH2O = ((C[1][0]*T)+((C[1][1]/1000)*Math.pow(T, 2))+((C[1][2]*100000)/T)+(C[1][3]))*4.186;
        DHSO2 = ((C[2][0]*T)+((C[2][1]/1000)*Math.pow(T, 2))+((C[2][2]*100000)/T)+(C[2][3]))*4.186;
        DHO2 = ((C[3][0]*T)+((C[3][1]/1000)*Math.pow(T, 2))+((C[3][2]*100000)/T)+(C[3][3]))*4.186;
        DHN2 = ((C[4][0]*T)+((C[4][1]/1000)*Math.pow(T, 2))+((C[4][2]*100000)/T)+(C[4][3]))*4.186;

        DHCO = ((C[5][0]*T)+((C[5][1]/1000)*Math.pow(T, 2))+((C[5][2]*100000)/T)+(C[5][3]))*4.186;
        DHH2 = ((C[6][0]*T)+((C[6][1]/1000)*Math.pow(T, 2))+((C[6][2]*100000)/T)+(C[6][3]))*4.186;
        DHNO = ((C[7][0]*T)+((C[7][1]/1000)*Math.pow(T, 2))+((C[7][2]*100000)/T)+(C[7][3]))*4.186;
        
        DSCO2 = ((C[0][0]*Math.log(T/To))+((2*C[0][1]/1000)*(T-To))+(((C[0][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSH2O = ((C[1][0]*Math.log(T/To))+((2*C[1][1]/1000)*(T-To))+(((C[1][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSSO2 = ((C[2][0]*Math.log(T/To))+((2*C[2][1]/1000)*(T-To))+(((C[2][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSO2 = ((C[3][0]*Math.log(T/To))+((2*C[3][1]/1000)*(T-To))+(((C[3][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSN2 = ((C[4][0]*Math.log(T/To))+((2*C[4][1]/1000)*(T-To))+(((C[4][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;

        DSCO = ((C[5][0]*Math.log(T/To))+((2*C[5][1]/1000)*(T-To))+(((C[5][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSH2 = ((C[6][0]*Math.log(T/To))+((2*C[6][1]/1000)*(T-To))+(((C[6][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        DSNO = ((C[7][0]*Math.log(T/To))+((2*C[7][1]/1000)*(T-To))+(((C[7][2]/2)*100000)*((1/Math.pow(T,2))-(1/Math.pow(To,2)))))*4.1861;
        
        double[] y = {X[0]/XTOTAL, X[1]/XTOTAL, X[2]/XTOTAL, X[3]/XTOTAL, X[4]/XTOTAL, X[5]/XTOTAL, X[6]/XTOTAL, X[7]/XTOTAL};
        
        DHECO2 = (DHCO2)*y[0];
        DHEH2O = (DHH2O)*y[1];
        DHESO2 = (DHSO2)*y[2];
        DHEO2 = (DHO2)*y[3];
        DHEN2 = (DHN2)*y[4];

        DHECO = DHCO*y[5];
        DHEH2 = DHH2*y[6];
        DHENO = DHNO*y[7];
        
        Hin = (DHECO2+DHEH2O+DHESO2+DHEO2+DHEN2+DHECO+DHEH2+DHENO);
        
        DSECO2 = (DSCO2)*y[0];
        DSEH2O = (DSH2O)*y[1];
        DSESO2 = (DSSO2)*y[2];
        DSEO2 = (DSO2)*y[3];
        DSEN2 = (DSN2)*y[4];

        DSECO = DSCO*y[5];
        DSEH2 = DSH2*y[6];
        DSENO = DSNO*y[7];
        
        Sin = (DSECO2+DSEH2O+DSESO2+DSEO2+DSEN2+DSECO+DSEH2+DSENO);
        
        ECO2 = (DHCO2-(To*DSCO2))*y[0];
        EH2O = (DHH2O-(To*DSH2O))*y[1];
        ESO2 = (DHSO2-(To*DSSO2))*y[2];
        EO2 = (DHO2-(To*DSO2))*y[3];
        EN2 = (DHN2-(To*DSN2))*y[4];

        ECO = (DHCO-(To*DSCO))*y[5];
        EH2 = (DHH2-(To*DSH2))*y[6];
        ENO = (DHNO-(To*DSNO))*y[7];
        
        Ein = (ECO2+EH2O+ESO2+EO2+EN2+ECO+EH2+ENO);
        
        double[] mfx = new double[8];
        double[] logy = new double[8];
        
        for(int i = 0; i < 8; i++){
            mfx[i] = (mf*y[i]);
            logy[i] = Math.log(y[i]);
            
            Double logyd = logy[i];
            if(logyd.isInfinite())
                logy[i] = 0;
        } 
        
        cr = session.createCriteria(ModelConsEXE_B.class);
        results = cr.list();
        ModelConsEXE_B b = (ModelConsEXE_B) results.get(0);
        
        double ylogy = 0;
        for(int i = 0; i < 8; i ++){
            ylogy += y[i]*logy[i];
        }
        
        double yb = 0;
        bgas = 0;
        for(int i = 0; i < 8; i++){
            yb += y[i]*b.getB()[i];
        }
        
        bgas += yb+(8.31416*ylogy*To);
        ET = bgas+Ein;
    }

    public double getHin() {
        return Hin;
    }

    public void setHin(double Hin) {
        this.Hin = Hin;
    }

    public double getSin() {
        return Sin;
    }

    public void setSin(double Sin) {
        this.Sin = Sin;
    }

    public double getEin() {
        return Ein;
    }

    public void setEin(double Ein) {
        this.Ein = Ein;
    }

    public double getBgas() {
        return bgas;
    }

    public void setBgas(double bgas) {
        this.bgas = bgas;
    }

    public double getET() {
        return ET;
    }

    public void setET(double ET) {
        this.ET = ET;
    }
}


