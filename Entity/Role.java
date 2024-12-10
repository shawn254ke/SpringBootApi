package app.Api.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "rolename", nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users; // Assuming a role can have multiple users
}
