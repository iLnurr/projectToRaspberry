package iserba.controller;

import iserba.model.User;
import iserba.model.UserQuotations;
import iserba.service.UserQuotationsService;
import iserba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserQuotationsService userQuotationsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String catalog(Model model) {
        List<UserQuotations> userQuotationsList = (List<UserQuotations>) userQuotationsService.getAll();
        List<String> result = new ArrayList<>();
        for (UserQuotations uq: userQuotationsList){
            String userName = userService.get(userQuotationsService.getUserId(uq.getId())).getName();
            String date = uq.getDateTime().toLocalDate().toString();
            String quotation = uq.getDescription();
            result.add(date + " " + userName + ": " + quotation);
        }
        List<String> reversedCopy = result.subList(0, result.size());
        Collections.reverse(reversedCopy);
        model.addAttribute("quotationsList", result);
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
    public ModelAndView listOfUsers(){
        ModelAndView model = new ModelAndView("UserList");
        List<User> listUsers = userService.getAll();
        model.addObject("userList", listUsers);
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser() {
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", new User());
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user) {
        userService.save(user);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Integer id) {
        User user = userService.get(id);
        ModelAndView model = new ModelAndView("UserForm");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value="/edit/save", method=RequestMethod.POST)
    public ModelAndView editingUser(@ModelAttribute User user) {
        userService.save(user);
        return new ModelAndView("redirect:/userList");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
