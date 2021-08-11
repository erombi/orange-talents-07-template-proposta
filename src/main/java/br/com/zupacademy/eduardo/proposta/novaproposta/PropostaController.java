package br.com.zupacademy.eduardo.proposta.novaproposta;

import br.com.zupacademy.eduardo.proposta.documentounico.DocumentoUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private DocumentoUnicoValidator validator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder) {
        Proposta proposta = request.toModel();
        proposta = repository.save(proposta);

        URI uri = builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
