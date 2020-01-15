package se.iths.complexjavaproject.mudders.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

}