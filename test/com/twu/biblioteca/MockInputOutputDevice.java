package com.twu.biblioteca;

import java.io.*;

/**
 * Created by dianadevasia on 26/02/15.
 */
public class MockInputOutputDevice implements InputOutputDevice {


    public String inputmsg;
    private String outputmsg;

    MockInputOutputDevice()
    {
        this.inputmsg="";
    }

    MockInputOutputDevice(String inputmsg){
        this.inputmsg=inputmsg;
    }

    @Override
    public String readInput() throws IOException{

        InputStream inputStream = new ByteArrayInputStream(inputmsg.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.readLine();

    }

    @Override
    public void writeOutput(String msg,Object... args) {
        this.outputmsg = msg;
    }

    public String getActualWrittenOutput(){
        return outputmsg;
    }


}
