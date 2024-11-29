package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;


import com.qualcomm.robotcore.hardware.Gamepad;

public class Wrist {
    private Robot robot;
    public double target;
    private double speed = 0.001;

    public boolean speed_control = false;

    public Wrist(Robot robot) {

        this.robot = robot;
        this.target = robot.servoWrist.getPosition();
    }

    public void setPosStarting(){
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_starting);
    }
    public void setPosSample()
    {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_sample);
    }
    public void setPosSampleTwo()
    {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_sample_two);
    }
    public void setPosSpecimen()
    {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_specimen);
    }
    public void setPosHighChamber() {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_high_chamber);}
   // public void setPosLowChamber() {robot.servoWrist.setPosition(robot.wrist_pos_low_chamber);}

    public void setPosBasket()
    {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_basket);
    }

    public void setPosChamberAuton()
    {
        speed_control = false;
        robot.servoWrist.setPosition(robot.wrist_pos_chamber_auton);
    }

    public void setPosAbsolute(double pos)
    {
        speed_control = false;
        robot.servoWrist.setPosition(pos);
    }

    public void setSCTarget(double target) {
        speed_control = true;
        this.target = target;
    }

    public void operate() {
        if (!speed_control) return;
        double curr_pos = robot.servoWrist.getPosition();
        if (Math.abs(target - curr_pos) > speed) {
            double next_pos = curr_pos + speed * ((target > curr_pos) ? 1 : -1);
            robot.servoWrist.setPosition(next_pos);
        }
        robot.telemetry.addData("Wrist Curr Pos:", curr_pos);
        robot.telemetry.addData("Wrist Target:", this.target);
        robot.telemetry.update();
    }

    public class WristStartingFoldAction implements Action {
        @Override
        public boolean run (@NonNull TelemetryPacket packet) {
            setPosStarting();
            return false;
        }
    }

    public Action setStartingFoldAction() {
        return new WristStartingFoldAction();
    }

    public class WristHighChamberAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosHighChamber();
            return false;
        }
    }
    public Action setHighChamberAction() {
        return new WristHighChamberAction();
    }

//    public class WristLowChamberAction implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            setPosLowChamber();
//            return false;
//        }
//    }
//    public Action setLowChamberAction() {
//        return new WristLowChamberAction();
//    }


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
