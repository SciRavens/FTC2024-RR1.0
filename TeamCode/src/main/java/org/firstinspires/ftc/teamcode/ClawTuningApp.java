package org.firstinspires.ftc.teamcode;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ClawTuning")
public class ClawTuningApp extends LinearOpMode {
    public Robot robot;
    public Claw claw;
    public double right_claw_start_pos = 0.5;
    public double right_claw_cur_pos = right_claw_start_pos;

    public double left_claw_start_pos = 0.5;
    public double left_claw_cur_pos = left_claw_start_pos;
    public double claw_inc = 0.05;
    public boolean buttonPressed = false;
    public boolean right_claw_tuning = true;
    public boolean left_claw_tuning = true;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        claw = new Claw(robot);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.dpad_left) {
                left_claw_tuning = true;
            } else if (gamepad2.dpad_right) {
                right_claw_tuning = true;
            }
            if (right_claw_tuning) {
                right_claw_operate();
            }
            if (left_claw_tuning) {
                left_claw_operate();
            }

        }
    }


    private void right_claw_operate() {
        if (gamepad2.dpad_up && !buttonPressed) {
            if (right_claw_cur_pos < 0.9) {
                right_claw_cur_pos += claw_inc;
            }
            buttonPressed = true;
        } else if (gamepad2.dpad_down && !buttonPressed) {
            if (right_claw_cur_pos > 0.1) {
                right_claw_cur_pos -= claw_inc;
            }
            buttonPressed = true;
        } else {
            if (!gamepad2.dpad_up && !gamepad2.dpad_down)
                buttonPressed = false;

        }
        claw.setPosAbsoluteRight(right_claw_cur_pos);
        robot.telemetry.addData("Right Current Value:", right_claw_cur_pos);
        robot.telemetry.update();
    }

    private void left_claw_operate() {
        if (gamepad2.dpad_up && !buttonPressed) {
                if (left_claw_cur_pos < 0.9) {
                    left_claw_cur_pos += claw_inc;
                }
                buttonPressed = true;
            } else if (gamepad2.dpad_down && !buttonPressed) {
                if (left_claw_cur_pos > 0.1) {
                    left_claw_cur_pos -= claw_inc;
                }
                buttonPressed = true;
            } else {
                if (!gamepad2.dpad_up && !gamepad2.dpad_down)
                    buttonPressed = false;
            }
        claw.setPosAbsoluteLeft(left_claw_cur_pos);
        robot.telemetry.addData("Left Claw Current Value:", left_claw_cur_pos);
        robot.telemetry.update();
    }
}

