/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control;

import Control.Ciclo3.ControlEvpeff;
import Control.Ciclo3.ControlBalanco;
import Control.Ciclo3.ControlBomba;
import Control.Ciclo3.ControlConeff;
import Control.Ciclo3.ControlMassa;
import Control.Ciclo3.ControlRegeff1;
import Control.Ciclo3.ControlRegenerador;
import Control.Ciclo3.ControlTurbina;
import Control.Conversao.ControlConverte;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class Start {
    
    Session session;
        
    public Start(Session session, ControlPrincipal ctrPrincipal){
        ControlConverte converte = new ControlConverte();
        
        this.session = session;
       
        double Tref = 217;
        double Pref = 10;

        double Teff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTeff().getText());
        double Beff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtBeff().getText());
        double eff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxteff().getText());
        double DTT=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtDtt().getText());
        double Km=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtKm().getText());

        double P1;
        if(!ctrPrincipal.getViewPrincipal().getComboP1().getSelectedItem().toString().equals("kPa")){
            P1 = converte.converte(ctrPrincipal.getViewPrincipal().getComboP1().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtP1().getText()));
        }else{
            P1 = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtP1().getText());
        }
        
        double T1;
        if(!ctrPrincipal.getViewPrincipal().getComboT1().getSelectedItem().toString().equals("K")){
            T1 = converte.converte(ctrPrincipal.getViewPrincipal().getComboT1().getSelectedItem().toString(), "K", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtT1().getText()));
        }else{
            T1 = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtT1().getText());
        }
        
        
        double Pconop;
        if(!ctrPrincipal.getViewPrincipal().getComboPconop().getSelectedItem().toString().equals("kPa")){
            Pconop = converte.converte(ctrPrincipal.getViewPrincipal().getComboPconop().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPconop().getText()));
        }else{
            Pconop = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPconop().getText());
        }
        
        double Tconop;
        if(!ctrPrincipal.getViewPrincipal().getComboTconop().getSelectedItem().toString().equals("K")){
            Tconop = converte.converte(ctrPrincipal.getViewPrincipal().getComboTconop().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTconop().getText()));
        }else{
            Tconop = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTconop().getText());
        }
        
        double G=1;
        double P2=Pconop;
        double T4=40+273.15;
        double P4=Pconop;
        double P5=P1;
        double P3 = P2;
        double P6 = P5;

        double Tf;
        if(!ctrPrincipal.getViewPrincipal().getComboTf().getSelectedItem().toString().equals("K")){
            Tf = converte.converte(ctrPrincipal.getViewPrincipal().getComboTf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTf().getText()));
        }else{
            Tf = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtTf().getText());
        }
        
        double Pf;
        if(!ctrPrincipal.getViewPrincipal().getComboPf().getSelectedItem().toString().equals("K")){
            Pf = converte.converte(ctrPrincipal.getViewPrincipal().getComboPf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPf().getText()));
        }else{
            Pf = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtPf().getText());
        }
        
        double Mf=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTxtMf().getText());
        int compressor = ctrPrincipal.getViewPrincipal().getComp();
        int FON = ctrPrincipal.getViewPrincipal().getFON();


        //[H1, H2, S1, S2, T2, H2s] = turbina(Teff, P1, T1, P2, Pref, Tref);
        ControlTurbina turbina = new ControlTurbina(Teff, P1, T1, P2, Pref, Tref, session);
        if(!turbina.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,turbina.getMensagem(),"Error",0);
            return;
        }
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
        if(!regenerador.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,regenerador.getMensagem(),"Error",0);
            return;
        }
        
        double T3 = regenerador.getT3();
        double H3 = regenerador.getH3();
        double H6 = regenerador.getH6();
        double S3 = regenerador.getS3();
        double S6 = regenerador.getS6();
        double T6 = regenerador.getT6();
        
        //[QTf, Tf2, m] = massa(H4, H1, H6, P1, Pref, Tref, T1, T6, SUP, Tf, compressor)
        ControlMassa massa = new ControlMassa(H4, H1, H6, P1, Pref, Tref, T1, T6, DTT, Tf, compressor, session);
        double m = massa.getM();
        double Tf2 = massa.getTf2();
        
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
        
        ctrPrincipal.getViewPrincipal().getTxtWt().setText(""+round(Wt, 3));
        ctrPrincipal.getViewPrincipal().getTxtWb().setText(""+round(Wb, 3));
        ctrPrincipal.getViewPrincipal().getTxtWn().setText(""+round(Wn, 3));
        ctrPrincipal.getViewPrincipal().getTxtQevp().setText(""+round(Qevp, 3));
        ctrPrincipal.getViewPrincipal().getTxtQcon().setText(""+round(Qcon, 3));
        ctrPrincipal.getViewPrincipal().getTxtEc().setText(""+round(ec, 3)+" %");
        ctrPrincipal.getViewPrincipal().getTxtQreg().setText(""+round(Qreg, 3));
        
        ControlEvpeff evpeff = new ControlEvpeff(Tf, Tf2, T1, T6, P1, P6, Pf, m, Mf, Qevp, FON, Km, session);
        double ATevp = evpeff.getAT();
        double Ahoevp = evpeff.getAho();
        double Acoevp = evpeff.getAco();
        double Vhxevp = evpeff.getVhx();
        double Lhevp = evpeff.getLh();
        double Lcevp = evpeff.getLc();
        double L3evp = evpeff.getL3();
        double DPhevp = evpeff.getDPh();
        double DPcevp = evpeff.getDPc();
        
        ControlRegeff1 regeff = new ControlRegeff1(T2, T3, T6, T5, P2, P5, P3, P6, m, Qreg, Km, eff, session);
        double ATreg = regeff.getAT();
        double Ahoreg = evpeff.getAho();
        double Acoreg = evpeff.getAco();
        double Vhxreg = evpeff.getVhx();
        double Lhreg = evpeff.getLh();
        double Lcreg = evpeff.getLc();
        double L3reg = evpeff.getL3();
        double DPhreg = evpeff.getDPh();
        double DPcreg = evpeff.getDPc();
        
        ControlConeff coneff = new ControlConeff(T4, T3, P3, P4, m, Qcon, Km, session);
        double AT = regeff.getAT();
        double Aho = evpeff.getAho();
        double Aco = evpeff.getAco();
        double Vhx = evpeff.getVhx();
        double Lh = evpeff.getLh();
        double Lc = evpeff.getLc();
        double L3 = evpeff.getL3();
        double DPh = evpeff.getDPh();
        double DPc = evpeff.getDPc();

    }
    
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
