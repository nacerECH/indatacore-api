package effyis.partners.socle.unit.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import effyis.partners.socle.security.JWTProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */
@SpringBootTest
public class JwtProviderTest {

	@Test
	public void generateJWTTest() {
		String jwt = JWTProvider.generateJWT("hamza", "ADMIN", "secret", 120000);
		Assert.assertNotNull(jwt);
	}

	@Test
	public void parseJwtTest() {
		String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYW16YSIsImV4cCI6MTY3NzE2MjMzNCwicm9sZSI6IkRFRkFVTFQifQ.emMWkFhfm8HGuK9v9RQvTf5o_QEbbvMyChwRh6NgqQvLQvk16uTiz9lrl2wUA5ykLrGZFQglQm_KG21apTbicg";
		Jws<Claims> parsedJwt = JWTProvider.parseJwt(jwt, "secret");
		Assert.assertEquals("hamza", parsedJwt.getBody().getSubject());
	}
}
