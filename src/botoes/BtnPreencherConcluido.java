package botoes;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class BtnPreencherConcluido implements AcaoRotinaJava {

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {

        Registro[] linhas = contextoAcao.getLinhas();

        for (Registro linha : linhas) {


            BigDecimal codEmp = (BigDecimal) linha.getCampo("CODEMP");

            linha.setCampo("CONCLUIDO", "S");
            linha.save();

        }
    }

}