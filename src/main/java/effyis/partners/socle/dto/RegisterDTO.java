package effyis.partners.socle.dto;


import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;


public class RegisterDTO extends BaseDTO {

    @NotNull
    //@Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$", message = "incorrect email syntax")
    @Email(message = "Email should be valid")
    @Schema(description = "the login of the user in db or ldap")
    private String login;
    @NotNull
    @Size(min = 8, max = 50)
    @Schema(description = "the password of the user in db or ldap")
    private String password;
    @NotNull
    @Size(min = 8, max = 50)
    @Schema(description = "confirm password")
    private String confirm_password;


    @AssertTrue(message = "Password and confirm password are not the same")
    private boolean isOk() {
        return password.equals(confirm_password);
    }


    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login;}

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}

    public String getConfirm_password() { return confirm_password;}

    public void setConfirm_password(String confirm_password) { this.confirm_password = confirm_password;}


    @Override
    public String toString() {
        StringBuilder audit = new StringBuilder("{login : ");
        return audit.append(this.login).append(" }").toString();
    }

    public RegisterDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
}
