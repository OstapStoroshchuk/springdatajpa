package ostap.springdatajpa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class PersonRequest {

    @NotNull
    @Size(min = 4,max = 16)
    private String firstName;

    @NotNull
    private String lastName;

    @Max(60)
    @Min(18)
    private Integer age;

    private String photo;

}
