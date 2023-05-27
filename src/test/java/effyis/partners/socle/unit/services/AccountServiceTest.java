package effyis.partners.socle.unit.services;

import effyis.partners.socle.dto.AccountDTO;
import effyis.partners.socle.dto.AuthenticationDTO;
import effyis.partners.socle.dto.JwtDTO;
import effyis.partners.socle.dto.RegisterDTO;
import effyis.partners.socle.entity.Account;
import effyis.partners.socle.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    @Order(1)
    void registerUser() {
        RegisterDTO validRegister = new RegisterDTO();
        validRegister.setLogin("nacer@effyis-partners.com");
        validRegister.setPassword("password12345");
        validRegister.setConfirm_password("password12345");
        JwtDTO jwtDTO = this.accountService.registerUser(validRegister);
        Assert.assertNotNull(jwtDTO);
    }

    @Test
    @Order(2)
    void authenticateUser() {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setLogin("nacer@effyis-partners.com");
        authenticationDTO.setPassword("password12345");
        JwtDTO jwtDTO = this.accountService.authenticateUser(authenticationDTO);
        Assert.assertNotNull(jwtDTO);
    }

    @Test
    @Order(3)
    void findByLogin() {
        Optional<Account> account = this.accountService.findByLogin("nacer@effyis-partners.com");
        Assert.assertNotNull(account);
    }

    @Test
    @Order(4)
    void getAccounts() {
        List<AccountDTO> accounts = this.accountService.getAccounts();
        Assert.assertNotNull(accounts);
        Assert.assertTrue(accounts.size()>0);
    }
}