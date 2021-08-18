package br.com.zupacademy.eduardo.proposta.associarcartao;

import org.apache.commons.codec.binary.Base64;

public class    EncodedFinger {

    private String fingerprint;

    public EncodedFinger(String senhaEncoded) {
        if (!Base64.isBase64(senhaEncoded))
            throw new IllegalArgumentException("Argumento não é um base64 válido !");

        this.fingerprint = senhaEncoded;
    }

    public String get() {
        return fingerprint;
    }
}
