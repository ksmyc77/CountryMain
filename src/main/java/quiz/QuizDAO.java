package quiz;

import java.sql.*;

import db.DBConnect;

public class QuizDAO {

    private Connection conn = DBConnect.getConnect();
    private PreparedStatement pstm;
    private ResultSet rs;

    private static QuizDAO quizDAO;

    private QuizDAO() throws Exception{

    }

    //싱글톤
    public static QuizDAO getInstance() {
        if(quizDAO == null) {
            try {
                quizDAO = new QuizDAO();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return quizDAO;
    }

    public void createTable(String dbName) throws SQLException {
        String tableCheckSql = "SELECT 1 FROM Information_schema.tables " +
                "WHERE table_schema = ? " +
                "AND table_name = 'quiz';";

        String sql ="create table if not exists quiz(" +
                "member_id varchar(45) primary key," +
                "quiz1 int," +
                "quiz2 int," +
                "quiz3 int," +
                "quiz4 int," +
                "quiz5 int," +
                "quiz6 int," +
                "quiz7 int," +
                "quiz8 int," +
                "quiz9 int," +
                "quiz10 int," +
                "foreign key(member_id)" +
                "references member(id) ON UPDATE CASCADE);";
        try {
            pstm = conn.prepareStatement(tableCheckSql);
            pstm.setString(1,dbName);
            rs = pstm.executeQuery();
            if(!rs.next()) {
                Statement stmt  = conn.createStatement();
                stmt.execute(sql);
                System.out.println("QUIZ Table 생성 완료");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //퀴즈 시작 시, 정답 유무를 모두 null로 바꾼다.
    public boolean startQuiz(String id) {
        boolean pass = false;

        try {
            String sql = "replace into quiz values (?,?,?,?,?,?,?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setNull(2,  java.sql.Types.INTEGER);
            pstm.setNull(3,  java.sql.Types.INTEGER);
            pstm.setNull(4,  java.sql.Types.INTEGER);
            pstm.setNull(5,  java.sql.Types.INTEGER);
            pstm.setNull(6,  java.sql.Types.INTEGER);
            pstm.setNull(7,  java.sql.Types.INTEGER);
            pstm.setNull(8,  java.sql.Types.INTEGER);
            pstm.setNull(9,  java.sql.Types.INTEGER);
            pstm.setNull(10,  java.sql.Types.INTEGER);
            pstm.setNull(11,  java.sql.Types.INTEGER);


            //확인용 변수
            int update = pstm.executeUpdate();

            if(update>0) {
                System.out.println("Success");
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

    //count 번째 문제를 풀때마다 quiz데이터를 update한다.
    public boolean updateQuiz(String id, int count, int quizId) {
        boolean pass = false;

        try {
            String sql = "UPDATE quiz SET quiz"+ count +" = ? WHERE member_id = ?";

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, quizId);
            pstm.setString(2, id);

            //확인용 변수
            int update = pstm.executeUpdate();

            if(update>0) {
                System.out.println("Success");
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

    public int[] getIncorrectQuiz(String id) {

        int inCorrectQuiz[] = new int[10];

        try {
            String sql = "select * from quiz where member_id=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);

            rs = pstm.executeQuery();

            if(rs.next()) {
                inCorrectQuiz[0]=rs.getInt("quiz1");
                inCorrectQuiz[1]=rs.getInt("quiz2");
                inCorrectQuiz[2]=rs.getInt("quiz3");
                inCorrectQuiz[3]=rs.getInt("quiz4");
                inCorrectQuiz[4]=rs.getInt("quiz5");
                inCorrectQuiz[5]=rs.getInt("quiz6");
                inCorrectQuiz[6]=rs.getInt("quiz7");
                inCorrectQuiz[7]=rs.getInt("quiz8");
                inCorrectQuiz[8]=rs.getInt("quiz9");
                inCorrectQuiz[9]=rs.getInt("quiz10");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return inCorrectQuiz;
    }
}
