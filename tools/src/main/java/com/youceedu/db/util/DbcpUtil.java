package com.youceedu.db.util;

import com.youceedu.tools.api.model.AutoLog;
import org.apache.commons.dbcp.BasicDataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: yqsy
 * @ClassName DbcpUtil
 * @Description java基于dbcp连接池操作数据库
 * @Author chen chunwei
 * @create: 2023-06-15 14:52
 * @Version 1.0
 */
public class DbcpUtil {
    //全局数据区有个公共的连接池
    private static BasicDataSource dataSource = null;
    private static String batchInsertSql = "insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) value(?,?,?,?,?,?,?,?)";

    //连接池唯一
    //static代码块优先执行
    static {
        if (dataSource == null) {
            //连接数据库基本信息
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/interface?characterEncoding=utf8");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");

            //连接池配置信息
            dataSource.setInitialSize(10);
            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(10);
            dataSource.setMaxActive(10);

            //连接池借出连接和客户端返回连接配置检查
            dataSource.setMaxWait(2000);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);

            dataSource.setPoolPreparedStatements(true);
        }
    }

    /***
     * @Title: getConnection
     * @Description: 从池子里借用连接
     * @param:
     * @return: * @return: java.sql.Connection
     * @author chen chunwei
     * @date 2023/6/15 15:20
     */
    public static synchronized Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    public static List<Object> handler(ResultSet rs, Class<?> className) throws Exception {
        //入参Class<?> className替代AutoLog.class，以防把类写死

        //多行结果需多个实体类，且多个实体类存储在List集合中
        List<Object> list = new ArrayList<Object>();
        //检索此ResultSet对象的列的数目、类型和属性
        ResultSetMetaData rsmd = rs.getMetaData();
        //总列数
        int columnCount = rsmd.getColumnCount();

        //控制行
        while (rs.next()) {
            //每循环一次就要新建一个类，新实例化一次实体类，得到一个新实体类的对象
            Object object = className.newInstance();
            // 遍历列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object columnValue = rs.getObject(columnName);
                //将查询结果的每一列值，映射给实体类中的属性
                //getDeclaredField其功能是获取指定类中所有声明属性的字段
                Field field = className.getDeclaredField(columnName);
                //设置可访问，反射原理
                field.setAccessible(true);
                //一行结果对应一个实体类
                field.set(object, columnValue);
            }
            list.add(object);
        }
        return list;
    }

    /**
     * @Title: jdbcQuery
     * @Description: 对执行sql进行查询
     * @param: * @param autoLog:
     * @param sql:
     * @return: * @return: java.util.List<java.lang.Object>
     * @author chen chunwei
     * @date 2023/6/6 16:42
     */
    public static List<Object> jdbcQuery(AutoLog autoLog, String sql) throws Exception {
        //初始化
        List<Object> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //从连接池中借用连接
            connection = getConnection();

            //对sql进行预编译检查并将sql存储到ps对象中
            ps = connection.prepareStatement(sql);
            ps.setInt(1, autoLog.getId());

            //执行查询得到结果
            rs = ps.executeQuery();
            list = handler(rs, autoLog.getClass());

        } finally {
            //归还连接
            close(rs,ps,connection);
        }
        return list;
    }

    /**
     * @Title: jdbcUpdate
     * @Description: 对指定sql进行增删改
     * @param: * @param autoLog:
     * @param sql:
     * @return: * @return: int
     * @author chen chunwei
     * @date 2023/6/6 16:45
     */
    public static int jdbcUpdate(AutoLog autoLog, String sql) throws Exception {
        //初始化
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //从连接池中借用连接
            connection = getConnection();

            //step3 对sql进行预编译检查并将sql存储到ps对象中
            ps = connection.prepareStatement(sql);
            ps.setInt(1, autoLog.getId());

            //执行增删改
            result = ps.executeUpdate();
        } finally {
            //归还连接
            close(null,ps,connection);
        }
        return result;
    }

    /**
     * @Title: jdbcUpdateBatch
     * @Description: 对指定sql进行批量增删改
     * @param: * @param list:
     * @return: * @return: int[]
     * @author chen chunwei
     * @date 2023/6/6 16:45
     */
    public static int[] jdbcUpdateBatch(List<AutoLog> list) throws Exception {
        //初始化
        int[] result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //从连接池中借用连接
            connection = getConnection();

            //step3 对sql进行预编译检查并将sql存储到ps对象中
            ps = connection.prepareStatement(batchInsertSql);
            for (AutoLog autoLog : list) {
                //testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime
                ps.setString(1, autoLog.getTestCase());
                ps.setString(2, autoLog.getReqType());
                ps.setString(3, autoLog.getReqUrl());
                ps.setString(4, autoLog.getReqData());
                ps.setString(5, autoLog.getExpResult());
                ps.setString(6, autoLog.getActResult());
                ps.setInt(7, autoLog.getResult());
                ps.setString(8, autoLog.getExecTime());
                ps.addBatch();
            }

            //执行增删改
            result = ps.executeBatch();
        } finally {
            //归还连接
            close(null,ps,connection);
        }
        //返回执行成功数据条数
        return result;
    }

    /**
     * @Title: close
     * @Description: 归还rs、ps、connection连接
     * @param: * @param rs:
     * @param ps:
     * @param connection:
     * @return: * @return: void
     * @author chen chunwei
     * @date 2023/6/15 15:23
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection connection) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    public static void main(String[] args) throws Exception {

        /*//测试-jdbcQuery
        AutoLog autoLog = new AutoLog();
        autoLog.setId(1);
        String sql = "select * from autolog where id = ?";
        List<Object> list = jdbcQuery(autoLog, sql);
        for (Object object:list){
            AutoLog tmp = (AutoLog)object;
            System.out.println(tmp.getId());
        }*/

        //测试-jdbcUpdateBatch
        List<AutoLog> list = new ArrayList<>();
        String sql = "insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) value(?,?,?,?,?,?,?,?)";

        AutoLog autoLog1 = new AutoLog();
        autoLog1.setTestCase("testCase3");
        autoLog1.setReqType("reqType3");
        autoLog1.setReqUrl("reqUrl3");
        autoLog1.setReqData("reqData3");
        autoLog1.setExpResult("expResult3");
        autoLog1.setActResult("actResult3");
        autoLog1.setResult(3);
        autoLog1.setExecTime("execTime3");
        list.add(autoLog1);

        AutoLog autoLog2 = new AutoLog();
        autoLog2.setTestCase("testCase4");
        autoLog2.setReqType("reqType4");
        autoLog2.setReqUrl("reqUrl4");
        autoLog2.setReqData("reqData4");
        autoLog2.setExpResult("expResult4");
        autoLog2.setActResult("actResult4");
        autoLog2.setResult(4);
        autoLog2.setExecTime("execTime4");
        list.add(autoLog2);

        //更新计数的数组，该数组包含批处理中每个命令的一个元素
        //查看数据库的值已经增加两条数据
//        int[] result = jdbcUpdateBatch(list, sql);
//        System.out.println(result[0]);
//        System.out.println(result[1]);
//        //遍历list集合
//        for (AutoLog autoLog : list) {
//            System.out.println(autoLog.getTestCase());
//        }

    }
}
