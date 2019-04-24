package by.bsuir.flylink.model;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "fl_role", schema = "public")
public class Role {
    @Id
    @SequenceGenerator(name = "fl_role_id_gen", sequenceName = "fl_role_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "fl_role_id_gen")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
