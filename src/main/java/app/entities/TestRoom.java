package app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_rooms")
@Data
@NoArgsConstructor
public class TestRoom extends Room {

    @Column(name = "randomInt")
    private int randomInt;

    public TestRoom(int randomInt) {
        this.randomInt = randomInt;
    }
}
