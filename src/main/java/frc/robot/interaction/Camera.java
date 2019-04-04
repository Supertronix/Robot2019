package frc.robot.interaction;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.RobotMap;

public class Camera implements RobotMap.Camera {

    public static UsbCamera camera;

    public Camera(){
        camera = CameraServer.getInstance().startAutomaticCapture();
        
        //camera.setFPS(IMAGE_PAR_SECONDE);
        //camera.setResolution(LARGEUR, HAUTEUR);
        
        //camera.setExposureManual(0);
        //camera.setWhiteBalanceManual(0);
        //camera.setPixelFormat(PixelFormat.kMJPEG);
        //camera.setExposureAuto();
        //camera.setWhiteBalanceAuto();
        
        //camera.setVideoMode(VideoMode.PixelFormat.kMJPEG,1280,720,60);
        //camera.setVideoMode(VideoMode.PixelFormat.kMJPEG,640,480,120);
        camera.setVideoMode(VideoMode.PixelFormat.kMJPEG,424,240,240);

    }
}