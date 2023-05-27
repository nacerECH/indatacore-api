package effyis.partners.socle.controller;

import java.util.List;

import effyis.partners.socle.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import effyis.partners.socle.dto.AccountDTO;
import effyis.partners.socle.dto.AuthenticationDTO;
import effyis.partners.socle.dto.JwtDTO;
import effyis.partners.socle.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.validation.Valid;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */
@RestController
@RequestMapping("/indatacore/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public JwtDTO authenticate(@Valid @RequestBody AuthenticationDTO authenticationDTO) {
		return this.accountService.authenticateUser(authenticationDTO);
	}

	@PostMapping("/register")
	public JwtDTO register(@Valid @RequestBody RegisterDTO registerDTO) {
		return this.accountService.registerUser(registerDTO);
	}



	@GetMapping()
	@Operation(security = { @SecurityRequirement(name = "Bearer Token") })
	public List<AccountDTO> getAccounts() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            System.err.println("authentication is null !");
		SecurityContextHolder.getContext().setAuthentication(authentication);
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
			System.err.println(((UserDetails) principal).getUsername());
        } else {
            System.err.println(principal);
            //System.err.println(principal.toString());
        }
		return this.accountService.getAccounts();
	}
}
