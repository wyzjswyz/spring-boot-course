package top.zds;


import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class Team {
    @Value("${team.leader}")
    @NotNull
    @Length(min = 2, max = 10, message = "长度必须介于 2 和 10 之间")
    private String leader;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号码格式有误")
    private String phone;

    @Value("${team.age}")
    @Max(5)
    @Min(1)
    private String age;

    @Value("${team.createDate}")
    @Past(message = "日期必须是过去的时间")
    private LocalDate createDate;
}