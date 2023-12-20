package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * The UserEntity class represents a user in the system.
 */
@Entity
@Table(name = "user", schema = "mycoolstore")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "usr_id")
    private Long usrId;

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public Long getUsrId() {
        return usrId;
    }

    /**
     * Sets the user ID.
     *
     * @param usrId The user ID.
     */
    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    @Column(name = "usr_email")
    private String usrEmail;

    /**
     * Gets the user email.
     *
     * @return The user email.
     */
    public String getUsrEmail() {
        return usrEmail;
    }

    /**
     * Sets the user email.
     *
     * @param usrEmail The user email.
     */
    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    @Column(name = "usr_password")
    private String usrPassword;

    /**
     * Gets the user password.
     *
     * @return The user password.
     */
    public String getUsrPassword() {
        return usrPassword;
    }

    /**
     * Sets the user password.
     *
     * @param usrPassword The user password.
     */
    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    /**
     * Generates SHA-512 hash of the given password.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
    public static String getHashSha512Password(String password) {
        String passwordHash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            passwordHash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm wasn't found: " + e);
        }

        return passwordHash;
    }

    @Column(name = "usr_role")
    private String usrRole;

    /**
     * Gets the user role.
     *
     * @return The user role.
     */
    public String getUsrRole() {
        return usrRole;
    }

    /**
     * Sets the user role.
     *
     * @param usrRole The user role.
     */
    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }

    @Column(name = "usr_banned_by")
    private Long usrBannedBy;

    /**
     * Gets the ID of the user who banned this user.
     *
     * @return The ID of the user who banned this user.
     */
    public Long getUsrBannedBy() {
        return usrBannedBy;
    }

    /**
     * Sets the ID of the user who banned this user.
     *
     * @param usrBannedBy The ID of the user who banned this user.
     */
    public void setUsrBannedBy(Long usrBannedBy) {
        this.usrBannedBy = usrBannedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(usrId, that.usrId)) return false;
        if (!Objects.equals(usrEmail, that.usrEmail)) return false;
        if (!Objects.equals(usrPassword, that.usrPassword)) return false;
        if (!Objects.equals(usrRole, that.usrRole)) return false;
        return Objects.equals(usrBannedBy, that.usrBannedBy);
    }

    @Override
    public int hashCode() {
        int result = usrId != null ? usrId.hashCode() : 0;
        result = 31 * result + (usrEmail != null ? usrEmail.hashCode() : 0);
        result = 31 * result + (usrPassword != null ? usrPassword.hashCode() : 0);
        result = 31 * result + (usrRole != null ? usrRole.hashCode() : 0);
        result = 31 * result + (usrBannedBy != null ? usrBannedBy.hashCode() : 0);
        return result;
    }
}
