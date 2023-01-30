package person.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It is custom annotation.
 *
 * @author Scrum team
 * @version 2.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTable {

    /**
     * table name in db.
     */
    String name();
}
