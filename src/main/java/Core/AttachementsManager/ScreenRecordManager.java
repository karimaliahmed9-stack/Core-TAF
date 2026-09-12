package Core.AttachementsManager;

import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import com.automation.remarks.video.RecorderFactory;
import com.automation.remarks.video.recorder.IVideoRecorder;
import com.automation.remarks.video.recorder.VideoRecorder;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;

public class ScreenRecordManager {
    public final static String RECORDINGS_PATHE = "Test-Output/ScreenRecords/";
    private static final ThreadLocal<IVideoRecorder> recorder = new ThreadLocal<>();

    /**
     * Start screen record
     */

    public static void StartRecord() {
        if (PropertyReader.getProperty("RecordTests").equalsIgnoreCase("true")) {
            try {
                //Ensure the record dir exists
                File recordDer = new File(RECORDINGS_PATHE);
                if (!recordDer.exists()) {
                    recordDer.mkdirs();
                }
                //configure recorde to user custom directory and file name
                if (PropertyReader.getProperty("ExecutionType").equalsIgnoreCase("local")) {
                    recorder.set(RecorderFactory.getRecorder(VideoRecorder.conf().recorderType()));
                    //Start recoding
                    recorder.get().start();
                    LogManager.Info("Recording is starting... ");
                }

            } catch (Exception e) {
                LogManager.Error("Failed Start record " + e.getMessage());
            }
        }
    }

    /**
     * Stop record and return the video as an inputstream
     */
    public static void StopRecord(String testmethodName) {
        try {
            if (recorder.get() != null) {
                //Stop the recorder and get the video file
                String FileVideoPath = String.valueOf(recorder.get().stopAndSave(testmethodName));
                File FileVideo = new File(FileVideoPath);
                LogManager.Info("Video saved at:" + FileVideo.getAbsolutePath());
                //Convert file video at mp4
                File mp4file = encodeRecording(FileVideo);
                LogManager.Info("Recorder stoping and converted to mp4 :" + mp4file.getName());
            }
        } catch (Exception e) {
            LogManager.Error("Failed stop record ", e.getMessage());
        } finally {
            recorder.remove();
        }
    }

    /**
     * Covert video to mp4
     */
    public static File encodeRecording(File surcefile) {
        File targetFile = new File(surcefile.getParent(), surcefile.getName().replace(".avi", ""));
        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("acc");


            VideoAttributes video = new VideoAttributes();
            video.setCodec("libx264");

            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setOutputFormat("mp4"); //Output Format


            encodingAttributes.setAudioAttributes(audio);
            encodingAttributes.setVideoAttributes(video);

            //Encoded the video
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(surcefile), targetFile, encodingAttributes);

            //Deleted the original .avi file after conversion
            if (targetFile.exists()) {
                surcefile.delete();
                LogManager.Info("Deleted Original File AVI : " + surcefile.getName());
            }

        } catch (EncoderException e) {
            LogManager.Error("Failed To Convert Video To Mp4 : ", e.getMessage());
        }
        return targetFile;
    }


}
