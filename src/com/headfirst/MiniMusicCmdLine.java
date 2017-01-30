package com.headfirst;

import javax.sound.midi.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Admin on 13.01.17.
 */
public class MiniMusicCmdLine {
    public static void main(String[] args) throws IOException {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            args = new String[2];
            args[0] = r.readLine();
            args[1] = r.readLine();
            if (args.length < 2) {
            System.out.println("Не забудьте аргументы для инструмента и ноты");
        } else {
            System.out.println(args[0]);
            System.out.println(args[1]);
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument,note);
        }
    }
    public void play(int instrument, int note){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();

        } catch (Exception ex){
            ex.getStackTrace();
        }
    }
}
