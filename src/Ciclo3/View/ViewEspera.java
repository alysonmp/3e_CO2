/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.View;

import Ciclo3.Control.ControlPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author leonardo
 */
public class ViewEspera{
    
    private BufferedImage imagem;
    private JLabel labelEspera;
    private JPanel panelEspera;
    private JLabel labelMensagem;
    
    public ViewEspera(ControlPrincipal ctrlPrincipal) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Ciclo3/Images/loading4.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        
        labelEspera = new JLabel(imageIcon);
        panelEspera = new JPanel();
        labelMensagem = new JLabel("Realizando cálculos   ");
        
        panelEspera.setLayout(new BorderLayout());
        
        panelEspera.add(labelEspera, BorderLayout.CENTER);
        panelEspera.add(labelMensagem, BorderLayout.EAST);
        panelEspera.setSize(200,100);
        panelEspera.setBackground(new Color(255,255,255));  
        panelEspera.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public JPanel getPanelEspera() {
        return panelEspera;
    }

    public void setPanelEspera(JPanel panelEspera) {
        this.panelEspera = panelEspera;
    }
}
