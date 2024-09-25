package com.seeta.proxy;

import com.seeta.pool.FaceDetectorPool;
import com.seeta.pool.SeetaConfSetting;
import com.seeta.sdk.FaceDetector;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaRect;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * 人脸位置评估器
 */
public class FaceDetectorProxy {
    private static Logger logger = LoggerFactory.getLogger(FaceDetectorProxy.class);

    private FaceDetectorPool faceDetectorPool;

    private FaceDetectorProxy(){}

    public FaceDetectorProxy(SeetaConfSetting config) {
        faceDetectorPool = new FaceDetectorPool(config);
    }

    public SeetaRect[] detect(SeetaImageData image) {
        FaceDetector faceDetector = null;

        SeetaRect[] detect = null;

        try {
            faceDetector = faceDetectorPool.borrowObject();
            detect = faceDetector.Detect(image);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        finally {
            if (faceDetector != null) {
                faceDetectorPool.returnObject(faceDetector);
            }
        }

        return detect;
    }


//    public void setProperty(FaceDetector.Property property, double value) throws Exception {
//
//        FaceDetector faceDetector = null;
//        SeetaRect[] detect;
//        try {
//            faceDetector = faceDetectorPool.borrowObject();
//
//        }finally {
//            if (faceDetector != null) {
//                faceDetectorPool.returnObject(faceDetector);
//            }
//        }
//
//    }

}
