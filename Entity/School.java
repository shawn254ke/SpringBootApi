package app.Api.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schoolID")
    private int schoolId;

    @Column(name = "schoolname", nullable = false)
    private String schoolName;

    @Column(name = "SchemaName", unique = true,  nullable = false)
    private String schemaName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email")
    private String email;

    @Column(name = "Motto")
    private String motto;

    @Column(name = "billingStatus")
    private Boolean billingStatus = true; // Default billingStatus to true

    @OneToMany(mappedBy = "school")
    private List<User> users; // Assuming a school can have multiple users
    
    @PrePersist
    private void generateSchemaName() {
        if (schemaName == null) {
            schemaName = "schema_" + UUID.randomUUID().toString().replace("-", "");
        }
}
    
}

