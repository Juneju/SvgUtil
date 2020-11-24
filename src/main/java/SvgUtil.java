import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class SvgUtil {

    /**
     * svg转成jpeg格式
     * @param s 路径
     */
    public static void SaveAsJPEG(String s) {
        try {
            // Create a JPEG transcoder
            JPEGTranscoder t = new JPEGTranscoder();

            // Set the transcoding hints.
            t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                    0.99f);
            t.addTranscodingHint(JPEGTranscoder.KEY_HEIGHT,
                    3000f);
            // Create the transcoder input.
            String svgURI = new File(s).toURI().toString();
            TranscoderInput input = new TranscoderInput(svgURI);

            // Create the transcoder output.
            OutputStream ostream = new FileOutputStream("out.jpg");
            TranscoderOutput output = new TranscoderOutput(ostream);

            // Save the image.
            t.transcode(input, output);

            // Flush and close the stream.
            ostream.flush();
            ostream.close();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (TranscoderException e) {
            e.printStackTrace();
        }

    }

    /**
     * svg转成png格式
     * @param s 路径
     */
    private static void SaveAsPNG(String s, int height,String outputPath) {
        try {
            // Create a JPEG transcoder
            PNGTranscoder t = new PNGTranscoder();

            // Set the transcoding hints.
            t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                    0.99f);
            t.addTranscodingHint(JPEGTranscoder.KEY_HEIGHT,
                    (float) height);

            // Create the transcoder input.
            String svgURI = new File(s).toURI().toString();
            TranscoderInput input = new TranscoderInput(svgURI);

            // Create the transcoder output.
            OutputStream ostream = new FileOutputStream(outputPath);
            TranscoderOutput output = new TranscoderOutput(ostream);

            // Save the image.
            t.transcode(input, output);

            // Flush and close the stream.
            ostream.flush();
            ostream.close();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (TranscoderException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args 0:文件路径，1：高度(推荐>1000)，2：指定输出路径
     */
    public static void main(String[] args) {
        String path = args[0];
        String height = args[1];
        String outputPath="output.png";
        if(args.length>=3){
            outputPath=args[0];
        }
        infoOutput(path,height);
        FilePreHandle.autoReplaceStr(path);
        SvgUtil.SaveAsPNG(path, Integer.valueOf(height),outputPath);
        System.out.println("success!target path:"+outputPath);
    }
    private static void infoOutput(String path,String height){
        System.out.println("source path:"+path);
        System.out.println("height:"+height);
    }
}
