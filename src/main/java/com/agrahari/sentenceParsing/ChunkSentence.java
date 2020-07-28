package com.agrahari.sentenceParsing;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;

import java.io.*;
import java.util.Arrays;

public class ChunkSentence {

    public ChunkerModel loadModels(){
        try {
            InputStream is = new FileInputStream("src\\main\\resources\\models\\en-chunker.bin");
            ChunkerModel model = new ChunkerModel(is);

            return model;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void chunkSentence(String input){
        //Tokenizing Sentence
        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(input);

        //Create Part of Speech Tags
        File posModelFile = new File("src\\main\\resources\\models\\en-pos-maxent.bin");
        POSModelLoader posModelLoader = new POSModelLoader();
        POSModel posModel = posModelLoader.load(posModelFile);
        POSTaggerME posTaggerME = new POSTaggerME(posModel);
        String[] tags = posTaggerME.tag(tokens);

        ChunkerModel chunkerModel = this.loadModels();
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        String chunks[] = chunkerME.chunk(tokens, tags);

        System.out.println(Arrays.toString(chunks));
    }

    public static void main(String[] args) {
        ChunkSentence cs = new ChunkSentence();
        String input = "Lets learn chunking sentence using Apache OpenNLP";
        cs.chunkSentence(input);
    }
}
