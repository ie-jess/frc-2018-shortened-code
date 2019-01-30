package frc.team4159.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4159.robot.commands.cube.LiftCube;

public class CubeHolder extends Subsystem {

    private static CubeHolder instance;

    public static CubeHolder getInstance() {
        if(instance == null)
            instance = new CubeHolder();
        return instance;
    }

    private VictorSP liftVictor;
    private VictorSP intakeVictor;
    private DoubleSolenoid pistons;

    private CubeHolder() {

        intakeVictor = new VictorSP(0);
        liftVictor = new VictorSP(5);
        pistons = new DoubleSolenoid(7, 6);

    }

    /* Runs wheels inwards to intake the cube */
    public void intake() {
        intakeVictor.set(1);
    }

    /* Runs wheels outwards to outtake the cube */
    public void outtake() {
        intakeVictor.set(-1);
    }


    /* Stops running the wheels */
    public void stopFlywheels() {
        intakeVictor.set(0);
    }

    /* Opens the claw */
    public void open() {
        pistons.set(DoubleSolenoid.Value.kForward);
    }

    /* Closes the claw */
    public void close() {
        pistons.set(DoubleSolenoid.Value.kReverse);
    }

    public void moveArm(double value) {

        liftVictor.set(value);
    }

    /**
     * Set default command
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new LiftCube());
    }
}
