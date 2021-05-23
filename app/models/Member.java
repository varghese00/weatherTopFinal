package models;

import play.Play;
import play.db.jpa.Model;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();
    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();


    public Member(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }
    public static Member findByFirstName(String firstname) {
        return find("firstname", firstname).first();
    }
    public static Member findByLastName(String lastname) {
        return find("lastname", lastname).first();
    }
    public static Member findByPassword(String password) {
        return find("password", password).first();
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
