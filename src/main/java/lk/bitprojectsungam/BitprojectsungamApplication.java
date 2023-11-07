package lk.bitprojectsungam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController //
public class BitprojectsungamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitprojectsungamApplication.class, args);
		System.out.println("Hello world!");
	}

	@GetMapping(value =  "/test",produces = "application/json")
	public String testRequest(){
		return "Welcome";
	}
	@RequestMapping(value = "/testui",method = RequestMethod.GET)
	public ModelAndView testUi(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test.html");
		return modelAndView;
	}

}
