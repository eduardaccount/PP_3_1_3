package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_roles")
//@IdClass(UsersRoles.UsersRolesId.class)
public class UsersRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long row_id;
    private long user_id;
    private long role_id;

    public UsersRoles() {
    }

    public UsersRoles(long user_id, long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

//    @Embeddable
//    public static class UsersRolesId implements Serializable {
//        @Column(name = "user_id")
//        private long user_id;
//        @Column(name = "role_id")
//        private long role_id;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            UsersRolesId that = (UsersRolesId) o;
//
//            if (user_id != that.user_id) return false;
//            return role_id == that.role_id;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = (int) (user_id ^ (user_id >>> 32));
//            result = 31 * result + (int) (role_id ^ (role_id >>> 32));
//            return result;
//        }
//    }
}
