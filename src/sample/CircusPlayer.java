package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CircusPlayer {
    private String name;
    private Color color;
    private int position;


    public CircusPlayer(String name, Color color) {
        this.name = name;
        this.color = color;
        this.position = 1;

    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addToPosition(int position) {
        this.position += position;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "CircusPlayer{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", position=" + position +
                '}';
    }

    //    public String boardAction(int fieldNumber) { // needs to be added all other action fields
//        String action;
//
//        switch (fieldNumber) {
//            case 3:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "3: Kangaroo with a phone - go forward to the field No.19");
//                this.setPosition(19);
//                break;
//            case 9:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "9: Seal with a ball - go forward to the field No.11");
//                this.setPosition(11);
//                break;
//            case 14:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "14: Bear - tightrope walker - go forward to the field No.30");
//                this.setPosition(30);
//                break;
//            case 16:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "16: Girl - tightrope dancer - go back to the first field");
//                this.setPosition(1);
//                break;
//            case 25:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "25: Pig on the board going down - go back to the field No.7");
//                this.setPosition(7);
//                break;
//            case 34:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "34: Lion on the stairs - go forward to the field No.97");
//                this.setPosition(97);
//                break;
//            case 36:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "36: Dancing dog - go forward to the field No.45");
//                this.setPosition(45);
//                break;
//            case 40:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "40: Rooster on the stairs - go forward to the field No.58");
//                this.setPosition(58);
//                break;
//            case 50:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "50: Running dog - go back to the field No.32");
//                this.setPosition(32);
//                break;
//            case 52:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "52: Dog jumping through the hoop - go forward to the field No.68");
//                this.setPosition(68);
//                break;
//            case 53:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "53: Monkey going down the pole - go back to the field No.8");
//                this.setPosition(8);
//                break;
//            case 57:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "57: Rabbit the drummer - go forward to the field No.63");
//                this.setPosition(63);
//                break;
//            case 59:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "59: Dancing horse - go forward to the field No.80");
//                this.setPosition(80);
//                break;
//            case 65:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "65: Flying pigeons - go forward to the field No.83");
//                this.setPosition(83);
//                break;
//            case 71:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "71: Sky-diving cat - go back to the field No.51");
//                this.setPosition(51);
//                break;
//            case 72:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "72: Tiger jumping through flaming hoop - go forward to the field No.94");
//                this.setPosition(94);
//                break;
//            case 87:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "87: Acrobat monkey - go back to the field No.67");
//                this.setPosition(67);
//                break;
//            case 92:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "92: Goose with a flower - go forward to the field No.108");
//                this.setPosition(108);
//                break;
//            case 96:
//                action = CircusGameBoard.pic(CircusGameBoard.GREEN, "96: Juggler with rings - go forward to the field No.116");
//                this.setPosition(116);
//                break;
//            case 102:
//                action = CircusGameBoard.pic(CircusGameBoard.RED,  "102: Acrobat - go back to the field No.81");
//                this.setPosition(81);
//                break;
//            case 107:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "107: Goat on the stairs - go back to the field No.23");
//                this.setPosition(23);
//                break;
//            case 112:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "112: Clown with an umbrella - go back to the field No.90");
//                this.setPosition(90);
//                break;
//            case 117:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "117: Flying trapeze trick - go back to the field No.98");
//                this.setPosition(98);
//                break;
//            case 119:
//                action = CircusGameBoard.pic(CircusGameBoard.RED, "119: Cat jumping through a hoop - go forward to the field No.101");
//                this.setPosition(101);
//                break;
//
//            default:
//                action = this.getPosition()+": This is regular field without special action";
//                break;
//        }
//        return action;
//    }

}
