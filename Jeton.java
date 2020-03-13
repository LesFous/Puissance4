class Jeton {
  private int id;

  public Jeton(int joueur){
    try {
      this.id = joueur;
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Ce n'est pas un nombre valide");
    }
  }

  public int getId(){
    return this.id;
  }

  @Override
  public String toString(){
    return ""+this.id;
  }
}
