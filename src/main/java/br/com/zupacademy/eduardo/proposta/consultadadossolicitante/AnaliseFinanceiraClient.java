package br.com.zupacademy.eduardo.proposta.consultadadossolicitante;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "analiseFinanceira", url = "${analise-financeira.host}")
public interface AnaliseFinanceiraClient {

    @PostMapping("${analise-financeira.analisa-proposta}")
    ResultadoAnalise analisaProposta(@RequestBody @Valid SolicitacaoAnalise solicitacaoAnalise);
}
