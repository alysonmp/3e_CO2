package Ciclo3.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Ciclo3.Control.ControlPrincipal;

public class ViewDadosOperacionais extends JPanel{

	//LABELS
    private JLabel lblP1 = new JLabel("<html>P<sub>1</sub></html>");
    private JLabel lblT1 = new JLabel("<html>T<sub>1</sub></html>");
    private JLabel lblTeff = new JLabel("<html>&eta;<sub>T</sub></html>");
    private JLabel lblBeff = new JLabel("<html>&eta;<sub>B</sub></html>");
    private JLabel lblPconop = new JLabel("<html>P<sub>conop</sub></html>");
    private JLabel lblTconop = new JLabel("<html>T<sub>conop</sub></html>");
    private JLabel lblDtt = new JLabel("DTT");
	
    //FIELDS
    private JTextField txtP1 = new JTextField("0.0", 10);
    private JTextField txtT1 = new JTextField("0.0");
    private JTextField txtPconop = new JTextField("0.0");
    private JTextField txtTconop = new JTextField("0.0");
    private JTextField txtBeff = new JTextField("0.0");
    private JTextField txtTeff = new JTextField("0.0");
    private JTextField txtDtt = new JTextField("0.0");
    
    //COMBO
    private String[] pressoes = {"kPa", "bar", "atm"};
    private String[] temps = {"K", "°C", "°F"};
    
    private JComboBox comboPconop = new JComboBox(pressoes);
    private JComboBox comboT1 = new JComboBox(temps);
    private JComboBox comboP1 = new JComboBox(pressoes);
    private JComboBox comboTconop = new JComboBox(temps);
    
    public ViewDadosOperacionais(ControlPrincipal ctrlPrincipal) {

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        GridBagConstraints g = new GridBagConstraints();

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblT1,g);

        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtT1,g);

        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboT1,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblP1,g);

        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtP1,g);
        
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboP1,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblPconop,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtPconop,g);
        
        g.gridx = 3;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboPconop,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTconop,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTconop,g);
        
        g.gridx = 3;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboTconop,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblBeff,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtBeff,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTeff,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTeff,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblDtt,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtDtt,g);
        
        comboT1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtT1.getText();
                    if(!valor.isEmpty()){
                        txtT1.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboPconop.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtPconop.getText();
                    if(!valor.isEmpty()){
                        txtPconop.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboTconop.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtTconop.getText();
                    if(!valor.isEmpty()){
                        txtTconop.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
		
        comboP1.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtP1.getText();
                    if(!valor.isEmpty()){
                        txtP1.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
	}
	
	public JLabel getLblP1() {
        return lblP1;
    }

    public JLabel getLblPconop() {
        return lblPconop;
    }
    
    public JTextField getTxtP1() {
        return txtP1;
    }

    public JTextField getTxtPconop() {
        return txtPconop;
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
    
    public JComboBox getComboPconop() {
        return comboPconop;
    }

    public void setComboPconop(JComboBox comboPconop) {
        this.comboPconop = comboPconop;
    }
    
    public JComboBox getComboP1() {
        return comboP1;
    }

    public void setComboP1(JComboBox comboP1) {
        this.comboP1 = comboP1;
    }

    public JComboBox getComboTconop() {
        return comboTconop;
    }

    public void setComboTconop(JComboBox comboTconop) {
        this.comboTconop = comboTconop;
    }
    
    public JComboBox getComboT1() {
        return comboT1;
    }

    public void setComboT1(JComboBox comboT1) {
        this.comboT1 = comboT1;
    }

    public JTextField getTxtTconop() {
        return txtTconop;
    }

    public void setTxtTconop(JTextField txtTconop) {
        this.txtTconop = txtTconop;
    }
}
