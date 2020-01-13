package fr.univlorraine.gheintz.RESTinpeace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Grave")
public class Grave {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Column(name = "Epitaph", length = 1024, nullable = true)
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

    public Grave(String firstName, String lastName, String birthName, String dateOfBirth, String dateOfDeath, String epitaph, Double longitude, Double latitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthName = birthName;
        this.epitaph = epitaph;
        this.longitude = longitude;
        this.latitude = latitude;

        try {
            this.dateOfBirth = parseDate(dateOfBirth);
            this.dateOfDeath = parseDate(dateOfDeath);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
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

    public void setDateOfBirth(String dateOfBirth) throws ParseException { this.dateOfBirth = parseDate(dateOfBirth); }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) throws ParseException { this.dateOfDeath = parseDate(dateOfDeath); }

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
        return dt.format(dateOfDeath);
    }

    @JsonIgnore
    public String getDateOfBirthString() {
        return getDateOfBirthString("dd.MM.yyy");
    }


    @JsonIgnore
    public String getDateOfDeathString() {
        return getDateOfDeathString("dd.MM.yyy");
    }

    @JsonIgnore
    private Date parseDate(String date) throws ParseException {
        SimpleDateFormat internationalFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat slashEuropeanFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dotEuropeanFormatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return internationalFormatter.parse(date);
        } catch (ParseException ex1) {
            try {
                return slashEuropeanFormatter.parse(date);
            } catch (ParseException ex2) {
                return dotEuropeanFormatter.parse(date);
            }
        }
    }

    @Override
    public String toString() {
        String str = id + ": " + firstName + " " + lastName;

        if (!birthName.equals("")) {
            str += " (" + birthName + ")";
        }

        str += ", " + getDateOfBirthString() + " - " + getDateOfDeathString() + ",";

        if (!epitaph.equals("")) {
            str += " \"" + epitaph + "\" ";
        }
        System.out.println(str);
        return str;
    }
}