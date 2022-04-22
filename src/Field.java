public class Field {

    private int sizeX = 24;
    private int sizeY = 24;


    private Fieldable[][] field;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field = new Fieldable[sizeX][sizeY];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setFieldable(int x, int y, Fieldable object) {
        field[x][y] = object;

    }

    public Fieldable getFieldable(int x, int y) {
        return field[x][y];
    }

    public void showField() {
        for (int i = 0; i < sizeX; i++) {
            System.out.println();
            for (int j = 0; j < sizeY; j++)
                System.out.print(field[i][j].getSymbol());
        }
        System.out.println();
    }

    public void fillFieldEmpty() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                setFieldable(i, j, new EmptyField());
            }
        }
    }


}
