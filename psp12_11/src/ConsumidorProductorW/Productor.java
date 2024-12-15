package ConsumidorProductorW;

import java.util.ArrayList;
import java.util.Random;

public class Productor implements Runnable{
    private String name;
    private Departamento departamento;
    private Buffer buffer;

    public Productor(String name, Departamento departamento, Buffer buffer) {
        this.name = name;
        this.departamento = departamento;
        this.buffer = buffer;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public void run() {

        while (true) {
            try {
                buffer.producir(getName(), getDepartamento());
                Thread.sleep(new Random().nextInt(500));

            } catch (InterruptedException | NullPointerException e) {

                e.printStackTrace();
            }
        }
    }
}