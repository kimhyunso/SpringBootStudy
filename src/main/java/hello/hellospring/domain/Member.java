package hello.hellospring.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {

    private Long id;
    private String name;



}
