package wooow;

import java.io.Serializable;

public class SomeCustomObject implements Serializable
{
	private String string = "test";
	private boolean bool = true;
	private int anInt = 101;

	public SomeCustomObject()
	{
	}

	public SomeCustomObject(String string, boolean bool, int anInt)
	{
		this.string = string;
		this.bool = bool;
		this.anInt = anInt;
	}

	public String getString()
	{
		return string;
	}

	public void setString(String string)
	{
		this.string = string;
	}

	public boolean isBool()
	{
		return bool;
	}

	public void setBool(boolean bool)
	{
		this.bool = bool;
	}

	public int getAnInt()
	{
		return anInt;
	}

	public void setAnInt(int anInt)
	{
		this.anInt = anInt;
	}

	@Override
	public String toString()
	{
		return "SomeCustomObject{" + "test='" + string + '\'' + ", testt=" + bool + ", testtt=" + anInt + '}';
	}
}
