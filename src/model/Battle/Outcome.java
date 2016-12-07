package model.Battle;

public enum Outcome {
	//Inventory errors
	None, 
	//Throw Ball Outcomes
	Caught, EscapedAndStayed, EscapedAndRan,
	//Throw Rock Outcomes
	Stayed, Ran;
	
	@Override
	public String toString(){
		return name();
	}
}
