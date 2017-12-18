package beans.models;

import lombok.Builder;
import lombok.Data;

/**
 * Store amount of money user has in the system
 */
@Data
@Builder
public class UserAccount {
    private long userId;
    private double balance;
}
