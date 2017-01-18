package hooke_sample;


public final class Main
{
	public static void main(final String[] rgArgs)
	{
		final int iWidth;
		final int iHeight;

		if(rgArgs.length > 0)
		{
			iWidth = Integer.parseInt(Utils.getWrapped(rgArgs, 0));
			iHeight = (rgArgs.length > 1 ? Integer.parseInt(Utils.getWrapped(rgArgs, 1)) : iWidth);
		}
		else
		{
			iWidth = Settings.DEFAULT_WIDTH;
			iHeight = Settings.DEFAULT_HEIGHT;
		}

		new MainWindow(iWidth, iHeight);
	}
}
