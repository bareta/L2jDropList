package util;

import java.io.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public final class NetworkInfo {
    private static String getHDSerial(){
        String os = System.getProperty("os.name");
        String serial = null;
        try {
            if(os.startsWith("Windows")) {
                serial=getHDSerialWindows("C");
            // } else if(os.startsWith("Linux")) {
            //    return getHDSerialLinux("D");
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch(Exception ex) {
            Logger.getLogger(NetworkInfo.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return serial;
    }

    public static String getHDSerialWindows(String drive) {
        String result = "";
        try {            
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
 
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n" 
                            + "Set objDrive = colDrives.item(\"" + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber";  
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception ex) {
            Logger.getLogger(NetworkInfo.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (result.trim().length() < 1  || result == null) {
            result = "NO_DISK_ID";
 
        }
 
        return result.trim();
    }

    public static void show(){
        DecimalFormat sizeFormatter = new DecimalFormat("###,###,###,###");
        String aux=sizeFormatter.format(Double.parseDouble(getHDSerial()));
        System.out.println("Serial HD: " + aux);
    }
}