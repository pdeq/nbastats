
public class Player implements Comparable<Player> {

  private String name; // Name
  private String team; // Team
  private double points; // Points per game
  private double rebounds; // Rebounds per game
  private double assists; // Assists per game
  private double ts; // True shooting percentage
  private double per; // Player efficiency rating
  private double vorp; // Value over replacement

  public Player(String name, String team, double rebounds, double assists, double points, double per,
      double ts, double vorp) {
    this.name = name;
    this.team = team;
    this.points = points;
    this.rebounds = rebounds;
    this.assists = assists;
    this.ts = ts;
    this.per = per;
    this.vorp = vorp;
  }

  public String getName() {
    return name;
  }

  public String getTeam() {
    return team;
  }

  public double getPoints() {
    return points;
  }

  public double getRebounds() {
    return rebounds;
  }

  public double getAssists() {
    return assists;
  }

  public double getTs() {
    return ts;
  }

  public double getPer() {
    return per;
  }

  public double getVorp() {
    return vorp;
  }

  public int comparePoints(Player p) {
    if (this.getPoints() > p.getPoints()) {
      return 1;
    }
    if (p.getPoints() > this.getPoints()) {
      return -1;
    }
    return 0;
  }

  public int compareRebounds(Player p) {
    if (this.getRebounds() > p.getRebounds()) {
      return 1;
    }
    if (p.getRebounds() > this.getRebounds()) {
      return -1;
    }
    return 0;
  }

  public int compareAssists(Player p) {
    if (this.getAssists() > p.getAssists()) {
      return 1;
    }
    if (p.getAssists() > this.getAssists()) {
      return -1;
    }
    return 0;
  }

  public int compareTs(Player p) {
    if (this.getTs() > p.getTs()) {
      return 1;
    }
    if (p.getTs() > this.getTs()) {
      return -1;
    }
    return 0;
  }

  public int comparePer(Player p) {
    if (this.getPer() > p.getPer()) {
      return 1;
    }
    if (p.getPer() > this.getPer()) {
      return -1;
    }
    return 0;
  }

  public int compareVorp(Player p) {
    if (this.getVorp() > p.getVorp()) {
      return 1;
    }
    if (p.getVorp() > this.getVorp()) {
      return -1;
    }
    return 0;
  }

  @Override
  public int compareTo(Player p) {
    if (this.comparePoints(p) + this.compareRebounds(p) + this.compareAssists(p) + this.compareTs(p)
        + this.comparePer(p) + this.compareVorp(p) == 6) {
      return 1;
    }
    if (this.comparePoints(p) + this.compareRebounds(p) + this.compareAssists(p) + this.compareTs(p)
        + this.comparePer(p) + this.compareVorp(p) == -6) {
      return -1;
    }
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Player)) {
      return false;
    }
    Player p = (Player) o;
    return (p.getName().equals(this.name) && p.getTeam().equals(this.team));
  }

  @Override
  public String toString() {
    return this.name + " (" + this.team + ") averaged " + this.points + ", " + this.rebounds + ", "
        + this.assists + " on " + this.ts + " true shooting, with PER of " + this.per
        + ", and VORP of " + this.vorp;
  }

}
