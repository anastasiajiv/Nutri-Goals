package src.entity;

import java.time.LocalDateTime;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    double getUserHeight();

    double getUserWeight();

    int getUserAge();

    int getUserExcerciseLevel(); // Excersice level will be in a range from 1 - 5 --> Specify each levels
                                // logistics in the ReadMe file

    String getUserRestriction(); // Restriction type will be stored as a hashmap <String(Restriction Type),
                                // Boolean(Weather user clicked this option or not)>
}
