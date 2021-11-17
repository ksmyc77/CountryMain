package country;

import java.io.IOException;
import java.sql.*;

import db.DBConnect;

public class CountryDAO {

    private Connection conn = DBConnect.getConnect();
    private PreparedStatement pstm;
    private ResultSet rs;

    private static CountryDAO countryDao;


    private CountryDAO() throws Exception{

    }

    //싱글톤
    public static CountryDAO getInstance() {
        if(countryDao == null) {
            try {
                countryDao = new CountryDAO();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return countryDao;
    }

    // 테이블 생성
    public void createTable(String dbName) throws SQLException {

        String tableCheckSql = "SELECT 1 FROM Information_schema.tables " +
                "WHERE table_schema = ? " +
                "AND table_name = 'country';";
        String createSql ="create table country(" +
                "id int primary key not null auto_increment," +
                "국가 text," +
                "수도 text," +
                "기후 text," +
                "위치 text," +
                "주요도시 text," +
                "종교 text," +
                "주요민족 text," +
                "언론 text," +
                "면적출처 text," +
                "면적설명 text," +
                "언어 text," +
                "기준년도 int);";
        String alterSql ="ALTER TABLE country " +
                "ADD COLUMN `국가코드(ISO 2자리 코드)` TEXT NULL AFTER `국가`," +
                "ADD COLUMN `면적(㎢)` TEXT NULL AFTER `언론`";
        try {
            pstm = conn.prepareStatement(tableCheckSql);
            pstm.setString(1,dbName);
            rs = pstm.executeQuery();

            //테이블이 없다면 테이블 생성, 데이터 삽입
            if(!rs.next()) {
                Statement stmt = conn.createStatement();
                stmt.execute(createSql);
                stmt.execute(alterSql);
                loadData();
                System.out.println("COUNTRY Table 생성, 데이터 삽입 완료");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //데이터 로딩 함수
    public void loadData() throws SQLException, IOException {
        CSVReader reader = new CSVReader();
        reader.read("data\\data.csv");
        CountryDTO[] dtos = new CountryDTO[227];
        String [][] lines = reader.getLines();


        for(int i =0; i<dtos.length; i++){
            dtos[i] = new CountryDTO(i+1, lines[i+1][0],lines[i+1][1],lines[i+1][2],lines[i+1][3], lines[i+1][4], lines[i+1][5], lines[i+1][6], lines[i+1][7], lines[i+1][8], lines[i+1][9], lines[i+1][10], lines[i+1][11], lines[i+1][12], Integer.parseInt(lines[i+1][13]));
            insertCountry(dtos[i]);
        }
    }
    //나라 table의 컬럼 수를 반환
    public int getTotalNum(){
        int id = 0;
        String sql = "select id from country order by id desc limit 1";

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return id;
    }

    //나라 정보 추가
    public boolean insertCountry(CountryDTO country){
        boolean pass = false;
        String sql = "insert into country values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, country.getName());
            pstm.setString(2, country.getCode());
            pstm.setString(3, country.getCapital());
            pstm.setString(4, country.getWeather());
            pstm.setString(5, country.getLocation());
            pstm.setString(6, country.getMainCity());
            pstm.setString(7, country.getReligion());
            pstm.setString(8, country.getRace());
            pstm.setString(9, country.getMedia());
            pstm.setString(10, country.getArea());
            pstm.setString(11, country.getAreaSource());
            pstm.setString(12, country.getAreaExplain());
            pstm.setString(13, country.getLanguage());
            pstm.setInt(14, country.getBaseYear());

            //확인용 변수
            int update = pstm.executeUpdate();

            if (update > 0) {
                pass = true;
            } else {
                System.out.println("insert Fail");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return pass;

    }

    public boolean UpdateCountry(CountryDTO country){
        boolean pass = false;
        PreparedStatement pstm = null;

        try {
        	String sql = "update country set 국가 = ?,`국가코드(ISO 2자리 코드)`= ?, 수도= ?, 기후= ?, 위치= ?, 주요도시= ?, 종교= ?, 주요민족= ?, 언론= ?"
        			+ ", `면적(㎢)`= ?, 면적출처= ?, 면적설명= ?, 언어= ?, 기준년도 = ? where 국가 = ?";
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, country.getName());
            pstm.setString(2, country.getCode());
            pstm.setString(3, country.getCapital());
            pstm.setString(4, country.getWeather());
            pstm.setString(5, country.getLocation());
            pstm.setString(6, country.getMainCity());
            pstm.setString(7, country.getReligion());
            pstm.setString(8, country.getRace());
            pstm.setString(9, country.getMedia());
            pstm.setInt(10, Integer.parseInt(country.getArea()));
            pstm.setString(11, country.getAreaSource());
            pstm.setString(12, country.getAreaExplain());
            pstm.setString(13, country.getLanguage());
            pstm.setInt(14, country.getBaseYear());
            pstm.setString(15, country.getName());

            //확인용 변수
            int update = pstm.executeUpdate();

            if(update>0) {
                System.out.println("update Success");
                pass = true;
            }
            else {
                System.out.println("update Fail");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return pass;

    }
    
    
    //나라 정보 삭제
    public boolean deleteCountry(String name){
        boolean pass = false;
        String sql = "delete from country where 국가=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);

            //확인용 변수
            int update = pstm.executeUpdate();

            if (update > 0) {
                pass = true;
                System.out.println("delete success");
            } else {
                System.out.println("delete fail");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return pass;

    }

    public CountryDTO getCountryByName(String name){
        CountryDTO country = null;
        String sql = "select * from country where 국가=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);

            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("국가코드(ISO 2자리 코드)");
                String capital = rs.getString("수도");
                String weather = rs.getString("기후");
                String location = rs.getString("위치");
                String mainCity = rs.getString("주요도시");
                String religion = rs.getString("종교");
                String race = rs.getString("주요민족");
                String media = rs.getString("언론");
                String area = rs.getString("면적(㎢)");
                String areaSource = rs.getString("면적출처");
                String areaExplain = rs.getString("면적설명");
                String language = rs.getString("언어");
                int baseYear = rs.getInt("기준년도");
                country = new CountryDTO(id, name, code, capital, religion, mainCity, weather, location, race, media, area, areaSource, areaExplain, language, baseYear);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return country;
    }

    public CountryDTO getCountryByID(int id) {
        CountryDTO country = null;;
        String sql = "select * from country where id=?";

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                String name = rs.getString("국가");
                String code = rs.getString("국가코드(ISO 2자리 코드)");
                String capital = rs.getString("수도");
                String weather = rs.getString("기후");
                String location = rs.getString("위치");
                String mainCity = rs.getString("주요도시");
                String religion = rs.getString("종교");
                String race = rs.getString("주요민족");
                String media = rs.getString("언론");
                String area = rs.getString("면적(㎢)");
                String areaSource = rs.getString("면적출처");
                String areaExplain = rs.getString("면적설명");
                String language = rs.getString("언어");
                int baseYear = rs.getInt("기준년도");
                country = new CountryDTO(id, name, code, capital, weather, location, mainCity, religion, race, media, area, areaSource, areaExplain, language, baseYear);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return country;
    }
}
