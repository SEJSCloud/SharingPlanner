package config.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomUser {
    public CustomUser(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    private String userName;
    private String userId;
    private String jwt;
    private List<String> authorities;
}
