package com.dlis.dip.mode;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "userd")
//@EnableConfigurationProperties(Userd.class)
public class Userd {
    private Long id;

    private String username;

    private String password;

    private String relaname;


}