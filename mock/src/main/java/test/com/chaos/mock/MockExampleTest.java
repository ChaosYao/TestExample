package test.com.chaos.mock; 

import com.chaos.mock.Foot;
import com.chaos.mock.Leg;
import com.chaos.mock.ToolMan;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/** 
* MockExample Tester. 
* 
* @author <Authors name> 
* @since <pre>12月 20, 2020</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class MockExampleTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

@Mock   Leg                    leg;
@Mock   Foot                   foot;
@InjectMocks
private ToolMan                superToolMan;

@Captor
private ArgumentCaptor<Collection> captor;

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

@Test
public void test7() {
    List list = new LinkedList();
    List spy = spy(list);

    //不会生效，真正的方法会被调用，抛出IndexOutOfBoundsException的异常
    //when(spy.get(0)).thenReturn("foo");

    //以下两种可以生效。因为被封装后，除非特殊声明（打桩stub），否则都会真正调用对象里的每个方法
    List list1 = mock(List.class);
    when(list1.get(0)).thenReturn("foo");

    doReturn("foo").when(spy).get(0);

}

@Test
public void test8() {
    superToolMan.getLegLength();

    verify(leg).getLength();

}


@Test
public final void shouldContainCertainListItem() {
    List<String> asList = Arrays.asList("someElement_test", "someElement");
    final List mockList = mock(List.class);
    mockList.add(asList);

    verify(mockList).add(captor.capture());
    final List capturedArgument = (List)captor.getValue();
    assertTrue(capturedArgument.contains("someElement"));
}





}
