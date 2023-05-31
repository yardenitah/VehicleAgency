package graphic;

import vehicless.Vehicle;

public interface AbstractFactory{
    public Vehicle create(int type, String... args);  // String... args = no limited args
}
