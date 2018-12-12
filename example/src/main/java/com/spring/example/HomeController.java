package com.spring.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.print.attribute.standard.JobSheets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.View;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value="/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		try {
			//젠킨스 서버 URI를 통한 연결
			JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8081/jenkins"));
			//JenkinsServer methodGet a list of all the defined jobs on the server (in the given folder)
			//isRunning(); - 현재 젠킨스 서버의 상태를 가저옴 boolean
			//getVersion(); - 젠킨스 버전 JenkinsVersion
			//getJobs();- 서버에 선언한 모든 job을 가져옴 Map<String, Job>
			//getJobs(FolderJob folder)  - 지정된 폴더에 있는 모든 잡을 가져옴 Map<String, Job>
			//
			
			Map<String, Job> jobs;
			View view;
			
			try {
				jobs = jenkins.getJobs();
				JobWithDetails job = jobs.get("firstJenkind").details();
				System.out.println("isRunning() : "+jenkins.isRunning());
				System.out.println("------------------------------------");
				System.out.println("JobName : " + job.getName());
				/*view = jenkins.getView("firstJenkind");
				System.out.println(view.getUrl());
				System.out.println(view.getName());*/
				
				String xmlJob = jenkins.getJobXml("firstJenkind");
				
				
				
				//jenkins.deleteJob("test1");
				
				System.out.println(xmlJob);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		return "home";
	}
	
}
