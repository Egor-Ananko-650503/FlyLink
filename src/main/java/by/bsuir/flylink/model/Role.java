package by.bsuir.flylink.model;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @SequenceGenerator(name = "role_id_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "role_id_gen")
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
