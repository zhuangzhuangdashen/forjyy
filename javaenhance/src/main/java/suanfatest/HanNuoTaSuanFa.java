package suanfatest;

/**
 *
 * 汉诺塔问题 666
 * 原来步骤也可以递归
 *
 * Created by LIZHUANGZHUANG001 on 2019/5/7.
 */
public class HanNuoTaSuanFa {

    public static void main(String[] args) {

        hannuota(5 , "A", "B", "C");

    }

    private static void hannuota(int n, String from, String in, String to) {
        if (n == 1) {
            System.out.println("第1个盘子从" + from + "移动到 " + to);
        } else {
            // 移动上面的盘子到中间位置
            hannuota(n-1, from, to, in);

            //移动下面的盘子
            System.out.println("第" + n + "个盘子从" + from + "移动到" + to);

            // 移动上面的盘子从中间位置到目标位置
            hannuota(n-1, in, from, to);
        }
    }
}
