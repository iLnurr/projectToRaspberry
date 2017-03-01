package iserba.controller;

import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.service.UserQuotationsService;
import iserba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserQuotationsService userQuotationsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String catalog(Model model) {
        List<UserQuotations> userQuotationsList = (List<UserQuotations>) userQuotationsService.getAll();
        model.addAttribute("userQuotationsService", userQuotationsService);
        model.addAttribute("userService", userService);
        model.addAttribute("quotationsList", userQuotationsList);
        return "index";
    }

    @RequestMapping(value = "/newQuota", method = RequestMethod.GET)
    public ModelAndView newUserQuota() {
        ModelAndView model = new ModelAndView("quotaForm");
        model.addObject("userQuotations", new UserQuotations());
        model.addObject("userService", userService);
        return model;
    }

    @RequestMapping(value = "/saveQuota", method = RequestMethod.POST)
    public ModelAndView saveQuota(@ModelAttribute UserQuotations userQuotations, HttpServletRequest request) {
        String userName = request.getParameter("name");
        int userId = userService.getUserIdByUserName(userName);
        userQuotations.setDateTime(LocalDateTime.now());
        userQuotationsService.save(userQuotations, userId);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/userList")
    public ModelAndView handleRequest() throws Exception {
        List<User> listUsers = userService.getAll();
        ModelAndView model = new ModelAndView("UserList");
        model.addObject("userList", listUsers);
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser() {
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.get(userId);
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        userService.delete(userId);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        userService.save(user);
        return new ModelAndView("redirect:/");
    }
}
