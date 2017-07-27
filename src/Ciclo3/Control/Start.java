/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control;

import Ciclo1.Control.Ciclo1.ControlAreas;
import Ciclo1.Control.Ciclo1.ControlBalanco;
import Ciclo1.Control.Ciclo1.ControlBomba;
import Ciclo1.Control.Ciclo1.ControlCondensador;
import Ciclo1.Control.Ciclo1.ControlEvaporador;
import Ciclo1.Control.Ciclo1.ControlMisturador;
import Ciclo1.Control.Ciclo1.ControlParametros;
import Ciclo1.Control.Ciclo1.ControlRegeneradorHT;
import Ciclo1.Control.Ciclo1.ControlRegeneradorLT;
import Ciclo1.Control.Ciclo1.ControlSeparador;
import Ciclo1.Control.Ciclo1.ControlTurbina;
import Ciclo1.Control.Ciclo1.ControlValvula;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class Start {
    
    double P1, PP, SUBT, Tf, Tres, effLT, zi, VE, P2, Pf, DT1, T1, ec;
    Session session;
    
    int compressor;
    
    public Start(Session session,ControlPrincipal ctrPrincipal){
        this.session = session;
        
        P1 = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtP1().getText());
        PP = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPP().getText());
        SUBT = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtSUBT().getText());
        Tf = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTf().getText());
        Tres = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTres().getText());
        compressor = ctrPrincipal.getViewPrincipal().getComp();
        effLT = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxteffLt().getText());
        zi = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtzi().getText());
        VE = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtVE().getText());
        
        P2 = P1;
        Pf = 4000/100; //%bar

        ControlParametros parametros = new ControlParametros(P1, Tf, Pf, zi, Tres, this.session, ctrPrincipal);
        
        DT1=(parametros.getTorvA()-parametros.getTbolA())-1;
        T1=parametros.getTbolA()+(DT1*VE);

        if(Tf-5<T1){
            JOptionPane.showMessageDialog(null,"Erro start","Error",0);
            return;
        }

        double Beff = parametros.getBeff();
        double Pcon = parametros.getPcon();
        double Tcon = parametros.getTcon();
        double Pref = parametros.getPref();
        double Tref = parametros.getTref();
        
        //ControlBomba bomba = new ControlBomba(0.8, 20, 11.8076, 313.15, 50, 273.15, 0.8, session);
        ControlBomba bomba = new ControlBomba(Beff, P1, Pcon, Tcon, Pref, Tref, zi, session);
        if(!bomba.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,bomba.getMensagem(),"Error",0);
            return;
        }
        
        //ControlSeparador separador = new ControlSeparador(P1, 378.3645, zi, Pref, Tref, session);
        ControlSeparador separador = new ControlSeparador(P1, T1, zi, Pref, Tref, session);
        
        double H2 = separador.getH2();
        double Teff = parametros.getTeff();
        double T2 = separador.getT2();
        double y2 = separador.getYi();
        
        ControlTurbina turbina = new ControlTurbina(H2, Teff, P2, T2, Pcon, Pref, Tref, y2, session);
        
        double x3 = separador.getX();
        
        ControlValvula valvula = new ControlValvula(SUBT, P1, x3, Pcon, Pref, Tref, session);
        
        double TbolB = parametros.getTbolB();
        double TorvB = parametros.getTorvB();
        double T6 = valvula.getT6();
        double T4 = turbina.getT4();
        double VF1 = separador.getVF();
        double H4 = turbina.getH4();
        double H6 = valvula.getH6();
        //double T4 = 0;
        //double H4 = 0;
        //ControlMisturador misturador = new ControlMisturador(313.15, 409.57, T6, zi, 355.0946, Pcon, VF1, 24279, H6, Pref, Tref, session);
        ControlMisturador misturador = new ControlMisturador(TbolB, TorvB, T6, zi, T4, Pcon, VF1, H4, H6, Pref, Tref, session);
        
        double TbolA = parametros.getTbolA();
        double TorvA = parametros.getTorvA();
        double H7 = misturador.getH7();
        double H10 = bomba.getH10();
        double T7 = misturador.getT7();
        double T10 = bomba.getT10();
        
        //ControlRegeneradorLT regeneradorLT = new ControlRegeneradorLT(333.6505, 424.0785, 313.1576, 409.5770, P1, H7, H10, 354.7027, T10, Pcon, Pref, Tref, zi, effLT, session);
        ControlRegeneradorLT regeneradorLT = new ControlRegeneradorLT(TbolA, TorvA, TbolB, TorvB, P1, H7, H10, T7, T10, Pcon, Pref, Tref, zi, effLT, session);
        if(!regeneradorLT.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,regeneradorLT.getMensagem(),"Error",0);
            return;
        }
              
        double H3 = separador.getH3();
        double H5 = valvula.getH5();
        double H11 = regeneradorLT.getH11();
        double T3 = separador.getT3();
        double T5 = valvula.getT5();
        double T11 = regeneradorLT.getT11();
        //double H11 = 0;
        //double T11 = 0;
        
        //ControlRegeneradorHT regeneradorHT = new ControlRegeneradorHT(333.6505, 424.0785, 0.7156, 5132.1, 3168.9, 1459, 378.3645, 353.5914, 317.4796, P1, Pref, Tref, zi, session);
        ControlRegeneradorHT regeneradorHT = new ControlRegeneradorHT(TbolA, TorvA, VF1, H3, H5, H11, T3, T5, T11, P1, Pref, Tref, zi, session);
        if(!regeneradorHT.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,regeneradorHT.getMensagem(),"Error",0);
            return;
        }
        
        double T8 = regeneradorLT.getT8();
        double P8 = regeneradorLT.getP8();
        double H8 = regeneradorLT.getH8();
        //double T8 = 0;
        //double P8 = 0;
        //double H8 = 0;
        
        //ControlCondensador condensador = new ControlCondensador(313.1576, 350.6612, 11.8076, 17960, Pref, Tref, zi, session);
        ControlCondensador condensador = new ControlCondensador(TbolB, T8, P8, H8, Pref, Tref, zi, session);
        
        double T12 = regeneradorHT.getT12();
        double H12 = regeneradorHT.getH12();
        double H1 = separador.getH1();
        
        //ControlEvaporador evaporador = new ControlEvaporador(333.6505, T12, H12, Tf, P1, zi, Pref, Tref, H1, PP, compressor, session);
        ControlEvaporador evaporador = new ControlEvaporador(TbolA, T12, H12, Tf, P1, zi, Pref, Tref, H1, PP, compressor, session);
        if(!evaporador.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,evaporador.getMensagem(),"Error",0);
            return;
        }
        
        double m = evaporador.getM();
        double H9 = condensador.getH9();
        
        //ControlBalanco balanco = new ControlBalanco(VF1, m, H2, 24279, H7, 17960, H9, H10, H12, 1459);
        ControlBalanco balanco = new ControlBalanco(VF1, m, H2, H4, H7, H8, H9, H10, H12, H11);
        
        double QLHR = balanco.getQLHR();
        double QHHR = balanco.getQHHR();
        double Qcon = balanco.getQcon();
        double Qsen = evaporador.getQsen();
        double Qlat = evaporador.getQlat();
        double Tfout = evaporador.getTfout();
        double T9 = condensador.getT9();
        
        //ControlAreas areas = new ControlAreas(QLHR, QHHR, Qcon, Qsen, Qlat, PP, Tf, Tfout, 378.3645, T12, 333.6505, T9, 350.6612, 317.4796, 354.7027, T10, 378.3645, 353.5914);
        ControlAreas areas = new ControlAreas(QLHR, QHHR, Qcon, Qsen, Qlat, PP, Tf, Tfout, T1, T12, TbolA, T9, T8, T11, T7, T10, T3, T5);
        
        double Wn = balanco.getWn();
        double QTf = evaporador.getQTf();
        
        ec=Wn/QTf;
        
        
        ctrPrincipal.getViewPrincipal().getTxtteste1().setText("Ec: "+ec);
        ctrPrincipal.getViewPrincipal().getTxtteste2().setText("QLHR: "+QLHR);
        ctrPrincipal.getViewPrincipal().getTxtteste3().setText("QHHR: "+QHHR);
        ctrPrincipal.getViewPrincipal().getTxtteste4().setText("Qcon: "+Qcon);
        ctrPrincipal.getViewPrincipal().getTxtteste5().setText("Qsen: "+Qsen);
        ctrPrincipal.getViewPrincipal().getTxtteste6().setText("Qlat: "+Qlat);
        ctrPrincipal.getViewPrincipal().getTxtteste7().setText("Tfout: "+Tfout);
        ctrPrincipal.getViewPrincipal().getTxtteste8().setText("T9: "+T9);
    }
}
