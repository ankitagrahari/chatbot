package com.agrahari.tokenizer;

import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Tokenization {

    public void simpleTokenize(String text){

        //Get the Simple Tokenizer Instance
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;

        //Call the tokenize method
        String[] tokens = tokenizer.tokenize(text);

        //Print the tokens
        System.out.println(Arrays.toString(tokens));

        //Get the token positions
        Span[] spans = tokenizer.tokenizePos(text);
        for(Span sp: spans){
            System.out.println(sp);
        }

    }

    public void whiteSpaceTokenize(String text){

        //Get the Simple Tokenizer Instance
        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

        //Call the tokenize method, this will split based on spaces
        String[] tokens = tokenizer.tokenize(text);

        //Print the tokens
        for(String s: tokens){
            System.out.println(s);
        }

        Span[] spans = tokenizer.tokenizePos(text);
        for(Span s: spans){
            System.out.println(s);
        }

    }

    public void tokenizeBasedOnModel(String text){
        try {

            //Load the token models
            InputStream is = new FileInputStream("src\\main\\resources\\models\\en-token.bin");
            TokenizerModel model = new TokenizerModel(is);

            //Create the tokenizer ME model
            TokenizerME tokenizerME = new TokenizerME(model);

            //Print the tokens
            for(String tokens: tokenizerME.tokenize(text)){
                System.out.println(tokens);
            }

            //Get the position of the tokens
            Span[] spans = tokenizerME.tokenizePos(text);
            for(Span span: spans){
                System.out.println(span);
            }

            //Get the token probabilities
            double[] prob = tokenizerME.getTokenProbabilities();
            for(double s: prob){
                System.out.println(s);
            }

            //Consider the Alpha Numeric optimization
            boolean isAlphaNumeric = tokenizerME.useAlphaNumericOptimization();
            System.out.println(isAlphaNumeric);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tokenization obj = new Tokenization();
        String sentence = "Hi. How are you? Hope everything is going well. " +
                "Welcome to ChatBot Lesson1. We will try to understand Open Apache NLP for sentence detection";
//        obj.simpleTokenize(sentence);
//        obj.whiteSpaceTokenize(sentence);
        obj.tokenizeBasedOnModel(sentence);
    }
}
