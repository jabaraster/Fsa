/**
 * 
 */
package jabara.fsa.service;

import jabara.fsa.service.impl.MemberServiceImpl;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(MemberServiceImpl.class)
public interface IMemberService {

    /**
     * 
     */
    void insertAdministratorIfNotExists();

}
