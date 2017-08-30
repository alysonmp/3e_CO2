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

import Ciclo3.View.ViewSaida;

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

        double Teff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtTeff().getText());
        double Beff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtBeff().getText());
        double eff=Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldRegEff().getText());
        double DTT=Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtDtt().getText());

        double P1;
        if(!ctrPrincipal.getViewPrincipal().getDadosOp().getComboP1().getSelectedItem().toString().equals("kPa")){
            P1 = converte.converte(ctrPrincipal.getViewPrincipal().getDadosOp().getComboP1().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtP1().getText()));
        }else{
            P1 = Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtP1().getText());
        }
        
        double T1;
        if(!ctrPrincipal.getViewPrincipal().getDadosOp().getComboT1().getSelectedItem().toString().equals("K")){
            T1 = converte.converte(ctrPrincipal.getViewPrincipal().getDadosOp().getComboT1().getSelectedItem().toString(), "K", Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtT1().getText()));
        }else{
            T1 = Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtT1().getText());
        }
        
        
        double Pconop;
        if(!ctrPrincipal.getViewPrincipal().getDadosOp().getComboPconop().getSelectedItem().toString().equals("kPa")){
            Pconop = converte.converte(ctrPrincipal.getViewPrincipal().getDadosOp().getComboPconop().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtPconop().getText()));
        }else{
            Pconop = Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtPconop().getText());
        }
        
        double Tconop;
        if(!ctrPrincipal.getViewPrincipal().getDadosOp().getComboTconop().getSelectedItem().toString().equals("K")){
            Tconop = converte.converte(ctrPrincipal.getViewPrincipal().getDadosOp().getComboTconop().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtTconop().getText()));
        }else{
            Tconop = Double.parseDouble(ctrPrincipal.getViewPrincipal().getDadosOp().getTxtTconop().getText());
        }
        
        int core1 = (int)Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldCondCore().getText());
        if(core1 < 1 || core1 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do cooler fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        int core2 = (int)Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldEvapCore().getText());
        if(core2 < 1 || core2 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do recuperador fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        int core3 = (int)Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldRegCore().getText());
        if(core3 < 1 || core3 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do regenerador fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        
        double G=1;
        double P2=Pconop;
        double T4=40+273.15;
        double P4=Pconop;
        double P5=P1;
        double P3 = P2;
        double P6 = P5;

        double Tf;
        if(!ctrPrincipal.getViewPrincipal().getFonteCalor().getComboTf().getSelectedItem().toString().equals("K")){
            Tf = converte.converte(ctrPrincipal.getViewPrincipal().getFonteCalor().getComboTf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getFonteCalor().getTxtTf().getText()));
        }else{
            Tf = Double.parseDouble(ctrPrincipal.getViewPrincipal().getFonteCalor().getTxtTf().getText());
        }
        
        double Pf;
        if(!ctrPrincipal.getViewPrincipal().getFonteCalor().getComboPf().getSelectedItem().toString().equals("K")){
            Pf = converte.converte(ctrPrincipal.getViewPrincipal().getFonteCalor().getComboPf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrPrincipal.getViewPrincipal().getFonteCalor().getTxtPf().getText()));
        }else{
            Pf = Double.parseDouble(ctrPrincipal.getViewPrincipal().getFonteCalor().getTxtPf().getText());
        }
        
        double Mf=Double.parseDouble(ctrPrincipal.getViewPrincipal().getFonteCalor().getTxtMf().getText());
        int compressor = ctrPrincipal.getViewPrincipal().getComp();
        int FON = ctrPrincipal.getViewPrincipal().getFON();

        if(Tf < T1+10){
            JOptionPane.showMessageDialog(null, "Temperatura de vaporização superior à temperatura da fonte de calor", "Error", 0);
            return;
        }
        
        
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
        
        double k = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldEvapK().getText());
        
        ControlEvpeff evpeff = new ControlEvpeff(Tf, Tf2, T1, T6, P1, P6, Pf, m, Mf, Qevp, FON, k,core2, session);
        double ATevp = evpeff.getAT();
        double Ahoevp = evpeff.getAho();
        double Acoevp = evpeff.getAco();
        double Vhxevp = evpeff.getVhx();
        double Lhevp = evpeff.getLh();
        double Lcevp = evpeff.getLc();
        double L3evp = evpeff.getL3();
        double DPhevp = evpeff.getDPh();
        double DPcevp = evpeff.getDPc();
        
        k = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldRegK().getText());
        
        ControlRegeff1 regeff = new ControlRegeff1(T2, T3, T6, T5, P2, P5, P3, P6, m, Qreg, k, eff, core3, session);
        double ATreg = regeff.getAT();
        double Ahoreg = regeff.getAho();
        double Acoreg = regeff.getAco();
        double Vhxreg = regeff.getVhx();
        double Lhreg = regeff.getLh();
        double Lcreg = regeff.getLc();
        double L3reg = regeff.getL3();
        double DPhreg = regeff.getDPh();
        double DPcreg = regeff.getDPc();
        
        k = Double.parseDouble(ctrPrincipal.getViewPrincipal().getTrocadores().getFieldCondK().getText());
        
        ControlConeff coneff = new ControlConeff(T4, T3, P3, P4, m, Qcon, k,core1 ,session);
        double ATcon = coneff.getAT();
        double Ahocon = coneff.getAho();
        double Acocon = coneff.getAco();
        double Vhxcon = coneff.getVhx();
        double Lhcon = coneff.getLh();
        double Lccon = coneff.getLc();
        double L3con = coneff.getL3();
        double DPhcon = coneff.getDPh();
        double DPccon = coneff.getDPc();

        double AT = ATcon+ATreg+ATevp;
        
        ViewSaida saida = new ViewSaida(ctrPrincipal);
        
        saida.getTxtWt().setText(round(Wt, 3)+" kW");
        saida.getTxtWb().setText(round(Wb, 3)+" kW");
        saida.getTxtWn().setText(round(Wn, 3)+" kW");
        saida.getTxtQevp().setText(round(Qevp, 3)+" kJ/s");
        saida.getTxtQcon().setText(round(Qcon, 3)+" kJ/s");
        saida.getTxtEc().setText(round(ec, 3)+" %");
        saida.getTxtQreg().setText(round(Qreg, 3)+" kJ/s");
        saida.getTxtAt().setText(round(AT, 3)+"  m²");
        saida.getTxtATreg().setText(round(ATreg, 3)+" m²");
        saida.getTxtATcon().setText(round(ATcon, 3)+" m²");
        saida.getTxtATevp().setText(round(ATevp, 3)+" m²");
        
    }
    
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
