package org.usfirst.frc.team1793.robot.components.sensor;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import org.usfirst.frc.team1793.robot.system.ISystem;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
@SuppressWarnings("unused")
public class MPU6050  implements Gyro, ISystem {
	
	private static final int RA_AUX_VDDIO = 0;
	private static final int RA_YG_OFFS_TC = 1;
	private static final int RA_ZG_OFFS_TC = 2;
	private static final int RA_X_FINE_GAIN = 3;
	private static final int RA_Y_FINE_GAIN = 4;
	private static final int RA_Z_FINE_GAIN = 5;
	private static final int RA_XA_OFFS_H = 6;
	private static final int RA_XA_OFFS_L_TC = 7;
	private static final int RA_YA_OFFS_H = 8;
	private static final int RA_YA_OFFS_L_TC = 9;
	private static final int RA_ZA_OFFS_H = 10;
	private static final int RA_ZA_OFFS_L_TC = 11;
	private static final int RA_XG_OFFS_USRH = 19;
	private static final int RA_XG_OFFS_USRL = 20;
	private static final int RA_YG_OFFS_USRH = 21;
	private static final int RA_YG_OFFS_USRL = 22;
	private static final int RA_ZG_OFFS_USRH = 23;
	private static final int RA_ZG_OFFS_USRL = 24;
	private static final int RA_SMPLRT_DIV = 25;
	private static final int RA_CONFIG = 26;
	private static final int RA_GYRO_CONFIG = 27;
	private static final int RA_ACCEL_CONFIG = 28;
	private static final int RA_FF_THR = 29;
	private static final int RA_FF_DUR = 30;
	private static final int RA_MOT_THR = 31;
	private static final int RA_MOT_DUR = 32;
	private static final int RA_ZRMOT_THR = 33;
	private static final int RA_ZRMOT_DUR = 34;
	private static final int RA_FIFO_EN = 35;
	private static final int RA_I2C_MST_CTRL = 36;
	private static final int RA_I2C_SLV0_ADDR = 37;
	private static final int RA_I2C_SLV0_REG = 38;
	private static final int RA_I2C_SLV0_CTRL = 39;
	private static final int RA_I2C_SLV1_ADDR = 40;
	private static final int RA_I2C_SLV1_REG = 41;
	private static final int RA_I2C_SLV1_CTRL = 42;
	private static final int RA_I2C_SLV2_ADDR = 43;
	private static final int RA_I2C_SLV2_REG = 44;
	private static final int RA_I2C_SLV2_CTRL = 45;
	private static final int RA_I2C_SLV3_ADDR = 46;
	private static final int RA_I2C_SLV3_REG = 47;
	private static final int RA_I2C_SLV3_CTRL = 48;
	private static final int RA_I2C_SLV4_ADDR = 49;
	private static final int RA_I2C_SLV4_REG = 50;
	private static final int RA_I2C_SLV4_DO = 51;
	private static final int RA_I2C_SLV4_CTRL = 52;
	private static final int RA_I2C_SLV4_DI = 53;
	private static final int RA_I2C_MST_STATUS = 54;
	private static final int RA_INT_PIN_CFG = 55;
	private static final int RA_INT_ENABLE = 56;
	private static final int RA_DMP_INT_STATUS = 57;
	private static final int RA_INT_STATUS = 58;
	private static final int RA_ACCEL_XOUT_H = 59;
	private static final int RA_ACCEL_XOUT_L = 60;
	private static final int RA_ACCEL_YOUT_H = 61;
	private static final int RA_ACCEL_YOUT_L = 62;
	private static final int RA_ACCEL_ZOUT_H = 63;
	private static final int RA_ACCEL_ZOUT_L = 64;
	private static final int RA_TEMP_OUT_H = 65;
	private static final int RA_TEMP_OUT_L = 66;
	private static final int RA_GYRO_XOUT_H = 67;
	private static final int RA_GYRO_XOUT_L = 68;
	private static final int RA_GYRO_YOUT_H = 69;
	private static final int RA_GYRO_YOUT_L = 70;
	private static final int RA_GYRO_ZOUT_H = 71;
	private static final int RA_GYRO_ZOUT_L = 72;
	private static final int RA_EXT_SENS_DATA_00 = 73;
	private static final int RA_EXT_SENS_DATA_01 = 74;
	private static final int RA_EXT_SENS_DATA_02 = 75;
	private static final int RA_EXT_SENS_DATA_03 = 76;
	private static final int RA_EXT_SENS_DATA_04 = 77;
	private static final int RA_EXT_SENS_DATA_05 = 78;
	private static final int RA_EXT_SENS_DATA_06 = 79;
	private static final int RA_EXT_SENS_DATA_07 = 80;
	private static final int RA_EXT_SENS_DATA_08 = 81;
	private static final int RA_EXT_SENS_DATA_09 = 82;
	private static final int RA_EXT_SENS_DATA_10 = 83;
	private static final int RA_EXT_SENS_DATA_11 = 84;
	private static final int RA_EXT_SENS_DATA_12 = 85;
	private static final int RA_EXT_SENS_DATA_13 = 86;
	private static final int RA_EXT_SENS_DATA_14 = 87;
	private static final int RA_EXT_SENS_DATA_15 = 88;
	private static final int RA_EXT_SENS_DATA_16 = 89;
	private static final int RA_EXT_SENS_DATA_17 = 90;
	private static final int RA_EXT_SENS_DATA_18 = 91;
	private static final int RA_EXT_SENS_DATA_19 = 92;
	private static final int RA_EXT_SENS_DATA_20 = 93;
	private static final int RA_EXT_SENS_DATA_21 = 94;
	private static final int RA_EXT_SENS_DATA_22 = 95;
	private static final int RA_EXT_SENS_DATA_23 = 96;
	private static final int RA_MOT_DETECT_STATUS = 97;
	private static final int RA_I2C_SLV0_DO = 99;
	private static final int RA_I2C_SLV1_DO = 100;
	private static final int RA_I2C_SLV2_DO = 101;
	private static final int RA_I2C_SLV3_DO = 102;
	private static final int RA_I2C_MST_DELAY_CTRL = 103;
	private static final int RA_SIGNAL_PATH_RESET = 104;
	private static final int RA_MOT_DETECT_CTRL = 105;
	private static final int RA_USER_CTRL = 106;
	private static final int RA_PWR_MGMT_1 = 107;
	private static final int RA_PWR_MGMT_2 = 108;
	private static final int RA_BANK_SEL = 109;
	private static final int RA_MEM_START_ADDR = 110;
	private static final int RA_MEM_R_W = 111;
	private static final int RA_DMP_CFG_1 = 112;
	private static final int RA_DMP_CFG_2 = 113;
	private static final int RA_FIFO_COUNTH = 114;
	private static final int RA_FIFO_COUNTL = 115;
	private static final int RA_FIFO_R_W = 116;
	private static final int RA_WHO_AM_I = 117;
	private static final int MPU6050_ADDRESS = 0x68;
	private static final double MPU6050_250DPS = 0.00762939453;
	private I2C i2c;
	public MPU6050() {
		i2c = new I2C(Port.kOnboard, MPU6050_ADDRESS);
		
		SmartDashboard.putBoolean("write?", i2c.write(RA_PWR_MGMT_1, 0));
	}
	
	@Override
	public void calibrate() {
	}

	@Override
	public void reset() {
	}
	
	@Override
	public double getAngle() {
		return 0;
	}

	/*TODO correct offset
	 * 
	 * rate as double in degrees per second
	 * 250/<output>
	 * integrate to get angle
	*/
	@Override
	public double getRate() {
		ByteBuffer buffer = ByteBuffer.allocateDirect(2);
		i2c.read(RA_GYRO_XOUT_H, 2, buffer);
		byte lsb = buffer.get(0);
		byte msb = buffer.get(1);
		double rate = ((msb << 8)| lsb);
		return (rate-230)*MPU6050_250DPS;
	}

	@Override
	public void free() {
	}

}
