import java.util.Scanner;

public class Rover {

    private int x;
    private int y;
    private char direction;

    //inner class
    public static class Plateau {
        private static int width;
        private static int height;

        Plateau(int width, int height) {
            Plateau.width = width;
            Plateau.height = height;
        }
    }

    public Rover(int x, int y, char direction) {

        if(x > Plateau.width || y > Plateau.height)
            throw new IllegalArgumentException("x, y should be inside plateau surface!");

        if (direction != 'N' && direction != 'S' && direction != 'E' && direction != 'W')
            throw new IllegalArgumentException("compass should be N, S, W or E !");

        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getDirection() {
        return direction;
    }

    //x y N
    @Override
    public String toString() {
        return String.format("%d %d %c", getX(), getY(), getDirection());
    }

    //L, R, M
    private void move (char command) {

        if (command != 'L' && command != 'R' && command != 'M')
            throw new IllegalArgumentException("Rover commands should be R, L or M");

    //TODO plateau coordinates
        switch (direction) {
            case 'W':
                if (command == 'L')
                    direction = 'S';
                else if (command == 'R')
                    direction = 'N';
                else if (command == 'M')
                    x -= 1;

                break;

            case 'E':
                if (command == 'L')
                    direction = 'N';
                else if (command == 'R')
                    direction = 'S';
                else if (command == 'M')
                    x += 1;

                break;

            case 'N':

                if (command == 'L')
                    direction = 'W';
                else if (command == 'R')
                    direction = 'E';
                else if (command == 'M')
                    y += 1;

                break;

            case 'S':

                if (command == 'L')
                    direction = 'E';
                else if (command == 'R')
                    direction = 'W';
                else if (command == 'M')
                    y -= 1;

                break;

        }

    }


    public static void main(String[] args) {

        /* System.in is the keyboard itself */
        Scanner in = new Scanner(System.in);
        String position;
        Rover r;

        //construct plateau
        position = in.nextLine();

        if (position.length() != 3)
            throw new IllegalArgumentException("First line should be 3 length. sample: 5 5");

        Plateau plateau = new Rover.Plateau(Character.getNumericValue(position.toCharArray()[0]),
                Character.getNumericValue(position.toCharArray()[2]));


        /* TODO check char inputs */
        for(int roverCount=0; roverCount<2; roverCount++){

            position = in.nextLine();

            if (position.length() != 5)
                throw new IllegalArgumentException("First line should be 5 length. sample: 5 5 N");

            //1 2 N
            r = new Rover(Character.getNumericValue(position.toCharArray()[0]),
                    Character.getNumericValue(position.toCharArray()[2]),
                    position.toCharArray()[4]);

            //LMLMLMLMM
            String commands = in.nextLine();
            for (char command : commands.toCharArray()) {
                r.move(command);
            }

            System.out.println(r);
        }


    }
}
