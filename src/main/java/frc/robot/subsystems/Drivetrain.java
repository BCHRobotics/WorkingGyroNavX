package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem{

    //How much we have to divde our encoders to get inches
    double encoderCal = 1;

    //Init talons
    TalonSRX TALONLEFT1 = new TalonSRX(RobotMap.TALONLEFT1);
    TalonSRX TALONLEFT2 = new TalonSRX(RobotMap.TALONLEFT2);
    TalonSRX TALONLEFT3 = new TalonSRX(RobotMap.TALONLEFT3);

    TalonSRX TALONRIGHT1 = new TalonSRX(RobotMap.TALONRIGHT1);
    TalonSRX TALONRIGHT2 = new TalonSRX(RobotMap.TALONRIGHT2);
    TalonSRX TALONRIGHT3 = new TalonSRX(RobotMap.TALONRIGHT3);

    

    public Drivetrain(){

        //Set ramp rates
        double rampRate = 0.5;

        TALONLEFT1.configOpenloopRamp(rampRate);
        TALONLEFT2.configOpenloopRamp(rampRate);
        TALONLEFT3.configOpenloopRamp(rampRate);

        TALONRIGHT1.configOpenloopRamp(rampRate);
        TALONRIGHT2.configOpenloopRamp(rampRate);
        TALONRIGHT3.configOpenloopRamp(rampRate);

        //Follow other motors
        TALONLEFT2.follow(TALONLEFT1);
        TALONLEFT3.follow(TALONLEFT1);

        TALONRIGHT2.follow(TALONRIGHT1);
        TALONRIGHT3.follow(TALONRIGHT1);

    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        

		TALONLEFT1.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT1.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        /*
        TALONLEFT2.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT2.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        TALONLEFT3.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
		TALONRIGHT3.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
		*/
    }
    
    public double getEncoderRight(){
    
        double encoderMove = this.TALONLEFT2.getSelectedSensorVelocity();
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }

    public double getEncoderLeft(){
    
        double encoderMove = this.TALONRIGHT2.getSelectedSensorVelocity();
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}