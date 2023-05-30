package utils;

public class Double<T,U,K> extends  Tuple<T,U>{

    public Double(T first, U second, K third) {
        super(first, second);
        this.third = third;
    }
    public K third;
}
