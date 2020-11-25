package io.kimmking.springboot01.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用事务，PrepareStatement方式，批处理方式，改进上述操作
 */
public class JdbcNativeWithDataSourceExample {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig("/hikari.properties");
        dataSource = new HikariDataSource(config);
    }

    public static void main(String[] args) {
        String sql1 = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,'test','测试用','test_code1','测试')";
        String sql2 = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,'test','测试用','test_code2','测试')";
        String sql3 = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,'test','测试用','test_code3','测试')";
        String sql4 = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,'test','测试用','test_code4','测试')";
        String sql5 = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,'test','测试用','test_code5','测试')";
        batchExecuteUpdate(new String[]{sql1, sql2, sql3, sql4, sql5});

        List<DictDO> resultList = queryList();
        System.out.println(resultList);

        String sql = "insert into sys_dic( id,category,category_name,code,value_desc) values (null,?,?,?,?)";
        String[] params = new String[]{"test", "测试用", "test_code6", "测试"};
        executeUpdateWithPrepareStatement(sql, params);

        resultList = queryList();
        System.out.println(resultList);

        sql = "truncate TABLE sys_dic";
        executeUpdateWithTransaction(sql);

        dataSource.close();
    }

    /**
     * 使用事务
     *
     * @param sql
     */
    private static void executeUpdateWithTransaction(String sql) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            stmt.execute(sql);

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
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

    /**
     * 使用事务+PrepareStatement
     *
     * @param sql
     */
    private static void executeUpdateWithPrepareStatement(String sql, String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }

            preparedStatement.execute();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
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

    /**
     * 批处理方式
     *
     * @param sqlArr
     */
    private static void batchExecuteUpdate(String[] sqlArr) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = dataSource.getConnection();

            stmt = conn.createStatement();
            for (String s : sqlArr) {
                stmt.addBatch(s);
            }

            stmt.executeBatch();
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

    private static List<DictDO> queryList() {
        List<DictDO> resultList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //新版本的mysql驱动可自动加载
//            Class.forName(JDBC_DRIVER);

            conn = dataSource.getConnection();

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

            conn = dataSource.getConnection();

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
