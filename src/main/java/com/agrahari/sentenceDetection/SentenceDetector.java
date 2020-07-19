package com.agrahari.sentenceDetection;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class SentenceDetector {

    public void detectSentence(String rawText){
        try {
            //Load the sentence detection model
            InputStream is = new FileInputStream("src\\main\\resources\\models\\en-sent.bin");
            SentenceModel modelObj = new SentenceModel(is);

            //Break raw text into sentences
            SentenceDetectorME detectorME = new SentenceDetectorME(modelObj);
            String[] detectedSen = detectorME.sentDetect(rawText);
            System.out.println("All sentences detected are:");
            for(String sen: detectedSen){
                System.out.println(sen);
            }
            //Find position of all sentences
            Span[] spans = detectorME.sentPosDetect(rawText);
            System.out.println("Positions of all detected sentences:");
            for(Span span: spans){
                System.out.println(span);
            }

            //Getting the probabilities of the last sendDetect method call for the sentences
            double[] probabilities = detectorME.getSentenceProbabilities();
            for(double v: probabilities){
                System.out.println(v);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SentenceDetector sd = new SentenceDetector();
        String sentence = "Hi. How are you? Hope everything is going well. " +
                "Welcome to ChatBot Lesson1. We will try to understand Open Apache NLP for sentence detection";
        sd.detectSentence(sentence);
    }
}
