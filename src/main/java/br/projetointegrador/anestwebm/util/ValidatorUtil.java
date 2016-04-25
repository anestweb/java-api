package br.projetointegrador.anestwebm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class ValidatorUtil {

    public static List<String> messagesFromConstraints(Set<ConstraintViolation<?>> violations) {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            messages.add(violation.getMessage());
        }
        return messages;
    }

}
