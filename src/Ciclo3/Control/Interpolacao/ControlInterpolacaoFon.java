/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control.Interpolacao;

import org.hibernate.Session;

/**
 *
 * @author alysonmp
 */
public class ControlInterpolacaoFon {
    
    Session session;
    private double Cpv, Prv, Vcv, Muv, kv, Df;
    private Object gas;
    
    public ControlInterpolacaoFon(int FON, double pressao, double temp, Session session){
        
        switch(FON){
            /*case 2:
                gas = new ControlAr(session);
                ((ControlAr)gas).interpolacao(pressao, temp);
                Cpv = ((ControlAr)gas).getCpv();
                Prv = ((ControlAr)gas).getPrv();
                kv = ((ControlAr)gas).getKv();
                Muv = ((ControlAr)gas).getMuv();
                Vcv = ((ControlAr)gas).getVcv();
                Df = ((ControlAr)gas).getDf();
                break;*/
            
            case 1:
            case 2:
            case 3:
            case 4:
                gas = new ControlCompressor(session);
                ((ControlCompressor)gas).interpolacao(pressao, temp);
                Cpv = ((ControlCompressor)gas).getCpv();
                Prv = ((ControlCompressor)gas).getPrv();
                kv = ((ControlCompressor)gas).getKv();
                Muv = ((ControlCompressor)gas).getMuv();
                Vcv = ((ControlCompressor)gas).getVcv();
                Df = ((ControlCompressor)gas).getDf();
                break;
                
            case 5:
                gas = new ControlCompressor5(session);
                ((ControlCompressor5)gas).interpolacao(pressao, temp);
                Cpv = ((ControlCompressor5)gas).getCpv();
                Prv = ((ControlCompressor5)gas).getPrv();
                kv = ((ControlCompressor5)gas).getKv();
                Muv = ((ControlCompressor5)gas).getMuv();
                Vcv = ((ControlCompressor5)gas).getVcv();
                Df = ((ControlCompressor5)gas).getDf();
                break;
        }
    }

    public double getCpv() {
        return Cpv;
    }

    public void setCpv(double Cpv) {
        this.Cpv = Cpv;
    }

    public double getPrv() {
        return Prv;
    }

    public void setPrv(double Prv) {
        this.Prv = Prv;
    }

    public double getVcv() {
        return Vcv;
    }

    public void setVcv(double Vcv) {
        this.Vcv = Vcv;
    }

    public double getMuv() {
        return Muv;
    }

    public void setMuv(double Muv) {
        this.Muv = Muv;
    }

    public double getKv() {
        return kv;
    }

    public void setKv(double kv) {
        this.kv = kv;
    }

    public double getDf() {
        return Df;
    }

    public void setDf(double Df) {
        this.Df = Df;
    }
    
}
