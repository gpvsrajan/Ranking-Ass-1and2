import java.util.ArrayList;
import java.util.List;
import java.util.Set;



import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;

public class DAO {

	 private static SessionFactory sessionFactory;
	 static {
	  sessionFactory = HibernateUtil.getSessionFactory();
	 }

	 public static People saveEvent(People event) {
	  Session session = sessionFactory.openSession();

	  session.beginTransaction();

	  session.save(event);

	  session.getTransaction().commit();
	  session.close();
	  return event;
	 }
		public static People savePeople(People p) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.save(p);

			  session.getTransaction().commit();
			  session.close();
			  return p;
		}
		public static Skill saveSkill(Skill s) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.save(s);

			  session.getTransaction().commit();
			  session.close();
			  return s;
		}
		
		public static Rank saveRank(Rank r) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.save(r);

			  session.getTransaction().commit();
			  session.close();
			  return r;
		}
		
		public static List<Skill> fetchALLSkill() {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Query query=session.createQuery("From Skill s");
			  query.setCacheable(true);
			  List<Skill>  skills= query.list();
			  session.getTransaction().commit();
			  session.close();
			  return skills;
		}
		public static List<People> fetchALLPeople() {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Query query=session.createQuery("From People s");
			  query.setCacheable(true);
			  List<People>  peoples=query.list();
			  session.getTransaction().commit();
			  session.close();
			  return peoples;
		}
		
		public static List<Rank> fetchALLRank() {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Query query=session.createQuery("From Rank s");
			  query.setCacheable(true);
			  List<Rank>  ranks=query.list();
			  session.getTransaction().commit();
			  session.close();
			  return ranks;
		}
		
		public static People fetchPeopleById(long id) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  People ev=  (People) session.get(People.class,id);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		
		
		public static Skill fetchSkillByid(long mid) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Skill ev=  (Skill) session.get(Skill.class,mid);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		public static Rank fetchRankByID(long mid) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Rank ev=  (Rank) session.get(Rank.class,mid);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		public static List<RankResultMap> fetchRankbySkillid(long id) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();
				Criteria criteria = session.createCriteria(Rank.class);
				 criteria.add(Restrictions.eq("skill", fetchSkillByid(id)));
				  criteria .setProjection(Projections.projectionList()
	                    .add(Projections.groupProperty("subject"),"subject")
	                           .add(Projections.avg("score"),"score"));
				  criteria.addOrder(Order.desc("score"));
				  criteria.setResultTransformer(Transformers.aliasToBean(RankResultMap.class));
				 
			 
			 
			
			  List<RankResultMap>  ev=criteria.list();
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		
		
		public static void deleteRank(Rank rank) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.delete(rank);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  
		}
		public static List<RankResultMap> fetchAvgSubRank() {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();
			
			      
					Criteria criteria = session.createCriteria(Rank.class);
			  criteria .setProjection(Projections.projectionList()
                    .add(Projections.groupProperty("subject"),"subject")
                    .add(Projections.groupProperty("skill"),"skill")
                    .add(Projections.avg("score"),"score"));
			  criteria.setResultTransformer(Transformers.aliasToBean(RankResultMap.class));
			  List<RankResultMap>  ranks=criteria.list();
			
			  
			  session.getTransaction().commit();
			  session.close();
			return ranks;
		}
		public static List<Object[]>  fetchTopSubRank(Long skillid) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();
			String sql="SELECT p.name,t.Subject_id ,MAX(t.rank) as TopMark "+
                        "from ("+
                        		"SELECT a.Subject_id,AVG(a.score) as rank "+
                        		 "from ranks as a"+
                        		 " where a.skill_id =:skillid group by a.Subject_id "+
                        		") as t "+
                        		"inner join people p on p.people_id= t.Subject_id";
			      Query q=session.createSQLQuery(sql);
			      q.setParameter("skillid", skillid);
			      
			List<Object[]>   ranks=q.list();
			
			  
			  session.getTransaction().commit();
			  session.close();
			return ranks;
		}
	
		
		
	
		
		
	
	




	
	
}
