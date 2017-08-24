package Ciclo3.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Ciclo3.Control.ControlPrincipal;

public class ViewFonteCalor extends JPanel{

    //LABELS
    private JLabel lblTf = new JLabel("<html>T<sub>f</sub></html>");
    private JLabel lblPf = new JLabel("<html>P<sub>f</sub></html>");
    private JLabel lblMf = new JLabel("<html>&#7745;<sub>f</sub></html>");
    private JLabel lblcompressor = new JLabel("Compressor: ");
	
    //FIELDS
    private JTextField txtTf = new JTextField("415.15");
    private JTextField txtPf = new JTextField("1144.40");
    private JTextField txtMf = new JTextField("55.54");

    //COMBOS
    private String[] pressoes = {"kPa", "bar", "atm"};
    private String[] temps = {"K", "°C", "°F"};
    
    private JComboBox comboTf = new JComboBox(temps);
    private JComboBox comboPf = new JComboBox(pressoes);
    
    private String[] fluidos = {"Compressor 1", "Compressor 2", "Compressor 3", "Compressor 4", "Compressor 5"};
    private JComboBox comboCompressor = new JComboBox(fluidos);
    
    int FON = 1;
    int comp = 1;
    
    public ViewFonteCalor(ControlPrincipal ctrlPrincipal) {

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Entrada", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        GridBagConstraints g = new GridBagConstraints();

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblcompressor,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboCompressor,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTf,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtTf,g);
        
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboTf,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblPf,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtPf,g);
        
        g.gridx = 3;
        g.gridy = 2;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboPf,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblMf,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtMf,g);
        
        txtMf.setEnabled(false);
        txtTf.setEnabled(false);
        txtPf.setEnabled(false);
        
        comboCompressor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FON = comboCompressor.getSelectedIndex()+1;
                switch(FON){
                    case 1:
                        comp = 1;
                        txtMf.setText(55.54+"");
                        txtTf.setText(415.15+"");
                        txtPf.setText(1144.4+"");
                        break;
                    case 2:
                        comp = 2;
                        txtMf.setText(55.54+"");
                        txtTf.setText(417.15+"");
                        txtPf.setText(3133.8+"");
                        break;
                    case 3: 
                        comp = 3;
                        txtMf.setText(55.54+"");
                        txtTf.setText(418.15+"");
                        txtPf.setText(8825.6+"");
                        break;
                    case 4:
                        comp = 4;
                        txtMf.setText(55.54+"");
                        txtTf.setText(408.15+"");
                        txtPf.setText(25109+"");
                        break;
                    case 5: 
                        comp = 5;
                        txtMf.setText(61.78+"");
                        txtTf.setText(431.15+"");
                        txtPf.setText(8196.1+"");
                        break;
                    default:
                        comp = 0;
                        break;
                }
                
                if(comp != 0){
                    txtMf.setEnabled(false);
                    txtTf.setEnabled(false);
                    txtPf.setEnabled(false);
                }else{
                    txtMf.setEnabled(true);
                    txtTf.setEnabled(true);
                    txtPf.setEnabled(true);
                }
                
                ctrlPrincipal.getViewPrincipal().setComp(comp);
                ctrlPrincipal.getViewPrincipal().setFON(FON);
            }
        });
        
comboTf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtTf.getText();
                    if(!valor.isEmpty()){
                        txtTf.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
        
        comboPf.addItemListener(new ItemListener() {
            
            String[] tipo = new String[2];
            int tip = 0;
            
            @Override
            public void itemStateChanged(ItemEvent ie) {
                
                tipo[tip] = ie.getItem().toString();
                tip++;
                if(tip == 2){
                    String valor = txtPf.getText();
                    if(!valor.isEmpty()){
                        txtPf.setText(String.valueOf(ctrlPrincipal.getControlConverte().converte(tipo[0],tipo[1],Double.parseDouble(valor))));
                    } 
                    tip = 0;
                }
            }
        });
	}
	
	public JLabel getLblTf() {
        return lblTf;
    }

    public JLabel getLblcompressor() {
        return lblcompressor;
    }
    
    public JTextField getTxtTf() {
        return txtTf;
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
    
    public JComboBox getComboPf() {
        return comboPf;
    }

    public void setComboPf(JComboBox comboPf) {
        this.comboPf = comboPf;
    }

    public JComboBox getComboTf() {
        return comboTf;
    }

    public void setComboTf(JComboBox comboTf) {
        this.comboTf = comboTf;
    }
}
