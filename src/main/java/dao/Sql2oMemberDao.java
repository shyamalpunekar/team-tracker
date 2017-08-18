package dao;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

/**
 * Created by spunek on 8/18/17.
 */
public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }



    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members (memberName, memberId) VALUES (:memberName, :memberId)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("memberName", member.getMemberName())
                    .addParameter("memberId", member.getMemberId())
                    .addColumnMapping("MEMBERNAME", "memberName")
                    .addColumnMapping("MEMBERID", "memberId")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
