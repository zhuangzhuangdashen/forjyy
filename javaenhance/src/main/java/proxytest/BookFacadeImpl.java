package proxytest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 */
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("增加图书方法。。。");
    }

    @Override
    public String toString() {
        return "BookFacadeImpl{}";
    }
}