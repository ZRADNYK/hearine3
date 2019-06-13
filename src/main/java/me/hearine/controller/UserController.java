package me.hearine.controller;

import me.hearine.domain.Playlist;
import me.hearine.domain.Role;
import me.hearine.domain.User;
import me.hearine.service.PlaylistService;
import me.hearine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController { // fixme unsubscribe, avatar

    @Autowired
    private UserService userService;

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userlist")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "parts/user/userList";
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "parts/user/userEdit";
    }

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/userlist")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user/userlist";
    }

    @GetMapping("/{user}/settings")
    public String getSettings(Model model,
                              @PathVariable User user,
                              @AuthenticationPrincipal    User currentUser) {
        if(currentUser.equals(user)) {
            model.addAttribute("email", user.getEmail());
            model.addAttribute("username", user.getUsername());
        }

        return "parts/user/settings";
    }

    @PostMapping("/{user}/settings")
    public String setSettings(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        if(currentUser.equals(user)) {
            userService.updateProfile(user, password, email);
        }
        return "redirect:/user/{user}/profile";
    }

    @GetMapping("/subscribe/{user}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        userService.subscribe(currentUser, user);

        return "redirect:/user/" + user.getId() + "/profile";
    }

    @GetMapping("/unsubscribe/{user}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        userService.unsubscribe(currentUser, user);

        return "redirect:/user/" + user.getId() + "/profile";
    }

    @GetMapping("{type}/{user}/list")
    public String subscriptionsList(
            Model model,
            @PathVariable User user,
            @PathVariable String type
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "parts/user/subscriptions";
    }

    // вывести профиль пользователя
    @GetMapping("/{user}/profile")
    public String profile(
            Model model,
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser,
            @RequestParam(required = false) Playlist playlist
    ) {
        List<Playlist> playlists;
        if(user.equals(currentUser)) {
            playlists = playlistService.findAll();
        }
        else {
            playlists = playlistService.findByAuthorAndLstAccess(user, "public");
        }

        model.addAttribute("playlists", playlists);
        model.addAttribute("user", currentUser);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("isAdmin", currentUser.isAdmin());


        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("isSubscriber", user.getSubscriptions().contains(currentUser));
        model.addAttribute("userChannel", user);

        return "parts/user/profile";
    }

}
