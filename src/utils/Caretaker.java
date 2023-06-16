package utils;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private final List<Memento> stateList = new ArrayList<>();
    public void addMemento(Memento memento){
        if (stateList.size() < 3) stateList.add(memento);
        else {
            stateList.remove(0);
            stateList.add(memento);
        }
    }
    public Memento getMemento() {
        Memento memento = null;
        if (stateList.size() != 0) {
            memento = stateList.get(stateList.size() - 1);
            stateList.remove(stateList.size() - 1);
        }
        return memento;
    }
}
