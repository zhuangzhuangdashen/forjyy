package com.forjyy.mybatistest.MoNiMybatis;


/**
 * Created by LIZHUANGZHUANG001 on 2019/3/10.
 */
public class Dao {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //1、工厂建立：读取xml配置文件并加载
        //2、通过工厂获取产品sqlSession
//        SqlSessionTest sqlSessionTest = new SqlSessionTest();
        SqlSessionTest sqlSessionTest = new SqlSessionTest();
        //3、通过getMapper创建代理类
        IDemo demo = sqlSessionTest.getMapper(IDemo.class);
        //4、执行接口对应的方法，会直接走代理类的invoke方法
        ResultDemo result = demo.getResult(1);
        //5、打印返回结果
        System.out.println(result.getId());
        System.out.println(result.getValue());
    }

}