import java.util.Vector;

class CP{
    Vector pool; //Vector是可以存储任何对象的表.这里
    //  当作产品池.
    int product = 0;
    public static int EMPTY = 0;
    public static int FULL = 25;

    public CP(){
        pool = new Vector();
    }//CP类的构造函数.
    public static void main(String[] args){
        CP cp = new CP();
        Consumer consumer = new Consumer(cp);
        Producer producer = new Producer(cp);
        consumer.start();//启动线程，使其运行它的run函数
        producer.start();
    }

    public synchronized void produce(){
        try{
            if(pool.size() == FULL)
                pool.wait();
            product++; //produce a product.
            pool.addElement(new Integer(product));
            //put the product into the pool.
            System.out.println("Produce: " + product);
            if(pool.size() == EMPTY + 1)
                pool.notify();
        }catch(InterruptedException e){}
    }
    public synchronized void consume(){
        try{
            int i = pool.size();
            if(i == EMPTY)
                pool.wait();
            System.out.println("Consume: " +
                    pool.firstElement().toString());
            pool.removeElementAt(0);
            if(pool.size() == FULL - 1)
                pool.notify();
        }catch(InterruptedException e){}
    }
} //End of class CP.


class Consumer extends Thread{
    CP cp;
    public Consumer(CP cp){
        super();
        this.cp = cp;
    }
    public void run(){//线程的执行函数
        while(true){
            cp.consume();//调用监控器的consume方法
            try {
                Thread.sleep(1000);//模拟做其它工作的时间
            }catch (Exception e) {

            }

        }
    }
} // End of class Consumer


class Producer extends Thread{
    CP cp;
    public Producer(CP cp){
        super();
        this.cp = cp;
    }// 构造函数
    public void run(){
        while(true){
            cp.produce();//调用监控器的produce方法
            try {
                Thread.sleep(200);//模拟做其它工作的时间
            }catch (Exception e){

            }

        }
    }
}
