package utils;

public class Triple<T,U,K> extends  Tuple<T,U>{

    public Triple(T first, U second, K third) {
        super(first, second);
        this.third = third;
    }
    public K third;
}
