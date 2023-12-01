package model;

import java.util.ArrayList;

public class UI {

    public void run() {
        scenario1();
    }

    public void scenario1() {
        ArrayList<User> users = DataReader.getUsers();
        for(User user : users){
            System.out.println(user);
        }
    }

    public void scenario2(){
        DataWriter.saveUsers();
    }

    public void scenario3() {
        UiFacade facade = new UiFacade();
        facade.addUser("Jenny", "Smith", "....", null, null, null, null, null);
        facade.login("jsmith", "12345");

        System.out.println(facade.getCurrentUser());

        facade.logout();
    }

    public void scenario5() {
        UiFacade facade = new UiFacade();
        // Open Electric Missiles project
        facade.getProjectByName("Electric Missiles");
        Project electricMissiles = facade.getCurrentProject();
        facade.login("jsmith", "12345");
        
    }

    // Helper method to print the scrum board to a txt file
    // private void printScrumBoardToFile(Project project) {
    //     project.printScrumBoardToFile("scrum_board.txt");
    //     System.out.println("Scrum board printed to scrum_board.txt");
    // }

    public static void main(String[] args) {
        UiFacade Scenario = new UiFacade();
        Scenario.addUser("Atticus", "Madden", "AMadden", "password", "AMadden@gmail.com", "0000000000", "address","Project Manager");
        Scenario.getUserByName("AMadden", "password");
        Scenario.addProject("Electric Missiles");
        Scenario.addProject("Soap Free Washers");
        Scenario.addProject("Air Computers");
        Scenario.getProjectByName("Electric Missiles");
        Scenario.addColumn("ToDo");
        Scenario.addColumn("Doing");
        Scenario.addColumn("Completed");
        Scenario.addColumn("Abandoned");
        Scenario.addTask("Curve the metal to make a cylindrical shape",2,null,"this is a note for you :)","ToDo");
        Scenario.moveTaskToColumnByName("Curve the metal to make a cylindrical shape", "Doing");
        Scenario.addTask("Make impossible burger possible", 0, null, null, "ToDo");
        Scenario.moveTaskToColumnByName("Make impossible burger possible", "Abandoned");
        Scenario.addTask("Initialize super algorithm to detonate at warp speed",2,null,"this is a note for you :)","ToDo");
        Scenario.addTaskComments(Scenario.getTaskByName("Initialize super algorithm to detonate at warp speed"),"Avoid civilians Jeff!",false);
        Scenario.logout();

        /*
         List<User> users = new ArrayList<>();
         users.add(new User("user1"));
         users.add(new User("user2"));
         users.add(new User("user3"));

         List<Task> tasks = new ArrayList<>();
         tasks.add(new Task("task1"));
         tasks.add(new Task("task2"));
         tasks.add(new Task("task3"));

         Random r = new random();
         for(Task task : tasks)
         {
            User randomUser = users.get(r.nextInt(users.size()));
            task.assignUSer(randomUser);
            users.remove(randomUser);
         }

         for(Task task : tasks)
         {
            User assignedUser = task.getAssignedUser();
            System.out.println("Task: " + task.getName() + " -> Assigned User: " + (assignedUser != null ? assignedUser.getName() : "Unassigned") ) ;
         }

         */
    }
}
