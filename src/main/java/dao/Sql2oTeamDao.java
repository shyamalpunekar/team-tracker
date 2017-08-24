package dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by spunek on 8/18/17.
 */
public class Sql2oTeamDao implements TeamDao{

    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o){
        this.sql2o = sql2o;

    }

    @Override
    public void add(Team team) {
        String sql = "INSERT INTO teams (teamName, createdAt) VALUES (:teamName, :createdAt)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("teamName", team.getTeamName())
                    .addParameter("createdAt", team.getCreatedAt().toString())

                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Team findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    @Override
    public List<Team> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams")
                    .executeAndFetch(Team.class);
        }
    }

    @Override
    public void update(int id, String newTeamName){
        String sql = "UPDATE teams SET (teamName) = (:teamName) WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("teamName", newTeamName)

                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteTeamById(int id) {
        String sql = "DELETE from teams WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void clearAllTeams() {
        String sql = "DELETE from teams";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public int getTeamByName(String  teamName) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT id FROM teams WHERE teamName = :teamName")
                    .addParameter("teamName", teamName)
                    .executeAndFetch(Team.class)
                    .get(0).getId();
        }
    }




}
