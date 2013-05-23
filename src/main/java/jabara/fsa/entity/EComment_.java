package jabara.fsa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-24T06:38:09.619+0900")
@StaticMetamodel(EComment.class)
public class EComment_ extends EReadable_ {
	public static volatile SingularAttribute<EComment, String> text;
	public static volatile SingularAttribute<EComment, EReport> report;
	public static volatile SingularAttribute<EComment, EMember> writer;
}
