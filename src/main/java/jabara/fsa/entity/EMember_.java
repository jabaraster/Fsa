package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-23T21:13:18.737+0900")
@StaticMetamodel(EMember.class)
public class EMember_ extends EntityBase_ {
	public static volatile SingularAttribute<EMember, String> name;
	public static volatile SingularAttribute<EMember, Boolean> administrator;
}
