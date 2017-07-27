/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Model;

/**
 *
 * @author alysonmp
 */
public class ModelComplexo {
    double real;
    double imaginario;

    public ModelComplexo(double real, double imaginario){
        this.real = real;
        this.imaginario = imaginario;
    }
    
    public double abs(ModelComplexo complexo){
        if(complexo.real < 0){
            complexo.real *= -1;
        }
        
        if(complexo.imaginario < 0){
            complexo.imaginario *= -1;
        }
        
        return Math.sqrt(Math.pow(complexo.real, 2)+Math.pow(complexo.imaginario, 2));
    }
    
    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginario() {
        return imaginario;
    }

    public void setImaginario(double imaginario) {
        this.imaginario = imaginario;
    }
    
    
}
