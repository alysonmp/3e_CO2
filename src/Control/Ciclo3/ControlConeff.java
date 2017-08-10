/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import Ciclo3.Control.Interpolacao.ControlCO2;
import Ciclo3.Control.Interpolacao.ControlInterpolacaoFon;
import Ciclo3.Model.ModelCore;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alysonmp
 */
public class ControlConeff {
    
    private double Te, Ts, Ee, He, Se, be, ETe, Es, Hs, Ss, bs, ETs, k4, Cp4, MU4, Pr4, Vc4, D4, k3, Cp3, MU3, Pr3, Vc3, D3, ke, Cpe, MUe, 
            Pre, Vce, De, ks, Cps, MUs, Prs, Vcs, Ds, Dh1, alp1, del1, gam1, b1, t1, l1, s1, bet1, por1, Dh2, alp2, del2, gam2, b2, t2, l2, 
            s2, bet2, por2, aa1, aa2, AA, cf1, cf2, Rehsup, fhsup, jhsup, Recsup, fcsup, jcsup, hhsup, hcsup, m1sup, lh, m2sup, lc, e1sup, 
            e2sup, e1gsup, e2gsup, iUsup, Ugsup, Ahsup, Ahosup, Aho, AT, Acsup, Acosup, Aco, Lcsup, Lc, Lhsup, Lh, DPhsup, DPh, DPcsup, DPc, 
            Afrh, Afrc, L3, Vhx, GCsup;
            
    public ControlConeff(double T4, double T3, double P3, double P4, double m, double Qcon, double km, Session session){
        
        Te = 273.15+30;
        Ts = T3-5;
        
        double[] X = {0, 0, 0, 21, 79, 0, 0, 0};
        
        ControlExergia_Gases exergia = new ControlExergia_Gases(1.0, Te, X, session);
        Ee = exergia.getEin();
        He = exergia.getHin();
        Se = exergia.getSin();
        be = exergia.getBgas();
        ETe = exergia.getET();
        
        exergia = new ControlExergia_Gases(1.0, Ts, X, session);
        Es = exergia.getEin();
        Hs = exergia.getHin();
        Ss = exergia.getSin();
        bs = exergia.getBgas();
        ETs = exergia.getET();
        
        double mair = (Qcon/(Hs-He))*29; //%kg/s

        double PM = 44.01;
        P4 = P4*100;
        P3 = P3*100;
        double Pair = 101.3525*1.2;

        double DPf = P4*1000*4/100;
        double DP6 = Pair*1000*4/100;
        
        ControlCO2 interpolacao = new ControlCO2(session);
        interpolacao.interpolacao(P4, T4);
        k4 = interpolacao.getKv();
        Cp4 = interpolacao.getCpv();
        MU4 = interpolacao.getMuv();
        Pr4 = interpolacao.getPrv();
        Vc4 = interpolacao.getVcv();
        D4 = interpolacao.getDf();
        
        interpolacao.interpolacao(P3, T3);
        k3 = interpolacao.getKv();
        Cp3 = interpolacao.getCpv();
        MU3 = interpolacao.getMuv();
        Pr3 = interpolacao.getPrv();
        Vc3 = interpolacao.getVcv();
        D3 = interpolacao.getDf();
        
        ControlInterpolacaoFon interpolacaoFon = new ControlInterpolacaoFon(2, Pair, 313.16, session);
        ke = interpolacaoFon.getKv();
        Cpe = interpolacaoFon.getCpv();
        MUe = interpolacaoFon.getMuv();
        Pre = interpolacaoFon.getPrv();
        Vce = interpolacaoFon.getVcv();
        De = interpolacaoFon.getDf();
        
        interpolacaoFon = new ControlInterpolacaoFon(2, Pair, Ts, session);
        ks = interpolacaoFon.getKv();
        Cps = interpolacaoFon.getCpv();
        MUs = interpolacaoFon.getMuv();
        Prs = interpolacaoFon.getPrv();
        Vcs = interpolacaoFon.getVcv();
        Ds = interpolacaoFon.getDf();
        
        double Tm = ((T3-Ts)-(T4-Te))/(Math.log((T3-Ts)/(T4-Te)));
        double UA = Qcon/(Tm);

        double Cc = (mair*((Cps+Cpe)/2))/1000;
        double Ch = (m*(PM)*((Cp4+Cp3)/2))/1000;
        double Csupmin = Double.min(Ch,Cc);
        double Csupmax = Double.max(Ch,Cc);
        double C = Csupmin/Csupmax;
        double epsilonsup1 = (Ch*(T3-T4))/(Csupmin*(T3-Te));
        double NTUsup = (UA)/Csupmin;
        double epsilonsup2 = 1-Math.exp((Math.exp((-Math.pow(NTUsup,0.78))*C)-1)*((Math.pow(NTUsup,0.22))/C));
        double epsup = Double.min(epsilonsup1,epsilonsup2);
        double Nhsup = 2*NTUsup/0.8;
        double Ncsup = 2*NTUsup/0.8;
        double Ghsup = Math.pow((((0.25)/((Math.pow(Pr3,(0.6666666666666666)))*Nhsup))*(2*((D4+D3)/2)*DPf)),0.5);
        double Gcsup = Math.pow((((0.25)/(Math.pow(Pre,(0.6666666666666666))*Ncsup))*(2*((De+Ds)/2)*DP6)),0.5);
        double Accsup = (mair)/Gcsup;
        double Achsup = (m*(PM))/Ghsup; 
        
        Criteria cr = session.createCriteria(ModelCore.class);
        cr.add(Restrictions.eq("cod", 13));
        List results = cr.list();
        ModelCore core = (ModelCore) results.get(0);
        
        Dh1 = core.getDh();
        alp1 = core.getAlp();
        del1 = core.getDel();
        gam1 = core.getGam();
        b1 = core.getB();
        t1 = core.getT();
        l1 = core.getL();
        s1 = core.getS();
        bet1 = core.getBet();
        por1 = core.getPor();
        
        cr = session.createCriteria(ModelCore.class);
        cr.add(Restrictions.eq("cod", 13));
        results = cr.list();
        core = (ModelCore) results.get(0);
        
        Dh2 = core.getDh();
        alp2 = core.getAlp();
        del2 = core.getDel();
        gam2 = core.getGam();
        b2 = core.getB();
        t2 = core.getT();
        l2 = core.getL();
        s2 = core.getS();
        bet2 = core.getBet();
        por2 = core.getPor();
        
        aa1 = (b1*bet1)/(b1+b2+(2*0.0005));
        aa2 = (b2*bet2)/(b1+b2+(2*0.0005));
        AA = aa1/aa2;
        cf1 = aa1*Dh1/4;
        cf2 = aa2*Dh2/4;
        
        double erro1 = 1;
        double erro2 = 1;
        
        while(erro1>0.001 || erro2>0.001){
            Rehsup = (Ghsup*Dh1)/((MU4+MU3)/2);
            fhsup = (9.6243*Math.pow(Rehsup, -0.7422)*Math.pow(alp1, -0.1856)*Math.pow(del1, 0.3053)*Math.pow(gam1, -0.2659))*(Math.pow((1+(7.669e-8*Math.pow(Rehsup, 4.429)*Math.pow(alp1, 0.920)*Math.pow(del1, 3.767)*Math.pow(gam1, 0.236))), 0.1));
            jhsup = (0.6522*Math.pow(Rehsup, -0.5403)*Math.pow(alp1, -0.1541)*Math.pow(del1, 0.1499)*Math.pow(gam1, -0.0678))*(Math.pow((1+(5.269e-5*Math.pow(Rehsup, 1.340)*Math.pow(alp1, 0.504)*Math.pow(del1, 0.456)*Math.pow(gam1, -1.055))), 0.1));

            Recsup = (Gcsup*Dh2)/((MUe+MUs)/2);
            fcsup = (9.6243*Math.pow(Recsup, -0.7422)*Math.pow(alp2, -0.1856)*Math.pow(del2, 0.3053)*Math.pow(gam2, -0.2659))*(Math.pow((1+(7.669e-8*Math.pow(Recsup, 4.429)*Math.pow(alp2, 0.920)*Math.pow(del2, 3.767)*Math.pow(gam2, 0.236))), 0.1));
            jcsup = (0.6522*Math.pow(Recsup, -0.5403)*Math.pow(alp2, -0.1541)*Math.pow(del2, 0.1499)*Math.pow(gam2, -0.0678))*(Math.pow((1+(5.269e-5*Math.pow(Recsup, 1.340)*Math.pow(alp2, 0.504)*Math.pow(del2, 0.456)*Math.pow(gam2, -1.055))), 0.1));
            
            hhsup = jhsup*Ghsup*((Cp3+Cp4)/2)/Math.pow(Pr3, (0.66666666666)); //% Coeficiente de transferÍncia de calor lado quente

            hcsup = jcsup*Gcsup*((Cpe+Cps)/2)/Math.pow(Pre, (0.66666666666)); //% Coeficiente de transferÍncia de calor lado frio
            
            m1sup = Math.pow((((2*hhsup)/(km*(t1)))*(1+(t1/l1))), 0.5);
            lh=0.5*(b1-t1);

            m2sup = Math.pow((((2*hcsup)/(km*(t2)))*(1+(t2/l2))), 0.5);
            lc=0.5*(b2-t2);
            
            e1sup = (Math.tanh(m1sup*lh))/(m1sup*lh);  //% EficiÍncia da aleta lado quente

            e2sup = (Math.tanh(m2sup*lc))/(m2sup*lc); //% EficiÍncia da aleta lado frio
            
            e1gsup = 1-(1-e1sup)*por1;  //% EficiÍncia global da SuperfÌcie lado Quente

            e2gsup = 1-(1-e2sup)*por2;  //% EficiÍncia global da SuperfÌcie lado Frio
            
            iUsup = (1/(e1gsup*hhsup))+(AA/(e2gsup*hcsup));
            Ugsup = 1/iUsup;
            
            Ahsup = NTUsup*Csupmin/(Ugsup/1000);

            Ahosup = (m*(PM))/Ghsup;
            Aho = Ahosup;
            
            AT = Ahsup;

            Acsup = Ahsup/AA;

            Acosup = (mair)/Gcsup;

            Aco = Acosup;

            Lcsup=(Dh2*Acsup)/(4*Aco);

            Lc=Lcsup;
            Lhsup=(Dh1*Ahsup)/(4*Aho);

            Lh=Lhsup;


            Ghsup=(m*(PM))/Aho;
            Gcsup=(mair)/Aco;

            DPhsup= (2*Math.pow(Ghsup, 2)*Lhsup*fhsup)/(Dh1*((D4+D3)/2));

            DPh=DPhsup;
            erro1=DPh-DPf;

            DPcsup= (2*Math.pow(Gcsup,2)*Lcsup*fcsup)/(Dh2*((De+Ds)/2));
            DPc=DPcsup;
            erro2=DPc-DP6;
            
            Afrh=Aho/cf1;
            Afrc=Aco/cf2;

            L3=Afrh/Lc;
            Vhx=L3*Lc*Lh;
            
            Ghsup = Math.pow(((DPf*(Dh1*((D4+D3)/2)))/(Lhsup*fhsup*2)), 0.5);
            Gcsup = Math.pow(((DP6*(Dh2*((De+Ds)/2)))/(Lcsup*fcsup*2)), 0.5);
        }
    }

    public double getAho() {
        return Aho;
    }

    public void setAho(double Aho) {
        this.Aho = Aho;
    }

    public double getAT() {
        return AT;
    }

    public void setAT(double AT) {
        this.AT = AT;
    }

    public double getAco() {
        return Aco;
    }

    public void setAco(double Aco) {
        this.Aco = Aco;
    }

    public double getLc() {
        return Lc;
    }

    public void setLc(double Lc) {
        this.Lc = Lc;
    }

    public double getLh() {
        return Lh;
    }

    public void setLh(double Lh) {
        this.Lh = Lh;
    }

    public double getDPh() {
        return DPh;
    }

    public void setDPh(double DPh) {
        this.DPh = DPh;
    }

    public double getDPc() {
        return DPc;
    }

    public void setDPc(double DPc) {
        this.DPc = DPc;
    }

    public double getL3() {
        return L3;
    }

    public void setL3(double L3) {
        this.L3 = L3;
    }

    public double getVhx() {
        return Vhx;
    }

    public void setVhx(double Vhx) {
        this.Vhx = Vhx;
    }
}


