package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-16T20:57:44.778+0900")
@StaticMetamodel(EOrganization.class)
public class EOrganization_ extends EntityBase_ {
	public static volatile SingularAttribute<EOrganization, String> name;
	public static volatile SingularAttribute<EOrganization, String> address;
	public static volatile SingularAttribute<EOrganization, String> phoneNumber;
}
