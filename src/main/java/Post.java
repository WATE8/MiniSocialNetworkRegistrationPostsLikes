import java.util.HashSet;
import java.util.Set;

public class Post {
    private static int counter = 1;
    private int id;
    private User author;
    private String content;
    private Set<User> likes = new HashSet<>();

    public Post(User author, String content) {
        this.id = counter++;
        this.author = author;
        this.content = content;
    }

    public void like(User user) {
        if (likes.contains(user)) {
            System.out.println("Вы уже лайкнули этот пост.");
        } else {
            likes.add(user);
            System.out.println("Лайк засчитан.");
        }
    }

    public void show() {
        System.out.println("[" + id + "] " + author + ": " + content + " ❤️ " + likes.size());
    }

    public int getId() {
        return id;
    }
}
