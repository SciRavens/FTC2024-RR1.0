package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import androidx.annotation.NonNull;




public class Arm {
    private Robot robot;
    //    static private double pos_whitepixel = 0.215;
    public Arm(Robot robot) {
        this.robot = robot;
    }
    public void setPosStarting(){robot.servoArm.setPosition(robot.arm_pos_starting);}
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


