/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class ControlH_Sistema {
    
    private double HDrefL, HDrefV, Hig, HDL, HDV, HL, HV;
    private String mensagem = "";
    
    public ControlH_Sistema(double T, double P, double Pref, double Tref, Session session){
        ControlH_Dep Hdep = new ControlH_Dep(Tref, Pref,session);
        if(!Hdep.getMensagem().equals("")) {
            this.mensagem = Hdep.getMensagem();
            return;
        }
        
        HDrefL = Hdep.getHDL();
        HDrefV = Hdep.getHDV();
        
        ControlH_Ideal_Gas HIdealgas = new ControlH_Ideal_Gas(T, Tref,session);
        Hig = HIdealgas.getHig();

        Hdep = new ControlH_Dep(T, P, session);
        if(!Hdep.getMensagem().equals("")) {
            this.mensagem = Hdep.getMensagem();
            return;
        }
        
        HDL = Hdep.getHDL();
        HDV = Hdep.getHDV();
                
        HL = -HDrefL+Hig+HDL;
        HV = -HDrefL+Hig+HDV;

    }

    public double getHL() {
        return HL;
    }

    public void setHL(double HL) {
        this.HL = HL;
    }

    public double getHV() {
        return HV;
    }

    public void setHV(double HV) {
        this.HV = HV;
    }
    
    public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
	}
}
