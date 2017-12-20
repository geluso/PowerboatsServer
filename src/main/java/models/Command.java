package models;

public class Command {
    public boolean isLeft = false;
    public boolean isRight = false;
    public boolean isStraight = true;
    public boolean isSpeedFaster = false;
    public boolean isSpeedSlower = false;
    public boolean isSpeedSame = true;

    // the server will send back the command but the game won't actually
    // press "GO!!" until the player is completely committed to the command.
    // this allows the player to turn left, and right to see their turn
    // before moving.
    public boolean isCommitted = false;

    // keep track of if the server has sent out this command yet.

    public boolean isRead = false;

    public Command() {
    }

    public void turnLeft() {
        this.setTurn(true, false, false);
    }

    public void turnRight() {
        this.setTurn(false, true, false);
    }

    public void goStraight() {
        this.setTurn(false, false, true);
    }

    public void goFaster() {
        this.setSpeed(true, false, false);
    }

    public void slowDown() {
        this.setSpeed(false, true, false);
    }

    public void maintainSpeed() {
        this.setSpeed(false, false, true);
    }

    public void setTurn(boolean isLeft, boolean isRight, boolean isStraight) {
        this.isLeft = isLeft;
        this.isRight = isRight;
        this.isStraight = isStraight;
    }

    public void setSpeed(boolean isFaster, boolean isSlower, boolean isSame) {
        this.isSpeedFaster = isFaster;
        this.isSpeedSlower = isSlower;
        this.isSpeedSame = isSame;
    }

    public void commit () {
        this.isCommitted = true;
    }

    public String toString() {
        String str = "";

        if (isCommitted) {
            str += "committed ";
        } else {
            str += "uncommitted ";
        }

        if (this.isSpeedFaster) {
            str += "faster ";
        } else if (this.isSpeedSlower) {
            str += "slower ";
        } else {
           str += "same-speed ";
        }

        if (isLeft) {
            str += "left";
        } else if (isRight) {
            str += "right";
        } else {
            str += "straight";
        }

        return str;
    }
}
