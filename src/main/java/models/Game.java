package models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<String> players;

    public Game() {
        this.players = new ArrayList<>();
        this.players.add("red");
        this.players.add("green");
        this.players.add("purple");
        this.players.add("white");
    }
}
