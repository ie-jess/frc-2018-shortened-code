package frc.team4159.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4159.robot.OI;
import frc.team4159.robot.Robot;
import frc.team4159.robot.subsystems.CubeHolder;
import frc.team4159.robot.subsystems.Superstructure;

public class LiftCube extends Command {

    private CubeHolder cubeHolder;
    private OI oi;

    public LiftCube() {
        requires(Superstructure.cubeHolder);
        cubeHolder = Superstructure.getInstance().getCubeHolder();
        oi = OI.getInstance();
    }

    @Override
    protected void execute() {

        /*
         * Intake and outtake wheel control
         */
        if(oi.intakeButton() && oi.outtakeButton()) {
            cubeHolder.stopFlywheels();

        } else if(oi.intakeButton()) {
            cubeHolder.intake();

        } else if(oi.outtakeButton()) {
            cubeHolder.outtake();

        } else {
            cubeHolder.stopFlywheels();
        }


        /*
         * Open claw if trigger is pressed. Closed by default.
         */
        if(oi.openClaw()) {
            cubeHolder.open();
        } else {
            cubeHolder.close();
        }

        /*
         * Use secondary y-axis to control lifter if not used to control climbing
         */
        if(!oi.climbEnable()) {

            if (!cubeHolder.getRawMode()) {
                if (oi.setSwitchHeight())
                    cubeHolder.setToSwitch();

                else if(oi.setLiftTargetZero())
                    cubeHolder.setToBottom();

                else if (Math.abs(oi.getSecondaryY()) > 0.1)
                    cubeHolder.updatePosition(oi.getSecondaryY());

                cubeHolder.move();

            } else {
                cubeHolder.setRawLift(oi.getSecondaryY());

            }
        }

        if(Robot.oi.toggleLifterRawMode()){
            cubeHolder.toggleLifterRawMode();
        }

//        if(Robot.oi.resetLiftEncoder()){
//            cubeHolder.resetLiftEncoder();
//        }

        cubeHolder.logDashboard();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        cubeHolder.stopFlywheels();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
