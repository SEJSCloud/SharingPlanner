package config.security.model.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class UserEntity {

    @Id
    private String userId;
    private String userName;
}
