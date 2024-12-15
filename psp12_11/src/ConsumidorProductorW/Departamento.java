package ConsumidorProductorW;

import java.util.LinkedList;
import java.util.Queue;

public class Departamento {
    private Queue<Integer> numbersList;

    public Departamento() {
        this.numbersList = createListNumber(30);
    }

    public Queue<Integer> getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(Queue<Integer> numbersList) {
        this.numbersList = numbersList;
    }

    public Queue<Integer> createListNumber(Integer numberElements){
        Queue<Integer> numberList = new LinkedList<>();
        for (int i = 1; i<= numberElements; i++){
            numberList.add(i);
        }
        return numberList;
    }

    public synchronized Integer takeElement(){
        return getNumbersList().poll();
    }

    public synchronized Integer takeElementMomentarily(){
        return getNumbersList().peek();
    }
}
