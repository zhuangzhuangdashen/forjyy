package proxytest;

/**
 * Created by LIZHUANGZHUANG001 on 2019/2/26.
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        BookFacadeImpl bookFacadeImpl=new BookFacadeImpl();
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookfacade.addBook();


//        DynamicProxy dynamicProxy = new DynamicProxy();
//        Inter1 obj = (Inter1) dynamicProxy.bind(new SubClass());
//        obj.execute1();
    }
}
