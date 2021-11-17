package member;

import java.sql.*;

import db.DBConnect;


public class MemberDAO {

    private Connection conn = DBConnect.getConnect();
    private PreparedStatement pstm;
    private ResultSet rs;

    private static MemberDAO memberDao;


    private MemberDAO() throws Exception{

    }

    //싱글톤
    public static MemberDAO getInstance() {
        if(memberDao == null) {
            try {
                memberDao = new MemberDAO();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return memberDao;
    }
    public void createTable(String dbName) throws SQLException {

        String tableCheckSql = "SELECT 1 FROM Information_schema.tables " +
                "WHERE table_schema = ? " +
                "AND table_name = 'member';";

        String sql ="create table if not exists member(" +
                "id varchar(45) primary key," +
                "password varchar(45)," +
                "name varchar(45)," +
                "email varchar(45)," +
                "phone varchar(45));";
        try {
            pstm = conn.prepareStatement(tableCheckSql);
            pstm.setString(1,dbName);
            rs = pstm.executeQuery();

            if(!rs.next()) {
                Statement stmt  = conn.createStatement();
                stmt.execute(sql);
                System.out.println("MEMBER Table 생성 완료");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //find member by ID
    public MemberDTO getMemberByID(String id) {
        MemberDTO member = null;
        try {
            String sql = "select * from member where id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);

            rs = pstm.executeQuery();

            if(rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                String eMail = rs.getString("email");
                String phone = rs.getString("phone");
                member = new MemberDTO(id, password,name,eMail,phone);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return member;
    }

    public boolean loginCheck(String id, String pw) {

        boolean pass = false;
        String sql = "select name from member where id=? and password=?";

        try {
            pstm = conn.prepareCall(sql);
            pstm.setString(1, id);
            pstm.setString(2, pw);
            rs = pstm.executeQuery();

            if(rs.next()) {
                //들어오면 로그인 성공
                pass = true;
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return pass;
    }


    public boolean joinMember(MemberDTO member) {

        boolean pass = false;
        String sql = "insert into member(id,password,name,email,phone) "
                + "values (?,?,?,?,?)";
        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, member.getId());
            pstm.setString(2, member.getPassword());
            pstm.setString(3, member.getName());
            pstm.setString(4, member.getEmail());
            pstm.setString(5, member.getPhone());

            //확인용 변수
            int update = pstm.executeUpdate();

            if(update>0) {
                System.out.println("회원가입 Success");
                pass = true;
            }
            else {
                System.out.println("Fail");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return pass;

    }



}
