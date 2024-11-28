package sa.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    private String locationName;
    private Double x;
    private Double y;
}
