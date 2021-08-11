package br.com.zupacademy.eduardo.proposta.novaproposta;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentoValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Documento {

    String message() default "Documento inválido !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
