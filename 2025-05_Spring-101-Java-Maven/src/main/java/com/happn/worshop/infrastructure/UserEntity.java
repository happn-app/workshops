package com.happn.worshop.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserEntity {

    @Id
    private UUID id;
    private String name;
    private String email;

}
