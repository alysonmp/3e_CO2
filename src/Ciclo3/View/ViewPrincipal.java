/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.View;

import Ciclo3.Control.ControlPrincipal;
import Ciclo3.Control.Start;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author alysonmp
 */
public class ViewPrincipal extends JFrame{

    //Labels
    //ENTRADA
    private JLabel lblP1 = new JLabel("P1: ");
    private JLabel lblT1 = new JLabel("T1: ");
    private JLabel lblPconop = new JLabel("Pconop: ");
    private JLabel lblTconop = new JLabel("Tconop: ");
    private JLabel lblTf = new JLabel("Tf: ");
    private JLabel lblPf = new JLabel("Pf: ");
    private JLabel lblMf = new JLabel("Mf: ");
    private JLabel lblTres = new JLabel("Tres: ");
    private JLabel lblcompressor = new JLabel("Compressor: ");
    private JLabel lbleff = new JLabel("eff: ");
    private JLabel lblBeff = new JLabel("Beff: ");
    private JLabel lblTeff = new JLabel("Teff: ");
    private JLabel lblDtt = new JLabel("DTT: ");
    
    //SAIDA
    private JLabel lblteste1 = new JLabel("teste1: ");
    private JLabel lblteste2 = new JLabel("teste2: ");
    private JLabel lblteste3 = new JLabel("teste3: ");
    private JLabel lblteste4 = new JLabel("teste4: ");
    private JLabel lblteste5 = new JLabel("teste5: ");
    private JLabel lblteste6 = new JLabel("teste6: ");
    private JLabel lblteste7 = new JLabel("teste7: ");
    private JLabel lblteste8 = new JLabel("teste8: ");
    private JLabel lblteste9 = new JLabel("teste9: ");
    private JLabel lblteste10 = new JLabel("teste10: ");
    
    //TextFields
    //ENTRADA
    private JTextField txtP1 = new JTextField("150", 20);
    private JTextField txtT1 = new JTextField("433.15");
    private JTextField txtPconop = new JTextField("80");
    private JTextField txtTconop = new JTextField("313.15");
    private JTextField txtTf = new JTextField("408.35");
    private JTextField txtPf = new JTextField("408.35");
    private JTextField txtMf = new JTextField("408.35");
    private JTextField txtTres = new JTextField("298.15");
    private JTextField txteff = new JTextField("0.0");
    private JTextField txtBeff = new JTextField("0.8");
    private JTextField txtTeff = new JTextField("0.8");
    private JTextField txtDtt = new JTextField("0.8");
    
    //SAIDA
    private JTextField txtteste1 = new JTextField(20);
    private JTextField txtteste2 = new JTextField();
    private JTextField txtteste3 = new JTextField();
    private JTextField txtteste4 = new JTextField();
    private JTextField txtteste5 = new JTextField();
    private JTextField txtteste6 = new JTextField();
    private JTextField txtteste7 = new JTextField();
    private JTextField txtteste8 = new JTextField();
    private JTextField txtteste9 = new JTextField();
    private JTextField txtteste10 = new JTextField();
    
    private String[] fluidos = {"Água", "Ar", "Compressor 1", "Compressor 2", "Compressor 3", "Compressor 4", "Compressor 5", "Gases"};
    private JComboBox cmbCompressor = new JComboBox(fluidos);
    
    //BUTTON
    private JButton btnCalcular = new JButton("Calcular");
            
    //Panel
    private JPanel painelEntrada = new JPanel(new GridBagLayout());
    private JPanel painelSaida = new JPanel(new GridBagLayout());
    private JPanel painelDados = new JPanel(new GridBagLayout());
    
    private JFrame frameEspera;
    
    private int comp = 0;
    
    public ViewPrincipal(ControlPrincipal ctrPrincipal) {
        this.setLayout(new FlowLayout());
        
        painelEntrada.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Saída", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        GridBagConstraints g = new GridBagConstraints();
       
        //PANEL ENTRADA
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblcompressor,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(cmbCompressor,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblPconop,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtPconop,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTconop,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTconop,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTf,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTf,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblPf,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtPf,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblMf,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtMf,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTres,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTres,g);
        
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblP1,g);

        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtP1,g);

        g.gridx = 0;
        g.gridy = 8;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblT1,g);

        g.gridx = 1;
        g.gridy = 8;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtT1,g);
        
        g.gridx = 0;
        g.gridy = 9;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lbleff,g);
        
        g.gridx = 1;
        g.gridy = 9;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txteff,g);
        
        g.gridx = 0;
        g.gridy = 10;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblBeff,g);
        
        g.gridx = 1;
        g.gridy = 10;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtBeff,g);
        
        g.gridx = 0;
        g.gridy = 11;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblTeff,g);
        
        g.gridx = 1;
        g.gridy = 11;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtTeff,g);
            
        g.gridx = 0;
        g.gridy = 12;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(lblDtt,g);
        
        g.gridx = 1;
        g.gridy = 12;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelEntrada.add(txtDtt,g);
        
        //PAINEL SAIDA
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste1,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste1,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste2,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste2,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste3,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste3,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste4,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste4,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste5,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste5,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste6,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste6,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste7,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste7,g);
        
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste8,g);
        
        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste8,g);
        
        g.gridx = 0;
        g.gridy = 8;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste9,g);
        
        g.gridx = 1;
        g.gridy = 8;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste9,g);
        
        g.gridx = 0;
        g.gridy = 9;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblteste10,g);
        
        g.gridx = 1;
        g.gridy = 9;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtteste10,g);
        
        //PAINEL MAIOR
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(painelEntrada,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(painelSaida,g);
        
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(btnCalcular,g);
     
        this.add(painelDados);
        
        cmbCompressor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int FON = cmbCompressor.getSelectedIndex()+1;
                switch(FON){
                    case 3:
                        comp = 1;
                        txtMf.setText(55.54+"");
                        txtTf.setText(415.15+"");
                        txtPf.setText(1144.4+"");
                        break;
                    case 4:
                        comp = 2;
                        txtMf.setText(55.54+"");
                        txtTf.setText(417.15+"");
                        txtPf.setText(3133.8+"");
                        break;
                    case 5: 
                        comp = 3;
                        txtMf.setText(55.54+"");
                        txtTf.setText(418.15+"");
                        txtPf.setText(8825.6+"");
                        break;
                    case 6:
                        comp = 4;
                        txtMf.setText(55.54+"");
                        txtTf.setText(408.15+"");
                        txtPf.setText(25109+"");
                        break;
                    case 7: 
                        comp = 5;
                        txtMf.setText(61.78+"");
                        txtTf.setText(431.15+"");
                        txtPf.setText(8196.1+"");
                        break;
                    default:
                        comp = 0;
                        break;
                }
            }
        });
        
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Thread t1 = new Thread(new Runnable() {
                @Override
                    public void run() {
                        Start start = new Start(ctrPrincipal.getSession(), ctrPrincipal);
                    }
                });
                t1.start();
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {    
                        try {
                            ctrPrincipal.getViewPrincipal().setEnabled(false);
                            ViewEspera painelEspera = new ViewEspera(ctrPrincipal);

                            frameEspera = new JFrame();
                            frameEspera.setUndecorated(true);
                            frameEspera.add(painelEspera.getPanelEspera());
                            frameEspera.setAlwaysOnTop(true);
                            frameEspera.setBounds((ctrPrincipal.getViewPrincipal().getWidth()/2)-100, (ctrPrincipal.getViewPrincipal().getHeight()/2)-50, 200, 100);
                            frameEspera.setResizable(false);
                            frameEspera.setVisible(true);
                            frameEspera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameEspera.revalidate();
                            frameEspera.repaint();

                            t1.join();
                            
                            frameEspera.dispose();
                            ctrPrincipal.getViewPrincipal().setEnabled(true);
                        }catch(InterruptedException ex){
                            
                        }
                    }
                }).start();
            }
        });
    }

    public JLabel getLblP1() {
        return lblP1;
    }

    public JLabel getLblPconop() {
        return lblPconop;
    }

    public JLabel getLblTf() {
        return lblTf;
    }

    public JLabel getLblTres() {
        return lblTres;
    }

    public JLabel getLblcompressor() {
        return lblcompressor;
    }

    public JLabel getLbleff() {
        return lbleff;
    }
    
    public JLabel getLblteste1() {
        return lblteste1;
    }

    public JLabel getLblteste2() {
        return lblteste2;
    }

    public JLabel getLblteste3() {
        return lblteste3;
    }

    public JTextField getTxtP1() {
        return txtP1;
    }

    public JTextField getTxtPconop() {
        return txtPconop;
    }

    public JTextField getTxtTf() {
        return txtTf;
    }

    public JTextField getTxtTres() {
        return txtTres;
    }

    public JTextField getTxteff() {
        return txteff;
    }

    public JTextField getTxtteste1() {
        return txtteste1;
    }

    public JTextField getTxtteste2() {
        return txtteste2;
    }

    public JTextField getTxtteste3() {
        return txtteste3;
    }

    public JPanel getPainelEntrada() {
        return painelEntrada;
    }

    public JPanel getPainelSaida() {
        return painelSaida;
    }

    public JPanel getPainelDados() {
        return painelDados;
    }

    public JLabel getLblBeff() {
        return lblBeff;
    }

    public void setLblBeff(JLabel lblBeff) {
        this.lblBeff = lblBeff;
    }

    public JLabel getLblTeff() {
        return lblTeff;
    }

    public void setLblTeff(JLabel lblTeff) {
        this.lblTeff = lblTeff;
    }

    public JLabel getLblteste4() {
        return lblteste4;
    }

    public void setLblteste4(JLabel lblteste4) {
        this.lblteste4 = lblteste4;
    }

    public JLabel getLblteste5() {
        return lblteste5;
    }

    public void setLblteste5(JLabel lblteste5) {
        this.lblteste5 = lblteste5;
    }

    public JTextField getTxtBeff() {
        return txtBeff;
    }

    public void setTxtBeff(JTextField txtBeff) {
        this.txtBeff = txtBeff;
    }

    public JTextField getTxtTeff() {
        return txtTeff;
    }

    public void setTxtTeff(JTextField txtTeff) {
        this.txtTeff = txtTeff;
    }

    public JTextField getTxtteste4() {
        return txtteste4;
    }

    public void setTxtteste4(JTextField txtteste4) {
        this.txtteste4 = txtteste4;
    }

    public JTextField getTxtteste5() {
        return txtteste5;
    }

    public void setTxtteste5(JTextField txtteste5) {
        this.txtteste5 = txtteste5;
    }

    public JLabel getLblteste6() {
        return lblteste6;
    }

    public void setLblteste6(JLabel lblteste6) {
        this.lblteste6 = lblteste6;
    }

    public JLabel getLblteste7() {
        return lblteste7;
    }

    public void setLblteste7(JLabel lblteste7) {
        this.lblteste7 = lblteste7;
    }

    public JLabel getLblteste8() {
        return lblteste8;
    }

    public void setLblteste8(JLabel lblteste8) {
        this.lblteste8 = lblteste8;
    }

    public JTextField getTxtteste6() {
        return txtteste6;
    }

    public void setTxtteste6(JTextField txtteste6) {
        this.txtteste6 = txtteste6;
    }

    public JTextField getTxtteste7() {
        return txtteste7;
    }

    public void setTxtteste7(JTextField txtteste7) {
        this.txtteste7 = txtteste7;
    }

    public JTextField getTxtteste8() {
        return txtteste8;
    }

    public void setTxtteste8(JTextField txtteste8) {
        this.txtteste8 = txtteste8;
    }

    public JLabel getLblteste9() {
        return lblteste9;
    }

    public void setLblteste9(JLabel lblteste9) {
        this.lblteste9 = lblteste9;
    }

    public JLabel getLblteste10() {
        return lblteste10;
    }

    public void setLblteste10(JLabel lblteste10) {
        this.lblteste10 = lblteste10;
    }

    public JTextField getTxtteste9() {
        return txtteste9;
    }

    public void setTxtteste9(JTextField txtteste9) {
        this.txtteste9 = txtteste9;
    }

    public JTextField getTxtteste10() {
        return txtteste10;
    }

    public void setTxtteste10(JTextField txtteste10) {
        this.txtteste10 = txtteste10;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public JTextField getTxtDtt() {
        return txtDtt;
    }

    public void setTxtDtt(JTextField txtDtt) {
        this.txtDtt = txtDtt;
    }

    public JTextField getTxtT1() {
        return txtT1;
    }

    public void setTxtT1(JTextField txtT1) {
        this.txtT1 = txtT1;
    }

    public JTextField getTxtPf() {
        return txtPf;
    }

    public void setTxtPf(JTextField txtPf) {
        this.txtPf = txtPf;
    }

    public JTextField getTxtMf() {
        return txtMf;
    }

    public void setTxtMf(JTextField txtMf) {
        this.txtMf = txtMf;
    }
    
}
