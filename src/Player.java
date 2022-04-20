public class Player {

    public static final Player ONE = new Player('1');
    public static final Player TWO = new Player('2');

    private final char id;

    private Player(char id) {
        this.id = id;
    }

    public boolean isEqualTo(Player other) {
        if (other == null || other.id != this.id) {
            return false;
        }
        return true;
    }

    public boolean isOne() {

        if (this.id == '1') {
            return true;
        }
        return false;
    }

    public boolean isTwo() {
        if (this.id == '2') {
            return true;
        }
        return false;
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
