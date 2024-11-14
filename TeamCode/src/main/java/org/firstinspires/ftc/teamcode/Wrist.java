package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;


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

    public void setPosChamberAuton()
    {
        robot.servoWrist.setPosition(robot.wrist_pos_chamber_auton);
    }

    public void setPosAbsolute(double pos)
    {
        robot.servoWrist.setPosition(pos);
    }


    public class WristChamberAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosChamber();
            return false;
        }
    }
    public Action setChamberAction() {
        return new WristChamberAction();
    }

    public class WristSpecimenAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSpecimen();
            return false;
        }
    }
    public Action setSpecimenAction() {
        return new WristSpecimenAction();
    }

    public class WristSampleAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSample();
            return false;
        }
    }
    public Action setSampleAction() {
        return new WristSampleAction();
    }

    public class WristChamberActionAuton implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosBasket();
            return false;
        }
    }
    public Action setChamberActionAuton() {
        return new WristChamberActionAuton();
    }


    public class WristBasketAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSpecimen();
            return false;
        }
    }
    public Action setBasketAction() {
        return new WristBasketAction();
    }





}
