package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;




public class Arm {
    private Robot robot;
    //    static private double pos_whitepixel = 0.215;
    public double target;

    private double max_speed = 0.002;
    private double threshold = 0.005;

    private final double P = 0.01;
    public Arm(Robot robot) {

        this.robot = robot;
        this.target = robot.servoArm.getPosition();
    }
    public void setPosStarting(){robot.servoArm.setPosition(robot.arm_pos_starting);}
    public void setPosSample() {
        robot.servoArm.setPosition(robot.arm_pos_sample);
    }
    public void setPosSampleTwo() {
        robot.servoArm.setPosition(robot.arm_pos_sample_two);
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

    public void operate() {
        double curr_pos = robot.servoArm.getPosition();
        double diff = target - curr_pos;
        if (Math.abs(diff) > threshold) {
            double next_speed = Math.max(Math.min(diff * P, max_speed), -max_speed);
            double next_pos = curr_pos + next_speed;
            robot.servoArm.setPosition(next_pos);
        }
        robot.telemetry.addData("Arm Curr Pos:", curr_pos);
        robot.telemetry.addData("Arm Target:", this.target);
        robot.telemetry.update();
    }

    public class ArmChamberAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosChamber();
            return false;
        }
    }
    public Action setChamberAction() {
        return new ArmChamberAction();
    }


    public class ArmSampleAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSample();
            return false;
        }
    }
    public Action setSampleAction() {
        return new ArmSampleAction();
    }

    public class ArmSpecimenAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosSpecimen();
            return false;
        }
    }
    public Action setSpecimenAction() {
        return new ArmSpecimenAction();
    }

    public class ArmBasketAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosBasket();
            return false;
        }
    }
    public Action setBasketAction() {
        return new ArmBasketAction();
    }

}


