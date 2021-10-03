package president.voting.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "voters")
public class Voter implements Serializable {

    @Id
    @Column(unique = true)
    private int ssn;
    @Column
    @Enumerated(EnumType.STRING)
    private Region region;

    protected Voter() {
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "ssn=" + ssn +
                ", region=" + region +
                '}';
    }
}
