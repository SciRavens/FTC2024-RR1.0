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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, 70, Math.toRadians(180)))
                 //SPECIMEN FOR RED SIDE
//                .splineTo(new Vector2d(0,-27), Math.toRadians(90))
//                .lineToYConstantHeading(-45)
//                //claw.closeClawAction()
//                //arm.setChamberAutonAction()
//                //wrist.setChamberAutonAction()
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.openClawAction
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .waitSeconds(1)
//                .setTangent(0)
//                .splineToConstantHeading(new Vector2d(35,-30), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(47,0), Math.toRadians(0))
//                .setTangent(Math.toRadians(90))
//                .lineToYConstantHeading(-60) // pushes first sample into the observation zone
//                .lineToY(-10)
//                .setTangent(0)
//                .lineToX(55)
//                .setTangent(Math.toRadians(90))
//                .lineToY(-60) // pushes second sample into the observation zone
//                .lineToY(-10)
//                .setTangent(Math.toRadians(0))
//                .lineToX(65)
//                .setTangent(Math.toRadians(90))
//                .lineToY(-60) // pushes third sample into the observation zone
//                .lineToY(-51)
//                .strafeTo(new Vector2d(46, -51))
//                .turn(Math.toRadians(180)) // sets up to pick up specimen
//                //wrist.setSampleAction()
//                //arm.setSampleAction()
//                //claw.closeClawAction()
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .waitSeconds(1)
////                .strafeTo(new Vector2d(0,-51))
////                .turn(Math.toRadians(180))
//                //arm.setChamberAutonAction()
//                //wrist.setChamberAutonAction()
//                .splineTo(new Vector2d(0,-30), Math.toRadians(90)) // places specimen
//                .waitSeconds(1)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.openClawAction
//                .splineTo(new Vector2d(47,-61), Math.toRadians(0)) // parking



                //SAMPLES FOR RED SIDE
//                //claw.closeClawAction()
//                //arm.setChamberAutonAction()
//                //wrist.setChamberAutonAction()
//                .splineTo(new Vector2d(0,-27), Math.toRadians(90))
//                .waitSeconds(1)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.openClawAction
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .lineToYConstantHeading(-45)
//                .strafeTo(new Vector2d(-48, -45)) //goes toward first sample
//                .setTangent(Math.toRadians(90))
//                .lineToY(-37)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.closeClawAction()
//                .lineToY(-52)
//                //arm.setBasketAction()
//                //wrist.setBasketAction()
//                .turn(Math.toRadians(135))
//                .setTangent(Math.toRadians(225))
//                .lineToY(-59)
//                //claw.openClawAction() // drops first sample in basket
//                .lineToY(-52)
//                .turnTo(Math.toRadians(90))
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .strafeTo(new Vector2d(-58.5, -52)) // goes toward second sample
//                .lineToY(-37)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.closeClawAction()
//                .lineToY(-52)
//                //arm.setBasketAction()
//                //wrist.setBasketAction()
//                .turn(Math.toRadians(135))
//                .setTangent(Math.toRadians(225))
//                .lineToY(-59)
//                //claw.openClawAction() // drops second sample in basket
//                .lineToY(-52)
//                .turnTo(Math.toRadians(90))
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .lineToY(-56.5)
//                .lineToY(-52)




                // SAMPLES FOR BLUE SIDE
                .strafeTo(new Vector2d(-70,25))
                .strafeTo(new Vector2d(-36,5))
                .strafeTo(new Vector2d(-42,5))
                //claw.closeClawAction()
                //arm.setChamberAutonAction()
                //wrist.setChamberAutonAction()
//                .setTangent(Math.toRadians(270))
//                .lineToY(27)
//                .waitSeconds(1)
                //arm.setSampleAction()
                //wrist.setSampleAction()
                //claw.openClawAction
                //arm.setStartingFoldAction()
                //wrist.setWristStartingAction()
                //.setTangent(Math.toRadians(360))
              // .lineToY(45)
//                .splineTo(new Vector2d(48, 25), Math.toRadians(270)) //goes toward first sample
//                .setTangent(Math.toRadians(90))
//                .lineToY(37)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.closeClawAction()
//                .lineToY(52)
//                //arm.setBasketAction()
//                //wrist.setBasketAction()
//                .turn(Math.toRadians(135))
//                .setTangent(Math.toRadians(225))
//                .lineToY(59)
//                //claw.openClawAction() // drops first sample in basket
//                .lineToY(52)
//                .turnTo(Math.toRadians(90))
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .strafeTo(new Vector2d(58.5, 52)) // goes toward second sample
//                .lineToY(37)
//                //arm.setSampleAction()
//                //wrist.setSampleAction()
//                //claw.closeClawAction()
//                .lineToY(52)
//                //arm.setBasketAction()
//                //wrist.setBasketAction()
//                .turn(Math.toRadians(135))
//                .setTangent(Math.toRadians(225))
//                .lineToY(59)
//                //claw.openClawAction() // drops second sample in basket
//                .lineToY(52)
//                .turnTo(Math.toRadians(90))
//                //arm.setStartingFoldAction()
//                //wrist.setWristStartingAction()
//                .lineToY(56.5)
//                .lineToY(52)
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}