package effyis.partners.socle.service.implService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import effyis.partners.socle.dto.RegisterDTO;
import effyis.partners.socle.entity.Enums.Roles;
import effyis.partners.socle.entity.Role;
import effyis.partners.socle.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import effyis.partners.socle.dto.AccountDTO;
import effyis.partners.socle.dto.AuthenticationDTO;
import effyis.partners.socle.dto.JwtDTO;
import effyis.partners.socle.entity.Account;
import effyis.partners.socle.repository.AccountRepository;
import effyis.partners.socle.security.JWTProvider;
import effyis.partners.socle.service.AccountService;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */
@Service
public class AccountImplService implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthenticationManager authenticationManger;

	@Value("${security.jwt.secret}")
	private String secret;

	@Value("${security.jwt.expiration.time}")
	private long expirationTime;

	@Value("${default.role}")
	private String defaultRole;

	@Override
	public Optional<Account> findByLogin(String login) {
		return this.accountRepository.findByLogin(login);
	}

	@Override
	public List<AccountDTO> getAccounts() {
		return this.accountRepository.findAll().stream().map(account -> this.modelMapper.map(account, AccountDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public JwtDTO authenticateUser(AuthenticationDTO authenticationDTO) {
		Authentication authentication = this.authenticationManger.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword()));
		Account account = this.findByLogin(authenticationDTO.getLogin()).orElse(null);
		String jwt = JWTProvider.generateJWT(authenticationDTO.getLogin(),
				((account != null) && (account.getRole() != null)) ? account.getRole().getRole().name() : Roles.DEFAULT.name(),
				this.secret, this.expirationTime);
		JwtDTO jwtDTO = new JwtDTO();
		jwtDTO.setJwt(jwt);
		return jwtDTO;
	}

	@Override
	public JwtDTO registerUser(RegisterDTO registerDTO) {
		Account account = new Account();
		account.setLogin(registerDTO.getLogin());// ldap user "riemann"
		account.setPassword(this.bcrypt.encode(registerDTO.getPassword()));
		Role role=new Role();
		role.setRole(Roles.DEFAULT);
		this.roleRepository.save(role);
		account.setRole(role);
		this.accountRepository.save(account);
		String jwt = JWTProvider.generateJWT(registerDTO.getLogin(),
				((account != null) && (account.getRole() != null)) ? account.getRole().getRole().name() : Roles.DEFAULT.name(),
				this.secret, this.expirationTime);
		JwtDTO jwtDTO = new JwtDTO();
		jwtDTO.setJwt(jwt);
		return jwtDTO;
	}

}
