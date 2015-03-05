package com.twu.biblioteca;

import com.twu.biblioteca.view.ConsoleInputOutputDevice;
import com.twu.biblioteca.view.InputOutputDevice;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConsoleInputOutputDeviceTest {

    @Test
    public void testReadInt() throws Exception {
        final LinkedList<String> input = new LinkedList<String>();
        input.add("qwe");
        input.add("1");
        InputOutputDevice consoleDeviceSpy = spy(new ConsoleInputOutputDevice());
        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return input.removeFirst();
            }
        }).when(consoleDeviceSpy).readInput();

        int expected=1;
        int actual= consoleDeviceSpy.readInt("Invalid input entered");

        assertEquals(actual,expected);

    }
}