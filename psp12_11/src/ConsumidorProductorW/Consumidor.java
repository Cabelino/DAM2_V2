package ConsumidorProductorW;

import java.util.Random;

public class Consumidor implements Runnable{
    private String name;
    private Buffer buffer;

    public Consumidor(String name,Buffer buffer) {
        this.name = name;
        this.buffer = buffer;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Buffer getAlmacen() {
        return buffer;
    }

    public void setAlmacen(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.consumir(getName());
                Thread.sleep(new Random().nextInt(1500));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
