package fr.univlorraine.gheintz.RESTinpeace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Grave")
public class Grave {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "First_name", length = 64, nullable = false)
    private String firstName;

    @Column(name = "Last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "Birth_name", length = 64, nullable = true)
    private String birthName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth", nullable = false)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Death", nullable = false)
    private Date dateOfDeath;

    @Column(name = "Epitaph", length = 255, nullable = true)
    private String epitaph;

    @Column(name = "Longitude", nullable = false)
    private Double longitude;

    @Column(name = "Latitude", nullable = false)
    private Double latitude;

    public Grave() {}

    public Grave(String firstName, String lastName, String birthName, Date dateOfBirth, Date dateOfDeath, String epitaph, Double longitude, Double latitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthName = birthName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.epitaph = epitaph;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthName() {
        return birthName;
    }

    @JsonIgnore
    public String getName() {
        return firstName + ' ' + lastName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getEpitaph() {
        return epitaph;
    }

    public void setEpitaph(String epitaph) {
        this.epitaph = epitaph;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getDateOfBirthString(String format) {
        SimpleDateFormat dt = new SimpleDateFormat(format);
        return dt.format(dateOfBirth);
    }

    public String getDateOfDeathString(String format) {
        SimpleDateFormat dt = new SimpleDateFormat(format);
        return dt.format(dateOfBirth);
    }

    @JsonIgnore
    public String getDateOfBirthString() {
        return getDateOfBirthString("dd.MM.yyy");
    }


    @JsonIgnore
    public String getDateOfDeathString() {
        return getDateOfDeathString("dd.MM.yyy");
    }


    @Override
    public String toString() {
        String str = "Grave " + id + ": " + firstName + " " + lastName + " ";

        if (birthName != "") {
            str += "(" + birthName + ") ";
        }

        str += getDateOfBirthString() + " - " + getDateOfDeathString();

        if (epitaph != "") {
            str += " \"" + epitaph + "\" ";
        }

        return str;
    }
}