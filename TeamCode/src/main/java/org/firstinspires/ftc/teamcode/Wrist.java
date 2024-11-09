package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Wrist {
    private Robot robot;

    public Wrist(Robot robot) {
        this.robot = robot;
    }

    public void setPosSample()
    {
        robot.servoWrist.setPosition(robot.wrist_pos_sample);
    }

    public void setPosSpecimen()
    {
        robot.servoWrist.setPosition(robot.wrist_pos_specimen);
    }

    public void setPosChamber()
    {
        robot.servoWrist.setPosition(robot.wrist_pos_chamber);
    }

    public void setPosBasket()
    {
        robot.servoWrist.setPosition(robot.wrist_pos_basket);
    }

    public void setPosAbsolute(double pos)
    {
        robot.servoWrist.setPosition(pos);
    }
}
