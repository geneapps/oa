package com.giro.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileUtils {
	
	public static String HTTPURL = "";
	public static String LOCALPATH = "";
	
	public static long saveFile(String savePath,String fileName,File f2) throws Exception{
		long fileSize = 0l;
        int length=2097152;

        File f1 = new File(savePath, fileName); 
        
        if(!f1.getParentFile().exists()){
        	f1.getParentFile().mkdirs();
        }
        
        if(!f1.exists()){
        	f1.createNewFile();
        }
        
        FileInputStream in=new FileInputStream(f2);
        FileOutputStream out=new FileOutputStream(f1);
        FileChannel inC=in.getChannel();
        FileChannel outC=out.getChannel();
        ByteBuffer b=null;
        while(true){
            if(inC.position()==inC.size()){
            	fileSize = inC.size();
                inC.close();
                outC.close();
                return fileSize;
            }
            if((inC.size()-inC.position())<length){
                length=(int)(inC.size()-inC.position());
            }else
                length=2097152;
            b=ByteBuffer.allocateDirect(length);
            inC.read(b);
            b.flip();
            outC.write(b);
            outC.force(false);
        }
    }
}
