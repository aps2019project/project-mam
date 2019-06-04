package Model.user;

import java.util.Comparator;

public class SortUsers implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user2.getNumberOfWin() - user1.getNumberOfWin();
    }
}
