package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-16T22:01:24.282+0900")
@StaticMetamodel(EBusinessItem.class)
public class EBusinessItem_ extends EntityBase_ {
	public static volatile SingularAttribute<EBusinessItem, String> name;
	public static volatile SingularAttribute<EBusinessItem, ECustomer> customer;
}
