package jabara.fsa.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-24T19:16:06.585+0900")
@StaticMetamodel(EMember.class)
public class EMember_ extends EntityBase_ {
	public static volatile SingularAttribute<EMember, Boolean> administrator;
	public static volatile SingularAttribute<EMember, String> userId;
}
