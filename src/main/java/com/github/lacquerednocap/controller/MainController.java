package com.github.lacquerednocap.controller;

import com.github.lacquerednocap.entity.Message;
import com.github.lacquerednocap.entity.User;
import com.github.lacquerednocap.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final MessageRepository messageRepository;

    public MainController(MessageRepository messageRepo) {
        this.messageRepository = messageRepo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        List<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        }
        else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);

        return "main";
    }

}
