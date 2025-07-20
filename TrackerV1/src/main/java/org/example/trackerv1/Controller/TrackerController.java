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
        project.setId(projects.size());
        projects.add(project);
        return new ApiResponse("project successfully created");
    }

    @GetMapping("/show")
    public ArrayList<Project> getProjects(){
        return projects;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@RequestBody Project project, @PathVariable int index){
        if(index < projects.size()) {
            project.setId(projects.get(index).getId());
            projects.set(index, project);
            return new ApiResponse("project successfully updated");
        }else {
            return new ApiResponse("project not found");
        }
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index){
        if(index < projects.size()) {
            projects.remove(index);
            for (int i = index; i < projects.size(); i++) {
                projects.get(i).setId(projects.get(i).getId()-1);
            }
            return new ApiResponse("project successfully deleted");
        }else  {
            return new ApiResponse("project not found");
        }
    }

    @PutMapping("/update-status/{index}")
    public ApiResponse updateProjectStatus(@PathVariable int index){
        if(index < projects.size()) {
            if (projects.get(index).isStatus()) {
                return new ApiResponse("project status already done");
            } else {
                projects.get(index).setStatus(true);
                return new ApiResponse("project status updated done");
            }
        }else{
            return new ApiResponse("project not found");
        }
    }


    @GetMapping("/find/title/{title}")
    public Object getProjectByTitle(@PathVariable String title){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title)){
                return project;
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
