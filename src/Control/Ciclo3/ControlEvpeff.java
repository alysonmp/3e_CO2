/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import Ciclo3.Control.Interpolacao.ControlInterpolacao;
import Ciclo3.Control.Interpolacao.ControlInterpolacaoFon;
import Ciclo3.Model.ModelCore;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author leonardo
 */
public class ControlEvpeff {

    private double AT,Aho,Aco,Vhx,Lh,Lc,L3,DPh,DPc;
    
    public ControlEvpeff(double Tf, double Tf2, double T1, double T6, double P1, double P6, double Pf, double m, double mf, double Qevp, int FON, double km, Session session) {
    
        double PM=44.01;

        P1=P1*100;
        P6=P6*100;
        double DP6=P6*1000*4/100;
        double DPf=Pf*1000*4/100;

        ControlInterpolacao propV = new ControlInterpolacao(session);
        propV.interpolacao(P1,T1);
        double k1 = propV.getKv();
        double Cp1 = propV.getCpv();
        double MU1 = propV.getMuv();
        double Pr1 = propV.getPrv();
        double Vc1 = propV.getVcv();
        double D1 = propV.getDf();

        propV = new ControlInterpolacao(session);
        propV.interpolacao(P6,T6);
        double k6 = propV.getKv();
        double Cp6 = propV.getCpv();
        double MU6 = propV.getMuv();
        double Pr6 = propV.getPrv();
        double Vc6 = propV.getVcv();
        double D6 = propV.getDf();

        ControlInterpolacaoFon propVf = new ControlInterpolacaoFon(FON, Pf, Tf, session);
        double kf = propVf.getKv();
        double Cpf = propVf.getCpv();
        double MUf = propVf.getMuv();
        double Prf = propVf.getPrv();
        double Vcf = propVf.getVcv();
        double Df = propVf.getDf();

        propVf = new ControlInterpolacaoFon(FON, Pf, Tf2, session);
        double kf2 = propVf.getKv();
        double Cpf2 = propVf.getCpv();
        double MUf2 = propVf.getMuv();
        double Prf2 = propVf.getPrv();
        double Vcf2 = propVf.getVcv();
        double Df2 = propVf.getDf();

        double Tm= ((Tf-T1)-(Tf2-T6))/(Math.log((Tf-T1)/(Tf2-T6)));
        double UA=Qevp/(Tm);

        double Cc=(m*(PM)*((Cp1+Cp6)/2))/1000;
        double Ch=(mf*((Cpf+Cpf2)/2))/1000;
        double Csupmin=Double.min(Ch,Cc);
        double Csupmax=Double.max(Ch,Cc);
        double C=Csupmin/Csupmax;
        double epsilonsup1=(Ch*(Tf-Tf2))/(Csupmin*(Tf-T6));
        double NTUsup=(UA)/Csupmin;
        double epsilonsup2=1-Math.exp((Math.exp((Math.pow(NTUsup,0.78))*-C)-1)*(Math.pow(NTUsup,0.22)/C));
        double epsup=Double.min(epsilonsup1,epsilonsup2);
        double Nhsup=2*NTUsup/0.8;
        double Ncsup=2*NTUsup/0.8;
        double Ghsup=Math.pow((((0.25)/(Math.pow(Prf,(0.6666666666666667))*Nhsup))*(2*((Df2+Df)/2)*DPf)),0.5);
        double Gcsup=Math.pow((((0.25)/(Math.pow(Pr1,(0.6666666666666667))*Ncsup))*(2*((D1+D6)/2)*DP6)),0.5);
        double Accsup=(m*(PM))/Gcsup;
        double Achsup=mf/Ghsup; 

        Criteria cr = session.createCriteria(ModelCore.class);
        cr.add(Restrictions.eq("cod", 13));
        List results = cr.list();
        ModelCore core = (ModelCore) results.get(0);

        double Dh1 = core.getDh();
        double alp1 = core.getAlp();
        double del1 = core.getDel();
        double gam1 = core.getGam();
        double b1 = core.getB();
        double t1 = core.getT();
        double l1 = core.getL();
        double s1 = core.getS();
        double bet1 = core.getBet();
        double por1 = core.getPor();

        cr = session.createCriteria(ModelCore.class);
        cr.add(Restrictions.eq("cod", 9));
        results = cr.list();
        core = (ModelCore) results.get(0);

        double Dh2 = core.getDh();
        double alp2 = core.getAlp();
        double del2 = core.getDel();
        double gam2 = core.getGam();
        double b2 = core.getB();
        double t2 = core.getT();
        double l2 = core.getL();
        double s2 = core.getS();
        double bet2 = core.getBet();
        double por2 = core.getPor();
        
        double aa1=(b1*bet1)/(b1+b2+(2*0.0005));
        double aa2=(b2*bet2)/(b1+b2+(2*0.0005));
        double AA=aa1/aa2;
        double cf1=aa1*Dh1/4;
        double cf2=aa2*Dh2/4;
        double erro1=1;
        double erro2=1;

        while(erro1>0 | erro2>0){
            double Rehsup= (Ghsup*Dh1)/((MUf+MUf2)/2);
            double fhsup= (9.6243*Math.pow(Rehsup,-0.7422)*Math.pow(alp1,-0.1856)*Math.pow(del1,0.3053)*Math.pow(gam1,-0.2659))*Math.pow((1+(7.669e-8*Math.pow(Rehsup,4.429)*Math.pow(alp1,0.920)*Math.pow(del1,3.767)*Math.pow(gam1,0.236))),0.1);
            double jhsup= (0.6522*Math.pow(Rehsup,-0.5403)*Math.pow(alp1,-0.1541)*Math.pow(del1,0.1499)*Math.pow(gam1,-0.0678))*Math.pow((1+(5.269e-5*Math.pow(Rehsup,1.340)*Math.pow(alp1,0.504)*Math.pow(del1,0.456)*Math.pow(gam1,-1.055))),0.1);

            double Recsup= (Gcsup*Dh2)/((MU1+MU6)/2);
            double fcsup= (9.6243*Math.pow(Recsup,-0.7422)*Math.pow(alp2,-0.1856)*Math.pow(del2,0.3053)*Math.pow(gam2,-0.2659))*Math.pow((1+(7.669e-8*Math.pow(Recsup,4.429)*Math.pow(alp2,0.920)*Math.pow(del2,3.767)*Math.pow(gam2,0.236))),0.1);
            double jcsup= (0.6522*Math.pow(Recsup,-0.5403)*Math.pow(alp2,-0.1541)*Math.pow(del2,0.1499)*Math.pow(gam2,-0.0678))*Math.pow((1+(5.269e-5*Math.pow(Recsup,1.340)*Math.pow(alp2,0.504)*Math.pow(del2,0.456)*Math.pow(gam2,-1.055))),0.1);

            double hhsup= jhsup*Ghsup*((Cpf+Cpf2)/2)/Math.pow(Prf,(0.6666666666666667));//% Coeficiente de transferência de calor lado quente

            double hcsup= jcsup*Gcsup*((Cp1+Cp6)/2)/Math.pow(Pr1,(0.6666666666666667));// % Coeficiente de transferência de calor lado frio


            double m1sup=Math.pow((((2*hhsup)/(km*(t1)))*(1+(t1/l1))),0.5);
            double lh=0.5*(b1-t1);

            double m2sup=Math.pow((((2*hcsup)/(km*(t2)))*(1+(t2/l2))),0.5);
            double lc=0.5*(b2-t2);

            double e1sup= (Math.tanh(m1sup*lh))/(m1sup*lh);//% Eficiência da aleta lado quente

            double e2sup= (Math.tanh(m2sup*lc))/(m2sup*lc);// % Eficiência da aleta lado frio

            double e1gsup= 1-(1-e1sup)*por1;//  % Eficiência global da Superfície lado Quente

            double e2gsup= 1-(1-e2sup)*por2;//  % Eficiência global da Superfície lado Frio

            double iUsup=(1/(e1gsup*hhsup))+(AA/(e2gsup*hcsup));
            double Ugsup=1/iUsup;

            double Ahsup=NTUsup*Csupmin/(Ugsup/1000);

            double Ahosup=mf/Ghsup;

            Aho=Ahosup;
            AT=Ahsup;

            double Acsup=Ahsup/AA;

            double Acosup=(m*PM)/Gcsup;

            Aco=Acosup;

            //Aho=max(Aho);
            //Aco=max(Aco);

            double Lcsup=(Dh2*Acsup)/(4*Aco);

            Lc=Lcsup;
            double Lhsup=(Dh1*Ahsup)/(4*Aho);

            Lh=Lhsup;


            Ghsup=mf/Aho;
            Gcsup=(m*PM)/Aco;

            double DPhsup= (2*Math.pow(Ghsup,2)*Lhsup*fhsup)/(Dh1*((Df2+Df)/2));

            DPh=DPhsup;
            erro1=DPh-DPf;

            double DPcsup= (2*Math.pow(Gcsup,2)*Lcsup*fcsup)/(Dh2*((D1+D6)/2));
            DPc=DPcsup;
            erro2=DPc-DP6;

            double Afrh=Aho/cf1;
            double Afrc=Aco/cf2;

            L3=Afrh/Lc;
            Vhx=L3*Lc*Lh;

            Ghsup=Math.pow(((DPf*(Dh1*((Df+Df)/2)))/(Lhsup*fhsup*2)),0.5);
            Gcsup=Math.pow(((DP6*(Dh2*((D6+D1)/2)))/(Lcsup*fcsup*2)),0.5);
        }
    }

    public double getAT() {
        return AT;
    }

    public void setAT(double AT) {
        this.AT = AT;
    }

    public double getAho() {
        return Aho;
    }

    public void setAho(double Aho) {
        this.Aho = Aho;
    }

    public double getAco() {
        return Aco;
    }

    public void setAco(double Aco) {
        this.Aco = Aco;
    }

    public double getVhx() {
        return Vhx;
    }

    public void setVhx(double Vhx) {
        this.Vhx = Vhx;
    }

    public double getLh() {
        return Lh;
    }

    public void setLh(double Lh) {
        this.Lh = Lh;
    }

    public double getLc() {
        return Lc;
    }

    public void setLc(double Lc) {
        this.Lc = Lc;
    }

    public double getL3() {
        return L3;
    }

    public void setL3(double L3) {
        this.L3 = L3;
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
}
