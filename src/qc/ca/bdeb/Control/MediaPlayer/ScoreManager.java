package qc.ca.bdeb.Control.MediaPlayer;

import qc.ca.bdeb.Model.User;
import qc.ca.bdeb.Utilities.Temps;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class de score manager
 */
public class ScoreManager {
    private String pathfile;
    private File file;
    private String[][] userList;
    public ScoreManager() throws IOException {
        pathfile = "src/qc/ca/bdeb/Texte/Score.txt";
        file=new File(pathfile);
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * méthode qui ecrit dans le fichier
     * @param user
     * @throws IOException
     */
    public void writeinfile(User user) throws IOException {
        String line=user.toString();
        try {
            FileWriter fileWriter=new FileWriter(file);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode qui lit tout le fichier
     * @return
     */

    public String[][] Readfile() {

        if (file.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(pathfile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    int i=0;
                    {
                        String [] values =line.split(";");
                        userList[i]=values;
                    }
                    i++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }
}
