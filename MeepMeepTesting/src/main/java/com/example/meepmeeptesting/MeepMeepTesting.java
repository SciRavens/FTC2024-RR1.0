package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -70, Math.toRadians(90)))
                 // Specimens for red side
                .splineTo(new Vector2d(0,-27), Math.toRadians(90))
                .lineToYConstantHeading(-45)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(35,-30), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(47,0), Math.toRadians(0))
                .setTangent(Math.toRadians(90))
                .lineToYConstantHeading(-60) // pushes first sample into the observation zone
                .lineToY(-10)
                .setTangent(0)
                .lineToX(55)
                .setTangent(Math.toRadians(90))
                .lineToY(-60) // pushes second sample into the observation zone
                .lineToY(-10)
                .setTangent(Math.toRadians(0))
                .lineToX(65)
                .setTangent(Math.toRadians(90))
                .lineToY(-60) // pushes third sample into the observation zone
                .lineToY(-40)
                .strafeTo(new Vector2d(46, -40))
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(46,-51))
                //wrist.setSampleAction()
                //arm.setSampleAction()
                //claw.closeClawAction()
                //arm.setStartingFoldAction()
                //wrist.setWristStartingAction()
                .waitSeconds(1)
                .strafeTo(new Vector2d(0,-50))
                .turn(Math.toRadians(180))
                //arm.setChamberAutonAction()
                //wrist.setChamberAutonAction()
                .splineTo(new Vector2d(0,-30), Math.toRadians(90))
                //arm.setSampleAction()
                //wrist.setSampleAction()
                //claw.openClawAction
                .splineTo(new Vector2d(47,-61), Math.toRadians(0))











                //Samples for red side
//                .splineTo(new Vector2d(0,-27), Math.toRadians(90))
//                .lineToYConstantHeading(-45)
//                .strafeTo(new Vector2d(-48, -45))
//                .setTangent(Math.toRadians(90))
//                .lineToY(-37)
//                .lineToY(-52)
//                .turn(Math.toRadians(135))
//                .setTangent(Math.toRadians(225))
//                .lineToY(-59)
//                .lineToY(-52)
//                .turnTo(Math.toRadians(90))
//                .strafeTo(new Vector2d(-58.5, -52)) // goes toward second sample
//                .setTangent(Math.toRadians(90))
//                .lineToY(-37)
//                .lineToY(-52)
//                .turn(Math.toRadians(150))
//                .lineToY(-56.5)
//                .lineToY(-52)
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}