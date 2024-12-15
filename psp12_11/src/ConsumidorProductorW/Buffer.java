package ConsumidorProductorW;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Buffer {
    List<Integer> listbuffer;
    final Integer maxCapacity;
    Integer pointer;
    private Integer lastIndexConsumed;

    public Buffer(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.listbuffer = fillWithZeros();
        this.pointer = 0;
        this.lastIndexConsumed = -1;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getLastIndexConsumed() {
        return lastIndexConsumed;
    }

    public void setLastIndexConsumed(Integer lastIndexConsumed) {
        this.lastIndexConsumed = lastIndexConsumed;
    }

    public List<Integer> fillWithZeros(){
        ArrayList<Integer> zeroList = new ArrayList<>(getMaxCapacity());

        for (int i = 0; i < getMaxCapacity();i++){
            zeroList.add(0);
        }
        return zeroList;
    }

    public synchronized void producir(String nameProducer, Departamento departamento) throws InterruptedException{

        while(!listbuffer.contains(0)){
            wait();
        }
        Integer number = departamento.takeElement();
        if (number == null){
            return;
        }

        if (pointer.equals(getMaxCapacity())){
            pointer = 0;
        }

        listbuffer.set(pointer, number);

        pointer++;

        showContent("Productor",nameProducer, number);

        notifyAll();

    }

    public synchronized void consumir(String nameConsumer) throws InterruptedException{

        if (getLastIndexConsumed() + 1 == getMaxCapacity()){
            setLastIndexConsumed(-1);
        }

        while(listbuffer.get(getLastIndexConsumed() + 1) == 0){
            wait();
        }

        setLastIndexConsumed(getLastIndexConsumed() + 1);
        Integer num = listbuffer.get( getLastIndexConsumed());
        listbuffer.set(getLastIndexConsumed(), 0);

        showContent("Consumidor",nameConsumer,num);

        notifyAll();

    }

    public void showContent(String nameType, String name, Integer num){
        StringBuilder content = new StringBuilder();
        content.append(nameType).append(" ").append(name);
        if (nameType.equals("Productor")){
            content.append(" a producido: ").append(num);
        }else{
            content.append(" a consumido: ").append(num);
        }
        content.append("\n-> Buffer: ").append(listbuffer).append("\n");

        System.out.println(content);
        writeFile(content.toString());
    }

    public void writeFile(String content){
        String path = "./src/contenido.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        return listbuffer.toString();
    }
}
