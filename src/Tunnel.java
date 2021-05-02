import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore sp;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        sp = new Semaphore(Main.CARS_COUNT / 2);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                if(!sp.tryAcquire()){
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    sp.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}