package app.Api.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "passwordhash", nullable = false)
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "SchoolId")
    private School school;
    
//    @PrePersist
//    @PreUpdate
//    private void hashPassword() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        this.passwordHash = passwordEncoder.encode(this.passwordHash);
//    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwordHash;
	}
}
