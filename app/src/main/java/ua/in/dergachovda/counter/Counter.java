package ua.in.dergachovda.counter;

class Counter {
    final static String VALUE_NAME = "value";
    private static volatile Counter instance;
    private int value;

    static Counter getInstance() {
        Counter localInstance = instance;
        if (localInstance == null) {
            synchronized (Counter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Counter();
                }
            }
        }
        return localInstance;
    }

    void setNull() {
        this.value = 0;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}