package fr.univlorraine.gheintz.RESTinpeace.model;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;

/**
 * Light representation of a Grave for CSV export
 */
public class LightGrave {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfDeath;

    public LightGrave() {
    }

    public LightGrave(Grave grave) {
        this.firstName = grave.getFirstName();
        this.lastName = grave.getLastName();
        this.dateOfBirth = grave.getDateOfBirthString();
        this.dateOfDeath = grave.getDateOfDeathString();
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}