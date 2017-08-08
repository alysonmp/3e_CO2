/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control;

import Ciclo3.Control.Interpolacao.ControlAr;
import Ciclo3.Control.Interpolacao.ControlCompressor;
import Ciclo3.Control.Interpolacao.ControlCompressor5;
import Ciclo3.Control.Interpolacao.ControlInterpolacao;
import Ciclo3.Dao.ControlConexao;
import Ciclo3.Model.ModelAir;
import Ciclo3.Model.ModelCompressor5;
import Ciclo3.Model.ModelConsEXE_A;
import Ciclo3.Model.ModelConsEXE_B;
import Ciclo3.Model.ModelConstantesMat;
import Ciclo3.Model.ModelCore;
import Ciclo3.Model.ModelFluidos;
import Ciclo3.Model.ModelQfpso;
import Ciclo3.Util.HibernateUtil;
import Ciclo3.View.ViewPrincipal;
import Ciclo3.Model.ModelCO2;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author alysonmp
 */
public class ControlPrincipal {
    private ViewPrincipal viewPrincipal;
    private Session session;
    private ControlConexao conexao;
    
    @SuppressWarnings("empty-statement")
    public ControlPrincipal(){
        conexao = new ControlConexao();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        this.session = sf.openSession();
        
        Criteria cr = this.session.createCriteria(ModelFluidos.class);
        List results = cr.list();
        if(results.isEmpty()){
            Transaction tx = session.beginTransaction();

            session.save(new ModelFluidos(1, "Agua"));
            session.save(new ModelFluidos(2, "Toluene (C7H8)"));
            session.save(new ModelFluidos(3, "Ethylbenzene (C8H10)"));
            session.save(new ModelFluidos(4, "Propylbenzene (C9H12)"));
            session.save(new ModelFluidos(5, "Dimetylbenzene(C8H10)"));
            session.save(new ModelFluidos(6, "Octamethyltrisiloxane (MDM)"));
            session.save(new ModelFluidos(7, "Decamethyltetrasiloxane (MD2M)"));
            session.save(new ModelFluidos(8, "Dodecamethylpentasiloxane (MD3M)"));
            session.save(new ModelFluidos(9, "Dodecamethylcyclohexasiloxane (D6)"));
            session.save(new ModelFluidos(10, "Isobutano"));
            session.save(new ModelFluidos(11, "Isopentano"));
            session.save(new ModelFluidos(12, "n-Butano"));
            session.save(new ModelFluidos(13, "n-Pentano"));
            session.save(new ModelFluidos(14, "R-134a"));
            session.save(new ModelFluidos(15, "R-290"));
            session.save(new ModelFluidos(16, "R-142b"));
            session.save(new ModelFluidos(17, "R-1270"));
            session.save(new ModelFluidos(18, "R-40"));
            session.save(new ModelFluidos(19, "Hexamethyldisiloxane, (MM)"));
            session.save(new ModelFluidos(20, "Tetradecamethylhexasiloxane (MD4M)"));
            session.save(new ModelFluidos(21, "Octamethylcyclotetrasiloxane (D4)"));
            session.save(new ModelFluidos(22, "Decamethylcyclopentasiloxane (D5)"));
            session.save(new ModelFluidos(23, "R11"));
            session.save(new ModelFluidos(24, "R12"));
            session.save(new ModelFluidos(25, "R216ca"));
            session.save(new ModelFluidos(26, "R114"));
            session.save(new ModelFluidos(27, "R141b"));
            session.save(new ModelFluidos(28, "R152a"));
            session.save(new ModelFluidos(29, "R142b"));
            session.save(new ModelFluidos(30, "n-Hexane"));
            session.save(new ModelFluidos(31, "n-Heptane"));
            session.save(new ModelFluidos(32, "n-octane"));
            session.save(new ModelFluidos(33, "R22"));

            tx.commit();
        }
        
        cr = this.session.createCriteria(ModelConstantesMat.class);
        results = cr.list();
        if(results.isEmpty()){
            Transaction tx = session.beginTransaction();

            double[][] valoresC1 = {{35.0187, -1511.9, -11.335, 0.0093383, 7.7626E-10}};
            session.save(new ModelConstantesMat("C1", valoresC1));
            
            double[][] valoresC2 = {{73.649, -7258.2, -7.3037, 4.1653E-6, 2.0}};
            session.save(new ModelConstantesMat("C2", valoresC2));

            double[][] valoresCC = {{27.437, 0.042315, -1.9555E-5, 3.9968E-9, -2.9872E-13, 0.0, 0.0}};
            session.save(new ModelConstantesMat("CC", valoresCC));
            
            tx.commit();
        }
        
        cr = this.session.createCriteria(ModelQfpso.class);
        results = cr.list();
        if(results.isEmpty()){
            Transaction tx = session.beginTransaction();
            
            session.save(new ModelQfpso(new double[]{-5.0989335E-5, 66.250549, -0.024244439, 1.4359252E-5, -3.1287299E-9, 2.7938016E-10, 3.1938551E-11, -8.6058944E-13, 1.0138024E-14, -5.7854786E-17, 1.3108388E-19, 0.0}));
            session.save(new ModelQfpso(new double[]{-7.6520373E-5, 68.996718, -0.015276822, 4.4519632E-5, 3.2384275E-7, -9.5543181E-9, 3.1876871E-10, -5.3692223E-12, 5.1514164E-14, -2.6095697E-16, 5.4962813E-19, 0.0}));
            session.save(new ModelQfpso(new double[]{7.875352E-5, -0.04698885, 77.569376, 7.5518527E-4, -3.6218838, -4.6157586E-6, 0.05773998, 8.645757E-9, -3.5076036E-4, 3.2457331E-12, 6.9448062E-7, 0.0}));
            session.save(new ModelQfpso(new double[]{-0.0025970942, 99.279743, 0.12007259, 8.0406572E-4, -1.1669656E-5, 4.9266436E-7, -1.169747E-8, 1.6715254E-10, -1.5243562E-12, 7.6811009E-15, -1.5862785E-17, 0.0}));
            session.save(new ModelQfpso(new double[]{1.5353958, 162.37375, 0.11646613, -0.0086520143, 1.3170876E-4, 5.772981E-6, -3.037534E-7, 6.0681698E-9, -6.2711257E-11, 3.3117369E-13, -7.0587574E-16, 0.0}));
  
            tx.commit();
        }
        
        cr = this.session.createCriteria(ModelConsEXE_A.class);
        results = cr.list();
        if(results.isEmpty()){
           Transaction tx = session.beginTransaction();
            
           session.save(new ModelConsEXE_A(new double[]{10.57,1.05,2.06,-3936.0}));
           session.save(new ModelConsEXE_A(new double[]{7.3,1.23,0.0,-2286.0}));
           session.save(new ModelConsEXE_A(new double[]{11.4,0.94,1.84,-3992.0}));
           session.save(new ModelConsEXE_A(new double[]{7.16,0.5,0.4,-2313.0}));
           session.save(new ModelConsEXE_A(new double[]{6.83,0.45,0.12,-2127.0}));
           session.save(new ModelConsEXE_A(new double[]{6.79,0.49,0.11,-2105.0}));
           session.save(new ModelConsEXE_A(new double[]{7.3,1.23,0.0,-5379.0}));
           session.save(new ModelConsEXE_A(new double[]{7.03,0.46,0.14,-2184.0}));

           tx.commit();
        }
        
        cr = this.session.createCriteria(ModelConsEXE_B.class);
        results = cr.list();
        if(results.isEmpty()){
           Transaction tx = session.beginTransaction();
            
           session.save(new ModelConsEXE_B(new double[]{20140,11710,303500,3970,720,275430,238490,89040}));

           tx.commit();
        }
        
        ControlCompressor comp = new ControlCompressor(session);
        comp.criaTabelaCompressor();
        
        ControlCompressor5 comp5 = new ControlCompressor5(session);
        comp5.criaTabelaCompressor5();
        
        ControlAr ar = new ControlAr(session);
        ar.criaTabelaAr();
        
        ControlInterpolacao interpolacao = new ControlInterpolacao(session);
        interpolacao.criaTabelaCO2();
        
        cr = this.session.createCriteria(ModelCore.class);
        results = cr.list();
        Transaction tx = session.beginTransaction();
        
        if(results.isEmpty()){
            String csvFile = "/Ciclo3/Csv/core.csv";
            InputStream is = getClass().getResourceAsStream(csvFile);
            
            BufferedReader br = null;
            String line = "";
            String csvSplitBy = ";";
            
            try{
                br = new BufferedReader(new InputStreamReader(is));
                while((line = br.readLine()) != null){
                    String[] table_c = line.split(csvSplitBy);
                    this.session.save(new ModelCore(Double.parseDouble(table_c[0]),Double.parseDouble(table_c[1]),Double.parseDouble(table_c[2]),Double.parseDouble(table_c[3]),Double.parseDouble(table_c[4]),Double.parseDouble(table_c[5]),Double.parseDouble(table_c[6]),Double.parseDouble(table_c[7]),Double.parseDouble(table_c[8]),Double.parseDouble(table_c[9])));
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        tx.commit();
        
        viewPrincipal = new ViewPrincipal(this);
        viewPrincipal.setResizable(false);
        viewPrincipal.setLocation(100, 100);
        viewPrincipal.pack();
        viewPrincipal.setTitle("CO2");
        viewPrincipal.setVisible(true);
        viewPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    conexao.getConn().close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
    }
 
    public ViewPrincipal getViewPrincipal() {
        return viewPrincipal;
    }

    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}


