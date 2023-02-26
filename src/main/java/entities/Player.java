package entities;

public class Player {
    private String name;
    private PlayerRole role;

    public Player(String name, PlayerRole role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public PlayerRole getRole() {
        return role;
    }
}
