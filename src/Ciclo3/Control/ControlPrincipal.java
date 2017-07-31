/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciclo3.Control;

import Ciclo3.Control.TabelaFluidos.ControlButanoGas;
import Ciclo3.Control.TabelaFluidos.ControlButanoLiquido;
import Ciclo3.Control.TabelaFluidos.ControlD4Gas;
import Ciclo3.Control.TabelaFluidos.ControlD4Liquido;
import Ciclo3.Control.TabelaFluidos.ControlD5Gas;
import Ciclo3.Control.TabelaFluidos.ControlD5Liquido;
import Ciclo3.Control.TabelaFluidos.ControlD6Gas;
import Ciclo3.Control.TabelaFluidos.ControlD6Liquido;
import Ciclo3.Control.TabelaFluidos.ControlHeptaneGas;
import Ciclo3.Control.TabelaFluidos.ControlHeptaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlHexaneGas;
import Ciclo3.Control.TabelaFluidos.ControlHexaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlIpentaneGas;
import Ciclo3.Control.TabelaFluidos.ControlIpentaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlIsobutanGas;
import Ciclo3.Control.TabelaFluidos.ControlIsobutanLiquido;
import Ciclo3.Control.TabelaFluidos.ControlMD2MGas;
import Ciclo3.Control.TabelaFluidos.ControlMD2MLiquido;
import Ciclo3.Control.TabelaFluidos.ControlMD3MGas;
import Ciclo3.Control.TabelaFluidos.ControlMD3MLiquido;
import Ciclo3.Control.TabelaFluidos.ControlMD4MGas;
import Ciclo3.Control.TabelaFluidos.ControlMD4MLiquido;
import Ciclo3.Control.TabelaFluidos.ControlMDMGas;
import Ciclo3.Control.TabelaFluidos.ControlMDMLiquido;
import Ciclo3.Control.TabelaFluidos.ControlMMGas;
import Ciclo3.Control.TabelaFluidos.ControlMMLiquido;
import Ciclo3.Control.TabelaFluidos.ControlOctaneGas;
import Ciclo3.Control.TabelaFluidos.ControlOctaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlPentaneGas;
import Ciclo3.Control.TabelaFluidos.ControlPentaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlPropaneGas;
import Ciclo3.Control.TabelaFluidos.ControlPropaneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlR114Gas;
import Ciclo3.Control.TabelaFluidos.ControlR114Liquido;
import Ciclo3.Control.TabelaFluidos.ControlR11Gas;
import Ciclo3.Control.TabelaFluidos.ControlR11Liquido;
import Ciclo3.Control.TabelaFluidos.ControlR12Gas;
import Ciclo3.Control.TabelaFluidos.ControlR12Liquido;
import Ciclo3.Control.TabelaFluidos.ControlR134AGas;
import Ciclo3.Control.TabelaFluidos.ControlR134ALiquido;
import Ciclo3.Control.TabelaFluidos.ControlR141BGas;
import Ciclo3.Control.TabelaFluidos.ControlR141BLiquido;
import Ciclo3.Control.TabelaFluidos.ControlR142BGas;
import Ciclo3.Control.TabelaFluidos.ControlR142BLiquido;
import Ciclo3.Control.TabelaFluidos.ControlR152AGas;
import Ciclo3.Control.TabelaFluidos.ControlR152ALiquido;
import Ciclo3.Control.TabelaFluidos.ControlR22Gas;
import Ciclo3.Control.TabelaFluidos.ControlR22Liquido;
import Ciclo3.Control.TabelaFluidos.ControlTolueneGas;
import Ciclo3.Control.TabelaFluidos.ControlTolueneLiquido;
import Ciclo3.Control.TabelaFluidos.ControlWaterGas;
import Ciclo3.Control.TabelaFluidos.ControlWaterLiquido;
import Ciclo3.Model.ModelConstantesMat;
import Ciclo3.Model.ModelFluidos;
import Ciclo3.Model.ModelQfpso;
import Ciclo3.Model.TabelasFluidos.ModelButanoGas;
import Ciclo3.Util.HibernateUtil;
import Ciclo3.View.ViewPrincipal;
import java.util.List;
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
    Session session;
    
    @SuppressWarnings("empty-statement")
    public ControlPrincipal(){
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
            session.save(new ModelFluidos(14, "R-134a (1,1,1,2-Tetrafluoroethane)"));
            session.save(new ModelFluidos(15, "R-290 (PROPANE)"));
            session.save(new ModelFluidos(16, "R-142b (1,1-Difluoro-1-chloroethane)"));
            session.save(new ModelFluidos(17, "R-1270 (1-Propene)"));
            session.save(new ModelFluidos(18, "R-40  (Clorometano)"));
            session.save(new ModelFluidos(19, "Hexamethyldisiloxane, (MM)"));
            session.save(new ModelFluidos(20, "Tetradecamethylhexasiloxane (MD4M)"));
            session.save(new ModelFluidos(21, "Octamethylcyclotetrasiloxane (D4)"));
            session.save(new ModelFluidos(22, "Decamethylcyclopentasiloxane (D5)"));
            session.save(new ModelFluidos(23, "Trichlorofluoromethane (R11)"));
            session.save(new ModelFluidos(24, "Dichlorodifluoromethane (R12)"));
            session.save(new ModelFluidos(25, "1,3-Dichloro-1,1,2,2,3,3-hexafluoropropane (R216ca)"));
            session.save(new ModelFluidos(26, "1,2-Dichlorotetrafluoroethane (R114)"));
            session.save(new ModelFluidos(27, "1,1-Dichloro-1-fluoroethane (R141b)"));
            session.save(new ModelFluidos(28, "1,1-Difluoroethane (R152a)"));
            session.save(new ModelFluidos(29, "1-Chloro-1,1-difluoroethane (R142b)"));
            session.save(new ModelFluidos(30, "n-Hexane"));
            session.save(new ModelFluidos(31, "n-Heptane"));
            session.save(new ModelFluidos(32, "n-octane"));
            session.save(new ModelFluidos(33, "Chlorodifluoromethane (R22)"));

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
        
        cr = this.session.createCriteria(ModelButanoGas.class);
        results = cr.list();
        if(results.isEmpty()){
            Transaction tx = session.beginTransaction();
            
            new ControlButanoGas(session).criaTabelaButanoGas();
            new ControlButanoLiquido(session).criaTabelaButanoLiquido();
            new ControlD4Gas(session).criaTabelaD4Gas();
            new ControlD4Liquido(session).criaTabelaD4Liquido();
            new ControlD5Gas(session).criaTabelaD5Gas();
            new ControlD5Liquido(session).criaTabelaD5Liquido();
            new ControlD6Gas(session).criaTabelaD6Gas();
            new ControlD6Liquido(session).criaTabelaD6Liquido();
            new ControlHeptaneGas(session).criaTabelaHeptaneGas();
            new ControlHeptaneLiquido(session).criaTabelaHeptaneLiquido();
            new ControlHexaneGas(session).criaTabelaHexaneGas();
            new ControlHexaneLiquido(session).criaTabelaHexaneLiquido();
            new ControlIpentaneGas(session).criaTabelaIpentaneGas();
            new ControlIpentaneLiquido(session).criaTabelaIpentaneLiquido();
            new ControlIsobutanGas(session).criaTabelaIsobutanGas();
            new ControlIsobutanLiquido(session).criaTabelaIsobutanLiquido();
            new ControlMD2MGas(session).criaTabelaMD2MGas();
            new ControlMD2MLiquido(session).criaTabelaMD2MLiquido();
            new ControlMD3MGas(session).criaTabelaMD3MGas();
            new ControlMD3MLiquido(session).criaTabelaMD3MLiquido();
            new ControlMD4MGas(session).criaTabelaMD4MGas();
            new ControlMD4MLiquido(session).criaTabelaMD4MLiquido();
            new ControlMDMGas(session).criaTabelaMDMGas();
            new ControlMDMLiquido(session).criaTabelaMDMLiquido();
            new ControlMMGas(session).criaTabelaMMGas();
            new ControlMMLiquido(session).criaTabelaMMLiquido();
            new ControlOctaneGas(session).criaTabelaOctaneGas();
            new ControlOctaneLiquido(session).criaTabelaOctaneLiquido();
            new ControlPentaneGas(session).criaTabelaPentaneGas();
            new ControlPentaneLiquido(session).criaTabelaPentaneLiquido();
            new ControlPropaneGas(session).criaTabelaPropaneGas();
            new ControlPropaneLiquido(session).criaTabelaPropaneLiquido();
            new ControlR114Gas(session).criaTabelaR114Gas();
            new ControlR114Liquido(session).criaTabelaR114Liquido();
            new ControlR11Gas(session).criaTabelaR11Gas();
            new ControlR11Liquido(session).criaTabelaR11Liquido();
            new ControlR12Gas(session).criaTabelaR12Gas();
            new ControlR12Liquido(session).criaTabelaR12Liquido();
            new ControlR134AGas(session).criaTabelaR134AGas();
            new ControlR134ALiquido(session).criaTabelaR134ALiquido();
            new ControlR141BGas(session).criaTabelaR141BGas();
            new ControlR141BLiquido(session).criaTabelaR141BLiquido();
            new ControlR142BGas(session).criaTabelaR142BGas();
            new ControlR142BLiquido(session).criaTabelaR142BLiquido();
            new ControlR152AGas(session).criaTabelaR152AGas();
            new ControlR152ALiquido(session).criaTabelaR152ALiquido();
            new ControlR22Gas(session).criaTabelaR22Gas();
            new ControlR22Liquido(session).criaTabelaR22Liquido();
            new ControlTolueneGas(session).criaTabelaTolueneGas();
            new ControlTolueneLiquido(session).criaTabelaTolueneLiquido();
            new ControlWaterGas(session).criaTabelaWaterGas();
            new ControlWaterLiquido(session).criaTabelaWaterLiquido();
            
            tx.commit();
        }
        
        viewPrincipal = new ViewPrincipal(this);
        viewPrincipal.setResizable(false);
        viewPrincipal.setLocation(100, 100);
        viewPrincipal.pack();
        viewPrincipal.setTitle("CO2");
        viewPrincipal.setVisible(true);
        viewPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


