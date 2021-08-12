package br.com.zupacademy.eduardo.proposta.associarcartao;

import br.com.zupacademy.eduardo.proposta.compartilhado.ExecutorTransaction;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.StatusProposta;
import br.com.zupacademy.eduardo.proposta.novaproposta.Proposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.List;

@Service
@EnableScheduling
public class AccountSchedule {

    private static final long delay = 1000L * 60L * 5L; // 5 minutos

    private final Logger logger = LoggerFactory.getLogger(AccountSchedule.class);

    @Autowired
    private EntityManager manager;

    @Autowired
    private ExecutorTransaction executor;

    @Autowired
    private AssociaCartaoClient client;

    @Scheduled(fixedDelay = delay)
    public void associaCartao() {
        TypedQuery<Proposta> query = manager.createQuery("SELECT p FROM Proposta p WHERE p.status = :pStatus AND COALESCE(p.numeroCartao, 'null') = 'null'", Proposta.class);
        query.setParameter("pStatus", StatusProposta.ELEGIVEL);
        query.setMaxResults(100);

        List<Proposta> propostas = query.getResultList();

        propostas.forEach(p -> {
            try {
                Cartao cartao = client.associaCartao(p.getId().toString());
                p.associaCartao(cartao);

                executor.inTransaction(() -> {
                    manager.merge(p);
                });

                logger.debug("Cartão associado !");
            } catch (FeignException e) {
                logger.debug("Cartão ainda não foi gerado !");
            }
        });

    }

}
