package effyis.partners.socle.dto;

public class RegisterValidationDto {

    private String password;
    private  String ok ;
    private  String login;

    public String getPassword() {
        return password;
    }

    public String getOk() {
        return ok;
    }

    public String getLogin() {
        return login;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    private  String confirm_password;

}
