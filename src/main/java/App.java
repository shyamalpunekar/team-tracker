import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

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
        }, new HandlebarsTemplateEngine());



        get("/members/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int memberId = Integer.parseInt(req.params("id"));
            memberDao.deleteById(memberId);
            model.put("deleteAllMembers", true);
            return new ModelAndView(model, "member-success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.clearAllMembers();
            model.put("deleteAllMembers", true);
            return new ModelAndView(model, "member-success.hbs");
        }, new HandlebarsTemplateEngine());


        //  get: show new form for members
        get("/members/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/members/new" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String memberName = request.queryParams("inputMember");
            String teamName = request.queryParams("teamName");
            int  teamId = teamDao.getTeamByName(teamName);

             Member member = new Member(memberName, teamId);
            memberDao.add(member);
            model.put("descriptions", member);

            ArrayList<Member> memberArrayList = new ArrayList<Member>();
            memberArrayList.add(member);

            model.put("descriptions", memberArrayList);

            model.put("teamName", teamName);
            List<Member> members = memberDao.getAll();
            return new ModelAndView(model, "member-success.hbs");
        }, new HandlebarsTemplateEngine());



        //get: show individual member
        get("/members/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToFind = Integer.parseInt(req.params("id"));
            Member foundMember = memberDao.findById(idOfBlogToFind);

            Team teamName = teamDao.findById(foundMember.getTeamId());
            model.put("member", foundMember);
            model.put("teamName", teamName.getTeamName());
            return new ModelAndView(model, "member-details.hbs");
        }, new HandlebarsTemplateEngine());


        //get: delete an individual member
        get("/teams/:teams_id/members/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToDelete = Integer.parseInt(req.params("id"));
            Member deleteMember = memberDao.findById(idOfMemberToDelete);
            memberDao.deleteById(idOfMemberToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

       //get: show a form to update a member
        get("/members/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int idOMemberToEdit = Integer.parseInt(request.params("id"));

            Member editMember = memberDao.findById(idOMemberToEdit);
            model.put("members", editMember);
            model.put("memberName", editMember.getMemberName());
            model.put("hiddenMemberId", editMember.getId());


            //memberDao.update(newTeamName);
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "member-form.hbs");
        },new HandlebarsTemplateEngine());

        //get: show a form to update a post
        post("/members/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            String inputMember = request.queryParams("inputMember");
            String teamName = request.queryParams("teamName");
             int teamId = teamDao.getTeamByName(teamName);
            int memberId = Integer.parseInt(request.params("id"));

            Member member = memberDao.findById(memberId);
            memberDao.update(member.getId(),inputMember, teamId);

            member = memberDao.findById(memberId);
            model.put("descriptions", member);

            ArrayList<Member> memberArrayList = new ArrayList<Member>();
            memberArrayList.add(member);

            model.put("descriptions", memberArrayList);


            model.put("teamName", teamName);

            return new ModelAndView(model, "member-success.hbs");
        },new HandlebarsTemplateEngine());

        //showing all posts
        get("/" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            List<Member> mydef = memberDao.getAll();
            model.put("descriptions", mydef);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        ////show new team form
        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs"); //new
        }, new HandlebarsTemplateEngine());

//post: process new team form
        post("/teams/new", (request, response) -> { //new
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("inputTeamName");
            Team newTeam = new Team(teamName);
            teamDao.add(newTeam);
            List<Team> teams = new ArrayList<Team>();
            teams.add(newTeam);

            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show a form to update a post
        get("/teams/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToEdit = Integer.parseInt(request.params("id"));
            model.put("teams", true);
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "team-form.hbs");
        },new HandlebarsTemplateEngine());

        //get: delete an individual cuisine which id?
        get("/teams/: id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(req.params(""));
            Team deleteTeam = teamDao.findById(idOfTeamToDelete);
            teamDao.deleteTeamById(idOfTeamToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


//        //get: show individual post
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToFind = Integer.parseInt(req.params("id"));
            Team foundBlog = teamDao.findById(idOfBlogToFind);
            model.put("team", foundBlog);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());
//        //showing all posts
        get("/teams" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//
//
//        //showing all posts
        get("/members" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();

            List<Member> members = memberDao.getAll();
            Map<String, List<String>> allTeamMembers = new HashMap<String, List<String>>();

            for(Member member: members) {

                String memberName = member.getMemberName();
               int teamId = member.getTeamId();
                Team team = teamDao.findById(teamId);
                if(allTeamMembers.get(team.getTeamName()) != null ) {

                    List<String> teamMembers = allTeamMembers.get(team.getTeamName());
                    teamMembers.add(memberName);
                }
                else {
                    List<String> teamMembers = new ArrayList<String>();
                    teamMembers.add(memberName);
                    allTeamMembers.put(team.getTeamName(), teamMembers);
                }
            }

            model.put("allTeamMembers", allTeamMembers);
            return new ModelAndView(model, "member-full-stack.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show individual post
        get("/members/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            int idOfBlogToFind = Integer.parseInt(req.params("id"));
            Member foundMember = memberDao.findById(idOfBlogToFind);
            model.put("member", foundMember);
            return new ModelAndView(model, "member-details.hbs");
        }, new HandlebarsTemplateEngine());

        //removes all members on a team
        get("/teams/:id/delete-members", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            teamDao.deleteAllMembersByTeam(teamId);
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);;
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
