import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

  private JPanel contentPane;
  private JTextField maxPoints;
  private JTextField minPoints;
  private JTextField maxRebounds;
  private JTextField minRebounds;
  private JTextField maxAssists;
  private JTextField minAssists;
  private JTextField maxPer;
  private JTextField minPer;
  private JTextField maxVorp;
  private JTextField minVorp;
  private JTextField maxTs;
  private JTextField minTs;
  public static ReboundTree rebounds;
  public static AssistTree assists;
  public static PointTree points;
  public static PerTree per;
  public static TsTree ts;
  public static VorpTree vorp;

  /**
   * Launch the application.
   */
  public static void main(String[] args) throws Exception {

    // Player, Team, TRB, AST, PTS, PER, TS%, VORP

    ArrayList<Player> NBA = new ArrayList<Player>();

    Scanner sc = new Scanner(new File("NBA2022.csv"));
    sc.useDelimiter(",");
    while (sc.hasNext()) {

      NBA.add(new Player(sc.next(), sc.next(), Double.valueOf(sc.next()), Double.valueOf(sc.next()),
          Double.valueOf(sc.next()), Double.valueOf(sc.next()), Double.valueOf(sc.next()),
          Double.valueOf(sc.next())));

    }
    sc.close();

    rebounds = new ReboundTree();
    assists = new AssistTree();
    points = new PointTree();
    per = new PerTree();
    ts = new TsTree();
    vorp = new VorpTree();

    for (Player p : NBA) {
      rebounds.addPlayer(p);
      assists.addPlayer(p);
      points.addPlayer(p);
      per.addPlayer(p);
      ts.addPlayer(p);
      vorp.addPlayer(p);
    }



    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame frame = new MainFrame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static ArrayList<Player> searchAllCriteria(double maxP, double minP, double maxR,
      double minR, double maxA, double minA, double maxT, double minT, double maxE, double minE,
      double maxV, double minV) {


    ArrayList<Player> pMatch = points.lookupAll(minP, maxP);
    ArrayList<Player> rMatch = rebounds.lookupAll(minR, maxR);
    ArrayList<Player> aMatch = assists.lookupAll(minA, maxA);
    ArrayList<Player> tMatch = ts.lookupAll(minT, maxT);
    ArrayList<Player> eMatch = per.lookupAll(minE, maxE);
    ArrayList<Player> vMatch = vorp.lookupAll(minV, maxV);

    pMatch.retainAll(rMatch);
    pMatch.retainAll(aMatch);
    pMatch.retainAll(tMatch);
    pMatch.retainAll(eMatch);
    pMatch.retainAll(vMatch);

    return pMatch;
  }


  /**
   * Create the frame.
   */
  public MainFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1350, 550);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblNewLabel = new JLabel("2022 NBA Season Search Engine");
    lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 39));
    lblNewLabel.setBounds(110, 6, 618, 126);
    contentPane.add(lblNewLabel);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(574, 144, 756, 263);
    contentPane.add(scrollPane);
    
    JTextArea res = new JTextArea();
    scrollPane.setViewportView(res);
    res.setEditable(false);
        
    JButton search = new JButton("Search");
    search.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        double maxP;
        double minP;
        double maxR;
        double minR;
        double maxA;
        double minA;
        double maxT;
        double minT;
        double maxE;
        double minE;
        double maxV;
        double minV;

        try {
          maxP = Double.valueOf(maxPoints.getText());
        } catch (Exception no) {
          maxP = Double.MAX_VALUE;
        }

        try {
          minP = Double.valueOf(minPoints.getText());
        } catch (Exception non) {
          minP = Double.MIN_VALUE;
        }

        try {
          maxR = Double.valueOf(maxRebounds.getText());
        } catch (Exception geen) {
          maxR = Double.MAX_VALUE;
        }

        try {
          minR = Double.valueOf(minRebounds.getText());
        } catch (Exception nr) {
          minR = Double.MIN_VALUE;
        }

        try {
          maxA = Double.valueOf(maxAssists.getText());
        } catch (Exception bu) {
          maxA = Double.MAX_VALUE;
        }

        try {
          minA = Double.valueOf(minAssists.getText());
        } catch (Exception ne) {
          minA = Double.MIN_VALUE;
        }

        try {
          maxT = Double.valueOf(maxTs.getText());
        } catch (Exception nee) {
          maxT = Double.MAX_VALUE;
        }

        try {
          minT = Double.valueOf(minTs.getText());
        } catch (Exception ei) {
          minT = Double.MIN_VALUE;
        }

        try {
          maxE = Double.valueOf(maxPer.getText());
        } catch (Exception ochi) {
          maxE = Double.MAX_VALUE;
        }

        try {
          minE = Double.valueOf(minPer.getText());
        } catch (Exception nahin) {
          minE = Double.MIN_VALUE;
        }

        try {
          maxV = Double.valueOf(maxVorp.getText());
        } catch (Exception bango) {
          maxV = Double.MAX_VALUE;
        }

        try {
          minV = Double.valueOf(minVorp.getText());
        } catch (Exception rara) {
          minV = Double.MIN_VALUE;
        }

        ArrayList<Player> finalMatch = searchAllCriteria(maxP, minP, maxR, minR, maxA, minA, maxT,
            minT, maxE, minE, maxV, minV);
        
        String r = "";
        
        for (Player p : finalMatch) {
          r += p.toString() + "\n";
        }
        
        res.setText(r);
        
       
        
      }
    });
    search.setBounds(574, 446, 117, 29);
    contentPane.add(search);

    JLabel points = new JLabel("Max Points");
    points.setBounds(30, 161, 76, 16);
    contentPane.add(points);

    maxPoints = new JTextField();
    maxPoints.setBounds(40, 182, 44, 26);
    contentPane.add(maxPoints);
    maxPoints.setColumns(10);

    JLabel pointsO = new JLabel("Min Points");
    pointsO.setBounds(137, 161, 82, 16);
    contentPane.add(pointsO);

    minPoints = new JTextField();
    minPoints.setColumns(10);
    minPoints.setBounds(147, 182, 44, 26);
    contentPane.add(minPoints);

    JLabel rebounds = new JLabel("Max Rebounds");
    rebounds.setBounds(30, 270, 92, 16);
    contentPane.add(rebounds);

    maxRebounds = new JTextField();
    maxRebounds.setColumns(10);
    maxRebounds.setBounds(50, 298, 44, 26);
    contentPane.add(maxRebounds);

    JLabel reboundsO = new JLabel("Min Rebounds");
    reboundsO.setBounds(150, 270, 92, 16);
    contentPane.add(reboundsO);

    minRebounds = new JTextField();
    minRebounds.setColumns(10);
    minRebounds.setBounds(175, 298, 44, 26);
    contentPane.add(minRebounds);

    JLabel assists = new JLabel("Max Assists");
    assists.setBounds(30, 391, 92, 16);
    contentPane.add(assists);

    maxAssists = new JTextField();
    maxAssists.setColumns(10);
    maxAssists.setBounds(40, 419, 44, 26);
    contentPane.add(maxAssists);

    JLabel assistsO = new JLabel("Min Assists");
    assistsO.setBounds(150, 391, 92, 16);
    contentPane.add(assistsO);

    minAssists = new JTextField();
    minAssists.setColumns(10);
    minAssists.setBounds(160, 419, 44, 26);
    contentPane.add(minAssists);

    JLabel per = new JLabel("Max PER");
    per.setBounds(339, 161, 58, 16);
    contentPane.add(per);

    JLabel perO = new JLabel("Min PER");
    perO.setBounds(436, 161, 58, 16);
    contentPane.add(perO);

    JLabel vorp = new JLabel("Max VORP");
    vorp.setBounds(328, 270, 69, 16);
    contentPane.add(vorp);

    JLabel vorpO = new JLabel("Min VORP");
    vorpO.setBounds(452, 270, 69, 16);
    contentPane.add(vorpO);

    JLabel ts = new JLabel("Max TS%");
    ts.setBounds(328, 391, 69, 16);
    contentPane.add(ts);

    JLabel tsO = new JLabel("Min TS%");
    tsO.setBounds(436, 391, 69, 16);
    contentPane.add(tsO);

    maxPer = new JTextField();
    maxPer.setColumns(10);
    maxPer.setBounds(339, 182, 44, 26);
    contentPane.add(maxPer);

    minPer = new JTextField();
    minPer.setColumns(10);
    minPer.setBounds(436, 182, 44, 26);
    contentPane.add(minPer);

    maxVorp = new JTextField();
    maxVorp.setColumns(10);
    maxVorp.setBounds(338, 298, 44, 26);
    contentPane.add(maxVorp);

    minVorp = new JTextField();
    minVorp.setColumns(10);
    minVorp.setBounds(462, 298, 44, 26);
    contentPane.add(minVorp);

    maxTs = new JTextField();
    maxTs.setColumns(10);
    maxTs.setBounds(328, 419, 44, 26);
    contentPane.add(maxTs);

    minTs = new JTextField();
    minTs.setColumns(10);
    minTs.setBounds(436, 419, 44, 26);
    contentPane.add(minTs);
    
    JLabel attribution = new JLabel("Programmed by Peter Engel; data from basketball-reference.com");
    attribution.setBounds(23, 500, 430, 16);
    contentPane.add(attribution);
    


    

    
 
  }
}
