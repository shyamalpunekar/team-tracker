package dao;

import models.Member;
import models.Team;

import java.util.List;

/**
 * Created by spunek on 8/18/17.
 */
public interface TeamDao {
    //create -------------
    void add (Team team);
   // List<Member> getAllMembersByTeam(int memberId);

    //read -------------
    List<Team> getAll();
    //
    Team findById(int id);
    //
//
    //update -------------
    void update(int id, String teamName);
    //
//
    //delete -------------
    void deleteTeamById(int id);
    //
   // void clearAllTeams();

}
