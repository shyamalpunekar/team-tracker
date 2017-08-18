import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

        //get: show all members
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            List<Member> members = memberDao.getAll();
            model.put("descriptions", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());//get: delete all restaurants
//


        get("/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.clearAllMembers();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //  get: show new form for members
        get("/members/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
           return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Process a new Form {needs re-work}
//        post("/members/new" , (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            List<Team> allTeams = teamDao.getAll();
//            model.put("teams" ,allTeams);
//
//            String memberName = request.queryParams("inputMember");
//            String teamName = request.queryParams("teamName");
//            int memberId = Integer.parseInt(request.queryParams("memberID"));
//            Member member = new Member(memberName, memberId);
//            ArrayList<Member> memberArrayList = new ArrayList<Member>();
//            memberArrayList.add(member);
//            Map<String, List<String>> teamMembers = Member.getTeamMembers();
//
//            if(teamMembers.get(teamName) != null ) {
//
//                List<String> members = teamMembers.get(teamName);
//                members.add(memberName);
//            }
//            else {
//                List<String> members = new ArrayList<String>();
//                members.add(memberName);
//                teamMembers.put(teamName, members);
//            }
//            model.put("descriptions", memberArrayList);
//            model.put("teams", Team.getAll());
//            model.put("teamMembers", teamMembers.get(teamName));
//
//            model.put("teamName", teamName);
//            return new ModelAndView(model, "member-success.hbs");
//        }, new HandlebarsTemplateEngine());
//

//       //Creating Objects with a POST Request
//        post("/teams/new" , (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String bananas = request.queryParams("inputTeamName");
//            Team newTeam = new Team(bananas);
//            ArrayList<Team> currentTeams = new ArrayList<Team>();
//            currentTeams.add(newTeam);
//            model.put("teams", currentTeams);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
////        //get: show new post form
//        get("/teams/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "team-form.hbs");
//        }, new HandlebarsTemplateEngine());
//

        //get: show individual member
        get("/members/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToFind = Integer.parseInt(req.params("id"));
            Member foundMember = memberDao.findById(idOfBlogToFind);
            model.put("member", foundMember);
            return new ModelAndView(model, "member-details.hbs");
        }, new HandlebarsTemplateEngine());
//

        //get: delete an individual member
        get("/teams/:teams_id/members/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToDelete = Integer.parseInt(req.params("id"));
            Member deleteMember = memberDao.findById(idOfMemberToDelete);
            memberDao.deleteById(idOfMemberToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//
//
        //get: show a form to update a member
        get("/members/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int memberId = Integer.parseInt(request.params("id"));
            model.put("teams", teamDao.getAll());
            return new ModelAndView(model, "member-form.hbs");
        },new HandlebarsTemplateEngine());
//
//        //get: show a form to update a member
        get("teams/:teamId/members/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            int idOMemberToEdit = Integer.parseInt(request.params("id"));
            int teamIdOfMemberToEdit= Integer.parseInt(request.queryParams("memberId"));

            Member editMember = memberDao.findById(idOMemberToEdit);
            model.put("editMember", editMember);
            model.put("teamIdOfMemberToEdit", teamIdOfMemberToEdit);

            List<Member> allMembers = memberDao.getAll();
            List<Team> allTeams = teamDao.getAll();
            //memberDao.update(newTeamName);
            model.put("descriptions", allMembers);
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-success.hbs");
        },new HandlebarsTemplateEngine());

        //get: show a form to update a post
        post("/members/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("inputMember");
            int idOfMemberToEdit = Integer.parseInt(request.params("id"));
            int teamIdOfMemberToEdit = Integer.parseInt(request.params("memberId"));

            Member editMember = memberDao.findById(idOfMemberToEdit);

            memberDao.update(idOfMemberToEdit, newTeamName, teamIdOfMemberToEdit);

            return new ModelAndView(model, "member-success.hbs");
        },new HandlebarsTemplateEngine());


//        //showing all posts
//        get("/" , (request, response) -> {
//            Map<String , Object> model =  new HashMap<String, Object>();
//            ArrayList<Team> teams = Team.getAll();
//            model.put("teams", teams);
//            ArrayList<Member> mydef = Member.getAll();
//            model.put("descriptions", mydef);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show individual post
//        get("/teams/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfBlogToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            Team foundBlog = Team.findById(idOfBlogToFind); //use it to find post
//            model.put("team", foundBlog); //add it to model for template to display
//            return new ModelAndView(model, "team-detail.hbs"); //individual post page.
//        }, new HandlebarsTemplateEngine());
//
//        //get: show a form to update a post
//        get("/teams/:id/update" , (request, response) -> {
//        Map<String, Object> model = new HashMap<>();
//        int idOfBlogToEdit = Integer.parseInt(request.params("id"));
//        return new ModelAndView(model, "team-form.hbs");
//        },new HandlebarsTemplateEngine());
//
//        //post : process a form to updates in post
//        post("/teams: id/update" , (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newTeamName = request.queryParams("inputTeamName");
//            int idOfBlogToEdit = Integer.parseInt(request.params("id"));
//            Team editTeam = Team.findById(idOfBlogToEdit);
//            editTeam.update(newTeamName);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //showing all posts
//        get("/teams" , (request, response) -> {
//            Map<String , Object> model =  new HashMap<String, Object>();
//            ArrayList<Team> teams = Team.getAll();
//            model.put("teams", teams);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
//        //showing all posts
//        get("/members" , (request, response) -> {
//            Map<String , Object> model =  new HashMap<String, Object>();
//            Map<String , List<String>> allTeamMembers = Member.getTeamMembers();
//            model.put("allTeamMembers", allTeamMembers);
//            return new ModelAndView(model, "member-full-stack.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        //get: show a form to update a post
//        get("/members/:id/update" , (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int memberId = Integer.parseInt(request.params("id"));
//            model.put("teams", Team.getAll());
//            return new ModelAndView(model, "member-form.hbs");
//        },new HandlebarsTemplateEngine());
//
//        //get: show a form to update a post
//        post("/members/:id/update" , (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newTeamName = request.queryParams("inputMember");
//            int idOfBlogToEdit = Integer.parseInt(request.params("id"));
//            Member member = Member.findById(idOfBlogToEdit);
//            member.update(newTeamName);
//
//            return new ModelAndView(model, "member-success.hbs");
//        },new HandlebarsTemplateEngine());
//
//
//        //get: show individual post
//        get("/members/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfBlogToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            Member foundBlog = Member.findById(idOfBlogToFind); //use it to find post
//            model.put("member", foundBlog); //add it to model for template to display
//            return new ModelAndView(model, "member-details.hbs"); //individual post page.
//        }, new HandlebarsTemplateEngine());
//

    }
}
