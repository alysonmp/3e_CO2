/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.View;

import Ciclo3.Control.ControlPrincipal;
import Ciclo3.Control.Start;
import Control.Conversao.ControlConverte;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author alysonmp
 */
public class ViewPrincipal extends JFrame{
    
    //BUTTON
    private JButton btnCalcular = new JButton("Calcular");
            
    //Panel
    ViewFonteCalor fonteCalor;
    ViewDadosOperacionais dadosOp;
    ViewTrocadores trocadores;
    
    private JTabbedPane tabbedPane = new JTabbedPane();
    
    JPanel painelDados = new JPanel(new GridBagLayout());
    
    private JFrame frameEspera;
    
    private int comp = 1, FON = 1;
    
    private ControlConverte controlConverte;
    
    public ViewPrincipal(ControlPrincipal ctrPrincipal) {
        controlConverte = new ControlConverte();
        
        this.setLayout(new FlowLayout());
        
        fonteCalor = new ViewFonteCalor(ctrPrincipal);
        tabbedPane.add("Fonte de Calor", fonteCalor);
        
        dadosOp = new ViewDadosOperacionais(ctrPrincipal);
        tabbedPane.add("Dados Operacionais", dadosOp);
        
        trocadores = new ViewTrocadores();
        tabbedPane.add("Trocadores de Calor", trocadores);

        GridBagConstraints g = new GridBagConstraints();
        
        //PAINEL MAIOR
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.insets = new Insets(0, 0, 0, 0);
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(tabbedPane,g);
        
        g.gridx = 2;
        g.gridy = 3;
        g.weightx = 0.5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(btnCalcular,g);
     
        this.add(painelDados);
        
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

    public JPanel getPainelDados() {
        return painelDados;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getFON() {
        return FON;
    }

    public void setFON(int FON) {
        this.FON = FON;
    }

    public ViewFonteCalor getFonteCalor() {
            return fonteCalor;
    }

    public void setFonteCalor(ViewFonteCalor fonteCalor) {
            this.fonteCalor = fonteCalor;
    }

    public ViewDadosOperacionais getDadosOp() {
            return dadosOp;
    }

    public void setDadosOp(ViewDadosOperacionais dadosOp) {
            this.dadosOp = dadosOp;
    }

    public ViewTrocadores getTrocadores() {
            return trocadores;
    }

    public void setTrocadores(ViewTrocadores trocadores) {
            this.trocadores = trocadores;
    }
}
