package capl_first_design.FirstDesign.main;


public final class KeyAction
{
	enum Action
	{
		Pressed, Released;
	}

	private final int k_iKeyCode;
	private final Action k_eAction;

	public KeyAction(final int iKeyCode, final Action eAction)
	{
		k_iKeyCode = iKeyCode;
		k_eAction = eAction;
	}

	public int keyCode()
	{
		return k_iKeyCode;
	}

	public Action action()
	{
		return k_eAction;
	}

	public boolean pressed()
	{
		return (k_eAction == Action.Pressed);
	}

	public boolean released()
	{
		return (k_eAction == Action.Released);
	}
}
