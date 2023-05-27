package effyis.partners.socle.service;

import java.util.List;
import java.util.Optional;

import effyis.partners.socle.dto.AccountDTO;
import effyis.partners.socle.dto.AuthenticationDTO;
import effyis.partners.socle.dto.JwtDTO;
import effyis.partners.socle.dto.RegisterDTO;
import effyis.partners.socle.entity.Account;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */
public interface AccountService {

	Optional<Account> findByLogin(String login);
	JwtDTO authenticateUser(AuthenticationDTO authenticationDTO);
	JwtDTO registerUser(RegisterDTO registerDTO);
	List<AccountDTO> getAccounts();

}
