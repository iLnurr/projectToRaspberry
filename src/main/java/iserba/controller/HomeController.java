package iserba.controller;

import iserba.service.UserQuotationsService;
import iserba.service.UserService;
import iserba.to.UserQuotationsTo;
import iserba.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserQuotationsService userQuotationsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String catalog(Model model) {
        List<String> result = userQuotationsService.getSortedQuotations();
        model.addAttribute("quotationsList", result);
        return "index";
    }

    @RequestMapping(value = "/newQuota", method = RequestMethod.GET)
    public ModelAndView newUserQuota() {
        ModelAndView model = new ModelAndView("quotaForm");
        List<UserTo> users = userService.getAll();
        model.addObject("userQuotationsTo", new UserQuotationsTo());
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/saveQuota", method = RequestMethod.POST)
    public String saveQuota(
            @Valid @ModelAttribute("userQuotationsTo") UserQuotationsTo userQuotationsTo,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()){
            List<UserTo> users = userService.getAll();
            model.addAttribute("users", users);
            return "quotaForm";
        }

        userQuotationsService.save(userQuotationsTo);
        return "redirect:/";
    }

    @RequestMapping("/userList")
    public ModelAndView listOfUsers(){
        ModelAndView model = new ModelAndView("UserList");
        List<UserTo> listUsers = userService.getAll();
        model.addObject("userList", listUsers);
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUser() {
        return new ModelAndView("UserNewForm", "user", new UserTo());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") UserTo userTo, BindingResult result) {
        if (result.hasErrors()){
            return "UserNewForm";
        }
        userService.save(userTo);
        return "index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Integer id) {
        return new ModelAndView("UserEditForm","user", userService.getTo(id));
    }

    @RequestMapping(value="/edit/save", method=RequestMethod.POST)
    public String editingUser(@Valid @ModelAttribute("user") UserTo userTo, BindingResult result) {
        if (result.hasErrors()){
            return "UserEditForm";
        }
        userService.update(userTo);
        return "redirect:/userList";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
