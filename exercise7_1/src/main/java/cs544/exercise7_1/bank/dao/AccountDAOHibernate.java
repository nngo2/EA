package cs544.exercise7_1.bank.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.exercise7_1.bank.domain.Account;

public class AccountDAOHibernate implements IAccountDAO {
	private SessionFactory sessionFactory;

	public AccountDAOHibernate() {
		super();
	}

	public AccountDAOHibernate(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public Collection<Account> getAccounts() {
		// To prevent our Lazy Initialization problems
		// we don't have open session in view, so instead we have to eagerly load!
		return sessionFactory.getCurrentSession().createQuery("select distinct a from Account a " +
				"join fetch a.customer " +
				"join fetch a.entryList").list();
	}

	public Account loadAccount(long accountnumber) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class, accountnumber);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveAccount(Account account) {
		sessionFactory.getCurrentSession().persist(account);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAccount(Account account) {
		sessionFactory.getCurrentSession().saveOrUpdate(account);
	}
}
