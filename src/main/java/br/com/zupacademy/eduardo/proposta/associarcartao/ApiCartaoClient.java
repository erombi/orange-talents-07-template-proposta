package br.com.zupacademy.eduardo.proposta.associarcartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

}
