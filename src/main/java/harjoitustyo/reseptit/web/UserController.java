package harjoitustyo.reseptit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitustyo.reseptit.domain.AppUser;
import harjoitustyo.reseptit.domain.AppUserRepository;
import harjoitustyo.reseptit.domain.NewUser;

@Controller
public class UserController {
	
	@Autowired
	private AppUserRepository repository;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin/addNewUser")
	public String newAppUser(Model model) {
		model.addAttribute("newUser", new NewUser());
		return "addNewUser";
	}
	
	@PostMapping("/admin/saveUser")
	public String saveUser(@Valid @ModelAttribute("newUser") NewUser newUser, BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors()) { 
			
			if (newUser.getPassword().equals(newUser.getPasswordCheck())) { 
				
				String pwd = newUser.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				AppUser newAppUser = new AppUser();
				newAppUser.setPasswordHash(hashPwd);
				newAppUser.setUsername(newUser.getUsername());
				newAppUser.setRole("USER");
				
				if (repository.findByUsername(newUser.getUsername()) == null) { 
					repository.save(newAppUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Tunnus on jo olemassa");
					return "addNewUser";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Salasanat eivät täsmää");
				return "addNewUser";
			}
		} else {
			return "addNewUser";
		}
		return "redirect:/login";
	}

}
