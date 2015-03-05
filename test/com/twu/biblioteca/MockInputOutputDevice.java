package com.twu.biblioteca;

import com.twu.biblioteca.view.InputOutputDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 26/02/15.
 */
public class MockInputOutputDevice implements InputOutputDevice {

    List<String> inputmsg = new ArrayList<String>();

    private String outputmsg;

    public MockInputOutputDevice(){}

    public MockInputOutputDevice(List<String> inputmsg){
        this.inputmsg=inputmsg;
    }


    @Override
    public String readInput() throws IOException
    {
        String inputToBeRead = inputmsg.get(0);
        inputmsg.remove(0);
        return inputToBeRead;
    }


    @Override
    public void writeOutput(String msg,Object... args) {
        this.outputmsg = msg;
    }

    @Override
    public int readInt(String message) throws IOException {
            String selectedId;
            boolean isNotValidMovieId;
            do
            {
                selectedId = readInput();
                isNotValidMovieId = !selectedId.matches("[0-9]+");
                if(isNotValidMovieId)
                    writeOutput(message);
            }while(isNotValidMovieId);
            return Integer.parseInt(selectedId);
        }

    public String getActualWrittenOutput(){
        return outputmsg;
    }



}
