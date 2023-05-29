package club.minimunch57.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import club.minimunch57.enums.ControllerCommand;
import club.minimunch57.enums.ONECommand;
import club.minimunch57.listeners.RequestListener;

/**
 * 
 * The compact, beautiful pop-up GUI interface for the controller.
 * 
 * @author Matthew Whitney
 *
 */
public class ControllerInterface extends JFrame {

	/** The <tt>ControllerInterface</tt>'s unique serial. */
	private static final long serialVersionUID = -7985308472410298265L;
	
	//	Static Return Values
	/** A <tt>String</tt> return value for printing text to the console in the error format. */
	final public static String CONSOLE_ERROR = "!ERR:";
	
	//	Swing Components
	/** A <tt>JPanel</tt> responsible for holding the content of the interface. */
	private JPanel contentPane;
	/** A <tt>JTextPane</tt> responsible for holding the styled console/output text. */
	private JTextPane textPane;
	/** A <tt>JScrollPane</tt> responsible for holding the text pane in a scrollable pane. */
	private JScrollPane scrollPane;
	/** A <tt>JTextField</tt> responsible for holding the input text. */
	private JTextField textField;
	/** A <tt>BeautifulButton</tt> used to send any input to be parsed. */
	private BeautifulButton sendButton;
	
	//	Various Objects
	/** The <tt>RequestListener</tt> used to send requests. */
	private RequestListener requestListener = null;
	
	//	Variables
	/** The <tt>Font</tt> for almost all text in the interface. */
	private Font textFont = null;
	/** An <code>int</code> return value for the interface's width in pixels. */
	final private int INTERFACE_WIDTH = 400;
	/** An <code>int</code> return value for the interface's height in pixels. */
	final private int INTERFACE_HEIGHT = 450;


	/**
	 * <ul>
	 * <p>	<b><i>ControllerInterface</i></b>
	 * <p>	<code>public ControllerInterface()</code>
	 * <p>	Creates a new <tt>ControllerInterface</tt>.
	 * </ul>
	 */
	public ControllerInterface() {
		//	Register Font
		try {
			final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, ControllerInterface.class.getResourceAsStream("/club/minimunch57/fonts/Montserrat-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,	ControllerInterface.class.getResourceAsStream("/club/minimunch57/fonts/Montserrat-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,	ControllerInterface.class.getResourceAsStream("/club/minimunch57/fonts/Montserrat-Italic.ttf")));
		} catch (FontFormatException | IOException e) {
			System.out.println(ControllerInterface.CONSOLE_ERROR + "<!> Error Setting Fonts");
			e.printStackTrace();
		}
		textFont = new Font("Montserrat", Font.BOLD, 30);
		
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				SwingUtilities.invokeLater(() -> {
					setVisible(false);
				});
			}
		});
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setBackground(new Color(0, 0, 0, 0));
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - INTERFACE_WIDTH - 10), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - INTERFACE_HEIGHT - 50), INTERFACE_WIDTH, INTERFACE_HEIGHT);
		
		contentPane = new BeautifulPanel(20);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(30, 30, 30));
		setContentPane(contentPane);
		
		BeautifulButton openButton = new BeautifulButton("Open", new Color(150, 255, 180), new Color(0, 216, 0));
		openButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		openButton.setFocusPainted(false);
		openButton.setForeground(Color.WHITE);
		openButton.setFont(textFont);
		openButton.setBounds(43, 41, 150, 100);
		openButton.addActionListener((actionEvent) -> {
			requestListener.commandRequested(ONECommand.OPEN, null);
		});
		contentPane.add(openButton);
		
		BeautifulButton closeButton = new BeautifulButton("Close", new Color(244, 171, 171), new Color(224, 28, 28));
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setFocusPainted(false);
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(textFont);
		closeButton.setBounds(203, 41, 150, 100);
		closeButton.addActionListener((actionEvent) -> {
			requestListener.commandRequested(ONECommand.CLOSE, null);
		});
		contentPane.add(closeButton);
		
		BeautifulButton unlockButton = new BeautifulButton("Unlock", new Color(87, 255, 28), new Color(38, 162, 0));
		unlockButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		unlockButton.setFocusPainted(false);
		unlockButton.setForeground(Color.WHITE);
		unlockButton.setFont(textFont);
		unlockButton.setBounds(43, 152, 150, 100);
		unlockButton.addActionListener((actionEvent) -> {
			requestListener.commandRequested(ONECommand.SYSTEM_UNLOCK, null);
		});
		contentPane.add(unlockButton);
		
		BeautifulButton lockButton = new BeautifulButton("Lock", new Color(254, 32, 42), new Color(161, 0, 4));
		lockButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lockButton.setFocusPainted(false);
		lockButton.setForeground(Color.WHITE);
		lockButton.setFont(textFont);
		lockButton.setBounds(203, 152, 150, 100);
		lockButton.addActionListener((actionEvent) -> {
			requestListener.commandRequested(ONECommand.SYSTEM_LOCK, null);
		});
		contentPane.add(lockButton);
		
		textPane = new JTextPane();
		textPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textPane.setEditable(false);
		textPane.setFont(textFont.deriveFont(Font.PLAIN, 16));
		textPane.setOpaque(false);
		
		scrollPane = new JScrollPane(textPane) {
			/** This <tt>JScrollPane</tt>'s unique serial. */
			private static final long serialVersionUID = 8587192831246156905L;

			@Override
			public void paintComponent(Graphics g) {
				//	Create Graphics and Rendering Hints
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				//	Draw the Rounded Panel
				g2d.setColor(getBackground());
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
				
				//	Dispose of the Graphics2D Object
				g2d.dispose();
				
				super.paintComponent(g);
			}
		};
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(2, 8, 5, 8));
		scrollPane.setBackground(textPane.getBackground());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.setFocusable(false);
		scrollPane.setBounds(43, 263, 310, 92);
		contentPane.add(scrollPane);
		
		textField = new JTextField() {
			/** This <tt>JTextField</tt>'s unique serial. */
			private static final long serialVersionUID = 1135008462145013609L;

			@Override
			public void paintComponent(Graphics g) {
				//	Create Graphics and Rendering Hints
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				//	Draw the Rounded Panel
				g2d.setColor(getBackground());
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
				
				//	Dispose of the Graphics2D Object
				g2d.dispose();
				
				super.paintComponent(g);
			}
		};
		textField.setOpaque(false);
		textField.setFont(textFont.deriveFont(Font.PLAIN, 16));
		textField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		textField.setHorizontalAlignment(JTextField.LEADING);
		textField.setBounds(43, 366, 255, 40);
		textField.addActionListener((actionEvent) -> {
			sendButton.getActionListeners()[0].actionPerformed(actionEvent);
		});
		contentPane.add(textField);
		
		sendButton = new BeautifulButton(">", new Color(0, 237, 210), new Color(0, 174, 233));
		sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendButton.setFocusPainted(false);
		sendButton.setForeground(Color.WHITE);
		sendButton.setFont(textFont.deriveFont(Font.BOLD, 18));
		sendButton.setBounds(308, 366, 45, 40);
		sendButton.addActionListener((actionEvent) -> {
			if(textField.getText().trim().length()>0) {
				parseTextInput(textField.getText());
				SwingUtilities.invokeLater(() -> {
					textField.setText("");
				});
			}
		});
		contentPane.add(sendButton);
		
		//	Register Text Styles for the Text Pane
		registerTextStyles();
		rerouteConsolePrints();
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>registerTextStyles</i></b>
	 * <p>	<code>private void registerTextStyles()</code>
	 * <p>	Registers the text styles used by the <tt>JTextPane</tt>.
	 * <p>	<b>Note:</b> This method is only meant to be called once by the constructor.
	 * </ul>
	 */
	private void registerTextStyles() {
		final Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		final StyledDocument styledDocument = textPane.getStyledDocument();
		
		//	Console Error Text Style
		Style adjustedStyle = styledDocument.addStyle("console-error", defaultStyle);
		adjustedStyle.addAttribute(StyleConstants.Foreground, Color.RED);
		
		//	Standard Text Style
		adjustedStyle = styledDocument.addStyle("text", defaultStyle);
		adjustedStyle.addAttribute(StyleConstants.Foreground, Color.DARK_GRAY);
		
		//	Server Text Style
		adjustedStyle = styledDocument.addStyle("server", defaultStyle);
		adjustedStyle.addAttribute(StyleConstants.Foreground, Color.GRAY);
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>rerouteConsolePrints</i></b>
	 * <p>	<code>private void rerouteConsolePrints()</code>
	 * <p>	Reroutes any prints to the console to the <tt>JTextPane</tt>.
	 * <p>	<b>Note:</b> This method is only meant to be called once by the constructor.
	 * </ul>
	 */
	private void rerouteConsolePrints() {
		//	Reroute System Prints to the Text Pane
		final OutputStream outputStream = new OutputStream() {
			@Override
			public void write(byte[] buffer, int offset, int length) throws IOException {
				final String text = new String(buffer, offset, length);
				if (text.trim().length() > 0) {
					if(text.trim().startsWith("!M:")) {
						addTextToPane("Message from Server: " + text.replaceFirst("!M:", ""), "server");
					}
					else if(text.trim().startsWith("!B:")) {
						addTextToPane("Broadcast from Server: " + text.replaceFirst("!B:", ""), "server");
					}
					else if(text.trim().startsWith("!C:")) {
						addTextToPane("Connection Message from Server: " + text.replaceFirst("!C:", ""), "server");
					}
					else if(text.trim().startsWith("!N:")) {
						addTextToPane("Notification from Server: " + text.replaceFirst("!N:", ""), "server");
					}
					else if(text.trim().startsWith("!R:")) {
						addTextToPane("Response from Server: " + text.replaceFirst("!R:", ""), "server");
					}
					else if(text.trim().startsWith("!ERR:")) {
						addTextToPane(text.replaceFirst("!ERR:", ""), "console-error");
					}
					else {
						addTextToPane(text, "text");
					}
				}
			}

			@Override
			public void write(int b) throws IOException {
				write(new byte[] { (byte) b }, 0, 1);
			}
		};
		final OutputStream outputStreamErr = new OutputStream() {
			@Override
			public void write(byte[] buffer, int offset, int length) throws IOException {
				final String text = new String(buffer, offset, length);
				if (text.startsWith("Exception")) {
					addTextToPane(text, "console-error");
				} else {
					appendTextToPane(text, "console-error");
				}
			}

			@Override
			public void write(int b) throws IOException {
				write(new byte[] { (byte) b }, 0, 1);
			}
		};
		System.setOut(new PrintStream(outputStream));
		System.setErr(new PrintStream(outputStreamErr));
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>appendTextToPane</i></b>
	 * <p>	<code>private void appendTextToPane(String text, String styleName)</code>
	 * <p>	Appends the passed <code>text</code> to the <tt>JTextPane</tt> under the specified style.
	 * @param text - a <tt>String</tt> with the text to append.
	 * @param styleName - a <tt>String</tt> for the name of the style to use when appending the text.
	 * </ul>
	 */
	private void appendTextToPane(String text, String styleName) {
		SwingUtilities.invokeLater(() -> {
			try {
				textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), text, textPane.getStyledDocument().getStyle(styleName));
			} catch (BadLocationException ble) {
				ble.printStackTrace();
			}
		});
		scrollToBottom();
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>addTextToPane</i></b>
	 * <p>	<code>private void addTextToPane(String text, String styleName)</code>
	 * <p>	Adds the passed <code>text</code> to the <tt>JTextPane</tt> under the specified style in a new line.
	 * @param text - the text to add.
	 * @param styleName - a <tt>String</tt> for the name of the style to use when appending the text.
	 * </ul>
	 */
	private void addTextToPane(String text, String styleName) {
		if(textPane.getText().length()<1) {
			appendTextToPane(text, styleName);
		}
		else {
			appendTextToPane("\n" + text, styleName);
		}
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>scrollToBottom</i></b>
	 * <p>	<code>private void scrollToBottom()</code>
	 * <p>	Adjusts the viewport (scrolls) so that the last line of the text pane is within view on the left side.
	 * </ul>
	 */
	private void scrollToBottom() {
		SwingUtilities.invokeLater(() -> {
			textPane.scrollRectToVisible(new Rectangle(0, Integer.MAX_VALUE-1, (int) scrollPane.getViewportBorderBounds().getWidth(), (int) scrollPane.getViewportBorderBounds().getHeight()));
		});
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>parseTextInput</i></b>
	 * <p>	<code>private void parseTextInput(String text)</code>
	 * <p>	Parses the passed input text and acts based upon it.
	 * <p>	This method is used to determine what action(s) should be taken after text is entered into the interface's command line.
	 * 		If the input does not match any local commands, then this method attempts to send it to the server.
	 * @param text - a <tt>String</tt> with the input text to parse.
	 * </ul>
	 */
	private void parseTextInput(String text) {
		//	Trim spaces off of the ends then add text to pane.
		text = text.trim();
		addTextToPane("> " + text, "text");

		//	Determine command arguments (if any).
		String command = text.toUpperCase();
		String[] commandArgs = null;
		final int spaceIndex = text.indexOf(' ');
		if(spaceIndex != -1) {
			command = text.substring(0, spaceIndex).toUpperCase();
			commandArgs = text.substring(spaceIndex + 1).split(" ");
		}
		
		//	Create unset enums then match a command if available.
		ONECommand sysCommand = null;
		ControllerCommand conCommand = null;
		
		//	ONECommand Matching
		if(command.equals("OPEN") || command.equals("UNLOCK")) {
			sysCommand = ONECommand.OPEN;
		}
		else if(command.equals("CLOSE") || command.equals("LOCK")) {
			sysCommand = ONECommand.CLOSE;
		}
		else if(command.equals("SYSTEM")) {
			if(commandArgs != null && commandArgs[0].equalsIgnoreCase("UNLOCK")) {
				sysCommand = ONECommand.SYSTEM_UNLOCK;
			}
			else if(commandArgs != null && commandArgs[0].equalsIgnoreCase("LOCK")) {
				sysCommand = ONECommand.SYSTEM_LOCK;
			}
		}
		else if(command.equals("POKE")) {
			sysCommand = ONECommand.POKE;
		}
		else if(command.equals("CONNECT")) {
			sysCommand = ONECommand.CONNECT;
		}
		else if(command.equals("DISCONNECT")) {
			sysCommand = ONECommand.DISCONNECT;
		}
		else if(command.equals("RECONNECT")) {
			sysCommand = ONECommand.RECONNECT;
		}
		else if(command.equals("PING")) {
			sysCommand = ONECommand.PING;
		}
		
		//	ControllerCommand Matching
		if(command.equals("EXIT")) {
			conCommand = ControllerCommand.EXIT;
		}
		else if(command.equals("GC")) {
			conCommand = ControllerCommand.GC;
		}
		
		//	Make request based on results.
		if(sysCommand != null) {
			requestListener.commandRequested(sysCommand, commandArgs);
		}
		else if(conCommand != null) {
			requestListener.controllerChangeRequested(conCommand);
		}
		else {
			requestListener.messageRequested(text);
		}
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>display</i></b>
	 * <p>	<code>public void display()</code>
	 * <p>	Displays this <tt>ControllerInterface</tt>.
	 * <p>	This method is custom and should not be confused with <code>show()</code> or <code>setVisible()</code>.
	 * 		This is simply a helper method for ensuring that this GUI is displayed.
	 * 		If the GUI is already visible, this method will send it to the front. If it was not already visible, this method will set it to be visible.
	 * 		Either way, this method will also request that the text field gets focus afterwards.
	 * 		Another benefit of using this method is that this is all done on the EDT using <code>SwingUtilities.invokeLater()</code>.
	 * </ul>
	 */
	public void display() {
		SwingUtilities.invokeLater(() -> {
			if (this.isVisible()) {
				this.toFront();
			}
			else {
				this.setVisible(true);
			}
			textField.requestFocusInWindow();
		});
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>setRequestListener</i></b>
	 * <p>	<code>public void setRequestListener(RequestListener rl)</code>
	 * <p>	Sets the one and only <tt>RequestListener</tt> to be used by this <tt>ControllerInterface</tt>.
	 * @param rl - the <tt>RequestListener</tt> used for sending requests.
	 * </ul>
	 */
	public void setRequestListener(RequestListener rl) {
		requestListener = rl;
	}
}
