import logic.Test;

/**
 * This class is used as proxy-starter, to allow maven-plugin <code>shade</code> to build fat/uber-jar of project, containing all classes as well as
 * dependencies in one single executable jar.
 *
 * @see Test
 */
public class TestStarter
{
	public static void main(String[] args)
	{
		Test.mainEmf(args);
	}
}

