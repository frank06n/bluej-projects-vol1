import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Utility related to files and directories
 */
public final class FileCompat {
	public static enum FolderFlag {
		SKIP, REPLACE, JOIN;
	}
	
    public static void moveFolder( String src, String dst, boolean replace_files, FolderFlag folder_flag, PrintStream out) {
    	String name = FileCompat.getLastPathSegment(src);
    	if (out == null) out = System.out;
    	
        if (isFile(src) && !isDirectory(dst) && (!isFile(dst) || replace_files)) {
        	FileCompat.copyFile(src, dst);
        	if (out != null)
        		out.println("File Copied: " + name); 
        }
        else if (isDirectory(src) && !isFile(dst)) {
        	if (isDirectory(dst))
        		switch(folder_flag) {
        		case SKIP:
        			out.println("Skipping Folder: " + name);
        			return;
        		case REPLACE:
        			FileCompat.delete(dst);
        			out.println("Replacing Folder: " + name);
        			break;
        		case JOIN:
        		default:
        			out.println("Joining Folder: " + name);
        		}
        	
        	ArrayList<String> src_subfile_list = new ArrayList<String>();
        	listDir(src, src_subfile_list);
        	
        	for (String src_subfile : src_subfile_list)
        		moveFolder(src_subfile, dst + "/" + FileCompat.getLastPathSegment(src_subfile), replace_files, folder_flag, out);
        	out.println("Copied Folder: " + name);
        }
        else {
        	out.println("Copying failed: " + src + " to " + dst);
        }
    }
    
    public static void createEmptyFile(String filePath) {
        try {
        	File file = new File(filePath);
        	file.getParentFile().mkdirs();
        	file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] readFile(String filePath) {
    	try {
    		RandomAccessFile file = new RandomAccessFile(filePath, "r");
    		long length = file.length();
    		if (length > (long)Integer.MAX_VALUE) {
    			file.close();
    			throw new Exception("File is too long to read!");
    		}
    		byte[] data = new byte[(int)length];
    		file.read(data);
    		file.close();
    		return data;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static String readFileAsText(String filePath) {
    	return new String(readFile(filePath));
    }

    
    public static void writeFile(String filePath, byte[] data) {
        try {
        	createEmptyFile(filePath);
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.write(data);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileAsText(String filePath, String data) {
        writeFile(filePath, data.getBytes());
    }
    
    public static void rename(String real_name, String new_name) {
    	new File(real_name).renameTo(new File(new_name));
    }
    
    public static void copyFile(String src, String dst) {
        try {
            if (!isExistingFile(src)) return;
            createEmptyFile(dst);
            FileInputStream fileInputStream = new FileInputStream(src);
            FileOutputStream fileOutputStream = new FileOutputStream(dst, false);
            FileCompat.copyStream(fileInputStream, fileOutputStream);
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void moveFile(String sourceFilePath, String destinationFilePath) {
        copyFile(sourceFilePath, destinationFilePath);
        delete(sourceFilePath);
    }
    
    /**
     * If the provided filePath:
     * Refers to a file 		- Deletes it
     * Refers to a directory	- Deletes all its files and subdirectories
     * Does not exist 			- Returns
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.isFile())
            file.delete();
        else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File _file : listFiles)
                    delete(_file.getAbsolutePath());
            }
            file.delete();
        }
    }

    /**
     * If the provided filePath:
     * Refers to a directory or file	- TRUE
     * Does not exist 					- FALSE
     */
    public static boolean isExistingFile(String filePath) {
        return (new File(filePath)).exists();
    }

    /**
     * Makes the directory referred by the provided filePath,
     * creating any necessary non-existent parents and,
     * returns if it was successful.
     */
    public static boolean makeDir(String filePath) {
        return new File(filePath).mkdirs();
    }

    /**
     * If the provided filePath is a directory,
     * then adds the filePaths of all the,
     * files and subdirectories in the directory,
     * into the provided list.
     */
    public static void listDir(String filePath, ArrayList<String> list) {
    	if (list == null)
    		return;
    	else
    		list.clear();
        File file = new File(filePath);
		File[] arr = file.listFiles();
		if (arr != null && arr.length > 0)
			for (File _file : arr)
				list.add(_file.getAbsolutePath());
    }
    
    /**
     * If the provided filePath:
     * Refers to a directory 				- TRUE
     * Refers to a file or does not exist 	- FALSE
     */
    public static boolean isDirectory(String filePath) {
        if (isExistingFile(filePath))
            return new File(filePath).isDirectory();
        return false;
    }
    
    /**
     * If the provided filePath:
     * Refers to a file 						- TRUE
     * Refers to a directory or does not exist 	- FALSE
     */
    public static boolean isFile(String filePath) {
        if (isExistingFile(filePath))
            return new File(filePath).isFile();
        return false;
    }
    
    /**
	 * Returns the size of the file in bytes.
     */
    public static long getFileLength(String filePath) {
        if (isExistingFile(filePath))
            return new File(filePath).length();
        return 0;
    }
    
    /**
     * Returns the last segment of the provided file path.
     */
    public static String getLastPathSegment(String filePath) {
        if (filePath.endsWith(File.separator))
        	filePath = filePath.substring(0, filePath.length() - 1);
        int i = filePath.lastIndexOf(File.separator);
        return (i == -1) ? filePath : filePath.substring(i + 1);
    }
    
    /**
     * Copies the data,
     * from the provided InputStream,
     * to   the provided OutputStream.
     */
    public static void copyStream(InputStream input, OutputStream output) throws IOException {
    	byte buffer[] = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
        	output.write(buffer, 0, length);
            output.flush();
        }
    }
}
            