package ConsumidorProductorW;

import java.util.ArrayList;

public class Fabrica {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Departamento departamento = new Departamento();
        ArrayList<Thread> hilosProductores = new ArrayList<>();
        ArrayList<Thread> hilosConsumidores = new ArrayList<>();

        for (int i = 1; i < 6; i++){
            Thread hiloProductor = new Thread(new Productor(Integer.toString(i),departamento,buffer));
            hilosProductores.add(hiloProductor);
            if (i < 4){
                Thread hiloConsumidor = new Thread(new Consumidor(Integer.toString(i),buffer));
                hilosConsumidores.add(hiloConsumidor);
            }
        }

        for (Thread hp : hilosProductores){
            hp.start();
        }
        for (Thread hc : hilosConsumidores){
            hc.start();
        }
        try{
            for (Thread hp : hilosProductores){
                hp.join();
            }
            for (Thread hc : hilosConsumidores){
                hc.join();
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }



    }
}
