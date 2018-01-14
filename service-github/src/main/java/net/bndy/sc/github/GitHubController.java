package net.bndy.sc.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github")
public class GitHubController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GitHubConfig config;

    @RequestMapping(path= "/me", method = RequestMethod.GET)
    public Object me() {
    		System.out.println(this.config);
        return "location.href='http://bndy.net'";
    }
    
    @RequestMapping(path= "/authorize", method = RequestMethod.GET)
    public String authorize() {
    		return "";
    }
}
