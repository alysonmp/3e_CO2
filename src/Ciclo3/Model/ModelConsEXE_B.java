/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConsEXE_B")
public class ModelConsEXE_B implements Serializable{

    @Id
    @GeneratedValue
    private int cod;
    
    @Column
    private double[] B;

    public ModelConsEXE_B() {
    }
    
    public ModelConsEXE_B(double[] B) {
        this.B = B;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double[] getB() {
        return B;
    }

    public void setB(double[] B) {
        this.B = B;
    }
}