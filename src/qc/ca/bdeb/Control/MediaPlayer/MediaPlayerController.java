package qc.ca.bdeb.Control.MediaPlayer;
import qc.ca.bdeb.View.ComponenetView.MediaPlayerView;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Class de controller de Media player
 */
public class MediaPlayerController {
    private File document;
    private ArrayList<String> filepath = new ArrayList<String>();
    private DataLine dataLine;
    private Clip clip;
    private Integer i=0;
    private MediaPlayerView mediaPlayerView;
    private Thread audioThread;

    /**
     * constructeur pour le mediaPlayer
     * @param mediaPlayerView
     * @throws LineUnavailableException
     */
    public MediaPlayerController(MediaPlayerView mediaPlayerView) throws LineUnavailableException {
        this.mediaPlayerView=mediaPlayerView;
        document = new File("src/qc/ca/bdeb/Music");
        for (File file : document.listFiles()) {
            filepath.add(file.getPath());
        }
        playlooped(i);
        playnextclip();
        playpreviousclip();
        startStop();
        changeVolume();
    }

    /**
     * jouer la musique next
     */

    public void playnextclip() {
       mediaPlayerView.getBtn_next().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               i=i+1;
               if(i>=document.listFiles().length){
                   i=0;
               }
               clip.stop();
               clip.flush();
               clip.close();
               playlooped(i);
           }
       });
    }

    /**
     * play previous audio
     */
    public void playpreviousclip(){
        mediaPlayerView.getBtn_previous().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i=i-1;
                if (i<=0){
                    i=document.listFiles().length-1;
                }
                clip.stop();
                clip.flush();
                clip.close();
                playlooped(i);
            }
        });
    }

    /**
     * Mettre en pause l'audio
     */
    public void startStop(){
        mediaPlayerView.getBtn_StopStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip.isRunning()){
                    clip.stop();
                }
                else {
                    clip.start();
                }
            }
        });
    }

    /**
     * méthode qui set le volume
     * @param volumevalue
     */
    public void setVolume(int volumevalue){
        if(clip!=null&& clip.isControlSupported(FloatControl.Type.MASTER_GAIN)){
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minGain = gainControl.getMinimum();
            float maxGain = gainControl.getMaximum();
            float gainRange = maxGain - minGain;

            float volume = (float) volumevalue / 100;
            float targetGain=(gainRange*volume)+minGain;
            gainControl.setValue(targetGain);
        }
    }

    /**
     * changer le volume
     */
    public void changeVolume(){
        mediaPlayerView.getVolumeslider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setVolume(mediaPlayerView.getVolumeslider().getValue());
            }
        });
    }

    /**
     * méthode qui joue en boucle l'audio
     * @param i
     */
    private void playlooped(int i) {
        try {
            AudioInputStream audioStream=AudioSystem.getAudioInputStream(new File(filepath.get(i)));
            clip=AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode qui close l'audio
     */
    public void close(){
        clip.close();
        clip.flush();
    }

}

