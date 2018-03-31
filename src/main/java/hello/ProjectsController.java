package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Controller
public class ProjectsController {

    @GetMapping("/projects")
    public String projects(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Project[] response = restTemplate.getForObject("http://localhost:8080/projects", Project[].class);
        model.addAttribute("projects", response);

        return "projects";
    }

    @PostMapping("/projects")
    public String createProject(@ModelAttribute Project project) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation("http://localhost:8080/projects", project);
        System.out.println("uri = " + uri);
        return "redirect:/projects";
    }

    @GetMapping("/project")
    public String project(Model model) {
        model.addAttribute("project", new Project());
        return "project";
    }
}
