/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConsEXE_A")
public class ModelConsEXE_A {

    @Id
    @GeneratedValue
    private int cod;
    
    @Column
    private double[] A;

    public ModelConsEXE_A() {
    }
    
    public ModelConsEXE_A(double[] A) {
        this.A = A;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double[] getA() {
        return A;
    }

    public void setA(double[] A) {
        this.A = A;
    }
}
