package com.agrahari.pos;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RecognizePOS {

    public POSModel loadModel(){
        try {
            InputStream is = new FileInputStream("src\\main\\resources\\models\\en-pos-maxent.bin");
            POSModel model = new POSModel(is);

            return model;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void recognizePOS(String sentence){

        POSModel model = loadModel();
        POSTaggerME posObj = new POSTaggerME(model);
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(sentence);
        String[] tags = posObj.tag(tokens);

        POSSample sample = new POSSample(tokens, tags);
        System.out.println(sample.toString());
    }

    public static void main(String[] args) {
        String input = "welcome to our class of natural language processing";
        RecognizePOS obj = new RecognizePOS();
        obj.recognizePOS(input);
    }
}
