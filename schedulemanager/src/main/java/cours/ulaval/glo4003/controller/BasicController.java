package cours.ulaval.glo4003.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

}
