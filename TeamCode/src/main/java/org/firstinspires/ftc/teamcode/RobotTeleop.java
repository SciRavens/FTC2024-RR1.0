package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SciRavens-TeleOp")
public class RobotTeleop extends LinearOpMode {
    public Robot robot;
    public DriveTrain DT;
    public Slider slider;
    public Arm arm;
    public Wrist wrist;
    public Claw claw;

    RevBlinkinLedDriver.BlinkinPattern pattern;
    Leds leds;
private int cur = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        DT = new DriveTrain(robot, gamepad1);
        slider = new Slider(robot, gamepad2);
        arm = new Arm(robot);
        wrist = new Wrist(robot);
        claw = new Claw(robot);

        leds = new Leds(robot);
        leds.setPattern(0);

        waitForStart();
        leds.setPattern(cur);
        while(opModeIsActive()) {
            DT.drive();
            slider_operate();
            arm_wrist_operate();
            claw_operate();
            leds_operate();
        }
    }

    private void arm_wrist_operate()
    {
        if (gamepad2.a) {
            arm.setPosSample();
            wrist.setPosSample();
        } else if (gamepad2.b) {
            arm.setPosBasket();
            wrist.setPosBasket();
        } else if(gamepad2.y) {
            arm.setPosChamber();
            wrist.setPosChamber();
        } else if(gamepad2.x) {
            arm.setPosSpecimen();
            wrist.setPosSpecimen();
        }
    }



    public void slider_operate() {
        slider.autoOpCompletionCheck();
        if (gamepad2.dpad_down) {
            // Go to the bottom
            slider.fold();
        } else if (gamepad2.dpad_up) {
            // Goto the middle
            slider.HighBasket();
        } else if (gamepad2.left_stick_y != 0) {
            slider.manualOp(gamepad2.left_stick_y);
        } else {
            slider.manualDefaultStop();
        }
    }

    //    private void slider_pos() {
//        if (gamepad2.dpad_up) {
//            slider.LowBasket();
//        } else if (gamepad2.dpad_down) {
//            slider.LowChamber();
//        } else if (gamepad2.dpad_left) {
//            slider.HighBasket();
//        } else if (gamepad2.dpad_right) {
//            slider.HighChamber();
//        }
//
//    }

    private void claw_operate() {
        if (gamepad2.left_trigger > 0.9) {
            claw.open();
        } else if (gamepad2.right_trigger > 0.9) {
            claw.open();
        } else {
            claw.close();
        }
    }
    private void leds_operate() {
        if (gamepad2.right_bumper || gamepad1.right_bumper) {
            cur = (cur + 1) % leds.patterns.length;
            leds.setPattern(cur);
            telemetry.addData("SETTING COLOR", leds.patterns[cur].toString());
            telemetry.update();
        }
    }
}

