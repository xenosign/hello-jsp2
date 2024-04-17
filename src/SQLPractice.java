import java.sql.*;

public class SQLPractice {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        String server = "localhost";
        String database = "sakila";
        String username = "root";
        String password = "1234";

        String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

        // 1. 드라이버 로딩
        try {
            Class.forName(JDBC_DRIVER);
        }catch (Exception e){
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("정상적으로 연결 되었습니다.");
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }


        // 3. 데이터 처리
        Statement statement = null;

        String SQL;
        SQL = "SELECT * FROM actor";

        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                int actor_id = rs.getInt("actor_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                System.out.println("actor_id  : " + actor_id + "\nfirst_name : " + first_name + "\nlast_name : " + last_name);
                System.out.println("------------------------------------------------");
            }

            rs.close();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        // 4. 해제
        try {
            if(conn != null) conn.close();
            System.out.println("정상적으로 해제 되었습니다.");
        } catch (SQLException e) {}

    }

}
