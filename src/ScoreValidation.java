import java.io.Serializable;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;
public class ScoreValidation extends EmptyInterceptor {
	Session session;
	private Set inserts = new HashSet();
	private Set updates = new HashSet();
	
	public void setSession(Session session) {
		this.session=session;
	}
	public boolean onSave(Object entity,Serializable id,
			Object[] state,String[] propertyNames,Type[] types)
			throws CallbackException {

			System.out.println("onSave");

			if (entity instanceof Rank){
				Rank obj=(Rank)entity;
				if(obj.getScore()>0&&obj.getScore()<11){
					inserts.add(entity);
				}else{
					throw new CallbackException("Score value should be between 1 -10");
				}
				
			}
			return false;

		}
	
}
