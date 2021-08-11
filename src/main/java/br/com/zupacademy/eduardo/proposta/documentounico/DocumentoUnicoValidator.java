package br.com.zupacademy.eduardo.proposta.documentounico;

import br.com.zupacademy.eduardo.proposta.novaproposta.Proposta;
import br.com.zupacademy.eduardo.proposta.novaproposta.PropostaRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class DocumentoUnicoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return PropostaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors())
            return;

        TypedQuery<Proposta> query = manager.createQuery("select p from Proposta p where p.documento = :value", Proposta.class);
        query.setParameter("value", ((PropostaRequest) o).getDocumento());

        if (!query.getResultList().isEmpty())
            errors.rejectValue("documento", null, "Já existe uma proposta para esse documento !");
    }
}
