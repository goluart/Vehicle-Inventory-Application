package com.project.vehicleinventory.web;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.vehicleinventory.domain.GearingRepository;
import com.project.vehicleinventory.domain.User;
import com.project.vehicleinventory.domain.UserData;
import com.project.vehicleinventory.domain.UserRepository;
import com.project.vehicleinventory.domain.Vehicle;
import com.project.vehicleinventory.domain.VehicleRepository;

@Controller
public class VehicleController {
    @Autowired
    private VehicleRepository vrepository;
    @Autowired
    private GearingRepository grepository;
    @Autowired
    private UserRepository urepository;

    // REST get all vehicles
    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public @ResponseBody List<Vehicle> vehicleListRest() {
        return (List<Vehicle>) vrepository.findAll();
    }

    // REST get vehicle by id
    @RequestMapping(value = "//{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Vehicle> findVehicleRest(@PathVariable("id") Long vehicleId) {
        return vrepository.findById(vehicleId);
    }

    // index page
    @RequestMapping(value = { "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "/vehiclelist")
    public String vehicleList(Model model) {
        model.addAttribute("vehicles", vrepository.findAll());
        return "vehiclelist";
    }

    // Add vehicle
    @RequestMapping(value = "/add")
    public String addVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("gearing", grepository.findAll());
        model.addAttribute("user", urepository.findAll());
        System.out.println(model);
        return "addvehicle";
    }

    // Saving vehicle
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Vehicle vehicle) {
        vrepository.save(vehicle);
        return "redirect:vehiclelist";
    }

    // Delete vehicle
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable("id") Long vehicleId, Model model) {
        vrepository.deleteById(vehicleId);
        return "redirect:../vehiclelist";
    }

    // Edit vehicle
    @RequestMapping(value = "/edit/{id}")
    public String showModVeh(@PathVariable("id") Long vehicleId, Model model) {
        model.addAttribute("vehicle", vrepository.findById(vehicleId));
        model.addAttribute("gearing", grepository.findAll());
        return "editvehicle";
    }

    // User registration page
    @RequestMapping(value = "/register")
    public String addUser(Model model) {
        model.addAttribute("userdata", new UserData());
        return "register";
    }

    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("userdata") UserData userData, BindingResult bindingResult) {
        // Email validation
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        // Validation errors
        if (!bindingResult.hasErrors()) {
            // check if password matches
            if (userData.getPassword().equals(userData.getPasswordCheck())) {
                // Encrypt password with BCrypt
                String pwd = userData.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                // Check email with regex pattern and return error if it does not match
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(userData.getEmail());
                if (!matcher.matches()) {
                    bindingResult.rejectValue("email", "err.email", "Email doesn't meet requirements");
                    return "register";
                }

                // Set values to user object
                User newUser = new User();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(userData.getUsername());
                newUser.setEmail(userData.getEmail());
                newUser.setRole("USER");

                // Check if user exists
                if (urepository.findByUsername(userData.getUsername()) == null) {
                    urepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "register";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
                return "register";
            }
        } else {
            return "register";
        }
        return "redirect:/login?success=true";
    }

}
