package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircusPlayer {
    private final String name;
    private final Paint color;
    private int position;
    private Circle token;

    public void setToken(Circle token) {
        this.token = token;
    }

    public CircusPlayer(String name, Paint color) {
        this.name = name;
        this.color = color;
        this.position = 1;
    }

    public Circle getToken() {
        return token;
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

    public Paint getColor() {
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

    public static int getRow(int position) {
        switch (position) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                return 11;
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20:
                return 10;
            case 21, 22, 23, 24, 25, 26, 27, 28, 29, 30:
                return 9;
            case 31, 32, 33, 34, 35, 36, 37, 38, 39, 40:
                return 8;
            case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50:
                return 7;
            case 51, 52, 53, 54, 55, 56, 57, 58, 59, 60:
                return 6;
            case 61, 62, 63, 64, 65, 66, 67, 68, 69, 70:
                return 5;
            case 71, 72, 73, 74, 75, 76, 77, 78, 79, 80:
                return 4;
            case 81, 82, 83, 84, 85, 86, 87, 88, 89, 90:
                return 3;
            case 91, 92, 93, 94, 95, 96, 97, 98, 99, 100:
                return 2;
            case 101, 102, 103, 104, 105, 106, 107, 108, 109, 110:
                return 1;
            case 111, 112, 113, 114, 115, 116, 117, 118, 119, 120:
                return 0;
            default:
                return 11;
        }
    }

    public static int getColumn(int position) {
        switch (position) {
            case 1, 20, 21, 40, 41, 60, 61, 80, 81, 100, 101, 120:
                return 0;
            case 2, 19, 22, 39, 42, 59, 62, 79, 82, 99, 102, 119:
                return 1;
            case 3, 18, 23, 38, 43, 58, 63, 78, 83, 98, 103, 118:
                return 2;
            case 4, 17, 24, 37, 44, 57, 64, 77, 84, 97, 104, 117:
                return 3;
            case 5, 16, 25, 36, 45, 56, 65, 76, 85, 96, 105, 116:
                return 4;
            case 6, 15, 26, 35, 46, 55, 66, 75, 86, 95, 106, 115:
                return 5;
            case 7, 14, 27, 34, 47, 54, 67, 74, 87, 94, 107, 114:
                return 6;
            case 8, 13, 28, 33, 48, 53, 68, 73, 88, 93, 108, 113:
                return 7;
            case 9, 12, 29, 32, 49, 52, 69, 72, 89, 92, 109, 112:
                return 8;
            case 10, 11, 30, 31, 50, 51, 70, 71, 90, 91, 110, 111:
                return 9;
            default:
                return 0;
        }
    }


    public String boardAction(int fieldNumber) { // needs to be added all other action fields
        String action;

        switch (fieldNumber) {
            case 3:
                action = "3: Kangaroo with a phone - go forward to the field No.19";
                this.setPosition(19);
                break;
            case 9:
                action = "9: Seal with a ball - go forward to the field No.11";
                this.setPosition(11);
                break;
            case 14:
                action = "14: Bear - tightrope walker - go forward to the field No.30";
                this.setPosition(30);
                break;
            case 16:
                action = "16: Girl - tightrope dancer - go back to the first field";
                this.setPosition(1);
                break;
            case 25:
                action = "25: Pig on the board going down - go back to the field No.7";
                this.setPosition(7);
                break;
            case 34:
                action = "34: Lion on the stairs - go forward to the field No.97";
                this.setPosition(97);
                break;
            case 36:
                action = "36: Dancing dog - go forward to the field No.45";
                this.setPosition(45);
                break;
            case 40:
                action = "40: Rooster on the stairs - go forward to the field No.58";
                this.setPosition(58);
                break;
            case 50:
                action = "50: Running dog - go back to the field No.32";
                this.setPosition(32);
                break;
            case 52:
                action = "52: Dog jumping through the hoop - go forward to the field No.68";
                this.setPosition(68);
                break;
            case 53:
                action = "53: Monkey going down the pole - go back to the field No.8";
                this.setPosition(8);
                break;
            case 57:
                action = "57: Rabbit the drummer - go forward to the field No.63";
                this.setPosition(63);
                break;
            case 59:
                action = "59: Dancing horse - go forward to the field No.80";
                this.setPosition(80);
                break;
            case 65:
                action = "65: Flying pigeons - go forward to the field No.83";
                this.setPosition(83);
                break;
            case 71:
                action = "71: Sky-diving cat - go back to the field No.51";
                this.setPosition(51);
                break;
            case 72:
                action = "72: Tiger jumping through flaming hoop - go forward to the field No.94";
                this.setPosition(94);
                break;
            case 87:
                action = "87: Acrobat monkey - go back to the field No.67";
                this.setPosition(67);
                break;
            case 92:
                action = "92: Goose with a flower - go forward to the field No.108";
                this.setPosition(108);
                break;
            case 96:
                action = "96: Juggler with rings - go forward to the field No.116";
                this.setPosition(116);
                break;
            case 102:
                action = "102: Acrobat - go back to the field No.81";
                this.setPosition(81);
                break;
            case 107:
                action = "107: Goat on the stairs - go back to the field No.23";
                this.setPosition(23);
                break;
            case 112:
                action = "112: Clown with an umbrella - go back to the field No.90";
                this.setPosition(90);
                break;
            case 117:
                action = "117: Flying trapeze trick - go back to the field No.98";
                this.setPosition(98);
                break;
            case 119:
                action = "119: Cat jumping through a hoop - go back to the field No.101";
                this.setPosition(101);
                break;

            default:
                action = this.getPosition()+".";
                break;
        }
        return action;
    }


}
