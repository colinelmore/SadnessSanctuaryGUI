package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The ProjectManager class is responsible for managing project information.
 */
public class ProjectManager {
    private static ProjectManager projectManager;
    private ArrayList<Project> projects = new ArrayList<Project>();

    private ProjectManager() {
            this.projects = DataReader.getProjects();
       //     this.projects = new ArrayList<Project>();
    }

    /**
     * Gets the instance of the ProjectManager using the Singleton pattern.
     *
     * @return The ProjectManager instance.
     */
    public static ProjectManager getInstance() {
        if (projectManager == null) {
            projectManager = new ProjectManager();
        }
        return projectManager;
    }

    /**
     * Adds a new project to the appropriate list based on its completion status.
     *
     * @param project The project to be added.
     */
    public boolean addProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        return true;
    }

    public Project getProject(String name){
        for(int i=0; i < projects.size(); i++)
        {
            if(projects.get(i).getProjectName().equals(name))
            {
                return projects.get(i);    
            }
        }
        return null;
    }

    /**
     * Removes a project from the appropriate list based on its completion status.
     *
     * @param project The project to be removed.
     */
    public void removeProject(Project project) {
        projects.remove(project);
    }


    public ArrayList<Project> getAllProjects(){
        return projects;
    }

    public void saveProjects() {
        DataWriter.saveProjects();
    }
}