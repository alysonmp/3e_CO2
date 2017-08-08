/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control.Interpolacao;

import Ciclo3.Model.ModelCO2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author alysonmp
 */
public class ControlInterpolacao {
 
    private ModelCO2 co21;
    private ModelCO2 co22;
    private ModelCO2 co23;
    private ModelCO2 co24;
    
    private double Cpv, kv, Muv, Prv, Vcv, Df;
    private double Cpv1, Cpv2, kv1, kv2, Muv1, Muv2, Prv1, Prv2, Vcv1, Vcv2, Df1, Df2;
    private Session session;
    
    public ControlInterpolacao(Session session){
        this.session = session;
    }
    
    public void criaTabelaCO2(){
        String csvFile = "/Ciclo3/Csv/CO2.csv";
        InputStream is = getClass().getResourceAsStream(csvFile);
        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        try {

            Criteria cr = this.session.createCriteria(ModelCO2.class);
            List results = cr.list();
            
            if(results.isEmpty()){
                br = new BufferedReader(new InputStreamReader(is));
                line = br.readLine();
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] compr_g = line.split(cvsSplitBy);
                    
                    session.save(new ModelCO2(Double.parseDouble(compr_g[0]), Double.parseDouble(compr_g[1]), Double.parseDouble(compr_g[2]), Double.parseDouble(compr_g[3]), Double.parseDouble(compr_g[4]), Double.parseDouble(compr_g[5]), Double.parseDouble(compr_g[6]), Double.parseDouble(compr_g[7])));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void interpolacao(double pressao, double temperatura){
        Criteria cr = session.createCriteria(ModelCO2.class);
        
        temperatura-=1;
        do{
            temperatura += 1;
            SQLQuery consulta = session.createSQLQuery("select * from CO2 where pressao <= " +pressao+ " and temperatura <= " +temperatura+ " ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelCO2.class));
            List<ModelCO2> CO2s = consulta.list();
            if(!CO2s.isEmpty())
                co21 = CO2s.get(0);

            consulta = session.createSQLQuery("select * from CO2 where pressao <= "+pressao+" and temperatura >= "+temperatura+" ORDER BY PRESSAO DESC, TEMPERATURA ASC FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelCO2.class));
            CO2s = consulta.list();
            if(!CO2s.isEmpty())
                co22 = CO2s.get(0);

            consulta = session.createSQLQuery("select * from CO2 where pressao >= "+pressao+" and temperatura <= "+temperatura+" ORDER BY PRESSAO ASC, TEMPERATURA DESC");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelCO2.class));
            CO2s = consulta.list();
            if(!CO2s.isEmpty())
                co23 = CO2s.get(0);

            consulta = session.createSQLQuery("select * from CO2 where pressao >= " +pressao+ " and temperatura >= " +temperatura+ " FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelCO2.class));
            CO2s = consulta.list();
            if(!CO2s.isEmpty())
                co24 = CO2s.get(0);

            
        }while(co21 == null || co22 == null || co23 == null || co24 == null);
        
        double p = ((pressao - co21.getPRESSAO())/(co23.getPRESSAO() - co21.getPRESSAO()));
        double t1 = ((temperatura - co21.getTEMPERATURA())/(co22.getTEMPERATURA() - co21.getTEMPERATURA()));
        double t2 = ((temperatura - co23.getTEMPERATURA())/(co24.getTEMPERATURA() - co23.getTEMPERATURA()));

        Cpv2 = co21.getCPV() + (co22.getCPV() - co21.getCPV()) * t1;
        Cpv2 = co23.getCPV() + (co24.getCPV() - co23.getCPV()) * t2;
        Cpv = Cpv1 + (Cpv2 - Cpv1) * p;
        
        Prv1 = co21.getPRV() + (co22.getPRV() - co21.getPRV()) * t1;
        Prv2 = co23.getPRV() + (co24.getPRV() - co23.getPRV()) * t2;
        Prv = Prv1 + (Prv2 - Prv1) * p;
        
        kv1 = co21.getKV() + (co22.getKV() - co21.getKV()) * t1;
        kv2 = co23.getKV() + (co24.getKV() - co23.getKV()) * t2;
        kv = kv1 + (kv2 - kv1) * p;
        
        Muv1 = co21.getMUV() + (co22.getMUV() - co21.getMUV()) * t1;
        Muv2 = co23.getMUV() + (co24.getMUV() - co23.getMUV()) * t2;
        Muv = Muv1 + (Muv2 - Muv1) * p;
        
        Vcv1 = co21.getVCV() + (co22.getVCV() - co21.getVCV()) * t1;
        Vcv2 = co23.getVCV() + (co24.getVCV() - co23.getVCV()) * t2;
        Vcv = Vcv1 + (Vcv2 - Vcv1) * p;
        
        Vcv1 = co21.getDFV() + (co22.getDFV() - co21.getDFV()) * t1;
        Vcv2 = co23.getDFV() + (co24.getDFV() - co23.getDFV()) * t2;
        Vcv = Vcv1 + (Vcv2 - Vcv1) * p;
    }

    public double getCpv() {
        return Cpv;
    }

    public void setCpv(double Cpv) {
        this.Cpv = Cpv;
    }

    public double getKv() {
        return kv;
    }

    public void setKv(double kv) {
        this.kv = kv;
    }

    public double getMuv() {
        return Muv;
    }

    public void setMuv(double Muv) {
        this.Muv = Muv;
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

    public double getDf() {
        return Df;
    }

    public void setDf(double Df) {
        this.Df = Df;
    }

}
