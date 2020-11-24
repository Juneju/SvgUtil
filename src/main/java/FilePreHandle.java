import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class FilePreHandle {
    /*
     * 替换文件中的字符串，并覆盖原文件
     * @param filePath
     * @param oldstr
     * @param newStr
     * @throws IOException
     */
    static void autoReplaceStr(String filePath) {
        try {
            File file = new File(filePath);
            Long fileLength = file.length();
            byte[] fileContext = new byte[fileLength.intValue()];
            FileInputStream in = null;
            PrintWriter out = null;
            in = new FileInputStream(filePath);
            in.read(fileContext);
            // 避免出现中文乱码
            String str = new String(fileContext, "utf-8");//字节转换成字符
            str = str.replace("optimize-speed", "optimizeSpeed");
            out = new PrintWriter(filePath, "utf-8");//写入文件时的charset
            out.write(str);
            out.flush();
            out.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
