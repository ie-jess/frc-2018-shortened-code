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

        cubeHolder = CubeHolder.getInstance();
        oi = OI.getInstance();

        requires(cubeHolder);

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

        cubeHolder.moveArm(-oi.getSecondaryY());

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
