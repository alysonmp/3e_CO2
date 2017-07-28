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
 * @author leonardo
 */
public class ControlPdeVapor {

    private double Psi;
    
    public ControlPdeVapor(double T, Session session) {
        //RECUPERA TODOS OS DADOS DA TABELA DE CONSTANTES
        Criteria cr = session.createCriteria(ModelConstantesMat.class); 
        List results = cr.list();
        
        //UTILIZA A PRIMEIRA LINHA BUSCADA, VARIÁVEL C1
        ModelConstantesMat constantesMat = (ModelConstantesMat)results.get(0); 
        double[][] valores = constantesMat.getValores();


        if(T>=216.58 && T<=304.1999999999999999999){
            //Psi=((10^(C1(1)+(C1(2)/T)+(C1(3)*log10(T))+(C1(4)*T)+((T^2*C1(5))))))*1.33E-03;
            Psi=(Math.pow(10,(valores[0][0]+(valores[0][1]/T)+(valores[0][2]*Math.log10(T))+(valores[0][3]*T)+((Math.pow(T,2)*valores[0][4])))))*1.33E-03;
        }else if(T==304.25){
            Psi=73.825;
        }else if(T>304.25){
            Psi=(0.0176*Math.pow(T,2))-(6.8973*T)+(575.16);
        }
    }

    public double getPsi() {
        return Psi;
    }

    public void setPsi(double Psi) {
        this.Psi = Psi;
    }
 
    
    
}

