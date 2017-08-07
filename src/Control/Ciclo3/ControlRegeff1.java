package Control.Ciclo3;

import Ciclo3.Model.ModelCore;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ControlRegeff1 {

    private double AT,Aho,Aco,Vhx,Lh,Lc,L3,DPh,DPc;
    
    public ControlRegeff1(double T2, double T3, double T6, double T5, double P2, double P5, double P3, double P6, double m, double Qreg, double km, double eff, Session session) {     
        if(eff==0){
            AT=0;
            Aho=0;
            Aco=0;
            Vhx=0;
            Lh=0;
            Lc=0;
            L3=0;
            DPh=0;
            DPc=0;
        }else{
            double PM=44.01;
            P2=P2*100;
            P5=P5*100;
            P3=P3*100;
            P6=P6*100;
            double DPf=P2*1000*4/100;
            double DP6=P5*1000*4/100;

            ControlInterpolacaoGas propV = new ControlInterpolacaoGas(ii,P2,T2,session);
            double k2 = propV.getKv_g();
            double Cp2 = propV.getCpv_g();
            double MU2 = propV.getMuv_g();
            double Pr2 = propV.getPrv_g();
            double Vc2 = propV.getVcv_g();
            double D2 = propV.getDv_g();

            propV = new ControlInterpolacaoGas(ii,P3,T3,session);
            double k3 = propV.getKv_g();
            double Cp3 = propV.getCpv_g();
            double MU3 = propV.getMuv_g();
            double Pr3 = propV.getPrv_g();
            double Vc3 = propV.getVcv_g();
            double D3 = propV.getDv_g();
            
            propV = new ControlInterpolacaoGas(ii, P5, T5, session);
            double k5 = propV.getKv_l();
            double Cp5 = propV.getCpv_l();
            double MU5 = propV.getMuv_l();
            double Pr5 = propV.getPrv_l();
            double Vc5 = propV.getVcv_l();
            double D5 = propV.getDv_g();
            
            propV = new ControlInterpolacaoGas(ii, P6, T6, session);
            double k6 = propV.getKv_l();
            double Cp6 = propV.getCpv_l();
            double MU6 = propV.getMuv_l();
            double Pr6 = propV.getPrv_l();
            double Vc6 = propV.getVcv_l();
            double D6 = propV.getDv_g();
            
            double Tm= ((T2-T6)-(T3-T5))/(Math.log((T2-T6)/(T3-T5)));
            double UA=Qreg/(Tm);

            double Cc=(m*(PM)*((Cp5+Cp6)/2))/1000;
            double Ch=(m*(PM)*((Cp2+Cp3)/2))/1000;
            double Csupmin = Double.min(Ch,Cc);
            double Csupmax = Double.max(Ch,Cc);
            double C=Csupmin/Csupmax;
            double epsilonsup1=(Ch*(T2-T3))/(Csupmin*(T2-T5));
            double NTUsup=(UA)/Csupmin;
            double epsilonsup2=1-Math.exp((Math.exp((Math.pow(NTUsup,0.78))*-C)-1)*((Math.pow(NTUsup,0.22))/C));
            double epsup= Double.min(epsilonsup1,epsilonsup2);
            double Nhsup=2*NTUsup/0.8;
            double Ncsup=2*NTUsup/0.8;
            double Ghsup=Math.pow((((0.25)/((Math.pow(Pr2,(2/3)))*Nhsup))*(2*((D2+D3)/2)*DPf)),0.5);
            double Gcsup=Math.pow((((0.25)/((Math.pow(Pr3,(2/3)))*Ncsup))*(2*((D5+D6)/2)*DP6)),0.5);
            double Accsup=(m*(PM))/Gcsup;
            double Achsup=(m*(PM))/Ghsup; 

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
                double Rehsup= (Ghsup*Dh1)/((MU2+MU3)/2);
                double fhsup= (9.6243*Math.pow(Rehsup,-0.7422)*Math.pow(alp1,-0.1856)*Math.pow(del1,0.3053)*Math.pow(gam1,-0.2659))*Math.pow((1+(7.669e-8*Math.pow(Rehsup,4.429)*Math.pow(alp1,0.920)*Math.pow(del1,3.767)*Math.pow(gam1,0.236))),0.1);
                double jhsup= (0.6522*Math.pow(Rehsup,-0.5403)*Math.pow(alp1,-0.1541)*Math.pow(del1,0.1499)*Math.pow(gam1,-0.0678))*Math.pow((1+(5.269e-5*Math.pow(Rehsup,1.340)*Math.pow(alp1,0.504)*Math.pow(del1,0.456)*Math.pow(gam1,-1.055))),0.1);

                double Recsup= (Gcsup*Dh2)/((MU5+MU6)/2);
                double fcsup= (9.6243*Math.pow(Recsup,-0.7422)*Math.pow(alp2,-0.1856)*Math.pow(del2,0.3053)*Math.pow(gam2,-0.2659))*Math.pow((1+(7.669e-8*Math.pow(Recsup,4.429)*Math.pow(alp2,0.920)*Math.pow(del2,3.767)*Math.pow(gam2,0.236))),0.1);
                double jcsup= (0.6522*Math.pow(Recsup,-0.5403)*Math.pow(alp2,-0.1541)*Math.pow(del2,0.1499)*Math.pow(gam2,-0.0678))*Math.pow((1+(5.269e-5*Math.pow(Recsup,1.340)*Math.pow(alp2,0.504)*Math.pow(del2,0.456)*Math.pow(gam2,-1.055))),0.1);

                double hhsup= jhsup*Ghsup*((Cp3+Cp2)/2)/Math.pow(Pr2,(2/3)); //% Coeficiente de transferência de calor lado quente

                double hcsup= jcsup*Gcsup*((Cp5+Cp6)/2)/Math.pow(Pr5,(2/3)); //% Coeficiente de transferência de calor lado frio

                double m1sup= Math.pow((((2*hhsup)/(km*(t1)))*(1+(t1/l1))),0.5);
                double lh=0.5*(b1-t1);

                double m2sup= Math.pow((((2*hcsup)/(km*(t2)))*(1+(t2/l2))),0.5);
                double lc=0.5*(b2-t2);

                double e1sup= (Math.tanh(m1sup*lh))/(m1sup*lh);  //% Eficiência da aleta lado quente

                double e2sup= (Math.tanh(m2sup*lc))/(m2sup*lc); //% Eficiência da aleta lado frio

                double e1gsup= 1-(1-e1sup)*por1;  //% Eficiência global da Superfície lado Quente

                double e2gsup= 1-(1-e2sup)*por2;//  % Eficiência global da Superfície lado Frio

                //%coeficiente de transferencia de calor (W/m2*K)
                double iUsup=(1/(e1gsup*hhsup))+(AA/(e2gsup*hcsup));
                double Ugsup=1/iUsup;

                //%Area de superficie / %Area de fluxo livre  / Comprimento
                double Ahsup=NTUsup*Csupmin/(Ugsup/1000);

                double Ahosup=(m*(PM))/Ghsup;

                Aho=Ahosup;
                AT=Ahsup;

                double Acsup=Ahsup/AA;

                double Acosup=(m*PM)/Gcsup;

                Aco=Acosup;

                //Aho=Double.max(Aho);
                //Aco=Double.max(Aco);

                double Lcsup=(Dh2*Acsup)/(4*Aco);

                Lc=Lcsup;
                double Lhsup=(Dh1*Ahsup)/(4*Aho);

                Lh=Lhsup;
            
                Ghsup=(m*(PM))/Aho;
                Gcsup=(m*PM)/Aco;

                double DPhsup= (2*Math.pow(Ghsup,2)*Lhsup*fhsup)/(Dh1*((D2+D3)/2));

                DPh=DPhsup;
                erro1=DPh-DPf;

                double DPcsup= (2*Math.pow(Gcsup,2)*Lcsup*fcsup)/(Dh2*((D5+D6)/2));
                DPc=DPcsup;
                erro2=DPc-DP6;

                double Afrh=Aho/cf1;
                double Afrc=Aco/cf2;

                L3=Afrh/Lc;
                Vhx=L3*Lc*Lh;
           
                Ghsup=Math.pow(((DPf*(Dh1*((D2+D3)/2)))/(Lhsup*fhsup*2)),0.5);
                Gcsup=Math.pow(((DP6*(Dh2*((D6+D5)/2)))/(Lcsup*fcsup*2)),0.5);
            }
        }
    }
}
