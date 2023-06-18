package com.f1dot5.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date createdAt = new Date();
    @Id
    private String name;
    private String password;
    private String phone;
    private String email;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
