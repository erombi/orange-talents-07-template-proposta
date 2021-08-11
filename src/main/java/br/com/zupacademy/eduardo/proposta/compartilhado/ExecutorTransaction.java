package br.com.zupacademy.eduardo.proposta.compartilhado;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ExecutorTransaction {

    @Transactional
    public void inTransaction(Runnable runnable) {
        runnable.run();
    }
}
