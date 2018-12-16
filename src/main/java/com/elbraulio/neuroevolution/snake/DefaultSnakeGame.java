package com.elbraulio.neuroevolution.snake;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class DefaultSnakeGame implements SnakeGame<SnakeAction> {


    private final int x;
    private final int y;
    private final List<Position> snake;
    private int[] direction;

    public DefaultSnakeGame(int x, int y) {

        direction = new int[]{0, 1};
        this.x = x;
        this.y = y;
        this.snake = new LinkedList<>();
        snake.add(new Position(0, 0));
        snake.add(new Position(0, 1));
        snake.add(new Position(0, 2));
        print();
    }

    private void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (snake.contains(new Position(i, j))) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public void action(SnakeAction action) {
        snake.remove(0);
        Position head = snake.get(snake.size() - 1);
        int[] newDirection = new int[direction.length];
        switch (action) {
            case TURN_LEFT:
                newDirection[0] = -direction[1];
                newDirection[1] = direction[0];
                direction = newDirection;
                snake.add(
                        new Position(
                                head.x + direction[0],
                                head.y + direction[1]
                        )
                );
                break;
            case TURN_RIGHT:
                newDirection[0] = direction[1];
                newDirection[1] = -direction[0];
                direction = newDirection;
                snake.add(
                        new Position(
                                head.x + direction[0],
                                head.y + direction[1]
                        )
                );
                break;
            case GO_STRAIGHT:
                snake.add(
                        new Position(
                                head.x + direction[0],
                                head.y + direction[1]
                        )
                );
                break;
        }
        print();
    }

    class Position {
        private int x;
        private int y;

        Position(int x, int y) {

            this.x = x;
            this.y = y;
        }

        int x() {
            return this.x;
        }

        int y() {
            return this.y;
        }

        void x(int x) {
            this.x = x;
        }

        void y(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (x != position.x) return false;
            return y == position.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}