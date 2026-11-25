
import java.time.LocalDateTime;

public class Car {

    String carNumber;
    LocalDateTime entryTime;

    Car(String carNumber) {
        this.carNumber = carNumber;
        this.entryTime = LocalDateTime.now();
    }
}
