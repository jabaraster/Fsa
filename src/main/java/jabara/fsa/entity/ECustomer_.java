package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-23T23:45:11.831+0900")
@StaticMetamodel(ECustomer.class)
public class ECustomer_ extends EntityBase_ {
	public static volatile SingularAttribute<ECustomer, String> name;
	public static volatile SingularAttribute<ECustomer, EOrganization> belong;
}
