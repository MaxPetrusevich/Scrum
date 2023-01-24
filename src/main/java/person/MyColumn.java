package person;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field annotation, practices on attributes.
 *
 * @author Scrum team.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumn {

    /**
     * Some description here.
     *
     */
    String name();
}
