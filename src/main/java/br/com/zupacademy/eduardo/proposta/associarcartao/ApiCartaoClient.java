package br.com.zupacademy.eduardo.proposta.associarcartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "accounts", url = "${accounts.host}")
public interface ApiCartaoClient {

    @GetMapping("${accounts.associaCartao}")
    CartaoResponse associaCartao(@RequestParam UUID idProposta);

    @GetMapping("${accounts.pesquisaCartao}")
    CartaoResponse pesquisaCartao(@PathVariable String idCartao);

    @PostMapping("${accounts.bloqueiaCartao}")
    OrdemDeBloqueioResponse bloqueiaCartao(@PathVariable String id, @RequestBody Map<String, String> body);

    @PostMapping("${accounts.avisoViagem}")
    RespostaAvisoViagem cadastraAviso(@PathVariable String id, @RequestBody @Valid AvisoViagemRequest request);

    @PostMapping("${accounts.criaCarteira}")
    RespostaCarteiraDigital cadastraCarteira(@PathVariable String id, @RequestBody @Valid SolicitacaoCarteiraRequest request);
}
