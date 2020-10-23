/*
 * Class Description: This class is for interacting with the filesystem for backup/restoring the database.
 * Currently only works in Linux servers.
*/
package brokers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class is for interacting with the filesystem for backup/restoring the database.
 * Currently only works in Linux servers.
 *
 * @author cuong
 */
public class BackupAndRestore {

    public String b = "";
    public String c = "";

    /**
     * Backs up the database to a file.
     * 
     * @param backUpFileName
     * @return boolean
     */
    public boolean backup(String backUpFileName) {
        String filepath = "/home/ubuntu/mysqlBackUpfile/"+backUpFileName+".sql";
        String command = "mysqldump -h capstonedatabase.cxxpzkl5egue.us-west-2.rds.amazonaws.com -uroot -ppassword --add-drop-database -B tandoorigrilldb -r" + filepath;
        try {
            Runtime rt = null;
            Process backupProcess = rt.getRuntime().exec(command);
             BufferedReader br = new BufferedReader(new InputStreamReader(backupProcess.getErrorStream()));
            String s = null;
            while ((s = br.readLine()) != null) {
                c += s;
            }
            int numberOfProcess = backupProcess.waitFor();
            
            if (numberOfProcess == 0) {
                return true;

            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(BackupAndRestore.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupAndRestore.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Lists all current backups in the filesystem
     * 
     * @return all filenames
     */
    public List<String> listAllBackupFiles()
    {
        //No real easy way to make this localhost AND target web service.
        String command = "ls /home/ubuntu/mysqlBackUpfile/";
        List<String> backupFileList = new ArrayList<>();
        Runtime rt = null;
        try {
            Process restoreProcess = rt.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(restoreProcess.getInputStream()));
            String s = null;
            while((s = br.readLine()) != null)
            {
                backupFileList.add(s);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(BackupAndRestore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return backupFileList;
        
    }
    
    /**
     * Replaces all data in the current database with a backup.
     * @param fileName
     * @return 
     */
    public boolean restore(String fileName) {
        String filepath = "/home/ubuntu/mysqlBackUpfile/"+fileName;
//        String command ="mysql -h capstonedb.ctiiyna588ff.us-west-2.rds.amazonaws.com --user=root --password=password -/home/ec2-user/backupsql/MysqlbackupVersion1.sql";
             String[] command = new String[]{"mysql", "-hcapstonedatabase.cxxpzkl5egue.us-west-2.rds.amazonaws.com","-u" +"root", "-p" + "password",
             "-e"," source " + filepath};
        try {
            Runtime rt = null;
            Process restoreProcess = rt.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(restoreProcess.getErrorStream()));
            String s = null;
            while ((s = br.readLine()) != null) {
                b += s;
            }
            int numberOfProcess = restoreProcess.waitFor();

            if (numberOfProcess == 0) {
                return true;

            } else {
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(BackupAndRestore.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupAndRestore.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
