package com.agrahari.ner;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * The process of finding names, people, places, and other entities,
 * from a given text is known as Named Entity Recognition (NER)
 */
public class NamedEntityRecognition {

    public void nameFinder(String text){
        try {
            InputStream is = new FileInputStream("src\\main\\resources\\models\\en-ner-person.bin");
            TokenNameFinderModel model = new TokenNameFinderModel(is);

            NameFinderME finder = new NameFinderME(model);
            String[] words = text.split(" ");

            Span[] spans = finder.find(words);

            for(Span s: spans){
                System.out.println(s+"---"+ words[s.getStart()]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        NamedEntityRecognition ner = new NamedEntityRecognition();
        String sentence = "Indian Names could either Abhay or Ankit and other options are Charles and Michael";
        ner.nameFinder(sentence);
    }
}
