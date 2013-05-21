package jabara.fsa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-21T21:22:36.101+0900")
@StaticMetamodel(EComment.class)
public class EComment_ extends EReadable_ {
	public static volatile SingularAttribute<EComment, String> text;
	public static volatile SingularAttribute<EComment, EBusinessItem> businessItem;
	public static volatile SingularAttribute<EComment, EMember> writer;
}
