/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control;

import Control.Ciclo3.ControlBalanco;
import Control.Ciclo3.ControlBomba;
import Control.Ciclo3.ControlMassa;
import Control.Ciclo3.ControlRegenerador;
import Control.Ciclo3.ControlTurbina;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class Start {
    
    double P1, PP, SUBT, Tf, effLT, zi, VE, P2, Pf, DT1, T1, ec, Mf;
    Session session;
    
    int compressor;
    
    public Start(Session session, ControlPrincipal ctrPrincipal){
        this.session = session;
       
        double Tref = 217;
        double Pref = 10;

        double Teff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTeff().getText());
        double Beff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtBeff().getText());
        double eff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxteff().getText());
        double DTT=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtDtt().getText());

        P1=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtP1().getText());
        T1=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtT1().getText());
        double Pconop=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPconop().getText());
        double Tconop=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPconop().getText());
        
        double G=1;
        P2=Pconop;
        double T4=40+273.15;
        double P4=Pconop;
        double P5=P1;

        Tf=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTf().getText());
        Pf=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPf().getText());
        Mf=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtMf().getText());
        compressor=ctrPrincipal.getViewPrincipal().getComp();


        //[H1, H2, S1, S2, T2, H2s] = turbina(Teff, P1, T1, P2, Pref, Tref);
        ControlTurbina turbina = new ControlTurbina(Teff, P1, T1, P2, Pref, Tref, session);
        double H2 = turbina.getH2();
        double S2 = turbina.getS2();
        double T2 = turbina.getT2();
        double H1 = turbina.getH1();
        double S1 = turbina.getS1();
        
        //[S4, H4, S5, H5, T5] = Bomba(Beff, P1, P4, T4, Pref, Tref);
        ControlBomba bomba = new ControlBomba(Beff, P1, P4, T4, Pref, Tref, session);
        double H5 = bomba.getH5();
        double S5 = bomba.getS5();
        double T5 = bomba.getT5();
        double H4 = bomba.getH4();
        double S4 = bomba.getS4();
        
        //[S3, H3, S6, H6, T3, T6, IHR] = Regenerador(G, H2, H5, S2, S5, P2, T2, P5, T5, P1, Pconop, Tconop, Pref, Tref, eff);
        ControlRegenerador regenerador = new ControlRegenerador(G, H2, H5, S2, S5, P2, T2, P5, T5, P1, Pconop, Tconop, Pref, Tref, eff, session);
        double H3 = regenerador.getH3();
        double H6 = regenerador.getH6();
        double S3 = regenerador.getS3();
        double S6 = regenerador.getS6();
        double T6 = regenerador.getT6();
        
        //[QTf, Tf2, m] = massa(H4, H1, H6, P1, Pref, Tref, T1, T6, SUP, Tf, compressor)
        ControlMassa massa = new ControlMassa(H4, H1, H6, P1, Pref, Tref, T1, T6, DTT, Tf, compressor, session);
        double m = massa.getM();
        
        //[Wt, Wb, Qevp, Qcon, ec, Qreg, Qreg1, Wn]=balanco(T1, H1, H2, H3, H4, H5, H6, S1, S2, S3, S4, S5, S6, m, Pref, Tref);
        ControlBalanco balanco = new ControlBalanco(T1, H1, H2, H3, H4, H5, H6, S1, S2, S3, S4, S5, S6, m, Pref, Tref);
        double Wt = balanco.getWt();
        double Wn = balanco.getWn();
        double Wb = balanco.getWb();
        double Qevp = balanco.getQevp();
        double Qcon = balanco.getQcon();
        double ec = balanco.getEc()*100;
        double Qreg = balanco.getQreg();
        double Qreg1 = balanco.getQreg1();
        
        
        ctrPrincipal.getViewPrincipal().getTxtteste1().setText("Wt: "+Wt);
        ctrPrincipal.getViewPrincipal().getTxtteste2().setText("Wn: "+Wn);
        ctrPrincipal.getViewPrincipal().getTxtteste3().setText("Wb: "+Wb);
        ctrPrincipal.getViewPrincipal().getTxtteste4().setText("Qevp: "+Qevp);
        ctrPrincipal.getViewPrincipal().getTxtteste5().setText("Qcon: "+Qcon);
        ctrPrincipal.getViewPrincipal().getTxtteste6().setText("ec: "+ec+"%");
        ctrPrincipal.getViewPrincipal().getTxtteste7().setText("Qreg"+Qreg);
        ctrPrincipal.getViewPrincipal().getTxtteste8().setText("Qreg1"+Qreg1);
    }
}
