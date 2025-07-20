package org.example.trackerv1.Controller;

import org.example.trackerv1.ApiResponse.ApiResponse;
import org.example.trackerv1.Project.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {

    private ArrayList<Project> projects =  new ArrayList<>();


    @PostMapping("/create")
    public ApiResponse create(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("project successfully created");
    }

    @GetMapping("/show")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@RequestBody Project project, @PathVariable int index){
        projects.set(index, project);
        return new ApiResponse("project successfully updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return new ApiResponse("project successfully deleted");
    }

    @PutMapping("/update-status/{index}")
    public ApiResponse updateProjectStatus(@PathVariable int index){
        if(projects.get(index).getStatus().equalsIgnoreCase("done")){
            projects.get(index).setStatus("not done");
        }else {
            projects.get(index).setStatus("done");
        }
        return new ApiResponse("project successfully updated");
    }


    @GetMapping("/find/title/{title}")
    public ApiResponse getProjectByTitle(@PathVariable String title){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title)){
                return new ApiResponse("project is found"+ project);
            }
        }
        return new ApiResponse("project not found");
    }


    @GetMapping("/find/company-name/{companyName}")
    public ArrayList<Project> getProjectsByCompanyName(@PathVariable String companyName){
        ArrayList<Project> company_projects = new ArrayList<>();

        for(Project project : projects){
            if(project.getCompanyName().equalsIgnoreCase(companyName)){
                company_projects.add(project);
            }
        }
        return company_projects;
    }




}
