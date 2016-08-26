package reflection;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author naveenede
 */
public class ReflectionTests
{
    enum XENTYPE {X, Y};
    byte[] bs = new byte[1024];
    Set<String> stringSet = new HashSet<>();

    @Test
    public void objectDotGetClass_whenAnObjectIsAvailable()
    {
        assertThat("String object returns String", "abc".getClass(), is(equalTo(String.class)));
        assertThat("Properties object type is returned", System.getProperties().getClass(), is(equalTo(Properties.class)));
        assertThat("Enum type is returned", XENTYPE.X.getClass(), is(equalTo(XENTYPE.class)));
        assertThat("Arrays type is returned", bs.getClass(), is(equalTo(byte[].class)));
        assertThat("Polymorphic referencing returns the runtime type", stringSet.getClass(), is(equalTo(HashSet.class)));
    }

    @Test
    public void dotClass_whenTheTypeIsKnown()
    {
        assertThat("Correct object type is dereferenced", PrintWriter.class, is(equalTo(PrintWriter.class)));
    }

    @Test
    public void classDotForName_whenFQNIsKnown() throws ClassNotFoundException
    {
        assertThat(Class.forName("java.util.HashMap"), is(equalTo(HashMap.class)));
        assertThat(Class.forName("[D"), is(equalTo(double[].class)));
        assertThat(Class.forName("Ljava.lang.String"), is(equalTo(String.class)));
    }

    @Test
    public void typeFieldForPrimitiveTypeWrapper()
    {
        assertThat(Double.TYPE, is(equalTo(double.class)));
        assertThat(Integer.TYPE, is(equalTo(int.class)));
        assertThat(Character.TYPE, is(equalTo(char.class)));
        assertThat(Float.TYPE, is(equalTo(float.class)));
        assertThat(Void.TYPE, is(equalTo(void.class)));
    }

    @Test
    public void superClassForTheGivenClass()
    {
        assertThat("super class for the given class", B.class.getSuperclass(), is(equalTo(A.class)));
        assertThat("super class for the given class", C.class.getSuperclass(), is(equalTo(A.class)));
    }

    @Test
    public void memberClassesInterfacesAndEnums()
    {
        Class<?>[] charClasses = Character.class.getClasses();
        //Character contains two member classes Character.Subset and Character.UnicodeBlock.
        Class<?>[] expected = new Class[] {Character.Subset.class, Character.UnicodeBlock.class};

        assertThat("public classes, interfaces, and enums that are members of the class including inherited members",
                   charClasses, is(equalTo(expected)));
    }

    @Test
    public void memberClassesInterfacesAndEnumsExplicitlyDeclared()
    {
        Class<?>[] charDeclaredClasses = Character.class.getDeclaredClasses();

        // Character contains two public member classes Character.Subset and Character.UnicodeBlock and
        // two private class Character.CharacterCache, Character.UnicodeScript.

        assertThat(charDeclaredClasses.length, is(equalTo(4)));
    }

    @Test
    public void getDeclaringClass() throws NoSuchFieldException, NoSuchMethodException
    {
        Field f = System.class.getField("out");
        Class fieldDeclaringClass = f.getDeclaringClass();

        Method getName = System.class.getMethod("currentTimeMillis");
        Class<?> methodDeclaringClass = getName.getDeclaringClass();

        Constructor<?> constructor = LinkedList.class.getConstructors()[0];
        Class<?> constructorDeclaringClass = constructor.getDeclaringClass();

        assertThat(fieldDeclaringClass, is(equalTo(System.class)));
        assertThat(methodDeclaringClass, is(equalTo(System.class)));
        assertThat(constructorDeclaringClass, is(equalTo(LinkedList.class)));
    }


    @Test
    public void booleanCannotBeDeferenced_itIsAPrimitiveType()
    {
        boolean x = false;
        // x.getClass();
        assertThat("boolean cannot be de-referenced", boolean.class, is(equalTo(boolean.class)));
    }
}

class A {
}

class B extends A {
}

interface I {
}

class C extends A implements I
{
}
