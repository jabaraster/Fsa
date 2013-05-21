/**
 * 
 */
package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @param <E>
 * @author jabaraster
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EReadable<E extends EntityBase<E>> extends EntityBase<E> {
    private static final long serialVersionUID = -390017195230681048L;
}
