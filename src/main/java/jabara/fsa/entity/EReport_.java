package jabara.fsa.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-05-21T21:22:36.124+0900")
@StaticMetamodel(EReport.class)
public class EReport_ extends EReadable_ {
	public static volatile SingularAttribute<EReport, String> text;
	public static volatile SingularAttribute<EReport, EBusinessItem> businessItem;
	public static volatile SingularAttribute<EReport, Date> reportDate;
	public static volatile SingularAttribute<EReport, EMember> writer;
}
