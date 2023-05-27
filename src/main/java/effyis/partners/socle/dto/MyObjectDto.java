package effyis.partners.socle.dto;

import javax.validation.constraints.NotBlank;

public class MyObjectDto {
    private Long id;

    @NotBlank
    private String attribute1;

    @NotBlank
    private String attribute2;

    public MyObjectDto(Long id, String attribute1, String attribute2) {
        this.id = id;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }
    public MyObjectDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
}
