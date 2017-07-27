/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Ciclo3;

import org.hibernate.Session;

/**
 *
 * @author leonardo
 */
public class ControlIsoentropiaTurbina {

    private double T2, S2, H2, H2s;
    
    public ControlIsoentropiaTurbina(double Teff, double P2, double Pref, double Tref, double S1, double H1, double T1, Session session) {
    
        double Test = T1-20;
        
        // [SL2, SV2] = S_sistema(Test, P2, Pref, Tref);
        ControlS_Sistema s_sistema = new ControlS_Sistema(Test, P2, Pref, Tref); 
        if(s_sistema.getSV() >= S1){
            double erro=1;
            double DT=T1*0.1;
            while(erro>= 0.0001){
                s_sistema = new ControlS_Sistema(Test, P2, Pref, Tref);
                S2=s_sistema.getSV();
                erro = Math.abs((S2-S1)/S2);
                double Burbuja=S2-S1;
                if(erro>0.0001 & Burbuja<0){
                    Test=Test+DT;
                    DT=DT/2;
                    if(DT<0.005){
                        DT=0.004987569731;
                    }
                }else if(erro>0.0001 & Burbuja>0){
                    Test=Test-DT;
                    DT=DT/2;
                    if(DT<0.005){
                        DT=0.002933254;
                    }
                }
            }
          
            ControlH_Sistema h_sistema = ControlH_Sistema(Test, P2, Pref, Tref,session);
            H2s=h_sistema.getHV();
            H2=(-(H1-H2s)*Teff)+H1;
            if H2>H2s
                erro=1;
                DT=5;
                while erro>0.0001
                    [HL2, HV2] = H_sistema(Test, P2, Pref, Tref);
                    H=HV2;
                    erro=abs((H2-H)/H2);
                    Burbuja=H2-H;
                    if erro>0.0001 & Burbuja<0
                        Test=Test-DT;
                        DT=DT/2;
                        if DT<0.005;
                            DT=0.004978953;
                        end
                    else if erro>0.0001 & Burbuja>0;
                            Test=Test+DT;
                            DT=DT/2;
                            if DT<0.005;
                                DT=0.00333695;
                            end
                        end
                    end
                end
            end
            T2=Test;
            [HL2, HV2] = H_sistema(T2, P2, Pref, Tref);
            [SL2, SV2] = S_sistema(T2, P2, Pref, Tref) ;
            H2=HV2;
            S2=SV2;

        }
    }
   
    
}

else
    x=(S1-SL2)/(SV2-SL2);
    [HL2, HV2] = H_sistema(Test, P2, Pref, Tref);
    H2s=(HV2*x)+(HL2*(1-x));
    H2=(-(H1-H2s)*Teff)+H1;
    DT=120;
    if H2>HV2
        erro=1;
        while erro>0.0001
            [HL2, HV2] = H_sistema(Test, P2, Pref, Tref);
            H=HV2;
            erro=abs((H2-H)/H2);
            Burbuja=H2-H;
            if erro>0.0001 & Burbuja<0
                Test=Test-DT;
                DT=DT/2;
                if DT<0.005;
                    DT=0.005;
                end
            else if erro>0.0001 & Burbuja>0;
                    Test=Test+DT;
                    DT=DT/2;
                    if DT<0.005;
                        DT=0.005;
                    end
                end
            end
        end
         T2=Test;
    [HL2, HV2] = H_sistema(T2, P2, Pref, Tref);
    [SL2, SV2] = S_sistema(T2, P2, Pref, Tref) ;
    H2=HV2;
    S2=SV2;
    else
        x2=(H2-HL2)/(HV2-HL2);
        if x2 < 0.99;
            disp('Titulo do vapor da saida na Turbina inferior 0.9')
            disp('Aumentar a Temperatura de Superaquecimento')
            Pasar=oiu*uy6r7*988
          
        else
            T2=Test;
           [HL2, HV2] = H_sistema(T2, P2, Pref, Tref);
           [SL2, SV2] = S_sistema(T2, P2, Pref, Tref); 
           H2=(HV2*x2)+(HL2*(1-x2));
           S2=(SV2*x2)+(SL2*(1-x2));    
        end
    end
 end
end