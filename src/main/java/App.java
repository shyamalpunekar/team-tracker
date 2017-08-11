import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import models.Member;
import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

/**
 * Created by spunek on 8/11/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get : render home page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //Creating Objects with a POST Request
        post("/words/new" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String bananas = request.queryParams("inputMeaning");
            Team newTeam = new Team(bananas);
            ArrayList<Team> myTeams = Team.getAll();
            model.put("Teams", myTeams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new post form for defintions
        get("/definitions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int foundMember = Integer.parseInt(req.params("id")) ;
            Member found= Member.find(foundMember);
            model.put("member", found);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Creating Objects with a POST Request
        post("/definitions/new" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String bananas = request.queryParams("inputMember");
            Member myMember = new Member(bananas);
            List<Member> mydef = Member.getAll();
            model.put("descriptions", mydef);
            return new ModelAndView(model, "member-success.hbs");
        }, new HandlebarsTemplateEngine());

        //showing all posts
        get("/" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            ArrayList<Team> Teams = Team.getAll();
            model.put("Teams", Teams);
            List<Member> mydef = Member.getAll();
            model.put("descriptions", mydef);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show individual post
        get("/words/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToFind = Integer.parseInt(req.params("inputMeaning")); //pull id - must match route segment
            Team foundBlog = Team.findById(idOfBlogToFind); //use it to find post
            model.put("word", foundBlog); //add it to model for template to display
            return new ModelAndView(model, "post-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/words/:id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToEdit = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findById(idOfBlogToEdit);
            return new ModelAndView(model, "post-form.hbs");
        },new HandlebarsTemplateEngine());

        //post : process a form to updates in post
        post("/words: id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newMeaning = request.queryParams("inputMeaning");
            int idOfBlogToEdit = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findById(idOfBlogToEdit);
            editTeam.update(newMeaning);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //showing all posts
        get("/words" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            ArrayList<Team> Teams = Team.getAll();
            model.put("words", Teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
