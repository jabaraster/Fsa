/**
 * 
 */
package jabara.fsa.service.impl;

import jabara.fsa.Environment;
import jabara.fsa.entity.EBusinessItem;
import jabara.fsa.entity.ECustomer;
import jabara.fsa.entity.EMember;
import jabara.fsa.entity.EOrganization;
import jabara.fsa.entity.EReport;
import jabara.jpa.entity.EntityBase;
import jabara.jpa_guice.ThreadLocalEntityManagerFactoryHandler;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @param <S> テスト対象のサービスの型.
 * @author jabaraster
 */
public abstract class JpaDaoRule<S> implements TestRule {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager        entityManager;
    private S                    sut;

    /**
     * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement, org.junit.runner.Description)
     */
    @Override
    public Statement apply(final Statement pBase, @SuppressWarnings("unused") final Description pDescription) {
        return new Statement() {

            @SuppressWarnings("synthetic-access")
            @Override
            public void evaluate() throws Throwable {
                final EntityManagerFactory original = Persistence.createEntityManagerFactory(Environment.getApplicationName());
                JpaDaoRule.this.entityManagerFactory = ThreadLocalEntityManagerFactoryHandler.wrap(original);
                JpaDaoRule.this.entityManager = JpaDaoRule.this.entityManagerFactory.createEntityManager();
                JpaDaoRule.this.sut = createService(JpaDaoRule.this.entityManagerFactory);

                JpaDaoRule.this.entityManager.getTransaction().begin();
                try {
                    pBase.evaluate();
                } finally {
                    try {
                        JpaDaoRule.this.entityManager.getTransaction().rollback();
                    } catch (final Throwable e) {
                        e.printStackTrace();
                    }
                    JpaDaoRule.this.entityManagerFactory.close();
                }

            }
        };
    }

    /**
     * @return entityManagerを返す.
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * @return sutを返す.
     */
    public S getSut() {
        return this.sut;
    }

    /**
     * @param pName
     * @param pCustomer
     * @return -
     */
    public EBusinessItem insertBusinessItem(final String pName, final ECustomer pCustomer) {
        final EBusinessItem businessItem = new EBusinessItem();
        businessItem.setCustomer(pCustomer);
        businessItem.setName(pName);
        return persist(businessItem);
    }

    /**
     * @param pName
     * @param pBelong
     * @return -
     */
    public ECustomer insertCustomer(final String pName, final EOrganization pBelong) {
        final ECustomer customer = new ECustomer();
        customer.setBelong(pBelong);
        customer.setName(pName);
        return persist(customer);
    }

    /**
     * @param pName
     * @return -
     */
    public EMember insertMember(final String pName) {
        final EMember member = new EMember();
        member.setName(pName);
        return persist(member);
    }

    /**
     * @param pName
     * @param pAddress
     * @param pPhoneNumber
     * @return -
     */
    public EOrganization insertOrganization(final String pName, final String pAddress, final String pPhoneNumber) {
        final EOrganization organization = new EOrganization();
        organization.setAddress(pAddress);
        organization.setName(pName);
        organization.setPhoneNumber(pPhoneNumber);
        return persist(organization);
    }

    /**
     * @param pWriter
     * @param pBusinessItem
     * @param pReportDate
     * @param pText
     * @return -
     */
    public EReport insertReport( //
            final EMember pWriter //
            , final EBusinessItem pBusinessItem //
            , final Calendar pReportDate //
            , final String pText //
    ) {
        final EReport report = new EReport();
        report.setBusinessItem(pBusinessItem);
        report.setReportDate(pReportDate.getTime());
        report.setText(pText);
        report.setWriter(pWriter);
        persist(report);
        return report;
    }

    /**
     * @param pEntityManagerFactory -
     * @return -
     */
    protected abstract S createService(EntityManagerFactory pEntityManagerFactory);

    private <E extends EntityBase<E>> E persist(final E pEntity) {
        this.entityManager.persist(pEntity);
        return pEntity;
    }
}
