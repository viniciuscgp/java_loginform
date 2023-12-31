package loginformi.main;

import java.util.List;

import loginform.interfaces.IEvent;
import loginform.views.*;

public class Main implements IEvent {
  public static final String NAME = "VINY";
  
	public static void main(String[] args) {
		LoginView login = new LoginView();
		login.subscribe(new Main());
		login.showMe();
	}

	@Override
	public void Click(String tag, List<String> data) {
		System.out.println(tag + "->" + data.toString());
	}
}

