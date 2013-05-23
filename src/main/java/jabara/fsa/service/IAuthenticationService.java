/**
 * 
 */
package jabara.fsa.service;

import jabara.fsa.service.impl.AuthenticationServiceImpl;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(AuthenticationServiceImpl.class)
public interface IAuthenticationService {

}
