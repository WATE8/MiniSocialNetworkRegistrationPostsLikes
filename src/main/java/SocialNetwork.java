import java.util.*;

public class SocialNetwork {
    private Map<String, User> users = new HashMap<>();
    private List<Post> posts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser = null;

    public void start() {
        while (true) {
            if (currentUser == null) {
                System.out.println("\n1. Регистрация\n2. Вход\n0. Выход");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> register();
                    case "2" -> login();
                    case "0" -> {
                        System.out.println("Пока!");
                        return;
                    }
                    default -> System.out.println("Неизвестная команда.");
                }
            } else {
                System.out.println("\nВы вошли как: " + currentUser);
                System.out.println("1. Создать пост\n2. Лайкнуть пост\n3. Показать все посты\n4. Выйти");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> createPost();
                    case "2" -> likePost();
                    case "3" -> showPosts();
                    case "4" -> currentUser = null;
                    default -> System.out.println("Неизвестная команда.");
                }
            }
        }
    }

    private void register() {
        System.out.print("Придумайте логин: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Такой пользователь уже существует.");
            return;
        }
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Успешная регистрация!");
    }

    private void login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Успешный вход!");
        } else {
            System.out.println("Неверные данные.");
        }
    }

    private void createPost() {
        System.out.print("Введите текст поста: ");
        String content = scanner.nextLine();
        Post post = new Post(currentUser, content);
        posts.add(post);
        System.out.println("Пост создан.");
    }

    private void showPosts() {
        if (posts.isEmpty()) {
            System.out.println("Постов пока нет.");
        } else {
            for (Post post : posts) {
                post.show();
            }
        }
    }

    private void likePost() {
        showPosts();
        System.out.print("Введите ID поста для лайка: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Post post : posts) {
                if (post.getId() == id) {
                    post.like(currentUser);
                    return;
                }
            }
            System.out.println("Пост не найден.");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID.");
        }
    }
}
