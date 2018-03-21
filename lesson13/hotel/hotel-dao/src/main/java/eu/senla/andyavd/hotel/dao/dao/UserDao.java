package eu.senla.andyavd.hotel.dao.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import eu.senla.andyavd.hotel.api.dao.IUserDao;
import eu.senla.andyavd.hotel.entity.beans.User;

public class UserDao extends GenericDao<User> implements IUserDao {

	private final static Logger logger = Logger.getLogger(User.class);
	
	public UserDao(Class<User> classs) {
		super(classs);
	}
	
	@Override
	public User getUserByUsername(Session session, String username) throws Exception {
		Criteria criteria = session.createCriteria(User.class);
		try {
			User user = (User) criteria.add(Restrictions.eqOrIsNull("username", username)).uniqueResult();
			return user;
		} catch (Exception e) {
			logger.error("Failed to get Visitors Room!");
			throw new Exception();
		}
	}
}