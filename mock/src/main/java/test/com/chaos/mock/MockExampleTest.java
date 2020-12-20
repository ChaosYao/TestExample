package test.com.chaos.mock; 

import com.chaos.mock.ToolMan;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/** 
* MockExample Tester. 
* 
* @author <Authors name> 
* @since <pre>12月 20, 2020</pre> 
* @version 1.0 
*/ 
public class MockExampleTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

@Test
public void test1() {
    ToolMan toolMan = mock(ToolMan.class);

    when(toolMan.getName()).thenReturn("工具银儿");
    assertEquals(toolMan.getName(), "工具银儿");
}

@Test
public void test2() {
    Comparable c = mock(Comparable.class);
    when(c.compareTo("舔我")).thenReturn(111);
    when(c.compareTo("A上去")).thenReturn(222);

    assertEquals(111, c.compareTo("舔我"));
}

@Test
public void test3() {
    Comparable c = mock(Comparable.class);

    when(c.compareTo(anyInt())).thenReturn(111);
    assertEquals(111, c.compareTo(211212));
}

@Test
public void test4() {
    Comparable c = mock(Comparable.class);

    when(c.compareTo(isA(Object.class))).thenReturn(111);
    Object tool = new Object();
    assertEquals(111, c.compareTo(tool));
}

@Test(expected = IOException.class)
public void test5() throws IOException {
    OutputStream mockStream = mock(OutputStream.class);
    doThrow(new IOException()).when(mockStream).close();

    OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);
    streamWriter.close();
}

@Test
public void test6() {
    ToolMan toolMan = mock(ToolMan.class);
    when(toolMan.getName()).thenReturn("哼");

    toolMan.setName("哈");
    toolMan.getName();
    toolMan.getName();

    verify(toolMan, times(2)).getName();
    verify(toolMan, atLeast(1)).getName();
    verify(toolMan, atMost(3)).getName();
    verify(toolMan).setName(eq("哈"));
}

} 
