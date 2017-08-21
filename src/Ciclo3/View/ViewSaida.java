package Ciclo3.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ciclo3.Control.ControlPrincipal;

public class ViewSaida extends JFrame{

	//LABELS
	private JLabel lblWt = new JLabel("Wt: ");
    private JLabel lblWn = new JLabel("Wn: ");
    private JLabel lblWb = new JLabel("Wb: ");
    private JLabel lblQevp = new JLabel("Qevp: ");
    private JLabel lblQcon = new JLabel("Qcon: ");
    private JLabel lblEc = new JLabel("Ec: ");
    private JLabel lblQreg = new JLabel("Qreg: ");
	
    //FIELDS
    private JLabel txtWt = new JLabel("0");
    private JLabel txtWn = new JLabel("0");
    private JLabel txtWb = new JLabel("0");
    private JLabel txtQevp = new JLabel("0");
    private JLabel txtQcon = new JLabel("0");
    private JLabel txtEc = new JLabel("0%");
    private JLabel txtQreg = new JLabel("0");
    
    JPanel painelSaida = new JPanel();
    
	public ViewSaida(ControlPrincipal ctrlPrincipal) {
		painelSaida.setLayout(new GridBagLayout());
		painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Saída", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));
		
		GridBagConstraints g = new GridBagConstraints();
		
		g.anchor = GridBagConstraints.LINE_START;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWt,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWt,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWn,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWn,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWb,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWb,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQevp,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQevp,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQcon,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQcon,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblEc,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtEc,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQreg,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQreg,g);
        
        this.add(painelSaida);
        this.setBounds(200, 200, 300, 250);
        this.setTitle("Dados de Saída");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JLabel getTxtWt() {
        return txtWt;
    }

    public void setTxtWt(JLabel txtWt) {
        this.txtWt = txtWt;
    }

    public JLabel getTxtWn() {
        return txtWn;
    }

    public void setTxtWn(JLabel txtWn) {
        this.txtWn = txtWn;
    }

    public JLabel getTxtWb() {
        return txtWb;
    }

    public void setTxtWb(JLabel txtWb) {
        this.txtWb = txtWb;
    }

    public JLabel getTxtQevp() {
        return txtQevp;
    }

    public void setTxtQevp(JLabel txtQevp) {
        this.txtQevp = txtQevp;
    }

    public JLabel getTxtQcon() {
        return txtQcon;
    }

    public void setTxtQcon(JLabel txtQcon) {
        this.txtQcon = txtQcon;
    }

    public JLabel getTxtEc() {
        return txtEc;
    }

    public void setTxtEc(JLabel txtEc) {
        this.txtEc = txtEc;
    }

    public JLabel getTxtQreg() {
        return txtQreg;
    }

    public void setTxtQreg(JLabel txtQreg) {
        this.txtQreg = txtQreg;
    }
}
