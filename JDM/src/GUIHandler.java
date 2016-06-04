import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

class GUIHandler extends JFrame
{
    private JMenuBar jMenuBar;
    private JPanel jPanel;
    private JMenu fileMenu;
    private JMenu aboutMenu;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;
    private JFileChooser jFileChooser;

    public GUIHandler()
    {
        initUI();
    }

    public final void initUI()
    {
        jMenuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");
        exitMenuItem = new JMenuItem("Exit");
        openMenuItem = new JMenuItem("Open");
        jFileChooser = new JFileChooser();
        jPanel = new JPanel(new SpringLayout());

        SpringLayout.Constraints spConstraints = new SpringLayout.Constraints (Spring.constant (10), Spring.constant (10), Spring.constant (100), Spring.constant (35));

        fileMenu.setMnemonic(KeyEvent.VK_F);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        openMenuItem.setToolTipText("Open Dataset");
        exitMenuItem.setToolTipText("Exit Application");

        exitMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        openMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == openMenuItem)
                {
                    int returnVal = jFileChooser.showOpenDialog(GUIHandler.this);
                    if(returnVal == jFileChooser.APPROVE_OPTION)
                    {
                        File file = jFileChooser.getSelectedFile ();
                        System.out.println("Opening: " + file.getName() + ".");
                    }
                }
            }
        });

        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(aboutMenu);
        setJMenuBar(jMenuBar);

        setTitle("JDM");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
