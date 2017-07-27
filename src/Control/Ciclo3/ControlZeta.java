package Control.Ciclo3;

import Ciclo3.Model.ModelComplexo;

public class ControlZeta {

    ControlCubica cubica;
    
    private double Zl;
    private double Zv;
    private double C;
    
    private double ter1, ter2, ter3, ter4, Z1m, Z2m;

    public ControlZeta(double A, double B, double C) {

        ter1 = 1;
        ter2 = (C-1);
        //ter3=((-2*B*C)-(B^2)-B-C+A);
        ter3 = (-2*B*C)-(Math.pow(B, 2))-B-C+A;
        //ter4=((B^2)*C)+(B*C)-(A*B);
        ter4 = (Math.pow(B, 2)*C)+(B*C)-(A*B);

        cubica = new ControlCubica();
        cubica.solve(ter1, ter2, ter3, ter4);

        ModelComplexo complexo1 = cubica.getComplexo1();
        ModelComplexo complexo2 = cubica.getComplexo2();
        ModelComplexo complexo3 = cubica.getComplexo3();
            
        double x1 = complexo1.getReal();
        double x2 = complexo2.getReal();
        double x3 = complexo3.getReal();
        
        if (complexo1.getImaginario() == 0 && complexo2.getImaginario() == 0 && complexo3.getImaginario() == 0) {
            if(x1 > x2 && x1 > x3){
                Z1m = x1;
            }else if(x2 > x3){
                Z1m = x2;
            }else{
                Z1m = x3;            
            }
            
            if (x1 < x2 && x1 < x3) {
                Z2m = x1;
            }else if (x2 < x3) {
                Z2m = x2;
            }else {
                Z2m = x3;
            }
        }else{
            if(complexo1.getImaginario() == 0){
                x1 = 0;
            }

            if(complexo2.getImaginario() == 0){
                x2 = 0;
            }

            if(complexo3.getImaginario() == 0){
                x3 = 0;
            }
            
            if(x1 > x2 && x1 > x3 && complexo1.getImaginario() == 0){
                Z1m = x1;
            }else if(x2 > x3 && complexo2.getImaginario() == 0){
                Z1m = x2;
            }else if(complexo3.getImaginario() == 0){
                Z1m = x3;
            }else{
                Z1m = 0;
            }
            
            if(x1 < x2 && x1 < x3 && complexo1.getImaginario() == 0){
                Z2m = x1;
            }else if(x2 < x3 && complexo2.getImaginario() == 0){
                Z2m = x2;
            }else if(complexo3.getImaginario() == 0){
                Z2m = x3;
            }else{
                Z2m = 0;
            }
            
            if(x1 == x2){
                Z2m = Z1m;
            }
            if(x1 == x3){
                Z2m = Z1m;
            }
            if(x2 == x3){
                Z2m = Z1m;
            }
        }
        Zl = Z2m;
        Zv = Z1m;
    }
    
    public double getZl() {
        return Zl;
    }

    public void setZl(double Zl) {
        this.Zl = Zl;
    }

    public double getZv() {
        return Zv;
    }

    public void setZv(double Zv) {
        this.Zv = Zv;
    }

    public double getZ1m() {
        return Z1m;
    }

    public void setZ1m(double Z1m) {
        this.Z1m = Z1m;
    }

    public double getZ2m() {
        return Z2m;
    }

    public void setZ2m(double Z2m) {
        this.Z2m = Z2m;
    }
}