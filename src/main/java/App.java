import models.Definition;
import models.Team;
import models.Word;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


//        //Creating Objects with a POST Request
        post("/words/new" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String bananas = request.queryParams("inputMeaning");
            Word newWord = new Word(bananas);
            ArrayList<Word> currentWords = new ArrayList<Word>();
            currentWords.add(newWord);
            model.put("words", currentWords);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//        //get: show new post form
        get("/words/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        //Creating Objects with a POST Request
        post("/definitions/new" , (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String bananas = request.queryParams("inputDefinition");
            Definition myDefinition = new Definition(bananas);
            ArrayList<Definition> mydef = new ArrayList<Definition>();
            mydef.add(myDefinition);
            model.put("descriptions", mydef);
            model.put("words", Word.getAll());
            return new ModelAndView(model, "member-details.hbs");
        }, new HandlebarsTemplateEngine());

      //  get: show new post form for defintions
        get("/definitions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("words", Word.getAll());
           return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //showing all posts
        get("/" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            ArrayList<Word> words = Word.getAll();
            model.put("words", words);
            ArrayList<Definition> mydef = Definition.getAll();
            model.put("descriptions", mydef);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show individual post
        get("/words/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBlogToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Word foundBlog = Word.findById(idOfBlogToFind); //use it to find post
            model.put("word", foundBlog); //add it to model for template to display
            return new ModelAndView(model, "team-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/words/:id/update" , (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int idOfBlogToEdit = Integer.parseInt(request.params("id"));
        Word editWord = Word.findById(idOfBlogToEdit);
        return new ModelAndView(model, "team-form.hbs");
        },new HandlebarsTemplateEngine());

        //post : process a form to updates in post
        post("/words: id/update" , (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newMeaning = request.queryParams("inputMeaning");
            int idOfBlogToEdit = Integer.parseInt(request.params("id"));
            Word editWord = Word.findById(idOfBlogToEdit);
            editWord.update(newMeaning);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //showing all posts
        get("/words" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            ArrayList<Word> words = Word.getAll();
            model.put("words", words);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //showing all posts
        get("/definitions" , (request, response) -> {
            Map<String , Object> model =  new HashMap<String, Object>();
            ArrayList<Definition> descriptions = Definition.getAll();
            model.put("descriptions", descriptions);
            return new ModelAndView(model, "member-details.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
