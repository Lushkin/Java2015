package Utils;

public enum RoleEnum
{
	Administrator(1),
	Teacher(2),
	Student(3);
	
	private int value;
	
	RoleEnum(int value)
	{
		this.value = value;
	}
	
	public int GetValue()
	{
		return this.value;
	}
}
