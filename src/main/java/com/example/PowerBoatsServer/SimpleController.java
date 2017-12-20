package com.example.PowerBoatsServer;

import models.Command;
import models.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    Command command = new Command();

    @GetMapping("/text")
    @ResponseBody
    public String text(Model model) {
        return "hello world";
    }

    @GetMapping("/json")
    @ResponseBody
    public String json(Model model) {
        return "{'players': ['Joe','Kenny','Alice']}";
    }

    @GetMapping("/game")
    @ResponseBody
    public Game game(Model model) {
        return new Game();
    }

    @PostMapping("/command")
    @ResponseBody
    public Command setCommand(
            @RequestParam(required=false, defaultValue="same") String speed,
            @RequestParam(required=false, defaultValue="same") String turn,
            @RequestParam(required=false, defaultValue="false") boolean commit,
            Model model) {

        System.out.println("speed: " + speed + " turn: " + turn + " commit: " + commit);

        Command command = this.command;
        if (speed.equals("speedup")) {
           command.goFaster();
        } else if (speed.equals("slowdown")) {
            command.slowDown();
        } else if (speed.equals("maintainspeed")) {
            command.maintainSpeed();
        }

        if (turn.equals("left")) {
            command.turnLeft();
        } else if (turn.equals("right")) {
            command.turnRight();
        } else if (turn.equals("straight")) {
            command.goStraight();
        }

        if (commit) {
            command.commit();
        }

        System.out.println(command);

        // save the command that was just set so it can be polled later.
        this.command = command;
        return command;
    }

    @GetMapping("/command")
    @ResponseBody
    public Command command(Model model) {
        this.command.isRead = true;
        return this.command;
    }

    @GetMapping("/command/reset")
    @ResponseBody
    public Command reset(Model model) {
        this.command = new Command();
        return this.command;
    }
}
