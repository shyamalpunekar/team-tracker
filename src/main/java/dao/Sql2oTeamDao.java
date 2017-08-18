package dao;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        String sql = "INSERT INTO teams (teamName) VALUES (:teamName)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("teamName", team.getTeamName())
                    .addColumnMapping("TEAMNAME", "teamName")
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


}
