package mysqltest;

import java.sql.*;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/6.
 */
public class TestForOldSolider {
    public static void main(String[] args) {

        String sql = "  select * from (select dtype, sum(daysum) as pvsum,1.1 as rate,\n" +
                "  avg(daysum) as avgdaysum,max(daysum) as maxdaysum, min(daysum) as mindaysum from(\n" +
                "  select -1 as dtype, sum(pv) as daysum,vsdate from tbl_colsum\n" +
                "  where vsdate between ifnull('1991-01-01','0000-00-00')\n" +
                "  and ifnull('2019-10-10','9999-99-99') group by vsdate) t1) daysum1\n" +
                "  left join (select -1 as dtype1, avg(pvsum) as avgmonsum, max(pvsum) as  maxmonsum,\n" +
                "  min(pvsum) as minmonsum from (select -1 as dtype, sum(pv) as pvsum,month(vsdate) as vsmonth\n" +
                "  from tbl_colsum where vsdate between ifnull('1991-01-01','0000-00-00')\n" +
                "  and ifnull('2019-10-10','9999-99-99') group by vsmonth) t2) monsum1\n" +
                "  on daysum1.dtype = monsum1.dtype1\n" +
                "  union all\n" +
                "\n" +
                "  select * from (select dtype,sum(pv) as pvsum, pv/sum(pv) as rate,\n" +
                "  avg(pv) as avgdaysum,max(pv) as maxdaysum, min(pv) as mindaysum from tbl_colsum\n" +
                "  where vsdate between ifnull('1991-01-01','0000-00-00')\n" +
                "  and ifnull('2019-10-10','9999-99-99') group by dtype) daysum2\n" +
                "  left join (select dtype as dtype1,avg(pvsum) as avgmonsum, max(pvsum) as  maxmonsum,\n" +
                "  min(pvsum) as minmonsum from (select dtype, sum(pv) as pvsum,month(vsdate) as vsmonth\n" +
                "  from tbl_colsum where vsdate between ifnull('1991-01-01','0000-00-00')\n" +
                "  and ifnull('2019-10-10','9999-99-99') group by dtype, vsmonth)t3 group by dtype) monsum2\n" +
                "  on daysum2.dtype = monsum2.dtype1";

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/forjyy?user=root&password=root&useUnicod \n" +
                            "e=true&characterEncoding=8859_1");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet execute = preparedStatement.executeQuery();
            System.out.println(execute);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
