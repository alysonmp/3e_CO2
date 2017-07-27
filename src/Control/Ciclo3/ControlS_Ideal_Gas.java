/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import Ciclo3.Model.ModelConstantesMat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class ControlS_Ideal_Gas {
 
    double R, Cp, Sig;
    
    public ControlS_Ideal_Gas(double T, double Tref, double P, double Pref, Session session){
        Criteria cr = session.createCriteria(ModelConstantesMat.class);
        List results = cr.list();

        //UTILIZA A PRIMEIRA LINHA BUSCADA, VARIÁVEL CC
        ModelConstantesMat constantesMat = (ModelConstantesMat)results.get(0); 
        double[][] valores = constantesMat.getValores();

        R = 8.314;
        
        //Cp=cc(ii,1)+cc(ii,2)*T+cc(ii,3)*T^2+cc(ii,4)*T^3+cc(ii,5)*T^4;
        Cp = valores[0][0] + valores[0][1] * T+valores[0][2] * Math.pow(T,2) + valores[0][3] * Math.pow(T, 3) + valores[0][4]*Math.pow(T,4);
        //Sig=((cc(ii,1)*log(T)+cc(ii,2)*T+(cc(ii,3)/2)*(T^2)+(cc(ii,4)/3)*(T^3)+(cc(ii,5)/4)*(T^4))- (cc(ii,1)*log(Tref)+cc(ii,2)*Tref+(cc(ii,3)/2)*(Tref^2)+(cc(ii,4)/3)*(Tref^3)+(cc(ii,5)/4)*(Tref^4)));
        Sig = (valores[0][0]*Math.log(T)+valores[0][1]*T+(valores[0][2]/2)*(Math.pow(T,2))+(valores[0][3]/3)*(Math.pow(T,3))+(valores[0][4]/4)*(Math.pow(T,4)))- (valores[0][0]*Math.log(Tref)+valores[0][1]*Tref+(valores[0][2]/2)*(Math.pow(Tref,2))+(valores[0][3]/3)*(Math.pow(Tref,3))+(valores[0][4]/4)*(Math.pow(Tref,4)));
    }

    public double getCp() {
        return Cp;
    }

    public void setCp(double Cp) {
        this.Cp = Cp;
    }

    public double getSig() {
        return Sig;
    }

    public void setSig(double Sig) {
        this.Sig = Sig;
    }
    
    
}