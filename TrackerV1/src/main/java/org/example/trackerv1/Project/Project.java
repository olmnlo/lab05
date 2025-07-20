package org.example.trackerv1.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Project {

    //: ID , title , description , status, companyName
    private String id;
    private String title;
    private String description;
    private boolean status;
    private String companyName;
}
