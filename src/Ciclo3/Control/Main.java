package Ciclo3.Control;

import Ciclo3.View.ViewInicio;

public class Main {
        
    public static void main(String[] args){
        ViewInicio vi = new ViewInicio();
        ControlPrincipal principal = new ControlPrincipal();
        vi.getViewInicio().dispose();
    }
}
