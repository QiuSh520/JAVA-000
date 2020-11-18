package io.kimmking.springboot01.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用事务，PrepareStatement方式，批处理方式，改进上述操作
 */
public class JdbcNativeWithTransactionExample {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Long count = count();
        System.out.println(count);

        String sql = "insert into sys_dic( id,category,category_name,code,value_desc) values (1,'test','测试用','test_code','测试')";
        insert(sql);

        List<DictDO> resultList = queryList();
        System.out.println(resultList);

        sql = "update sys_dic set value_desc = '测试2' where id =1";
        update(sql);

        resultList = queryList();
        System.out.println(resultList);

        sql = "delete from sys_dic where id=1";
        delete(sql);

        resultList = queryList();
        System.out.println(resultList);

        sql = "truncate TABLE sys_dic";
        update(sql);

        resultList = queryList();
        System.out.println(resultList);
    }

    private static void delete(String sql) {
        executeUpdate(sql);
    }

    private static void update(String sql) {
        executeUpdate(sql);
    }

    private static void executeUpdate(String sql) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insert(String sql) {
        executeUpdate(sql);
    }

    private static List<DictDO> queryList() {
        List<DictDO> resultList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            String sql = "SELECT id,category,category_name,code,value_desc FROM sys_dic";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DictDO dictDO = new DictDO();
                Long id = rs.getLong("id");
                String category = rs.getString("category");
                String categoryName = rs.getString("category_name");
                String code = rs.getString("code");
                String valueDesc = rs.getString("value_desc");

                dictDO.setId(id);
                dictDO.setCategory(category);
                dictDO.setCategoryName(categoryName);
                dictDO.setCode(code);
                dictDO.setValueDesc(valueDesc);
                resultList.add(dictDO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException ignored) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    private static Long count() {
        Long count = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            String sql = "SELECT count(0) data_count FROM sys_dic";

            rs = stmt.executeQuery(sql);


            if (rs.next()) {
                count = rs.getLong("data_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException ignored) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
