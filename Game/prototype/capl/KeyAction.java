package capl;


import java.util.Objects;


public final class KeyAction
{
	enum Action
	{
		Pressed,
		Released;
	}


	private final int k_iKeyCode;
	private final Action k_xAction;


	public KeyAction(final int iKeyCode, final Action xAction)
	{
		k_iKeyCode = ArgumentChecker.require(iKeyCode, i -> i >= 0, "KeyCode must be positive or equal zero");
		k_xAction = Objects.requireNonNull(xAction);
	}

	public Action action()
	{
		return k_xAction;
	}

	public int keyCode()
	{
		return k_iKeyCode;
	}
}
