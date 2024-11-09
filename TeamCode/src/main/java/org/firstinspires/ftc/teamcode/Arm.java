package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Gamepad;



public class Arm {
    private Robot robot;
    //    static private double pos_whitepixel = 0.215;
    public Arm(Robot robot) {
        this.robot = robot;
    }

    public void setPosSample() {
        robot.servoArm.setPosition(robot.arm_pos_sample);
    }
    public void setPosChamber() {
        robot.servoArm.setPosition(robot.arm_pos_chamber);
    }
    public void setPosSpecimen() {
        robot.servoArm.setPosition(robot.arm_pos_specimen);
    }
    public void setPosBasket(){robot.servoArm.setPosition(robot.arm_pos_basket);}
    public void setPosAbsolute(double pos) {
        robot.servoArm.setPosition(pos);
    }
}


