package eventos;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.ws.ServiceContext;

import java.math.BigDecimal;

public class EvtAtualizarCampo implements EventoProgramavelJava {

    @Override
    public void beforeInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent persistenceEvent) throws Exception {

        System.out.println("Evento techday");
        BigDecimal usuarioLogado = ((AuthenticationInfo) ServiceContext.getCurrent().getAutentication()).getUserID();

        DynamicVO oldValue = (DynamicVO) persistenceEvent.getOldVO();
        String descOld = oldValue.asString("DESCRICAO");

        DynamicVO newValue = (DynamicVO) persistenceEvent.getVo();
        String descNew = newValue.asString("DESCRICAO");

        if (!descNew.equals(descOld)) {


            JapeSession.SessionHandle hnd = null;
            try {
                hnd = JapeSession.open();

                JapeWrapper usuarioDAO = JapeFactory.dao(DynamicEntityNames.USUARIO);
                DynamicVO usuario = usuarioDAO.findByPK(usuarioLogado);
                String nome = usuario.asString("NOMEUSU");
                newValue.setProperty("LOG", "O usuario cód.: " + usuarioLogado + " nome: " + nome + " alterou o campo, de " + descOld + " para " + descNew);

            } catch (Exception e) {
                MGEModelException.throwMe(e);
            } finally {
                JapeSession.close(hnd);
            }

        }

    }

    @Override
    public void beforeDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent persistenceEvent) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext transactionContext) throws Exception {

    }
}
