public class Player {

    public static final Player ONE = new Player('1');
    public static final Player TWO = new Player('2');

    private final char id;

    private Player(char id) {
        this.id = id;
    }

    public boolean isEqualTo(Player other) {
        return !(other == null || other.id != this.id);
    }

    public boolean isOne() {

        return this.id == '1';
    }

    public boolean isTwo() {
        return this.id == '2';
    }

    // Only for testing

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public static Player fromChar(char c) {
        switch (c) {
            case '1':
                return ONE;
            case '2':
                return TWO;
            default:
                return null;
        }
    }
}
