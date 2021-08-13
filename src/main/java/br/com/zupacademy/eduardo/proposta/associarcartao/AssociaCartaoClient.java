package br.com.zupacademy.eduardo.proposta.associarcartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accounts", url = "${accounts.host}")
public interface AssociaCartaoClient {

    @GetMapping("${accounts.associaCartao}")
    CartaoResponse associaCartao(@RequestParam String idProposta);

}
