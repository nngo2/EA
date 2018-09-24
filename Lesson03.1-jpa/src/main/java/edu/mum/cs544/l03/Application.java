package edu.mum.cs544.l03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.cs544.l03.model.bidirectional.BAddress;
import edu.mum.cs544.l03.model.bidirectional.BJoinAddress;
import edu.mum.cs544.l03.model.bidirectional.BJoinPerson;
import edu.mum.cs544.l03.model.bidirectional.BPerson;
import edu.mum.cs544.l03.model.many2many.M2MBAddress;
import edu.mum.cs544.l03.model.many2many.M2MBPerson;
import edu.mum.cs544.l03.model.many2many.M2MUniAddress;
import edu.mum.cs544.l03.model.many2many.M2MUniPerson;
import edu.mum.cs544.l03.model.many2one.MJoinUniAddress;
import edu.mum.cs544.l03.model.many2one.MJoinUniPerson;
import edu.mum.cs544.l03.model.many2one.MUniAddress;
import edu.mum.cs544.l03.model.many2one.MUniPerson;
import edu.mum.cs544.l03.model.one2many.OJoinUniAddress;
import edu.mum.cs544.l03.model.one2many.OJoinUniPerson;
import edu.mum.cs544.l03.model.one2many.OUniAddress;
import edu.mum.cs544.l03.model.one2many.OUniPerson;
import edu.mum.cs544.l03.model.one2one.BiAddress;
import edu.mum.cs544.l03.model.one2one.BiPerson;
import edu.mum.cs544.l03.model.one2one.UniAddress;
import edu.mum.cs544.l03.model.one2one.UniPerson;

public class Application {
	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			// Create new instance of Car and set values to it

			// --- ONE TO ONE UNIDIRECTIONAL  ---
			UniAddress uniAddress = new UniAddress();
			uniAddress.setStreet("1st");			
			uniAddress.setCity("Fairfield");
			uniAddress.setState("IA");
			uniAddress.setZip("13452");
			
			UniPerson uniPerson = new UniPerson();
			uniPerson.setFirstname("John");
			uniPerson.setLastname("Johnes");
			uniPerson.setAddress(uniAddress);

			// --- ONE TO ONE UNIDIRECTIONAL  ---
			BiAddress biAddress = new BiAddress();
			biAddress.setStreet("1st");			
			biAddress.setCity("Fairfield");
			biAddress.setState("IA");
			biAddress.setZip("13452");
			
			BiPerson biPerson = new BiPerson();
			biPerson.setFirstname("John");
			biPerson.setLastname("Johnes");
			
			biPerson.setAddress(biAddress);
			biAddress.setPerson(biPerson);  

			// --- ONE TO MANY UNIDIRECTIONAL  ---
			OUniAddress oUniAddress1 = new OUniAddress();
			oUniAddress1.setStreet("1st");			
			oUniAddress1.setCity("Fairfield");
			oUniAddress1.setState("IA");
			oUniAddress1.setZip("13452");
			OUniAddress oUniAddress2 = new OUniAddress();
			oUniAddress2.setStreet("2st");			
			oUniAddress2.setCity("Fairfield");
			oUniAddress2.setState("IA");
			oUniAddress2.setZip("13452");
			
			OUniPerson oUniPerson = new OUniPerson();
			oUniPerson.setFirstname("John");
			oUniPerson.setLastname("Johnes");
			oUniPerson.getAddress().add(oUniAddress1);
			oUniPerson.getAddress().add(oUniAddress2);

			// --- ONE TO MANY UNIDIRECTIONAL  JOIN TABLE ---
			OJoinUniAddress oJoinUniAddress1 = new OJoinUniAddress();
			oJoinUniAddress1.setStreet("1st");			
			oJoinUniAddress1.setCity("Fairfield");
			oJoinUniAddress1.setState("IA");
			oJoinUniAddress1.setZip("13452");
			OJoinUniAddress oJoinUniAddress2 = new OJoinUniAddress();
			oJoinUniAddress2.setStreet("2st");			
			oJoinUniAddress2.setCity("Fairfield");
			oJoinUniAddress2.setState("IA");
			oJoinUniAddress2.setZip("13452");
			
			OJoinUniPerson oJoinUniPerson = new OJoinUniPerson();
			oJoinUniPerson.setFirstname("John");
			oJoinUniPerson.setLastname("Johnes");
			oJoinUniPerson.getAddress().add(oJoinUniAddress1);
			oJoinUniPerson.getAddress().add(oJoinUniAddress2);

			// --- MANY TO ONE UNIDIRECTIONAL  ---
			MUniPerson mUniPerson = new MUniPerson();
			mUniPerson.setFirstname("John");
			mUniPerson.setLastname("Johnes");

			MUniAddress mUniAddress1 = new MUniAddress();
			mUniAddress1.setStreet("1st");			
			mUniAddress1.setCity("Fairfield");
			mUniAddress1.setState("IA");
			mUniAddress1.setZip("13452");
			mUniAddress1.setPerson(mUniPerson);
			MUniAddress mUniAddress2 = new MUniAddress();
			mUniAddress2.setStreet("2st");			
			mUniAddress2.setCity("Fairfield");
			mUniAddress2.setState("IA");
			mUniAddress2.setZip("13452");
			mUniAddress2.setPerson(mUniPerson);
			
			// --- MANY TO ONE UNIDIRECTIONAL  JOIN TABLE ---
			MJoinUniPerson mJoinUniPerson = new MJoinUniPerson();
			mJoinUniPerson.setFirstname("John");
			mJoinUniPerson.setLastname("Johnes");

			MJoinUniAddress mJoinUniAddress1 = new MJoinUniAddress();
			mJoinUniAddress1.setStreet("1st");			
			mJoinUniAddress1.setCity("Fairfield");
			mJoinUniAddress1.setState("IA");
			mJoinUniAddress1.setZip("13452");
			mJoinUniAddress1.setPerson(mJoinUniPerson);
			MJoinUniAddress mJoinUniAddress2 = new MJoinUniAddress();
			mJoinUniAddress2.setStreet("2st");			
			mJoinUniAddress2.setCity("Fairfield");
			mJoinUniAddress2.setState("IA");
			mJoinUniAddress2.setZip("13452");
			mJoinUniAddress2.setPerson(mJoinUniPerson);
			
			// --- MANY TO ONE/ONE TO MANY BIDIRECTIONAL  ---
			BAddress bAddress1 = new BAddress();
			bAddress1.setStreet("1st");			
			bAddress1.setCity("Fairfield");
			bAddress1.setState("IA");
			bAddress1.setZip("13452");

			BAddress bAddress2 = new BAddress();
			bAddress2.setStreet("2st");			
			bAddress2.setCity("Fairfield");
			bAddress2.setState("IA");
			bAddress2.setZip("13452");

			BPerson bPerson = new BPerson();
			bPerson.setFirstname("John");
			bPerson.setLastname("Johnes");
			// NOTICE this will assign the person to the address and the address to the person
			bPerson.addAddress(bAddress1); 
			bPerson.addAddress(bAddress2);  

			// --- MANY TO ONE/ONE TO MANY BIDIRECTIONAL  JOIN TABLE ---

			BJoinAddress bJoinAddress1 = new BJoinAddress();
			bJoinAddress1.setStreet("1st");			
			bJoinAddress1.setCity("Fairfield");
			bJoinAddress1.setState("IA");
			bJoinAddress1.setZip("13452");

			BJoinAddress bJoinAddress2 = new BJoinAddress();
			bJoinAddress2.setStreet("2st");			
			bJoinAddress2.setCity("Fairfield");
			bJoinAddress2.setState("IA");
			bJoinAddress2.setZip("13452");

			BJoinPerson bJoinPerson = new BJoinPerson();
			bJoinPerson.setFirstname("John");
			bJoinPerson.setLastname("Johnes");
			// NOTICE this will assign the person to the address and the address to the person
			bJoinPerson.addAddress(bJoinAddress1);
			bJoinPerson.addAddress(bJoinAddress2);

			// --- MANY TO MANY UNIDIRECTIONAL  ---
			M2MUniAddress m2mUniAddress1 = new M2MUniAddress();
			m2mUniAddress1.setStreet("1st");			
			m2mUniAddress1.setCity("Fairfield");
			m2mUniAddress1.setState("IA");
			m2mUniAddress1.setZip("13452");

			M2MUniAddress m2mUniAddress2 = new M2MUniAddress();
			m2mUniAddress2.setStreet("2st");			
			m2mUniAddress2.setCity("Fairfield");
			m2mUniAddress2.setState("IA");
			m2mUniAddress2.setZip("13452");

			M2MUniPerson m2mUniPerson = new M2MUniPerson();
			m2mUniPerson.setFirstname("John");
			m2mUniPerson.setLastname("Johnes");
			m2mUniPerson.addAddress(m2mUniAddress1); 
			m2mUniPerson.addAddress(m2mUniAddress2);  

			// --- MANY TO MANY BIDIRECTIONAL  ---
			M2MBAddress m2mbAddress1 = new M2MBAddress();
			m2mbAddress1.setStreet("1st");			
			m2mbAddress1.setCity("Fairfield");
			m2mbAddress1.setState("IA");
			m2mbAddress1.setZip("13452");

			M2MBAddress m2mbAddress2 = new M2MBAddress();
			m2mbAddress2.setStreet("2st");			
			m2mbAddress2.setCity("Fairfield");
			m2mbAddress2.setState("IA");
			m2mbAddress2.setZip("13452");

			M2MBPerson m2mbPerson = new M2MBPerson();
			m2mbPerson.setFirstname("John");
			m2mbPerson.setLastname("Johnes");
			// NOTICE this will assign the person to the address and the address to the person
			m2mbPerson.addAddress(m2mbAddress1); 
			m2mbPerson.addAddress(m2mbAddress2);  
			
			// --------------------------------------------------
			// STORE OBJECTS
			// --------------------------------------------------

			tx.begin();
			// save the car
			em.persist(uniPerson);
			em.persist(uniAddress);
			em.persist(biPerson);
			em.persist(biAddress);
			em.persist(oUniPerson);
			em.persist(oUniAddress1);
			em.persist(oUniAddress2);
			em.persist(oJoinUniPerson);
			em.persist(oJoinUniAddress1);
			em.persist(oJoinUniAddress2);
			em.persist(mUniPerson);
			em.persist(mUniAddress1);
			em.persist(mUniAddress2);
			em.persist(mJoinUniPerson);
			em.persist(mJoinUniAddress1);
			em.persist(mJoinUniAddress2);			
			em.persist(bPerson);
			em.persist(bAddress1);
			em.persist(bAddress2);
			em.persist(bJoinPerson);
			em.persist(bJoinAddress1);
			em.persist(bJoinAddress2);			
			em.persist(m2mUniPerson);
			em.persist(m2mUniAddress1);
			em.persist(m2mUniAddress2);
			em.persist(m2mbPerson);
			em.persist(m2mbAddress1);
			em.persist(m2mbAddress2);
			
			tx.commit();
			output("get ID from detached beans : " 
					+ "\n\tOne to One Unidirectional person  : " + uniPerson.getId()
					+ "\n\tOne to One Unidirectional address : " + uniAddress.getId()
					+ "\n\tOne to One Bidirectional person  : " + biPerson.getId()
					+ "\n\tOne to One Bidirectional address : " + biAddress.getId()
					+ "\n\tOne to Many Unidirectional person    : " + oUniPerson.getId()
					+ "\n\tOne to Many Unidirectional address 1 : " + oUniAddress1.getId()
					+ "\n\tOne to Many Unidirectional address 2 : " + oUniAddress2.getId()
					+ "\n\tOne to Many Unidirectional person    JT : " + oJoinUniPerson.getId()
					+ "\n\tOne to Many Unidirectional address 1 JT : " + oJoinUniAddress1.getId()
					+ "\n\tOne to Many Unidirectional address 2 JT : " + oJoinUniAddress2.getId()
					+ "\n\tMany to One Unidirectional person    : " + mUniPerson.getId()
					+ "\n\tMany to One Unidirectional address 1 : " + mUniAddress1.getId()
					+ "\n\tMany to One Unidirectional address 2 : " + mUniAddress2.getId()
					+ "\n\tMany to One Unidirectional person    JT : " + mJoinUniPerson.getId()
					+ "\n\tMany to One Unidirectional address 1 JT : " + mJoinUniAddress1.getId()
					+ "\n\tMany to One Unidirectional address 2 JT : " + mJoinUniAddress2.getId()
					+ "\n\tBidirectional 1:M person    : " + bPerson.getId()
					+ "\n\tBidirectional 1:M address 1 : " + bAddress1.getId()
					+ "\n\tBidirectional 1:M address 2 : " + bAddress2.getId()
					+ "\n\tBidirectional 1:M person    JT : " + bJoinPerson.getId()
					+ "\n\tBidirectional 1:M address 1 JT : " + bJoinAddress1.getId()
					+ "\n\tBidirectional 1:M address 2 JT : " + bJoinAddress2.getId()

					+ "\n\tUnidirectional Many to Many person    : " + m2mUniPerson.getId()
					+ "\n\tUnidirectional Many to Many address 1 : " + m2mUniAddress1.getId()
					+ "\n\tUnidirectional Many to Many address 2 : " + m2mUniAddress2.getId()

					+ "\n\tBidirectional Many to Many person    : " + m2mbPerson.getId()
					+ "\n\tBidirectional Many to Many address 1 : " + m2mbAddress1.getId()
					+ "\n\tBidirectional Many to Many address 2 : " + m2mbAddress2.getId()
					);

		} catch (Throwable e) {
			System.err.println(e);
			if ((tx != null) && (tx.isActive())) tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}

		if (emf.isOpen()) {
			emf.close();
		}
	}

	private static void output(String output) {
		System.out.println("================= OUTPUT =================");
		System.out.println(output);
		System.out.println("==========================================");
	}
}
