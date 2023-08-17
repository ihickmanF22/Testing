package com.java.pitfall.environment.utils;

import java.io.FileInputStream;
import java.io.InputStream;

public class AudioInputStream implements AudioStream {

    public AudioInputStream(AudioStream bGM) {
    }

    public AudioInputStream() {
    }

    public AudioInputStream(FileInputStream test) {
    }

    public AudioInputStream(InputStream test) {
    }

    @Override
    public AudioData getData() {
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

}
